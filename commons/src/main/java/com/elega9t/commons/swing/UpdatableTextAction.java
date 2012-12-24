/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;

public abstract class UpdatableTextAction extends TextAction implements KeymapListener {

    protected final Action defaultAction;
    protected final Object defaultActionKey;
    protected final JTextComponent textComponent;

    protected KeyStroke currentActionKeyStroke;

    public UpdatableTextAction(String name, JTextComponent textComponent, KeyStroke keyStroke) {
        super(name);
        this.textComponent = textComponent;
        this.currentActionKeyStroke = keyStroke;

        this.defaultActionKey = textComponent.getInputMap().get(keyStroke);
        if(defaultActionKey != null) {
            this.defaultAction = textComponent.getActionMap().get(defaultActionKey);
        } else {
            defaultAction = null;
        }
        textComponent.getInputMap().put(keyStroke, getValue(NAME));
        textComponent.getActionMap().put(getValue(NAME), this);
    }

    @Override
    public void update(KeyStroke keyStroke) {
        textComponent.getInputMap().remove(currentActionKeyStroke);
        currentActionKeyStroke = keyStroke;
        textComponent.getInputMap().put(keyStroke, getValue(NAME));
    }

}
