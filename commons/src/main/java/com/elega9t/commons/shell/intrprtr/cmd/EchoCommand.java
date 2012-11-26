package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.UnnamedParameter;

public class EchoCommand extends Command {

    @UnnamedParameter(index=0)
    private String what;

    public EchoCommand() {
        super("echo");
    }

    @Override
    public int execute(Shell shell) {
        shell.outln(shell.resolve(what));
        return 0;
    }
}
