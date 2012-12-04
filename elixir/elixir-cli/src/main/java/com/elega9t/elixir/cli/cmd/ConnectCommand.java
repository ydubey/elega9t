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

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Map;

public class ConnectCommand extends DefaultEntity implements Command {

    @Parameter(index=0)
    private String databaseName;
    @NamedParameter(name="user", required = false)
    private String userName;
    @NamedParameter(name="password", required = false)
    private String password;
    @NamedParameter(name="port", required = false)
    private String port;

    public ConnectCommand() {
        super("connect");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) throws Exception {
        Map<String, Driver> drivers = (Map<String, Driver>) shell.getContextElement("elixir-drivers");
        Driver driver = drivers.get(databaseName.toLowerCase());
        if(userName == null) {
            out.print("User Name: ");
            userName = in.readLine();
        }
        if(password == null) {
            out.print("Password: ");
            password = in.readLine();
        }
        final Connection connection = driver.createConnection(userName, password);
        shell.setContextElement("connection", connection);
        out.println("Connection successful!");
        shell.switchInterpreter(new SqlInterpreter(connection));
        return 0;
    }

}
