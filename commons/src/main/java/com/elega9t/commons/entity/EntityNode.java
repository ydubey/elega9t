/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public interface EntityNode<T extends EntityNode> extends Entity {

    int getChildCount();

    T getChildAt(int index);

    void addChild(T node);

    boolean removeChild(T node);

    void clear();

}
