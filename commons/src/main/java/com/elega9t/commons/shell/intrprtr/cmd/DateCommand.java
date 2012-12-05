package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.EnvironmentProperty;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.BufferedReader;
import java.io.PrintStream;

public class DateCommand extends DefaultEntity implements Command {

    public DateCommand() {
        super("date");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        out.println(shell.getEnvironmentProperty(EnvironmentProperty.DATE));
        return 0;
    }

}
