/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.renderer.table.ConsoleTableDataRenderer;
import com.elega9t.commons.renderer.table.PropertiesDataModel;
import com.elega9t.commons.renderer.tree.ConsoleTreeRenderer;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.Connection;

import java.sql.SQLWarning;
import java.util.HashMap;
import java.util.Map;

public class ConnectionCommand extends DefaultEntity implements Command {

    private static final Map<String, Integer> operations = new HashMap<String, Integer>();
    static {
        operations.put("catalogue", 1);
        operations.put("client", 2);
        operations.put("warnings", 3);
        operations.put("autocommit", 4);
        operations.put("tree", 5);
    }

    @Parameter(index=0)
    private String what;

    public ConnectionCommand() {
        super("connection");
    }

    @Override
    public int execute(Shell shell) throws Exception {
        Connection connection = (Connection) shell.getContextElement("connection");
        if(connection != null) {
            Integer whatToDo = operations.get(what.toLowerCase());
            switch (whatToDo) {
                case 1:
                    shell.outln(connection.getCatalog());
                    break;
                case 2:
                    ConsoleTableDataRenderer tableDataRenderer = new ConsoleTableDataRenderer(shell.getBorder());
                    shell.outln(tableDataRenderer.render(new PropertiesDataModel(connection.getClientInfo())));
                    break;
                case 3:
                    SQLWarning warnings = connection.getWarnings();
                    while(warnings != null) {
                        shell.outln("Code:" + warnings.getErrorCode() + ": " + warnings.getMessage());
                        warnings = warnings.getNextWarning();
                    }
                    break;
                case 4:
                    shell.outln(connection.getAutoCommit());
                    break;
                case 5:
                    connection.loadAll();
                    ConsoleTreeRenderer treeRenderer = new ConsoleTreeRenderer(shell.getBorder());
                    shell.outln(treeRenderer.render(connection));
                    break;
            }
        } else {
            throw new IllegalArgumentException("No database connection exists. Please connect to a database first.");
        }
        return 0;
    }

}
