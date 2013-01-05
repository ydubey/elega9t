/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    public static final String ALL_LOG_LISTENER = "**ALL_LOGS**";

    private static final EventManager INSTANCE = new EventManager();

    private Map<String, List<EventListener>> logListeners = new HashMap<String, List<EventListener>>();

    private EventManager() {
    }

    public static EventManager getInstance() {
        return INSTANCE;
    }

    private List<EventListener> getLogListeners(String logType) {
        List<EventListener> listeners = logListeners.get(logType);
        if(listeners == null) {
            synchronized (EventManager.class) {
                listeners = logListeners.get(logType);
                if(listeners == null) {
                    listeners = new ArrayList<EventListener>();
                    logListeners.put(logType, listeners);
                }
            }
        }
        return listeners;
    }

    public void addLogListener(String logType, EventListener listener) {
        List<EventListener> listeners = getLogListeners(logType);
        listeners.add(listener);
    }

    public void fireLogEvent(Event event) {
        for (EventListener eventListener : getLogListeners(event.getLogType())) {
            eventListener.log(event);
        }
        for (EventListener eventListener : getLogListeners(ALL_LOG_LISTENER)) {
            eventListener.log(event);
        }
    }

}
