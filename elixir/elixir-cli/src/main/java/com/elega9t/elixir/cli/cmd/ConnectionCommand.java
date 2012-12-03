/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli.cmd;

import com.elega9t.commons.entity.DefaultEntity;
import com.elega9t.commons.renderer.table.TableToStringRenderer;
import com.elega9t.commons.renderer.table.PropertiesDataModel;
import com.elega9t.commons.renderer.tree.TreeToStringRenderer;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.Command;
import com.elega9t.commons.shell.intrprtr.Parameter;
import com.elega9t.elixir.*;

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
        operations.put("schemas", 6);
        operations.put("tables", 7);
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
                    TableToStringRenderer tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
                    shell.outln(tableToStringRenderer.render(new PropertiesDataModel(connection.getClientInfo())));
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
                    TreeToStringRenderer treeRenderer = new TreeToStringRenderer(shell.getBorder());
                    shell.outln(treeRenderer.render(connection));
                    break;
//                case 6:
//                    connection.loadAll();
//                    final Schemas schemas = connection.getSchemas();
//                    tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
//                    shell.outln(tableToStringRenderer.render(
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
//                    final Tables tables = connection.getSchemas().getChild(0).getTables();
//                    tableToStringRenderer = new TableToStringRenderer(shell.getBorder());
//                    shell.outln(tableToStringRenderer.render(
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
        } else {
            throw new IllegalArgumentException("No database connection exists. Please connect to a database first.");
        }
        return 0;
    }

}
