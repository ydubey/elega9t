/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.elixir.gui.mgrs.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogManager {

    private static final LogManager INSTANCE = new LogManager();

    private Map<LogType, List<LogListener>> logListeners = new HashMap<LogType, List<LogListener>>();

    private LogManager() {
        logListeners.put(LogType.QUERY, new ArrayList<LogListener>());
    }

    public static LogManager getInstance() {
        return INSTANCE;
    }

    public void addLogListener(LogType logType, LogListener listener) {
        logListeners.get(logType).add(listener);
    }

    public void fireLogEvent(LogEvent event) {
        for (LogListener logListener : logListeners.get(event.getLogType())) {
            logListener.log(event);
        }
    }

}
