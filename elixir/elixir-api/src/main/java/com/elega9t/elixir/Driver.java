/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.impl.DefaultLoadableEntity;
import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.elixir.binding.plugin.DriverDefinition;
import com.elega9t.elixir.binding.plugin.DriverVendor;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Driver extends DefaultLoadableEntity {

    private DriverDefinition driverDefinition;

    private Map<String, Boolean> vendors = new HashMap<String, Boolean>();

    public Driver(DriverDefinition driverDefinition) {
        super(driverDefinition.getDatabase());
        this.driverDefinition = driverDefinition;
    }

    @Override
    public void load() throws EntityLoadException {
        if(!loaded) {
            for (DriverVendor driverVendor : driverDefinition.getDriverVendors().getDriverVendor()) {
                try {
                    for (String clazz : driverVendor.getDriver().getClazz()) {
                        Class.forName(clazz);
                    }
                    vendors.put(driverVendor.getName(), true);
                } catch(Exception e) {
                    vendors.put(driverVendor.getName(), false);
                }
            }
        }
        loaded = true;
    }

    @Override
    public void clear() {
        loaded = false;
        vendors.clear();
    }

    public Connection createConnection(String userName, String password) throws SQLException {
        String _url = driverDefinition.getJdbcUrl();
        _url = _url.replace("[host]", "localhost");
        _url = _url.replace("[port]", "3306");
        _url = _url.replace("[db]", "mysql");

        final Connection connection = new Connection(DriverManager.getConnection(_url, userName, password), "mysql");
        try {
            TableTypes tableTypes = new TableTypes(connection.getCatalog(), "%", connection);
            int tableTypesCount = tableTypes.getChildCount();
            for(int index=0; index< tableTypesCount; index++) {
                connection.addChild(tableTypes.getChildAt(index));
            }
            connection.load();
        } catch (EntityLoadException e) {
            throw new SQLException(e);
        }
        return connection;
    }

    public String databaseWebsite() {
        return driverDefinition.getWebsite();
    }

}
