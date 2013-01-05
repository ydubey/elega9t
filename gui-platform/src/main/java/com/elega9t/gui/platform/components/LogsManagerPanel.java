/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.components;

import com.elega9t.gui.platform.mgr.event.Event;
import com.elega9t.gui.platform.mgr.event.EventListener;
import com.elega9t.gui.platform.mgr.event.EventManager;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.text.DateFormat;

/**
 *
 * @author yogesh
 */
public class LogsManagerPanel extends javax.swing.JPanel implements EventListener {

    private DateFormat dateFormat = DateFormat.getTimeInstance();

    /**
     * Creates new form LogsManagerPanel
     */
    public LogsManagerPanel() {
        initComponents();
        EventManager.getInstance().addLogListener(EventManager.ALL_LOG_LISTENER, this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logsManagerScrollPane = new javax.swing.JScrollPane();
        logsManagerTextPane = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        logsManagerScrollPane.setViewportView(logsManagerTextPane);

        add(logsManagerScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void pastEvent(Event event) {
        eventOccured(event);
    }

    @Override
    public void eventOccured(Event event) {
        Document document = logsManagerTextPane.getDocument();
        try {
            if(document.getLength() > 0) {
                document.insertString(document.getLength(), "\n", null);
            }
            document.insertString(document.getLength(), dateFormat.format(event.getDate()) + " ", null);
            document.insertString(document.getLength(), event.getEventLog(), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane logsManagerScrollPane;
    private javax.swing.JTextPane logsManagerTextPane;
    // End of variables declaration//GEN-END:variables
}
