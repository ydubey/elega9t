/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.form.actions;

import com.elega9t.commons.swing.ExpandShorthandAction;
import com.elega9t.commons.swing.KeymapListener;
import com.elega9t.elixir.gui.mgr.ShorthandManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

public class ExpandSqlShorthandAction extends ExpandShorthandAction {

    public ExpandSqlShorthandAction(String name, JTextComponent textComponent, KeyStroke keyStroke) {
        super(name, textComponent, keyStroke, ShorthandManager.getInstance());
    }

}
