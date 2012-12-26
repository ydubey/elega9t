package com.elega9t.elixir.gui.form;

import com.elega9t.commons.swing.ResultSetTableModel;
import com.elega9t.elixir.Connection;
import com.elega9t.elixir.gui.entity.ConnectionGuiEntity;

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

    public ResultSetTableModel getResultTableModel() {
        EditorPanel editorPanel = (EditorPanel) main.editorTabbedPane.getSelectedComponent();
        return (ResultSetTableModel) editorPanel.resultTable.getModel();
    }

}
