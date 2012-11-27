/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.DatabaseConnection;
import com.elega9t.elixir.DatabaseDriver;
import com.elega9t.elixir.cli.ExecInterpreter;

import java.sql.SQLException;
import java.util.Map;

public class ConnectCommand extends Command {

    @Parameter(index=0)
    private String databaseName;
    @Parameter(index=1)
    private String userName;
    @Parameter(index=2)
    private String password;

    public ConnectCommand() {
        super("connect");
    }

    @Override
    public int execute(Shell shell) {
        Map<String, DatabaseDriver> drivers = (Map<String, DatabaseDriver>) shell.getContextElement("elixir-drivers");
        DatabaseDriver databaseDriver = drivers.get(databaseName.toLowerCase());
        try {
            final DatabaseConnection connection = databaseDriver.createConnection(userName, password);
            shell.outln("Connection successful!");
            shell.switchInterpreter(new ExecInterpreter(connection));
        } catch (SQLException e) {
            shell.outln("ERROR: " + e.getErrorCode() + " - " + e.getMessage());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
