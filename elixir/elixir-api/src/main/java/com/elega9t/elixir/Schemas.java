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

public class Schemas extends DefaultLoadableEntityNode {

    private final Connection connection;

    public Schemas(Connection connection) {
        super("SCHEMAS");
        this.connection = connection;
    }

    @Override
    protected void loadChildren() throws EntityLoadException {
        try {
            final DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getSchemas();
            while (resultSet.next()) {
                addChild(new Schema(resultSet.getString("TABLE_SCHEM")));
            }
            resultSet.close();
            addChild(new Schema("%"));
        } catch (SQLException e) {
            throw new EntityLoadException(e);
        }
    }
}
