/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing.action;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class FindAction extends AbstractAction implements DocumentListener, KeyListener {

    private final JComponent component;

    private JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    protected JTextField searchField = new JTextField();

    private JPopupMenu popup = new JPopupMenu();

    public FindAction(final JComponent component){
        super("Incremental Search"); //NOI18N

        this.component = component;
        component.registerKeyboardAction(this, KeyStroke.getKeyStroke('F', KeyEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
        component.registerKeyboardAction(this, KeyStroke.getKeyStroke('F', KeyEvent.CTRL_MASK | KeyEvent.SHIFT_MASK), JComponent.WHEN_FOCUSED);

        searchPanel.setBackground(UIManager.getColor("ToolTip.background")); //NOI18N
        searchField.setOpaque(false);
        JLabel label = new JLabel("Search for:");
        label.setFont(new Font("DialogInput", Font.BOLD, 12)); // for readability
        searchPanel.add(label);
        searchPanel.add(searchField);
        searchField.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        popup.setBorder(BorderFactory.createLineBorder(Color.black));
        popup.add(searchPanel);
        searchField.setFont(component.getFont()); // for readability
        // when the window containing the "comp" has registered Esc key
        // then on pressing Esc instead of search popup getting closed
        // the event is sent to the window. to overcome this we
        // register an action for Esc.
        searchField.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popup.setVisible(false);
                component.requestFocus();

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_FOCUSED);
    }

    protected JComponent comp = null;
    protected boolean ignoreCase;

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == searchField) {
            popup.setVisible(false);
        } else {
            comp = (JComponent) ae.getSource();
            ignoreCase = (ae.getModifiers() & ActionEvent.SHIFT_MASK) != 0;
            searchField.removeActionListener(this);
            searchField.removeKeyListener(this);
            searchField.getDocument().removeDocumentListener(this);
            initSearch(ae);
            searchField.addActionListener(this);
            searchField.addKeyListener(this);
            searchField.getDocument().addDocumentListener(this);
            Rectangle rect = comp.getVisibleRect();
            popup.show(comp, rect.x, rect.y - popup.getPreferredSize().height - 5);
            searchField.requestFocus();
            searchField.select(0, 0);
        }
    }

    protected void initSearch(ActionEvent ae) {
        searchField.setText(""); //NOI18N
        searchField.setForeground(Color.black);
    }

    private void changed(Position.Bias bias) {
        popup.setVisible(false);
        popup.setVisible(true);
        searchField.requestFocus();
        searchField.select(0, 0);
        searchField.setForeground(changed(comp, searchField.getText(), bias) ? Color.black : Color.red);
    }

    protected abstract boolean changed(JComponent comp, String text, Position.Bias bias);

    public void insertUpdate(DocumentEvent e){
        changed(null);
    }

    public void removeUpdate(DocumentEvent e){
        changed(null);
    }

    public void changedUpdate(DocumentEvent e){}

    protected boolean shiftDown = false;
    protected boolean controlDown = false;

    public void keyPressed(KeyEvent ke){
        shiftDown = ke.isShiftDown();
        controlDown = ke.isControlDown();
        switch(ke.getKeyCode()) {
            case KeyEvent.VK_UP:
                changed(Position.Bias.Backward);
                break;
            case KeyEvent.VK_DOWN:
                changed(Position.Bias.Forward);
                break;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

}