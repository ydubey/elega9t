/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.Connection;
import com.elega9t.elixir.DatabaseEntity;
import com.elega9t.elixir.Driver;
import com.elega9t.elixir.gui.config.ConnectionDetails;
import com.elega9t.elixir.mgr.DriverManager;

import java.sql.SQLException;

public class ConnectionGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity> {

    private final ConnectionDetails connectionDetails;

    private Connection connection;

    public ConnectionGuiEntity(ConnectionDetails connectionDetails) {
        super(connectionDetails.getName(), new javax.swing.ImageIcon(ConnectionGuiEntity.class.getResource("/com/elega9t/elixir/gui/icons/database.png")));
        this.connectionDetails = connectionDetails;
    }

    public ConnectionDetails getConnectionDetails() {
        return connectionDetails;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    public void connect() throws SQLException {
        Driver driver = DriverManager.getInstance().getDriver(connectionDetails.getDriver());
        connection = driver.createConnection(connectionDetails.getUser(), connectionDetails.getPassword());
        int childCount = connection.getChildCount();
        for(int index=0; index<childCount; index++) {
            DatabaseEntity entity = connection.getChildAt(index);
            addChild(new DatabaseGuiEntity(entity.getName(), null));
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

}
