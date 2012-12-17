package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.shell.Environment;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

import java.io.BufferedReader;
import java.io.PrintStream;

public class SetCommand extends DefaultEntity implements Command {

    public SetCommand() {
        super("set");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) {
        Environment environment = shell.getEnvironment();
        for (String propertyName: environment.getPropertyNames()) {
            out.println(propertyName + "=" + environment.getValue(propertyName));
        }
        return 0;
    }

}
