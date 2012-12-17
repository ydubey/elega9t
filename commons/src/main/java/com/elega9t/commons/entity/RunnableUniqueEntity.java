/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import com.elega9t.commons.entity.impl.RunnableEntityException;

public interface RunnableUniqueEntity extends UniqueEntity {

    public static enum STATUS {
        STARTING,
        RUNNING,
        STOPPING,
        STOPPED
    }

    STATUS getStatus();

    void start() throws RunnableEntityException;

    void stop() throws RunnableEntityException;

    void addLifecycleListener(LifecycleListener lifecycleListener);

    boolean removeLifecycleListener(LifecycleListener lifecycleListener);

    void removeAllLifecycleListeners();

}
