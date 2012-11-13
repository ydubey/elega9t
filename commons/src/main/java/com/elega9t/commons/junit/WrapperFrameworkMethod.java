/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;

import static org.mockito.Mockito.mock;

public class WrapperFrameworkMethod extends FrameworkMethod {

    private final FrameworkField mockTargetFrameworkField;

    public WrapperFrameworkMethod(Method method, FrameworkField mockTargetFrameworkField) {
        super(method);
        this.mockTargetFrameworkField = mockTargetFrameworkField;
        mockTargetFrameworkField.getField().setAccessible(true);
    }

    @Override
    public Object invokeExplosively(Object target, Object... params) throws Throwable {
        System.out.println(mockTargetFrameworkField.get(target));
        return null;
    }

}
