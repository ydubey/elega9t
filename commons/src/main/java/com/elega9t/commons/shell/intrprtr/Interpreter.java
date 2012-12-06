package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.args.Argument;
import com.elega9t.commons.args.ArgumentParser;
import com.elega9t.commons.cp.ClassFilter;
import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.EnvironmentProperty;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.transform.TransformFactory;
import com.elega9t.commons.util.ReflectionUtilities;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;

import static com.elega9t.commons.util.StringUtilities.split;
import static java.util.Arrays.asList;

public class Interpreter extends DefaultEntity {

    private final Map<String, Class<? extends Command>> commands;

    private final Context<Object> context = new Context<Object>();

    public Interpreter(String name) {
        super(name);
        this.commands = new HashMap<String, Class<? extends Command>>();
    }

    public Interpreter(String name, Class<? extends Command>... commands) throws InstantiationException, IllegalAccessException {
        this(name);
        addCommand(commands);
    }

    public Interpreter(String name, Package... commandsPackages) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        this(name);
        String packageNames[] = new String[commandsPackages.length];
        for (int i = 0, commandsPackagesLength = commandsPackages.length; i < commandsPackagesLength; i++) {
            Package commandsPackage = commandsPackages[i];
            packageNames[i] = commandsPackage.getName();
        }
        addCommands(packageNames);
    }

    public Interpreter(String name, String... commandsPackages) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        this(name);
        addCommands(commandsPackages);
    }

    public void addCommand(Class<? extends Command>... commands) throws IllegalAccessException, InstantiationException {
        for (Class<? extends Command> command : commands) {
            Command cmd = command.newInstance();
            this.commands.put(cmd.getName(), command);
        }
    }

    private void addCommands(String... commandsPackages) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        final List<Class> commandClasses = ReflectionUtilities.getClasses(new ClassFilter() {
            @Override
            public boolean accept(Class aClass) {
                return (asList(aClass.getInterfaces()).contains(Command.class));
            }
        }, commandsPackages);
        for (Class commandClass : commandClasses) {
            addCommand(commandClass);
        }
    }

    public void execute(final Shell shell, BufferedReader in, PrintStream out, String line) throws Exception {
        String[] split = split(line, ';');
        for (int i = 0, splitLength = split.length; i < splitLength; i++) {
            String cmd = split[i].trim();
            if(i > 0) {
                out.print(shell.getEnvironmentProperty(EnvironmentProperty.PROMPT) + " ");
                out.println(cmd);
            }
            String[] piped = cmd.split("\\|");
            int pipedLength = piped.length;
            ExecutorService executor = Executors.newFixedThreadPool(splitLength);
            CompletionService completion = new ExecutorCompletionService(executor);

            PipedOutputStream pipedOut =  new PipedOutputStream();
            PipedInputStream pipedIn = new PipedInputStream();

            for (int pipedElementIndex = 0; pipedElementIndex < pipedLength; pipedElementIndex++) {
                final String pipedElement = piped[pipedElementIndex].trim();

                BufferedReader callableIn;
                PrintStream callableOut;

                if(pipedElementIndex == 0) {
                    callableIn = in;
                } else {
                    pipedIn.connect(pipedOut);
                    pipedOut = new PipedOutputStream();
                    callableIn = new BufferedReader(new InputStreamReader(pipedIn));
                }
                if(pipedElementIndex == pipedLength - 1) {
                    callableOut = out;
                } else {
                    callableOut = new PrintStream(pipedOut);
                    pipedIn = new PipedInputStream();
                }

                sumbit(shell, completion, pipedElement, callableIn, callableOut);
            }
            for (int count = 0; count < pipedLength; count++) {
                Future future = completion.take();
                Throwable thrown = (Throwable) future.get();
                if(thrown != null) {
                    out.println("Error: " + thrown.getMessage());
                }
            }
            executor.shutdown();
        }
    }

    private void sumbit(final Shell shell, CompletionService completion, final String pipedElement, final BufferedReader callableIn, final PrintStream callableOut) {
        completion.submit(
                new Callable() {
                    @Override
                    public Throwable call() {
                        try {
                            executeCommand(shell, callableIn, callableOut, pipedElement);
                            return null;
                        } catch (Exception e) {
                            return e;
                        }
                    }
                });
    }

    protected void executeCommand(Shell shell, BufferedReader in, PrintStream out, String cmd) throws Exception {
        if(cmd.trim().length() > 0) {
            String commandName;
            if (cmd != null && cmd.contains(" ")) {
                commandName = cmd.substring(0, cmd.indexOf(" "));
                cmd = cmd.substring(cmd.indexOf(" ") + 1);
            } else {
                commandName = cmd;
                cmd = "";
            }
            commandName = shell.resolve(commandName);
            if (commandName != null) {
                Class<? extends Command> commandClass = commands.get(commandName);
                if (commandClass == null) {
                    throw new CommandNotFoundException(commandName + ": command not found");
                }
                Command command = commandClass.newInstance();
                Map<RequiredContextElement, Field> contextElementFieldMap = ReflectionUtilities.getDeclaredFieldsWithAnnotation(RequiredContextElement.class, commandClass);
                for (final RequiredContextElement contextElement : contextElementFieldMap.keySet()) {
                    Object shellContextElement = shell.getContextElement(contextElement.name());
                    if(shellContextElement != null) {
                        Field field = contextElementFieldMap.get(contextElement);
                        field.setAccessible(true);
                        field.set(command, shellContextElement);
                    } else {
                        throw new IllegalStateException(contextElement.notSetMessage());
                    }
                }

                Map<NamedParameter, Field> namedParameterFieldMap = ReflectionUtilities.getDeclaredFieldsWithAnnotation(NamedParameter.class, commandClass);

                ArgumentParser parser = new ArgumentParser(new ByteArrayInputStream(cmd.getBytes()));
                Map<String, Argument> parsedArguments = parser.parse(getBooleanParameters(namedParameterFieldMap));

                for (final NamedParameter namedParameter : namedParameterFieldMap.keySet()) {
                    Field field = namedParameterFieldMap.get(namedParameter);
                    field.setAccessible(true);
                    Argument parsedArgument = parsedArguments.get(namedParameter.name());
                    if (parsedArgument != null) {
                        setValue(shell, command, field, parsedArgument.getValue());
                    } else if (namedParameter.required()) {
                        throw new IllegalStateException("Parameter '" + namedParameter.name() + "' is required.");
                    }
                }
                Map<com.elega9t.commons.shell.intrprtr.Parameter, Field> parameterFieldMap = ReflectionUtilities.getDeclaredFieldsWithAnnotation(com.elega9t.commons.shell.intrprtr.Parameter.class, commandClass);
                for (final com.elega9t.commons.shell.intrprtr.Parameter param : parameterFieldMap.keySet()) {
                    Field field = parameterFieldMap.get(param);
                    field.setAccessible(true);
                    Argument parsedArgument = parsedArguments.get(param.index() + "");
                    if (parsedArgument != null) {
                        setValue(shell, command, field, parsedArgument.getValue());
                    } else if (param.required()) {
                        throw new IllegalStateException("Parameter '" + field.getName() + "' is required.");
                    }
                }
                int exitVal = command.execute(shell, in, out);
                shell.setExitVal(exitVal);
                return;
            }
        }
        shell.setExitVal(0);
    }

    private List<String> getBooleanParameters(Map<NamedParameter, Field> namedParameters) {
        List<String> booleanParameters = new ArrayList<String>();
        for (NamedParameter namedParameter : namedParameters.keySet()) {
            final Field field = namedParameters.get(namedParameter);
            if(field.getType() == Boolean.class || field.getType() == boolean.class) {
                booleanParameters.add(namedParameter.name());
            }
        }
        return booleanParameters;
    }

    private void setValue(Shell shell, Command command, Field field, String value) throws IllegalAccessException {
        Object valueToSet = null;
        value = shell.resolve(value);
        valueToSet = TransformFactory.transform(String.class, field.getType(), value);
        field.set(command, valueToSet);
    }

    public Collection<Class<? extends Command>> getCommands() {
        return commands.values();
    }

}
