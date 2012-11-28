/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.shell.intrprtr.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class ClearCommand extends Command {

    public ClearCommand() {
        super("clear");
    }

    @Override
    public int execute(Shell shell) {
        AnsiConsole.out.print(Ansi.ansi().eraseScreen().cursor(0, 0));
        return 0;
    }

}
