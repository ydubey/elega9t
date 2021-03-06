/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

import com.elega9t.commons.entity.impl.EntityLoadException;

import java.io.IOException;

public interface LoadableEntity extends Entity {

    void load() throws EntityLoadException;

    boolean isLoaded();

    void clear();

}
