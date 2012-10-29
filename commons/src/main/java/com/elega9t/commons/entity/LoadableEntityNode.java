/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public interface LoadableEntityNode extends LoadableEntity {

    void loadAll() throws EntityLoadException;

    int getChildCount();

    void addChild(LoadableEntityNode node);

    boolean removeChild(LoadableEntityNode node);

    void clear();

}
