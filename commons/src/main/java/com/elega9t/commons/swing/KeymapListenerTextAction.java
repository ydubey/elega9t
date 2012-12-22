/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;

public abstract class KeymapListenerTextAction extends TextAction implements KeymapListener {

    private static final String ELEGA9T_ACTION_PREFIX = "Elega9tAction.";

    protected final Action defaultAction;
    protected final Object defaultActionKey;
    protected final JTextComponent textComponent;

    protected KeyStroke currentActionKeyStroke;

    public KeymapListenerTextAction(String name, JTextComponent textComponent, KeyStroke keyStroke) {
        super(ELEGA9T_ACTION_PREFIX + name);
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
    public void updateActionKey(KeyStroke keyStroke) {
        textComponent.getInputMap().remove(currentActionKeyStroke);
        currentActionKeyStroke = keyStroke;
        textComponent.getInputMap().put(keyStroke, getValue(NAME));
    }

}
