/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class KeyStrokeEditor extends JTextField {

    protected KeyStroke keyStroke;

    public KeyStrokeEditor() {
        enableEvents(KeyEvent.KEY_EVENT_MASK);
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_SHIFT ||
            keyCode == KeyEvent.VK_ALT ||
            keyCode == KeyEvent.VK_CONTROL ||
            keyCode == KeyEvent.VK_ALT_GRAPH ||
            keyCode == KeyEvent.VK_META) {
                return;
            }
            setKeyStroke(KeyStroke.getKeyStroke(keyCode, e.getModifiers()));

        }
    }

    public KeyStroke getKeyStroke() {
        return keyStroke;
    }

    public void setKeyStroke(KeyStroke keyStroke) {
        KeyStroke oldValue = getKeyStroke();
        this.keyStroke = keyStroke;
        firePropertyChange("keyStroke", oldValue, keyStroke);
        fireActionPerformed();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setText(getKeyStroke().toString());
            }
        });
    }

   
}