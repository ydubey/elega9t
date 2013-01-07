/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.event;

import java.util.Date;

public class Event {

    private Date date;
    private Level level;
    private Object source;
    private String eventType;
    private String eventLog;

    public Event(Level level, Object source, String eventType, String eventLog) {
        this(new Date(), level, source, eventType, eventLog);
    }

    public Event(Date date, Level level, Object source, String eventType, String eventLog) {
        this.level = level;
        this.source = source;
        this.eventType = eventType;
        this.date = date;
        this.eventLog = eventLog;
    }

    public Date getDate() {
        return date;
    }

    public Level getLevel() {
        return level;
    }

    public Object getSource() {
        return source;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEventLog() {
        return eventLog;
    }

}
