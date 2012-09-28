/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.carbon;

import com.elega9t.carbon.api.Host;
import com.elega9t.carbon.api.Module;
import com.elega9t.commons.entity.LifecycleListenerRunnableEntity;
import com.elega9t.commons.entity.RunnableEntityException;

import java.util.ArrayList;
import java.util.List;

public class StandardHost extends LifecycleListenerRunnableEntity implements Host {

    private List<Module> modules = new ArrayList<Module>();

    public StandardHost(String id, String name) {
        super(id, name);
    }

    @Override
    protected void startEntity() throws RunnableEntityException {
    }

    @Override
    protected void stopEntity() {
    }

    @Override
    public void addModule(Module connector) {
        addLifecycleListener(connector);
        this.modules.add(connector);
    }

}
