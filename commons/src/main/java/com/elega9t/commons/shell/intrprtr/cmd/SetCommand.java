package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.Environment;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

public class SetCommand extends Command {

    public SetCommand() {
        super("set");
    }

    @Override
    public int execute(Shell shell) {
        Environment environment = shell.getEnvironment();
        for (String propertyName: environment.getPropertyNames()) {
            shell.outln(propertyName + "=" + environment.getValue(propertyName));
        }
        return 0;
    }

}
