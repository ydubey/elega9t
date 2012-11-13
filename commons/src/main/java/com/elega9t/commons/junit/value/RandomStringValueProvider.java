/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit.value;

import java.util.UUID;

public class RandomStringValueProvider extends RandomValueProvider {

    protected RandomStringValueProvider() {
        super(String.class);
    }

    @Override
    public Object create() {
        return UUID.randomUUID().toString();
    }

}
