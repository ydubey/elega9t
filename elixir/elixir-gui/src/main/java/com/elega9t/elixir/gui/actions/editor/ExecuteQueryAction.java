/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.actions.editor;

import com.elega9t.commons.swing.UpdatableAction;
import com.elega9t.elixir.gui.form.Context;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

public class ExecuteQueryAction extends UpdatableAction {

    private final JTextComponent queryEditorTextPane;

    public ExecuteQueryAction(String name, JTextComponent queryEditorTextPane, KeyStroke keyStroke) {
        super(name, queryEditorTextPane, keyStroke);
        this.queryEditorTextPane = queryEditorTextPane;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Context.getInstance().execute(queryEditorTextPane.getText());
    }
}
