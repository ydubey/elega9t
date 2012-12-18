/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.evnt;

import com.elega9t.elixir.gui.entity.ConnectionGuiEntity;

public interface DatabaseConnectionEventListener {

    void connectionAdded(ConnectionGuiEntity connection);

    void connectionRemoved(ConnectionGuiEntity connection);

    void connectionStateChanged(ConnectionGuiEntity connection);

}
