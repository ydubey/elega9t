/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.DefaultLoadableEntityNode;
import com.elega9t.commons.entity.EntityLoadException;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tables extends DefaultLoadableEntityNode<Table> {

    private final Schema schema;

    public Tables(Schema schema) {
        super("TABLES");
        this.schema = schema;
    }

    @Override
    public void load() throws EntityLoadException {
        clear();
        try {
            final DatabaseMetaData metaData = getConnection().getMetaData();
            final ResultSet resultSet = metaData.getTables(null, schema.getName(), null, null);
            while (resultSet.next()) {
                addChild(new Table(this, resultSet.getString("TABLE_NAME")));
            }
        } catch (SQLException e) {
            throw new EntityLoadException(e);
        }
    }

    public Connection getConnection() {
        return schema.getConnection();
    }

}
