package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.args.*;
import com.elega9t.commons.args.Parameter;
import com.elega9t.commons.cp.ClassFilter;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.util.ReflectionUtilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {

    private final String name;

    private final Map<String, Class<? extends Command>> commands;

    private final Context<Object> context = new Context<Object>();

    public Interpreter(String name) {
        this.name = name;
        this.commands = new HashMap<String, Class<? extends Command>>();
    }

    public Interpreter(String name, Class<? extends Command>... commands) throws InstantiationException, IllegalAccessException {
        this(name);
        addCommand(commands);
    }

    public Interpreter(String name, String... commandsPackages) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
        this(name);
        addCommands(commandsPackages);
    }

    public String getName() {
        return name;
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
                return (aClass.getSuperclass() == Command.class);
            }
        }, commandsPackages);
        for (Class commandClass : commandClasses) {
            addCommand(commandClass);
        }
    }

    public int execute(Shell shell, String cmd) throws IllegalAccessException, InstantiationException, ParseException {
        String commandName;
        if(cmd != null && cmd.contains(" ")) {
            commandName = cmd.substring(0, cmd.indexOf(" "));
            cmd = cmd.substring(cmd.indexOf(" ") + 1);
        } else {
            commandName = cmd;
            cmd = "";
        }
        if(commandName != null) {
            ArgumentParser parser = new ArgumentParser(new ByteArrayInputStream(cmd.getBytes()));
            Map<String, Parameter> commandLineParameters = parser.parse();
            Class<? extends Command> commandClass = commands.get(commandName);
            if(commandClass == null) {
                throw new CommandNotFoundException(commandName + ": command not found");
            }
            Command command = commandClass.newInstance();
            Map<NamedParameter, Field> namedParameterFieldMap = ReflectionUtilities.getDeclaredFieldsWithAnnotation(NamedParameter.class, commandClass);
            for (final NamedParameter namedParameter : namedParameterFieldMap.keySet()) {
                Field field = namedParameterFieldMap.get(namedParameter);
                field.setAccessible(true);
                Parameter parameter = commandLineParameters.get(namedParameter.name());
                if(parameter != null) {
                    field.set(command, parameter.getValue());
                } else if(namedParameter.required()) {
                    throw new IllegalStateException("Parameter " + namedParameter.name() + "is required.");
                }
            }
            Map<com.elega9t.commons.shell.intrprtr.Parameter, Field> parameterFieldMap = ReflectionUtilities.getDeclaredFieldsWithAnnotation(com.elega9t.commons.shell.intrprtr.Parameter.class, commandClass);
            for (final com.elega9t.commons.shell.intrprtr.Parameter param : parameterFieldMap.keySet()) {
                Field field = parameterFieldMap.get(param);
                field.setAccessible(true);
                Parameter parameter = commandLineParameters.get(param.index() + "");
                if(parameter != null) {
                    field.set(command, parameter.getValue());
                } else if(param.required()) {
                    throw new IllegalStateException("Parameter '"+ field.getName() +"' is required.");
                }
            }
            return command.execute(shell);
        } else {
            return 0;
        }
    }

}
