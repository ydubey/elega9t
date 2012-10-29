/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mysql;

import com.elega9t.elixir.DatabaseConnection;
import com.elega9t.elixir.DatabaseDriver;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

public class MySqlDatabaseDriver implements DatabaseDriver {

    @Override
    public String databaseName() {
        return "MySQL";
    }

    @Override
    public List<String> supportedVersions() {
        return Collections.<String>emptyList();
    }

    @Override
    public String databaseWebsite() {
        return "http://www.mysql.com";
    }

    @Override
    public DatabaseConnection getConnectionSkeleton(Connection connection, String databaseName) {
        return new DatabaseConnection(connection, databaseName);
    }

}
