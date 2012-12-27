package com.elega9t.elixir.gui.form;

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
        EditorPanel editorPanel = main.currentEditorPanel();
        editorPanel.execute(query);
    }

}
