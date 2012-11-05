/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface DatabaseDriver {

    String databaseName();

    List<String> supportedVersions();

    String databaseWebsite();

    Map<String, List<String>> getDrivers();

    DatabaseConnection getConnectionSkeleton(Connection connection, String databaseName);

}
