/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.cli;

import com.elega9t.commons.args.ParseException;
import com.elega9t.commons.renderer.Border;
import com.elega9t.commons.renderer.ConsoleTableDataRenderer;
import com.elega9t.commons.renderer.ResultSetDataProvider;
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
    public int execute(Shell shell, String cmd) throws IllegalAccessException, InstantiationException, ParseException {
        try {
            super.execute(shell, cmd);
        } catch(CommandNotFoundException e) {
            try {
                final PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                final boolean isResultSet = preparedStatement.execute();
                if(isResultSet) {
                    final ResultSetDataProvider dataProvider = new ResultSetDataProvider(preparedStatement.getResultSet());
                    shell.outln(renderer.render(dataProvider));
                    shell.outln(String.format("%d row(s) selected.", dataProvider.rowCount()));
                } else {
                    shell.outln(String.format("%d row(s) updated.", preparedStatement.getUpdateCount()));
                }
            } catch (SQLException sqlE) {
                shell.outln(String.format("ERROR: %d - %s", sqlE.getErrorCode(), sqlE.getMessage()));
            }
        }
        return 0;
    }

}
