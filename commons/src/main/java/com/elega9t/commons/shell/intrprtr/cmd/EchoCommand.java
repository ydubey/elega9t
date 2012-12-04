package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;

import java.io.BufferedReader;
import java.io.PrintStream;

public class EchoCommand extends DefaultEntity implements Command {

    @Parameter(index=0)
    private String what;

    public EchoCommand() {
        super("echo");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        out.println(shell.resolve(what));
        return 0;
    }
}
