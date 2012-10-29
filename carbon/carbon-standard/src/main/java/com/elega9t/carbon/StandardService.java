/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.carbon;

import com.elega9t.carbon.api.Connector;
import com.elega9t.carbon.api.Host;
import com.elega9t.carbon.api.Service;
import com.elega9t.commons.entity.LifecycleListenerRunnableEntity;
import com.elega9t.commons.entity.RunnableEntityException;

import java.util.ArrayList;
import java.util.List;

public class StandardService extends LifecycleListenerRunnableEntity implements Service {

    private List<Connector> connectors = new ArrayList<Connector>();
    private List<Host> hosts = new ArrayList<Host>();

    public StandardService(String id, String name) {
        super(id, name);
    }

    @Override
    protected void startEntity() throws RunnableEntityException {
    }

    @Override
    protected void stopEntity() {
    }

    @Override
    public void addConnector(Connector connector) {
        addLifecycleListener(connector);
        this.connectors.add(connector);
    }

    @Override
    public void addHost(Host host) {
        addLifecycleListener(host);
        this.hosts.add(host);
    }

}
