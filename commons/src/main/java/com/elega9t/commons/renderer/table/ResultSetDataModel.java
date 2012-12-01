/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.renderer.table;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetDataModel implements DataModel<ResultSet> {

    private final List<List<String>> data;
    private final List<String> columnNames;

    public ResultSetDataModel(ResultSet resultSet) throws SQLException {
        final ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        data = new ArrayList<List<String>>();
        columnNames = new ArrayList<String>();
        for(int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnLabel(column));
        }
        while(resultSet.next()) {
            List<String> rowData = new ArrayList<String>();
            for(int column = 1; column <= columnCount; column++) {
                final Object value = resultSet.getObject(column);
                rowData.add(String.valueOf(value));
            }
            data.add(rowData);
        }
    }

    @Override
    public int rowCount() {
        return data.size();
    }

    @Override
    public int columnCount() {
        return columnNames.size();
    }

    @Override
    public String value(int row, int column) {
        return data.get(row).get(column);
    }

    @Override
    public String columnName(int column) {
        return columnNames.get(column);
    }

}
