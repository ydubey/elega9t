package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.util.StringUtilities;
import com.elega9t.commons.args.ArgumentParser;
import com.elega9t.commons.shell.Shell;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {

    private final String name;

    private final Map<String, Class<? extends Command>> commands;

    private final Context<Object> context = new Context<Object>();

    public Interpreter(String name, Class<? extends Command>... commands) throws InstantiationException, IllegalAccessException {
        this.name = name;
        this.commands = new HashMap<String, Class<? extends Command>>();
        for (Class<? extends Command> command : commands) {
            addCommand(command);
        }
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

    public int execute(Shell shell, String cmd) throws IllegalAccessException, InstantiationException {
        List<String> parts = StringUtilities.split(cmd);
        if(parts.size() > 0) {
            final String commandName = parts.get(0);
            Class<? extends Command> commandClass = commands.get(commandName);
            if(commandClass == null) {
                throw new IllegalArgumentException(commandName + ": command not found");
            }
            Command command = commandClass.newInstance();
            parts.remove(0);
            Map<Argument, Field> arguments = command.getArguments();
            ArgumentParser.parse(parts.listIterator(), arguments.keySet().toArray(new Argument[arguments.size()]));
            for (Argument argument : arguments.keySet()) {
                Field field = arguments.get(argument);
                field.setAccessible(true);
                field.set(command, argument.getValue());
            }
            return command.execute(shell);
        } else {
            return 0;
        }
    }

}
