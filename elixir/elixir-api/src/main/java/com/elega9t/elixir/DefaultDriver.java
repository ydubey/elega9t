/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DefaultDriver implements Driver {

    @Override
    public List<String> supportedVersions() {
        return null;
    }

    @Override
    public String databaseWebsite() {
        return null;
    }

    @Override
    public Map<String, List<String>> getDrivers() {
        return null;
    }

    @Override
    public void load() {
    }

    @Override
    public boolean isLoaded() {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public Connection createConnection(String userName, String password) throws SQLException {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}
