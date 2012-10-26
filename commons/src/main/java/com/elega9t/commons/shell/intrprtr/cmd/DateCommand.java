package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.EnvironmentProperty;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

public class DateCommand extends Command {

    public DateCommand() {
        super("date");
    }

    @Override
    public int execute(Shell shell) {
        shell.outln(shell.getEnvironment().getValue(EnvironmentProperty.DATE.name()));
        return 0;
    }

}
