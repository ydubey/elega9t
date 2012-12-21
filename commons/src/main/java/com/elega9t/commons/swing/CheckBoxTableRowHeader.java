/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CheckBoxTableRowHeader extends JTable implements ChangeListener, PropertyChangeListener {

    private JTable main;

    public CheckBoxTableRowHeader(JTable table) {
        main = table;
        main.addPropertyChangeListener(this);

        setFocusable(false);
        setAutoCreateColumnsFromModel(false);
        setModel(main.getModel());
        setSelectionModel(main.getSelectionModel());

        TableColumn column = new TableColumn();
        column.setHeaderValue(" ");
        column.setCellRenderer(new RowNumberRenderer());
        column.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        addColumn(column);
        getColumnModel().getColumn(0).setPreferredWidth(30);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public void addNotify() {
        super.addNotify();
        Component c = getParent();
        //  Keep scrolling of the row table in sync with the main table.
        if (c instanceof JViewport) {
            JViewport viewport = (JViewport) c;
            viewport.addChangeListener(this);
        }
    }

    @Override
    public int getRowCount() {
        return main.getRowCount();
    }

    @Override
    public int getRowHeight(int row) {
        return main.getRowHeight(row);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return false;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    public void stateChanged(ChangeEvent e) {
        JViewport viewport = (JViewport) e.getSource();
        JScrollPane scrollPane = (JScrollPane) viewport.getParent();
        scrollPane.getVerticalScrollBar().setValue(viewport.getViewPosition().y);
    }

    public void propertyChange(PropertyChangeEvent e) {
        if ("selectionModel".equals(e.getPropertyName())) {
            setSelectionModel(main.getSelectionModel());
        }
        if ("model".equals(e.getPropertyName())) {
            setModel(main.getModel());
        }
    }

    private static class RowNumberRenderer extends JCheckBox implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(header.getBackground());
                    setFont(header.getFont());
                }
            }
            if (isSelected) {
                setFont( getFont().deriveFont(Font.BOLD) );
            }
            setSelected(Boolean.valueOf(String.valueOf(value)));
            setText("");
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(CENTER);
            return this;
        }

    }

}
