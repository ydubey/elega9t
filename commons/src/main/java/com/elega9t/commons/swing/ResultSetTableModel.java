/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.swing;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ResultSetTableModel extends DefaultTableModel {

    private ResultSet resultSet;

    public void setResultSet(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        ResultSetMetaData metaData = resultSet.getMetaData();
        final int columnCount = metaData.getColumnCount();
        Object[] columnNames = new String[columnCount];
        for(int index=1; index<=columnCount; index++) {
            columnNames[index - 1] = metaData.getColumnLabel(index);
        }
        super.setColumnIdentifiers(columnNames);
    }

    @Override
    public int getRowCount() {
        int rowCount = 0;
        if(resultSet != null) {
            try {
                resultSet.last();
                rowCount = resultSet.getRow();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowCount;
    }

    @Override
    public Object getValueAt(int row, int column) {
        try {
            resultSet.absolute(row + 1);
            return resultSet.getString(column + 1);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

}
