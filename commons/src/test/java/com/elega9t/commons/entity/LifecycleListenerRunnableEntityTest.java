package com.elega9t.commons.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LifecycleListenerRunnableEntityTest {

    private LifecycleListenerRunnableEntity test = new LifecycleListenerRunnableEntity("id", "name") {
        @Override
        protected void startEntity() throws RunnableEntityException {
        }
        @Override
        protected void stopEntity() {
        }
    };

    @Test
    public void startsEntityWhenStartingEventRecieved() throws Exception {
        assertEquals(RunnableUniqueEntity.STATUS.STOPPED, test.getStatus());
        test.lifecycleEventOccurred(RunnableUniqueEntity.STATUS.STARTING, null);

        assertEquals(RunnableUniqueEntity.STATUS.RUNNING, test.getStatus());
    }

    @Test
    public void stopsEntityWhenStoppingEventRecieved() throws Exception {
        assertEquals(RunnableUniqueEntity.STATUS.STOPPED, test.getStatus());
        test.start();

        assertEquals(RunnableUniqueEntity.STATUS.RUNNING, test.getStatus());
        test.lifecycleEventOccurred(RunnableUniqueEntity.STATUS.STOPPING, null);

        assertEquals(RunnableUniqueEntity.STATUS.STOPPED, test.getStatus());
    }

}
