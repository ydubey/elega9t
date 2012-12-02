/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Interpreter;
import com.elega9t.commons.shell.intrprtr.Parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpCommand extends DefaultEntity implements Command {

    @Parameter(index = 0, required = false)
    private String commandName;

    public HelpCommand() {
        super("help");
    }

    @Override
    public int execute(Shell shell) throws Exception {
        if(commandName == null) {
            shell.outln(shell.getName());
            final Interpreter interpreter = shell.getInterpreter();
            shell.outln("Using '" + interpreter.getName() + "' interpreter. Commands available under this interpreter are:");
            List<String> commands = new ArrayList<String>();
            for (Class<? extends Command> commandClass : interpreter.getCommands()) {
                final Command command = commandClass.newInstance();
                commands.add(command.getName());
            }
            Collections.sort(commands);
            for (String command : commands) {
                shell.outln(command);
            }
        }
        return 0;
    }

}
