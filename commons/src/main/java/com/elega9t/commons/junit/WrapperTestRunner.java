/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import com.elega9t.commons.util.ReflectionUtilities;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static org.mockito.Mockito.mock;

public class WrapperTestRunner extends Runner {

    private final Class testClass;
    private final Description description;

    public WrapperTestRunner(Class testClass) {
        this.testClass = testClass;
        this.description = createDescription();
    }

    private Description createDescription() {
        Description suiteDescription = Description.createSuiteDescription(testClass);
        Annotation annotation = testClass.getAnnotation(Contract.class);
        if(annotation == null) {
            throw new IllegalStateException("Expected @Contract");
        }
        for (Class contractClass  : ((Contract)annotation).value()) {
            addTestDescriptionsForContact(suiteDescription, contractClass);
        }
        return suiteDescription;
    }

    private void addTestDescriptionsForContact(Description suiteDescription, Class contractClass) {
        for (Method method : contractClass.getMethods()) {
            suiteDescription.addChild(Description.createTestDescription(testClass, method.getName()));
        }
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            Object test = testClass.newInstance();
            notifier.fireTestRunStarted(description);
            for (Description testDescription : description.getChildren()) {
                initializeMockTargets(test);
                notifier.fireTestStarted(testDescription);
                System.out.println(testDescription.getMethodName());
                notifier.fireTestFinished(testDescription);
            }
        } catch(Throwable e) {
            notifier.fireTestFailure(new Failure(description, e));
        }
    }

    private void initializeMockTargets(Object test) throws IllegalAccessException {
        final Map<MockTarget,Field> mockTargets = ReflectionUtilities.getDeclaredFieldsWithAnnotation(MockTarget.class, testClass);
        for (MockTarget annotation : mockTargets.keySet()) {
            Field field = mockTargets.get(annotation);
            field.setAccessible(true);
            field.set(test, mock(field.getType()));
        }
    }

}
