/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.EntityLoadException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableTypes extends DatabaseEntity<TableType> {

    private final String catalogueName;
    private final String schemaName;

    public TableTypes(String catalogueName, String schemaName, Connection connection) throws EntityLoadException {
        super("TABLES", connection);
        this.catalogueName = catalogueName;
        this.schemaName = schemaName;
    }

    @Override
    protected void loadChildren() throws EntityLoadException {
        super.loadChildren();
        try {
            final DatabaseMetaData metaData = getConnection().getMetaData();
            final ResultSet resultSet = metaData.getTableTypes();
            while (resultSet.next()) {
                addChild(new TableType(catalogueName, schemaName, resultSet.getString("TABLE_TYPE"), getConnection()));
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
