/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir;

import com.elega9t.commons.entity.DefaultLoadableEntityNode;

import java.sql.Connection;

public class DatabaseConnection extends DefaultLoadableEntityNode {

    private final Connection connection;

    public DatabaseConnection(Connection connection, String name) {
        super(name);
        this.connection = connection;
    }

}
