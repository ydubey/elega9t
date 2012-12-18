/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.impl.EntityLoadException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Indexes extends DatabaseEntity<Index> {

    private final String catalogueName;
    private final String schemaName;
    private final String tableName;

    public Indexes(String catalogueName, String schemaName, Table table, Connection connection) throws EntityLoadException {
        super("INDEXES", table, connection);
        this.catalogueName = catalogueName;
        this.schemaName = schemaName;
        this.tableName = table.getName();
    }

    @Override
    public void load() throws EntityLoadException {
        try {
            final DatabaseMetaData metaData = getConnection().getMetaData();
            final ResultSet resultSet = metaData.getIndexInfo(catalogueName, schemaName, tableName, false, true);
            while (resultSet.next()) {
                addChild(new Index(catalogueName, schemaName, tableName, this,
                        resultSet.getString("COLUMN_NAME"),
                        getConnection()));
            }
        } catch (SQLException e) {
            throw new EntityLoadException(e);
        } finally {
            loaded = true;
        }
    }

    @Override
    public <R> R visit(DatabaseEntityVisitor<R> visitor) {
        return visitor.visit(this);
    }

}
