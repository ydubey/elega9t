package com.elega9t.commons.entity;

public interface LifecycleListener {

    void lifecycleEventOccurred(RunnableUniqueEntity.STATUS event, DefaultRunnableUniqueEntity defaultRunnableUniqueEntity) throws RunnableEntityException;

}
