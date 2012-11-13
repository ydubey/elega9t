/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public class WrapperTestRunnerV2 extends BlockJUnit4ClassRunner {

    private FrameworkField subjectField;

    public WrapperTestRunnerV2(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected void collectInitializationErrors(List<Throwable> errors) {
        validateTestSubject(errors);
        super.collectInitializationErrors(errors);
    }

    protected void validateTestSubject(List<Throwable> errors) {
        final List<FrameworkField> subject = getTestClass().getAnnotatedFields(TestSubject.class);
        if(subject.size() != 1) {
            errors.add(new Exception("There should be only one @TestSubject per test, found " + subject.size()));
        } else {
            this.subjectField = subject.get(0);
        }
    }

    @Override
    protected String testName(FrameworkMethod method) {
        if(WrapperFrameworkMethod.class.isAssignableFrom(method.getClass())) {
            StringBuilder name = new StringBuilder(method.getName());
            name.append("(");
            Class<?>[] parameterTypes = method.getMethod().getParameterTypes();
            for (int index = 0, parameterTypesLength = parameterTypes.length; index < parameterTypesLength; index++) {
                Class<?> parameterType = parameterTypes[index];
                if(index != 0) {
                    name.append(", ");
                }
                name.append(parameterType.getCanonicalName());
            }
            name.append(")");
            return name.toString();
        } else {
            return super.testName(method);
        }
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testMethods = new ArrayList<FrameworkMethod>();
        testMethods.addAll(super.computeTestMethods());
        final List<FrameworkField> mockTargetFields = getTestClass().getAnnotatedFields(MockTarget.class);
        for (FrameworkField mockTargetField : mockTargetFields) {
            for (Annotation annotation : mockTargetField.getAnnotations()) {
                if(MockTarget.class.isAssignableFrom(annotation.getClass())) {
                    for (Class contract : ((MockTarget) annotation).value()) {
                        for (Method method : contract.getDeclaredMethods()) {
                            testMethods.add(new WrapperFrameworkMethod(method, subjectField, mockTargetField));
                        }
                    }
                }
            }
        }
        return testMethods;
    }

    @Override
    protected Object createTest() throws Exception {
        Object test = super.createTest();
        initMocks(test);
        return test;
    }

    public void initMocks(Object test) throws IllegalAccessException {
        List<FrameworkField> mockTargetFields = getTestClass().getAnnotatedFields(MockTarget.class);
        for (FrameworkField mockTargetField : mockTargetFields) {
            mockTargetField.getField().setAccessible(true);
            mockTargetField.getField().set(test, mock(mockTargetField.getType()));
        }
    }

}
