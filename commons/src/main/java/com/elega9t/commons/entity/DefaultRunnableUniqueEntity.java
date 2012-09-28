/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DefaultRunnableUniqueEntity extends DefaultUniqueEntity implements RunnableUniqueEntity {

    private Logger logger = Logger.getLogger(getClass().getName());

    private AtomicReference<STATUS> status;

    private List<LifecycleListener> lifecycleListeners = new ArrayList<LifecycleListener>();

    public DefaultRunnableUniqueEntity(String id, String name) {
        super(id, name);
        status = new AtomicReference<STATUS>(STATUS.STOPPED);
    }

    @Override
    public STATUS getStatus() {
        return status.get();
    }

    private void updateStatus(STATUS status) throws RunnableEntityException {
        this.status.set(status);
        fireLifecycleEvent(status);
    }

    public void fireLifecycleEvent(STATUS status) throws RunnableEntityException {
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.lifecycleEventOccurred(status, this);
        }
    }

    @Override
    public final void start() throws RunnableEntityException {
        if (status.get() != STATUS.STOPPED) {
            throw new RunnableEntityException(getUniqueName() + " is still running.");
        } else {
            logger.log(Level.FINE, "Starting " + getUniqueName() + "...");
            updateStatus(STATUS.STARTING);
            startEntity();
            logger.log(Level.FINE, getUniqueName() + " started.");
            updateStatus(STATUS.RUNNING);
        }
    }

    protected abstract void startEntity() throws RunnableEntityException;

    @Override
    public void stop() throws RunnableEntityException {
        if (status.get() == STATUS.STOPPING || status.get() == STATUS.STOPPED) {
            throw new RunnableEntityException(getUniqueName() + " already stopping or stopped.");
        } else {
            updateStatus(STATUS.STOPPING);
            stopEntity();
            updateStatus(STATUS.STOPPED);
        }
    }

    protected abstract void stopEntity();

    @Override
    public void addLifecycleListener(LifecycleListener lifecycleListener) {
        lifecycleListeners.add(lifecycleListener);
    }

    @Override
    public boolean removeLifecycleListener(LifecycleListener lifecycleListener) {
        return lifecycleListeners.remove(lifecycleListener);
    }

    @Override
    public void removeAllLifecycleListeners() {
        lifecycleListeners.clear();
    }

}
