/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.action;

import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextComponentFindAction extends FindAction implements FocusListener {

    public TextComponentFindAction(JTextComponent component) {
        super(component);
    }

    protected void initSearch(ActionEvent ae) {
        super.initSearch(ae);
        JTextComponent textComp = (JTextComponent) ae.getSource();
        String selectedText = textComp.getSelectedText();
        if(selectedText != null) {
            searchField.setText(selectedText);
        }
        searchField.removeFocusListener(this);
        searchField.addFocusListener(this);
    }

    protected boolean changed(JComponent comp, String str, Position.Bias bias) {
        JTextComponent textComp = (JTextComponent)comp;
        int offset = bias==Position.Bias.Forward ? textComp.getCaretPosition() : textComp.getCaret().getMark() - 1;
        int index = getNextMatch(textComp, str, offset, bias);
        if(index != -1) {
            textComp.select(index, index + str.length());
            return true;
        } else {
            offset = bias==null || bias==Position.Bias.Forward ? 0 : textComp.getDocument().getLength();
            index = getNextMatch(textComp, str, offset, bias);
            if(index != -1) {
                textComp.select(index, index + str.length());
                return true;
            } else {
                return false;
            }
        }
    }

    protected int getNextMatch(JTextComponent textComp, String str, int startingOffset, Position.Bias bias) {
        String text = textComp.getText();
        if(ignoreCase) {
            str = str.toUpperCase();
            text = text.toUpperCase();
        }
        return bias==null || bias==Position.Bias.Forward
                ? text.indexOf(str, startingOffset)
                : text.lastIndexOf(str, startingOffset);
    }

    public void focusGained(FocusEvent e) {
        Caret caret = ((JTextComponent)comp).getCaret();
        caret.setVisible(true);
        caret.setSelectionVisible(true);
    }

    public void focusLost(FocusEvent e) {
    }

}