/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;
import java.util.Set;

public class CheckBoxTableRowHeader extends JTable implements ChangeListener, PropertyChangeListener {

    private JTable main;

    public CheckBoxTableRowHeader(JTable table) {
        main = table;
        main.addPropertyChangeListener(this);

        setFocusable(false);
        setAutoCreateColumnsFromModel(false);
        setModel(new CheckBoxTableRowHeaderModel(main.getModel()));
        setSelectionModel(main.getSelectionModel());

        TableColumn column = new TableColumn();
        column.setHeaderValue(" ");
        column.setCellRenderer(new CheckBoxRowNumberRenderer());
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
    public int getRowHeight(int row) {
        return main.getRowHeight(row);
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
            setModel(new CheckBoxTableRowHeaderModel(main.getModel()));
        }
    }

    private static class CheckBoxRowNumberRenderer extends JCheckBox implements TableCellRenderer {

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

    public static class CheckBoxTableRowHeaderModel extends AbstractTableModel {

        private TableModel mainTableModel;
        private Set<Integer> selectedRows = new LinkedHashSet<Integer>();

        private CheckBoxTableRowHeaderModel(TableModel mainTableModel) {
            this.mainTableModel = mainTableModel;
        }

        @Override
        public int getRowCount() {
            return mainTableModel.getRowCount();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return selectedRows.contains(rowIndex);
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            boolean booleanValue = Boolean.valueOf(String.valueOf(value));
            if(booleanValue) {
                selectedRows.add(rowIndex);
            } else {
                selectedRows.remove(rowIndex);
            }
        }

        public Set<Integer> getSelectedRows() {
            return selectedRows;
        }

    }

}
