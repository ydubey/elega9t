package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

public class HistoryCommand extends Command {

    public HistoryCommand() {
        super("history");
    }

    @Override
    public int execute(Shell shell) {
        for (int index = 1; index <= shell.getHistorySize(); index++) {
            shell.outln(index + ": " + shell.getFromHistory(index));
        }
        return 0;
    }

}
