/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.DatabaseDriver;

import java.util.List;
import java.util.Map;

public class ConnectCommand extends Command {

    @Parameter(index=0)
    private String databaseName;

    public ConnectCommand() {
        super("connect");
    }

    @Override
    public int execute(Shell shell) {
        List<DatabaseDriver> drivers = (List<DatabaseDriver>) shell.getContextElement("elixir-drivers");
        DatabaseDriver databaseDriver = null;
        for (DatabaseDriver driver : drivers) {
            if(driver.databaseName().equalsIgnoreCase(databaseName)) {
                databaseDriver = driver;
                break;
            }
        }
        shell.outln(databaseDriver.getDrivers());
        return 0;
    }

}
