/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.event;

import java.util.Date;

public class LogEvent {

    private String logType;
    private Date date;
    private String log;

    public LogEvent(String logType, Date date, String log) {
        this.logType = logType;
        this.date = date;
        this.log = log;
    }

    public String getLogType() {
        return logType;
    }

    public Date getDate() {
        return date;
    }

    public String getLog() {
        return log;
    }

}
