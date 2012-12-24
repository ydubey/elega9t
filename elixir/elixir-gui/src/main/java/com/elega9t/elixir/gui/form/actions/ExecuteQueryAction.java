/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.form.actions;

import com.elega9t.commons.swing.UpdatableTextAction;
import com.elega9t.commons.swing.ResultSetTableModel;
import com.elega9t.elixir.Connection;
import com.elega9t.elixir.gui.entity.ConnectionGuiEntity;
import com.elega9t.elixir.gui.form.Main;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExecuteQueryAction extends UpdatableTextAction {

    private final Main main;
    private final JComboBox currentDatabaseComboBox;
    private final JTextComponent queryEditorTextPane;
    private final JTable resultTable;

    public ExecuteQueryAction(Main main, JComboBox currentDatabaseComboBox, JTextComponent queryEditorTextPane, JTable resultTable, KeyStroke keyStroke) {
        super("ExecuteQuery", queryEditorTextPane, keyStroke);
        this.main = main;
        this.currentDatabaseComboBox = currentDatabaseComboBox;
        this.queryEditorTextPane = queryEditorTextPane;
        this.resultTable = resultTable;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final ConnectionGuiEntity connectionGuiEntity = (ConnectionGuiEntity) currentDatabaseComboBox.getSelectedItem();
        final Connection connection = connectionGuiEntity.getEntity();
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(queryEditorTextPane.getText());
            final boolean isResultSet = preparedStatement.execute();
            if(isResultSet) {
                final ResultSetTableModel model = (ResultSetTableModel) resultTable.getModel();
                model.setResultSet(preparedStatement.getResultSet());
                //setMessage(model.getRowCount() + " row(s) selected.");
                //resultsTabbedPane.setSelectedComponent(resultTablePanel);
            } else {
                //setMessage(preparedStatement.getUpdateCount() + " row(s) updated.");
                //resultsTabbedPane.setSelectedComponent(messagesPanel);
            }
        } catch (SQLException e) {
            main.errorOccured(e);
        }
    }
}
