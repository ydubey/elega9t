/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public interface EntityNode extends Entity {

    int getChildCount();

    void addChild(LoadableEntityNode node);

    boolean removeChild(LoadableEntityNode node);

    void clear();

}
