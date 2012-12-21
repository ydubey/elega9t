/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import java.awt.event.ActionEvent;

public abstract class KeymapListenerTextAction extends TextAction implements KeymapListener {

    protected final Action defaultAction;
    protected final Object defaultActionObj;
    protected final JTextComponent textComponent;

    protected Object currentInputKey;

    public KeymapListenerTextAction(String name, JTextComponent textComponent, KeyStroke keyStroke) {
        super(name);
        this.textComponent = textComponent;
        this.currentInputKey = name;
        if(currentInputKey != null) {
            this.defaultAction = textComponent.getActionMap().get(currentInputKey);
            this.defaultActionObj = textComponent.getInputMap().get(keyStroke);
        } else {
            defaultAction = new TextAction("NoAction") {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                }
            };
            defaultActionObj = null;
        }
        textComponent.getInputMap().put(keyStroke, currentInputKey);
        textComponent.getActionMap().put(currentInputKey, this);
        System.out.println("done");
    }

    @Override
    public void updateActionKey(KeyStroke keyStroke) {
        textComponent.getActionMap().remove(currentInputKey);
        this.currentInputKey = textComponent.getInputMap().get(keyStroke);
        textComponent.getActionMap().put(currentInputKey, this);
    }

}
