/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli;

import com.elega9t.commons.args.ParseException;
import com.elega9t.commons.renderer.Border;
import com.elega9t.commons.renderer.ConsoleTableDataRenderer;
import com.elega9t.commons.renderer.ResultSetDataModel;
import com.elega9t.commons.shell.Shell;
import com.elega9t.commons.shell.intrprtr.CommandNotFoundException;
import com.elega9t.commons.shell.intrprtr.Interpreter;
import com.elega9t.commons.shell.intrprtr.cmd.ExitCommand;
import com.elega9t.commons.shell.intrprtr.cmd.HistoryCommand;
import com.elega9t.elixir.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlInterpreter extends Interpreter {

    private static final ConsoleTableDataRenderer renderer = new ConsoleTableDataRenderer(Border.SINGLE);
    private final DatabaseConnection connection;

    public SqlInterpreter(DatabaseConnection connection) throws InstantiationException, IllegalAccessException {
        super("sql");
        this.connection = connection;
        addCommand(ExitCommand.class, HistoryCommand.class);
    }

    @Override
    protected void executeCommand(Shell shell, String cmd) throws IllegalAccessException, InstantiationException, ParseException {
        try {
            super.executeCommand(shell, cmd);
        } catch(CommandNotFoundException e) {
            try {
                final PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                final boolean isResultSet = preparedStatement.execute();
                if(isResultSet) {
                    final ResultSetDataModel dataModel = new ResultSetDataModel(preparedStatement.getResultSet());
                    shell.outln(renderer.render(dataModel));
                    shell.outln(String.format("%d row(s) selected.", dataModel.rowCount()));
                } else {
                    shell.outln(String.format("%d row(s) updated.", preparedStatement.getUpdateCount()));
                }
            } catch (SQLException sqlE) {
                shell.outln(String.format("ERROR: %d - %s", sqlE.getErrorCode(), sqlE.getMessage()));
            }
        }
        shell.setExitVal(0);
    }

}
