/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity.impl;

import com.elega9t.commons.entity.LoadableEntity;

import java.io.IOException;

public class DefaultLoadableEntity extends DefaultEntity implements LoadableEntity {

    protected boolean loaded;

    public DefaultLoadableEntity(String name) {
        super(name);
    }

    @Override
    public void load() throws EntityLoadException{
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void clear() {
        loaded = false;
    }

}
