package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.BufferedReader;
import java.io.PrintStream;

public class HistoryCommand extends DefaultEntity implements Command {

    public HistoryCommand() {
        super("history");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        for (int index = 1; index <= shell.getHistorySize(); index++) {
            out.println(index + ": " + shell.getFromHistory(index));
        }
        return 0;
    }

}
