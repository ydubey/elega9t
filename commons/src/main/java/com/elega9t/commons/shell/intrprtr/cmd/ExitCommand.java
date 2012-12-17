package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.BufferedReader;
import java.io.PrintStream;

public class ExitCommand extends DefaultEntity implements Command {

    public ExitCommand() {
        super("exit");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        shell.nextInterpreter();
        return 0;
    }

}
