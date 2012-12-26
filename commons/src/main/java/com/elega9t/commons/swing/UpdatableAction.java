/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;

public abstract class UpdatableAction extends AbstractAction implements KeymapListener, Action {

    protected final Action defaultAction;
    protected final Object defaultActionKey;
    protected final JComponent component;

    protected KeyStroke currentActionKeyStroke;

    public UpdatableAction(String name, JComponent component, KeyStroke keyStroke) {
        super(name);
        this.component = component;
        this.currentActionKeyStroke = keyStroke;

        this.defaultActionKey = component.getInputMap().get(keyStroke);
        if(defaultActionKey != null) {
            this.defaultAction = component.getActionMap().get(defaultActionKey);
        } else {
            defaultAction = null;
        }
        component.getInputMap().put(keyStroke, getValue(NAME));
        component.getActionMap().put(getValue(NAME), this);
    }

    @Override
    public void update(KeyStroke keyStroke) {
        component.getInputMap().remove(currentActionKeyStroke);
        currentActionKeyStroke = keyStroke;
        component.getInputMap().put(keyStroke, getValue(NAME));
    }

}
