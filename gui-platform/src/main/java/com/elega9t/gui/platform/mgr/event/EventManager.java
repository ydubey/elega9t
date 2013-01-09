/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.gui.platform.mgr.event;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventManager {

    public static final String ALL_EVENTS = "**ALL_LOGS**";

    private static final EventManager INSTANCE = new EventManager();

    private Map<String, Queue<EventListener>> logListeners = new ConcurrentHashMap<String, Queue<EventListener>>();
    private Queue<Event> eventLog = new ConcurrentLinkedQueue<Event>();

    private EventManager() {
    }

    public static EventManager getInstance() {
        return INSTANCE;
    }

    private Queue<EventListener> getLogListeners(String logType) {
        Queue<EventListener> listeners = logListeners.get(logType);
        if(listeners == null) {
            synchronized (EventManager.class) {
                listeners = logListeners.get(logType);
                if(listeners == null) {
                    listeners = new ConcurrentLinkedQueue<EventListener>();
                    logListeners.put(logType, listeners);
                }
            }
        }
        return listeners;
    }

    public void addLogListener(String eventType, EventListener listener) {
        Queue<EventListener> listeners = getLogListeners(eventType);
        listeners.add(listener);
        for (Event event : eventLog) {
            if(ALL_EVENTS.equals(eventType) || event.getEventType().equals(eventType)) {
                listener.pastEvent(event);
            }
        }
    }

    public void fireLogEvent(Event event) {
        for (EventListener eventListener : getLogListeners(event.getEventType())) {
            eventListener.eventOccurred(event);
        }
        for (EventListener eventListener : getLogListeners(ALL_EVENTS)) {
            eventListener.eventOccurred(event);
        }
        eventLog.add(event);
    }

}
