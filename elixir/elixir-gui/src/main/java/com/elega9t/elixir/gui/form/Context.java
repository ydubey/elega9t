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

    public void execute(String query) {
        EditorPanel editorPanel = (EditorPanel) main.editorTabbedPane.getSelectedComponent();
        editorPanel.execute(query);
    }

}
