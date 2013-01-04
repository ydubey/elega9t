/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.log;

public class LogEvent {

    private String logType;
    private String log;

    public LogEvent(String logType, String log) {
        this.logType = logType;
        this.log = log;
    }

    public String getLogType() {
        return logType;
    }

    public String getLog() {
        return log;
    }

}
