/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.EnvironmentProperty;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;

public class PwdCommand extends Command {

    public PwdCommand() {
        super("pwd");
    }

    @Override
    public int execute(Shell shell) {
        shell.outln(shell.getEnvironmentProperty(EnvironmentProperty.PWD));
        return 0;
    }

}
