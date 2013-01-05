/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.event;

import java.util.Date;

public class Event {

    private String eventType;
    private Date date;
    private String eventLog;

    public Event(String eventType, Date date, String eventLog) {
        this.eventType = eventType;
        this.date = date;
        this.eventLog = eventLog;
    }

    public String getEventType() {
        return eventType;
    }

    public Date getDate() {
        return date;
    }

    public String getEventLog() {
        return eventLog;
    }

}
