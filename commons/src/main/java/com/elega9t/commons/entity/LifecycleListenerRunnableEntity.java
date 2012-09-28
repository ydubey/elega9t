/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public abstract class LifecycleListenerRunnableEntity extends DefaultRunnableUniqueEntity implements LifecycleListener {

    public LifecycleListenerRunnableEntity(String id, String name) {
        super(id, name);
    }

    @Override
    public void lifecycleEventOccurred(STATUS event, DefaultRunnableUniqueEntity defaultRunnableUniqueEntity) throws RunnableEntityException {
        switch (event) {
            case STARTING:
                start();
                break;

            case STOPPING:
                stop();
                break;
        }
    }

}
