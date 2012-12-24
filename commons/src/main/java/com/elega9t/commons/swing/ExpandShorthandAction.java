/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;

public class ExpandShorthandAction extends UpdatableTextAction {

    private final ShorthandFactory shorthandFactory;

    public ExpandShorthandAction(String name, JTextComponent textComponent, KeyStroke keyStroke, ShorthandFactory shorthandFactory) {
        super(name, textComponent, keyStroke);
        this.shorthandFactory = shorthandFactory;
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
                defaultAction.actionPerformed(e);
            }
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    private boolean isSpaceChar(char character) {
        return character == ' ' || character == '\t' || character == '\n' || character == '\r';
    }

    public static interface ShorthandFactory {

        boolean isShorthand(String key);

        String getReplacement(String key);

    }

}
