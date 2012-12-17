/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.entity.impl.DefaultEntity;
import com.elega9t.commons.renderer.table.TableToStringRenderer;
import com.elega9t.commons.renderer.table.PropertiesDataModel;
import com.elega9t.commons.renderer.tree.TreeToStringRenderer;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.commons.shell.intrprtr.RequiredContextElement;
import com.elega9t.elixir.*;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.SQLWarning;
import java.util.HashMap;
import java.util.Map;

public class ConnectionCommand extends DefaultEntity implements Command {

    @RequiredContextElement(name="elixir-connection", notSetMessage = "No database connection exists. Please connect to a database first.")
    private Connection connection;

    private static final Map<String, Integer> operations = new HashMap<String, Integer>();
    static {
        operations.put("catalogue", 1);
        operations.put("client", 2);
        operations.put("warnings", 3);
        operations.put("autocommit", 4);
        operations.put("tree", 5);
        operations.put("schemas", 6);
        operations.put("tables", 7);
    }

    @Parameter(index=0)
    private String what;

    public ConnectionCommand() {
        super("connection");
    }

    @Override
    public int execute(Shell shell, BufferedReader in, PrintStream out) throws Exception {
        Integer whatToDo = operations.get(what.toLowerCase());
        switch (whatToDo) {
            case 1:
                out.println(connection.getCatalog());
                break;
            case 2:
                TableToStringRenderer tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
                out.println(tableToStringRenderer.render(new PropertiesDataModel(connection.getClientInfo())));
                break;
            case 3:
                SQLWarning warnings = connection.getWarnings();
                while(warnings != null) {
                    out.println("Code:" + warnings.getErrorCode() + ": " + warnings.getMessage());
                    warnings = warnings.getNextWarning();
                }
                break;
            case 4:
                out.println(connection.getAutoCommit());
                break;
            case 5:
                connection.loadAll();
                TreeToStringRenderer treeRenderer = new TreeToStringRenderer(shell.getBorder());
                out.println(treeRenderer.render(connection));
                break;
//                case 6:
//                    connection.loadAll();
//                    final Schemas schemas = connection.getSchemas();
//                    tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
//                    out.println(tableToStringRenderer.render(
//                            new EntityNodeDataModel<Schema>(schemas,
//                            new ColumnDataModel<Schema>("Name") {
//                                @Override
//                                public String value(Schema schema) {
//                                    return schema.getName();
//                                }
//                            }
//                    )));
//                    break;
//                case 7:
//                    connection.loadAll();
//                    final TableTypes tables = connection.getSchemas().getChildAt(0).getTables();
//                    tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
//                    out.println(tableToStringRenderer.render(
//                            new EntityNodeDataModel<Table>(tables,
//                            new ColumnDataModel<Table>("Name") {
//                                @Override
//                                public String value(Table table) {
//                                    return table.getName();
//                                }
//                            }
//                    )));
//                    break;
        }
        return 0;
    }

}
