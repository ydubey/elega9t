/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.mgr.evnt;

import com.elega9t.commons.entity.impl.EntityLoadException;

public class EventManager {

    private static final EventManager INSTANCE = new EventManager();
    private int eventCount = 0;

    public static EventManager getInstance() {
        return INSTANCE;
    }

    public void error(EntityLoadException e) {
        eventCount++;
    }

    public int getEventCount() {
        return eventCount;
    }

}
