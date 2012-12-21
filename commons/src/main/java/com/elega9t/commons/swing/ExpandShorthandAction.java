/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import java.awt.event.ActionEvent;

public class ExpandShorthandAction extends TextAction implements KeymapListener {

    private Action defaultTabAction;
    private ShorthandFactory shorthandFactory;
    private Object currentInputKey;
    private JTextComponent textComponent;

    public ExpandShorthandAction() {
        super("ExpandShorthand");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JTextComponent textComponent = getTextComponent(e);
        final int caretPosition = textComponent.getCaretPosition();
        try {
            final Document document = textComponent.getDocument();
            int startPos = caretPosition;
            if(startPos > 0) {
                char charAt0;
                do {
                    startPos--;
                    charAt0 = document.getText(startPos, caretPosition - startPos).charAt(0);
                } while(startPos > 0 && !isSpaceChar(charAt0));
                if(isSpaceChar(charAt0)) {
                    startPos ++;
                }
            }
            String shorthand = document.getText(startPos, caretPosition - startPos);
            if(shorthand.length() > 0 && shorthandFactory.isShorthand(shorthand)) {
                document.remove(startPos, caretPosition - startPos);
                document.insertString(startPos, shorthandFactory.getReplacement(shorthand), null);
            } else {
                defaultTabAction.actionPerformed(e);
            }
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    private boolean isSpaceChar(char character) {
        return character == ' ' || character == '\t' || character == '\n' || character == '\r';
    }

    public void install(JTextComponent textComponent, KeyStroke keyStroke, ShorthandFactory shorthandFactory) {
        this.textComponent = textComponent;
        this.currentInputKey = textComponent.getInputMap().get(keyStroke);
        this.defaultTabAction = textComponent.getActionMap().get(currentInputKey);
        this.shorthandFactory = shorthandFactory;
        textComponent.getActionMap().put(currentInputKey, this);
    }

    @Override
    public void updateActionKey(KeyStroke keyStroke) {
        textComponent.getActionMap().remove(currentInputKey);
        this.currentInputKey = textComponent.getInputMap().get(keyStroke);
        textComponent.getActionMap().put(currentInputKey, this);
    }

    public static interface ShorthandFactory {

        boolean isShorthand(String key);

        String getReplacement(String key);

    }

}
