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
import javax.swing.table.TableColumn;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RowNumberTableRowHeader extends JTable implements ChangeListener, PropertyChangeListener {

    private JTable main;

    public RowNumberTableRowHeader(JTable table) {
        main = table;
        main.addPropertyChangeListener(this);

        setFocusable( false );
        setAutoCreateColumnsFromModel(false);
        setModel( main.getModel() );
        setSelectionModel( main.getSelectionModel() );

        TableColumn column = new TableColumn();
        column.setHeaderValue(" ");
        addColumn( column );
        column.setCellRenderer(new RowNumberRenderer());
        getColumnModel().getColumn(0).setPreferredWidth(50);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public void addNotify() {
        super.addNotify();
        Component c = getParent();
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
        return Integer.toString(row + 1);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
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

    private static class RowNumberRenderer extends DefaultTableCellRenderer {
        public RowNumberRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

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
            setText((value == null) ? "" : value.toString());
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            return this;
        }

    }

}
