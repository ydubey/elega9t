package com.elega9t.commons.entity;

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
