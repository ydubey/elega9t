/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.impl.EntityLoadException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Columns extends DatabaseEntity<Column> {

    private final String catalogueName;
    private final String schemaName;
    private final String tableName;

    public Columns(String catalogueName, String schemaName, String tableName, Connection connection) throws EntityLoadException {
        super("COLUMNS", connection);
        this.catalogueName = catalogueName;
        this.schemaName = schemaName;
        this.tableName = tableName;
    }

    @Override
    protected void loadChildren() throws EntityLoadException {
        super.loadChildren();
        try {
            final DatabaseMetaData metaData = getConnection().getMetaData();
            final ResultSet resultSet = metaData.getColumns(catalogueName, schemaName, tableName, null);
            while (resultSet.next()) {
                addChild(new Column(catalogueName, schemaName, tableName,
                        resultSet.getString("COLUMN_NAME"),
                        resultSet.getInt("DATA_TYPE"),
                        resultSet.getString("TYPE_NAME"),
                        resultSet.getInt("COLUMN_SIZE"),
                        resultSet.getInt("DECIMAL_DIGITS"),
                        resultSet.getInt("NUM_PREC_RADIX"),
                        resultSet.getInt("NULLABLE"),
                        resultSet.getString("COLUMN_DEF"),
                        resultSet.getString("IS_AUTOINCREMENT"),
                        getConnection()));
            }
        } catch (SQLException e) {
            throw new EntityLoadException(e);
        }
    }

    @Override
    public <R> R visit(DatabaseEntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
