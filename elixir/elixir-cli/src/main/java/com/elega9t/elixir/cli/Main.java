/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Interpreter;
import com.elega9t.commons.shell.intrprtr.cmd.ExitCommand;
import com.elega9t.elixir.cli.cmd.ConnectCommand;
import com.elega9t.elixir.mgr.DriverManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        DriverManager.getInstance().load();
        final Interpreter elx = new Interpreter("elx", ExitCommand.class.getPackage(), ConnectCommand.class.getPackage());
        Shell shell = new Shell(elx);
        shell.execute();
    }

}
