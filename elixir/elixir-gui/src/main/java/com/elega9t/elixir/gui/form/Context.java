package com.elega9t.elixir.gui.form;

import com.elega9t.elixir.Connection;
import com.elega9t.elixir.gui.entity.ConnectionGuiEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Context {

    private static final Context INSTANCE = new Context();

    private Main main = new Main();

    public static Context getInstance() {
        return INSTANCE;
    }

    public Main getMain() {
        return main;
    }

    public Connection getConnection() {
        EditorPanel editorPanel = (EditorPanel) main.editorTabbedPane.getSelectedComponent();
        return ((ConnectionGuiEntity)editorPanel.currentDatabaseComboBox.getSelectedItem()).getEntity();
    }

    public void setResultSet(ResultSet resultSet) throws SQLException {
        EditorPanel editorPanel = (EditorPanel) main.editorTabbedPane.getSelectedComponent();
        editorPanel.setResultSet(resultSet);
    }

    public void setResultMessage(String message) {
        EditorPanel editorPanel = (EditorPanel) main.editorTabbedPane.getSelectedComponent();
        editorPanel.setMessage(message);
    }

}
