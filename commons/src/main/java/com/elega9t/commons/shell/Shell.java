package com.elega9t.commons.shell;

import com.elega9t.commons.renderer.Border;
import com.elega9t.commons.shell.intrprtr.Interpreter;
import com.elega9t.commons.shell.intrprtr.cmd.ExitCommand;
import com.elega9t.commons.util.ReplacementProvider;
import com.elega9t.commons.util.StringUtilities;
import org.fusesource.jansi.AnsiConsole;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell {

    private static final Logger LOGGER = Logger.getLogger(Shell.class.getName());

    private final Environment environment = new Environment();

    private Stack<Interpreter> interpreterStack = new Stack<Interpreter>();

    private Scanner scanner;

    private Interpreter interpreter;

    private int exitVal;

    private List<String> history = new ArrayList<String>();

    private ConcurrentHashMap<String, Object> context = new ConcurrentHashMap<String, Object>();

    public Shell(@NotNull Interpreter interpreter) {
        EnvironmentProperty.init(this);
        interpreterStack.push(interpreter);
        scanner = new Scanner(System.in);
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public int getExitVal() {
        return exitVal;
    }

    public void setExitVal(int exitVal) {
        this.exitVal = exitVal;
        EnvironmentProperty.update(this);
    }

    public void execute() {
        outln("Elega9t Shell v1.0.0");
        nextInterpreter();
        do {
            out(getEnvironmentProperty(EnvironmentProperty.PROMPT) + " ");
            String line = scanner.nextLine().trim();
            EnvironmentProperty.update(this);
            try {
                line = handleSpecialCommand(this, line);
                history.add(line);
                interpreter.execute(this, line);
            } catch (Exception e) {
                LOGGER.log(Level.INFO, "Command [" + line + "] threw exception", e);
                outln(interpreter.getName() + ": " + e.getMessage());
                setExitVal(1);
            }
        } while(interpreter != null);
    }

    public static String handleSpecialCommand(final Shell shell, String str) {
        str = StringUtilities.replace("(!!)", str, new ReplacementProvider() {
            @Override
            public String getReplacement(String match) {
                return shell.getLastFromHistory();
            }
        });
        str = StringUtilities.replace("!(\\d+)", str, new ReplacementProvider() {
            @Override
            public String getReplacement(String match) {
                int index = Integer.parseInt(match);
                return shell.getFromHistory(index);
            }
        });
        return str;
    }

    public String resolve(String str) {
        str = StringUtilities.replace("\\$\\(([. ]+)", str, new ReplacementProvider() {
            @Override
            public String getReplacement(String match) {
                return "Hi";
            }
        });
        return environment.resolve(str);
    }

    public String getLastFromHistory() {
        return getFromHistory(history.size());
    }

    public String getFromHistory(int index) {
        if(index > 0 && index <= history.size()) {
            return history.get(index - 1);
        } else {
            throw new IllegalStateException("!" + index + ": event not found.");
        }
    }

    public int getHistorySize() {
        return history.size();
    }

    public String getEnvironmentProperty(EnvironmentProperty environmentProperty) {
        return environment.resolve(environment.getValue(environmentProperty.name()));
    }

    public Border getBorder() {
        try {
            return Border.valueOf(getEnvironmentProperty(EnvironmentProperty.BORDER));
        } catch (IllegalArgumentException e) {
            Border defaultBorder = Border.PLAIN;
            environment.setProperty("BORDER", defaultBorder.name());
            return defaultBorder;
        }
    }

    public void out(Object msg) {
        AnsiConsole.out.print(msg);
        AnsiConsole.out().flush();
    }

    public void outln(Object msg) {
        AnsiConsole.out.println(msg);
        AnsiConsole.out().flush();
    }

    public void error(Throwable t) {
        outln(interpreter.getName() + ": " + t.getMessage());
    }

    public void nextInterpreter() {
        Interpreter nextInterpreter = interpreterStack.size() == 0 ? null : interpreterStack.pop();
        if(nextInterpreter != null) {
            outln("shell: switching to '" + nextInterpreter.getName() + "' interpreter.");
        }
        interpreter = nextInterpreter;
        EnvironmentProperty.update(this);
    }

    public void switchInterpreter(Interpreter nextInterpreter) {
        interpreterStack.push(interpreter);
        interpreterStack.push(nextInterpreter);
        nextInterpreter();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {
        Shell shell = new Shell(new Interpreter("yok", ExitCommand.class.getPackage().getName()));
        shell.execute();
    }

    public void setContextElement(String name, Object obj) {
        context.put(name, obj);
    }

    public Object getContextElement(String name) {
        return context.get(name);
    }

}
