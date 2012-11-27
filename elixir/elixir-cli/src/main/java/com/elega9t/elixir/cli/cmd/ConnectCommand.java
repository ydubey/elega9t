/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.DatabaseDriver;

import java.util.Map;

public class ConnectCommand extends Command {

    @Parameter(index=0)
    private String databaseName;

    public ConnectCommand() {
        super("connect");
    }

    @Override
    public int execute(Shell shell) {
        Map<String, DatabaseDriver> drivers = (Map<String, DatabaseDriver>) shell.getContextElement("elixir-drivers");
        DatabaseDriver databaseDriver = drivers.get(databaseName.toLowerCase());
        shell.outln(databaseDriver.getDrivers());
        return 0;
    }

}
