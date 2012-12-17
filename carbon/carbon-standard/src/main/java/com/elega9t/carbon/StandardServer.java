/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.carbon;

import com.elega9t.carbon.api.Server;
import com.elega9t.carbon.api.Service;
import com.elega9t.commons.entity.impl.DefaultRunnableUniqueEntity;
import com.elega9t.commons.entity.impl.RunnableEntityException;

import java.util.ArrayList;
import java.util.List;

public class StandardServer extends DefaultRunnableUniqueEntity implements Server {

    private List<Service> services = new ArrayList<Service>();

    public StandardServer(String id, String name) {
        super(id, name);
    }

    @Override
    protected void startEntity() throws RunnableEntityException {
    }

    @Override
    protected void stopEntity() {
    }

    @Override
    public void addService(Service service) {
        addLifecycleListener(service);
        this.services.add(service);
    }

}
