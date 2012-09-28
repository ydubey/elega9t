/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.carbon;

import com.elega9t.carbon.api.Connector;
import com.elega9t.commons.entity.LifecycleListenerRunnableEntity;
import com.elega9t.commons.entity.RunnableEntityException;

public class StandardConnector extends LifecycleListenerRunnableEntity implements Connector {

    public StandardConnector(String id, String name) {
        super(id, name);
    }

    @Override
    protected void startEntity() throws RunnableEntityException {
    }

    @Override
    protected void stopEntity() {
    }

}
