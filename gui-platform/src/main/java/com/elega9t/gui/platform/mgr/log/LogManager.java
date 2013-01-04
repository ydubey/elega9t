/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogManager {

    public static final String ALL_LOG_LISTENER = "**ALL_LOGS**";

    private static final LogManager INSTANCE = new LogManager();

    private Map<String, List<LogListener>> logListeners = new HashMap<String, List<LogListener>>();

    private LogManager() {
    }

    public static LogManager getInstance() {
        return INSTANCE;
    }

    public void addLogListener(String logType, LogListener listener) {
        List<LogListener> listeners = logListeners.get(logType);
        if(listeners == null) {
            synchronized (LogManager.class) {
                listeners = logListeners.get(logType);
                if(listeners == null) {
                    listeners = new ArrayList<LogListener>();
                    logListeners.put(logType, listeners);
                }
            }
        }
        listeners.add(listener);
    }

    public void fireLogEvent(LogEvent event) {
        for (LogListener logListener : logListeners.get(event.getLogType())) {
            logListener.log(event);
        }
        for (LogListener logListener : logListeners.get(ALL_LOG_LISTENER)) {
            logListener.log(event);
        }
    }

}
