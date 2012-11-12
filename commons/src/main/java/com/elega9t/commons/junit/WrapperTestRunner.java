/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import com.elega9t.commons.util.ReflectionUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WrapperTestRunner extends Runner {

    private final Class testClass;
    private final Description description;

    private final Object test;
    private final Map<Class, Field> mockTargetFields;
    private Field subjectField;

    private Map<String,Method> testMethods = new HashMap<String, Method>();

    public WrapperTestRunner(Class testClass) throws IllegalAccessException, InstantiationException {
        this.testClass = testClass;
        test = testClass.newInstance();
        mockTargetFields = initMockTargets();
        Map<Subject, Field> subjectFieldMap = ReflectionUtilities.getDeclaredFieldsWithAnnotation(Subject.class, testClass);
        if(subjectFieldMap.size() != 1) {
            throw new IllegalArgumentException("There should be 1 test subject per test");
        }
        subjectField = subjectFieldMap.entrySet().iterator().next().getValue();
        subjectField.setAccessible(true);
        this.description = createDescription();
    }

    private Description createDescription() {
        Description suiteDescription = Description.createSuiteDescription(testClass);
        for (Class contractClass : mockTargetFields.keySet()) {
            addTestDescriptionsForContact(suiteDescription, contractClass);
        }
        return suiteDescription;
    }

    private void addTestDescriptionsForContact(Description suiteDescription, Class contractClass) {
        for (Method method : contractClass.getMethods()) {
            String testName = createTestName(method);
            suiteDescription.addChild(Description.createTestDescription(testClass, testName));
            testMethods.put(testName, method);
        }
    }

    private String createTestName(Method method) {
        final String argName = "arg";
        StringBuilder name = new StringBuilder(method.getName());
        name.append("(");
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int index = 0, parameterTypesLength = parameterTypes.length; index < parameterTypesLength; index++) {
            Class<?> parameterType = parameterTypes[index];
            if(index != 0) {
                name.append(", ");
            }
            name.append(parameterType.getCanonicalName());
            name.append(" ");
            name.append(argName + index);
        }
        name.append(")");
        return name.toString();
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public void run(RunNotifier notifier) {
        Result result = new Result();
        notifier.addListener(result.createListener());
        notifier.fireTestRunStarted(description);
        try {
            for (Description testDescription : description.getChildren()) {
                notifier.fireTestStarted(testDescription);
                initMockTargets();
                invokeBefore();
                Object subject = getSubject();
                String methodName = testDescription.getMethodName();
                Method method = testMethods.get(methodName);
                if(method.getParameterTypes().length == 0) {
                    final AtomicBoolean invokedInMock = new AtomicBoolean(false);
                    Field mockField = mockTargetFields.get(method.getDeclaringClass());
                    Object mock = mockField.get(test);
                    when(method.invoke(mock)).then(new Answer<Object>() {
                        @Override
                        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                            invokedInMock.set(true);
                            return null;
                        }
                    });
                    method.invoke(subject);
                    try {
                        assertTrue(methodName + " not invoked in the target mock", invokedInMock.get());
                        notifier.fireTestFinished(testDescription);
                    } catch(AssertionError e) {
                        notifier.fireTestAssumptionFailed(new Failure(description, e));
                    }
                } else {
                    notifier.fireTestIgnored(testDescription);
                }
            }
        } catch(Throwable e) {
            notifier.fireTestFailure(new Failure(description, e));
        }
        notifier.fireTestRunFinished(result);
    }

    private void invokeBefore() throws InvocationTargetException, IllegalAccessException {
        ReflectionUtilities.invokeDeclaredMethodWithAnnotation(Before.class, test);
    }

    private Object getSubject() throws IllegalAccessException {
        return subjectField.get(test);
    }

    private Map<Class, Field> initMockTargets() throws IllegalAccessException {
        Map<MockTarget, Field> mockTargets = ReflectionUtilities.getDeclaredFieldsWithAnnotation(MockTarget.class, testClass);
        Map<Class, Field> mockTargetsToReturn = new HashMap<Class, Field>();
        for (MockTarget annotation : mockTargets.keySet()) {
            Field field = mockTargets.get(annotation);
            field.setAccessible(true);
            field.set(test, mock(field.getType()));
            for (Class targetClasses : annotation.value()) {
                mockTargetsToReturn.put(targetClasses, field);
            }
        }
        return mockTargetsToReturn;
    }

}
