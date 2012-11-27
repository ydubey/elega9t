/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mysql;

import com.elega9t.elixir.DatabaseConnection;
import com.elega9t.elixir.DatabaseDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class MySqlDatabaseDriver implements DatabaseDriver {

    private Map<String, List<String>> drivers = new HashMap<String, List<String>>();
    private boolean available = false;

    public MySqlDatabaseDriver() {
        drivers.put("MM MySql", Arrays.asList("org.gjt.mm.mysql.Driver"));
        drivers.put("MySql", Arrays.asList("com.mysql.jdbc.Driver"));
    }

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
    public Map<String, List<String>> getDrivers() {
        return Collections.unmodifiableMap(drivers);
    }

    @Override
    public void loadDrivers() {
        Map<String, List<String>> drivers = getDrivers();
        for (String driverName : drivers.keySet()) {
            List<String> driverClasses = drivers.get(driverName);
            try {
                for (String driverClass : driverClasses) {
                    Class.forName(driverClass);
                }
                available = true;
                break;
            } catch (ClassNotFoundException e) {
                available = false;
            }
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public DatabaseConnection getConnectionSkeleton(Connection connection, String databaseName) {
        return new DatabaseConnection(connection, databaseName);
    }

    @Override
    public DatabaseConnection createConnection(String userName, String password) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mysql";
        return new DatabaseConnection(DriverManager.getConnection(url, userName, password), "mysql");
    }

}
