/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.impl.EntityLoadException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableType extends DatabaseEntity<Table> {

    private String catalogueName;
    private String schemaName;

    public TableType(String catalogueName, Schema schema, String name, Connection connection) throws EntityLoadException {
        super(name, schema, connection);
        this.catalogueName = catalogueName;
        this.schemaName = schema.getName();
    }

    @Override
    public void load() throws EntityLoadException {
        try {
            final DatabaseMetaData metaData = getConnection().getMetaData();
            final ResultSet resultSet = metaData.getTables(catalogueName, schemaName, null, new String[] { getName() });
            while (resultSet.next()) {
                addChild(new Table(catalogueName, schemaName, this, resultSet.getString("TABLE_NAME"), getConnection()));
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
