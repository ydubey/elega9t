/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.NamedParameter;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.Connection;
import com.elega9t.elixir.Driver;
import com.elega9t.elixir.cli.SqlInterpreter;

import java.util.Map;

public class ConnectCommand extends DefaultEntity implements Command {

    @Parameter(index=0)
    private String databaseName;
    @NamedParameter(name="user", required = true)
    private String userName;
    @NamedParameter(name="password", required = true)
    private String password;
    @NamedParameter(name="port", required = false)
    private String port;

    public ConnectCommand() {
        super("connect");
    }

    @Override
    public int execute(Shell shell) throws Exception {
        Map<String, Driver> drivers = (Map<String, Driver>) shell.getContextElement("elixir-drivers");
        Driver driver = drivers.get(databaseName.toLowerCase());
        final Connection connection = driver.createConnection(userName, password);
        shell.setContextElement("connection", connection);
        shell.outln("Connection successful!");
        shell.switchInterpreter(new SqlInterpreter(connection));
        return 0;
    }

}
