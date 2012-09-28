/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.carbon.api;

import com.elega9t.commons.entity.LifecycleListener;
import com.elega9t.commons.entity.RunnableUniqueEntity;

public interface Host extends LifecycleListener, RunnableUniqueEntity {

    void addModule(Module module);

}
