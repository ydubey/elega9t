package com.elega9t.commons.shell.intrprtr;

import com.elega9t.commons.args.*;
import com.elega9t.commons.args.Parameter;
import com.elega9t.commons.shell.Shell;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
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

    public int execute(Shell shell, String cmd) throws IllegalAccessException, InstantiationException, ParseException {
        String commandName = null;
        if(cmd != null && cmd.contains(" ")) {
            commandName = cmd.substring(0, cmd.indexOf(" "));
            cmd = cmd.substring(cmd.indexOf(" ") + 1);
        } else {
            commandName = cmd;
            cmd = "";
        }
        if(commandName != null) {
            ArgumentParser parser = new ArgumentParser(new ByteArrayInputStream(cmd.getBytes()));
            Map<String, Parameter> parameters = parser.parse();
            Class<? extends Command> commandClass = commands.get(commandName);
            if(commandClass == null) {
                throw new IllegalArgumentException(commandName + ": command not found");
            }
            Command command = commandClass.newInstance();
            Map<String, Field> arguments = command.getArguments();
            for (String argument : arguments.keySet()) {
                Field field = arguments.get(argument);
                field.setAccessible(true);
                field.set(command, parameters.get(argument).getValue());
            }
            return command.execute(shell);
        } else {
            return 0;
        }
    }

}
