/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;

public class WrapperFrameworkMethod extends FrameworkMethod {

    private final FrameworkField field;

    public WrapperFrameworkMethod(Method method, FrameworkField field) {
        super(method);
        this.field = field;
    }

    public String createTestName() {
        StringBuilder name = new StringBuilder(getMethod().getName());
        name.append("(");
        Class<?>[] parameterTypes = getMethod().getParameterTypes();
        for (int index = 0, parameterTypesLength = parameterTypes.length; index < parameterTypesLength; index++) {
            Class<?> parameterType = parameterTypes[index];
            if(index != 0) {
                name.append(", ");
            }
            name.append(parameterType.getCanonicalName());
        }
        name.append(")");
        return name.toString();
    }

    public FrameworkField getField() {
        return field;
    }

}
