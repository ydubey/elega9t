/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

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
    }

}
