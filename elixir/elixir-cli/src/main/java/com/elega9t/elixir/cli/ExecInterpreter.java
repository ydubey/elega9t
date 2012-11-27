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
import com.elega9t.elixir.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecInterpreter extends Interpreter {

    private static final ConsoleTableDataRenderer renderer = new ConsoleTableDataRenderer(Border.SINGLE);
    private final DatabaseConnection connection;

    public ExecInterpreter(DatabaseConnection connection) throws InstantiationException, IllegalAccessException {
        super("exec");
        this.connection = connection;
        addCommand(ExitCommand.class);
    }

    @Override
    public int execute(Shell shell, String cmd) throws IllegalAccessException, InstantiationException, ParseException {
        try {
            super.execute(shell, cmd);
        } catch(CommandNotFoundException e) {
            try {
                final PreparedStatement preparedStatement = connection.prepareStatement(cmd);
                final ResultSet resultSet = preparedStatement.executeQuery();
                shell.outln(renderer.render(new ResultSetDataProvider(resultSet)));
            } catch (SQLException sqlE) {
                shell.outln("ERROR: " + sqlE.getErrorCode() + " - " + sqlE.getMessage());
            }
        }
        return 0;
    }

}
