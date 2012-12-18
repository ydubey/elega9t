/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.commons.entity.impl.EntityLoadException;
import com.elega9t.elixir.Connection;
import com.elega9t.elixir.Driver;
import com.elega9t.elixir.gui.config.ConnectionDetails;
import com.elega9t.elixir.gui.mgr.IconsManager;
import com.elega9t.elixir.mgr.DriverManager;

import javax.swing.*;
import java.sql.SQLException;

public class ConnectionGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity, Connection> {

    private final ConnectionDetails connectionDetails;

    private static Icon connectedDatabaseIcon = IconsManager.getInstance().database().getConnectedDatabaseIcon();
    private static Icon databaseIcon = IconsManager.getInstance().database().getDatabaseIcon();

    public ConnectionGuiEntity(ConnectionDetails connectionDetails) {
        super(connectionDetails.getName(), null);
        this.connectionDetails = connectionDetails;
    }

    @Override
    public Icon getIcon() {
        return isLoaded() ? connectedDatabaseIcon : databaseIcon;
    }

    public ConnectionDetails getConnectionDetails() {
        return connectionDetails;
    }

    public void load() throws EntityLoadException {
        Driver driver = DriverManager.getInstance().getDriver(connectionDetails.getDriver());
        try {
            this.databaseEntity = driver.createConnection(connectionDetails.getUser(), connectionDetails.getPassword());
            super.load();
        } catch (SQLException e) {
            throw new EntityLoadException(e);
        }
    }

}
