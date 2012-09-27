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
