package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("exit");
    }

    @Override
    public int execute(Shell shell) {
        shell.nextInterpreter();
        return 0;
    }

}
