/*
 * The MIT License
 *
 * Copyright 2012 yogesh.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.elega9t.elixir.gui.form;

/**
 *
 * @author yogesh
 */
public class EditorPanel extends javax.swing.JPanel {

    /**
     * Creates new form EditorPanel
     */
    public EditorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorSplitPane = new javax.swing.JSplitPane();
        topPanel = new javax.swing.JPanel();
        queryEditorScrollPane = new javax.swing.JScrollPane();
        queryEditorPane = new javax.swing.JEditorPane();
        bottomPanel = new javax.swing.JPanel();
        resultsTabbedPane = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        editorSplitPane.setDividerLocation(300);
        editorSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        topPanel.setLayout(new java.awt.BorderLayout());

        queryEditorScrollPane.setViewportView(queryEditorPane);

        topPanel.add(queryEditorScrollPane, java.awt.BorderLayout.CENTER);

        editorSplitPane.setTopComponent(topPanel);

        bottomPanel.setLayout(new java.awt.BorderLayout());
        bottomPanel.add(resultsTabbedPane, java.awt.BorderLayout.CENTER);

        editorSplitPane.setRightComponent(bottomPanel);

        add(editorSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JSplitPane editorSplitPane;
    private javax.swing.JEditorPane queryEditorPane;
    private javax.swing.JScrollPane queryEditorScrollPane;
    private javax.swing.JTabbedPane resultsTabbedPane;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}