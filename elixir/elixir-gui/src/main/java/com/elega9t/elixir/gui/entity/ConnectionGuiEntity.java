/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.entity;

import com.elega9t.elixir.gui.config.ConnectionDetails;

public class ConnectionGuiEntity extends DatabaseGuiEntity<DatabaseGuiEntity> {

    public ConnectionGuiEntity(ConnectionDetails connectionDetails) {
        super(connectionDetails.getName(), new javax.swing.ImageIcon(ConnectionGuiEntity.class.getResource("/com/elega9t/elixir/gui/icons/database.png")));
    }

}
