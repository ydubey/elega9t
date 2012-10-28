/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.entity;

public class EntityLoadException extends Exception {

    public EntityLoadException() {
    }

    public EntityLoadException(String s) {
        super(s);
    }

    public EntityLoadException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EntityLoadException(Throwable throwable) {
        super(throwable);
    }

}
