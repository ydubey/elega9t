package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.UnnamedParameter;

public class ExportCommand extends Command {

    @UnnamedParameter()
    private String what;

    public ExportCommand() {
        super("export");
    }

    @Override
    public int execute(Shell shell) {
        int seperatorIndex = what.indexOf("=");
        if(seperatorIndex != -1) {
            shell.getEnvironment().setProperty(what.substring(0, seperatorIndex), what.substring(seperatorIndex+1));
        }
        return 0;
    }

}
