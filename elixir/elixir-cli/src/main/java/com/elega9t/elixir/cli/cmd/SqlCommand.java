/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.elixir.DatabaseConnection;
import com.elega9t.elixir.cli.SqlInterpreter;

public class SqlCommand extends DefaultEntity implements Command {

    public SqlCommand() {
        super("sql");
    }

    @Override
    public int execute(Shell shell) throws Exception {
        DatabaseConnection connection = (DatabaseConnection) shell.getContextElement("connection");
        if(connection != null) {
            shell.switchInterpreter(new SqlInterpreter(connection));
        } else {
            throw new IllegalArgumentException("No database connection exists. Please connect to a database first.");
        }
        return 0;
    }

}
