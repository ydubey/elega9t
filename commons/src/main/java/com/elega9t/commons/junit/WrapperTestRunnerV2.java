/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.junit;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WrapperTestRunnerV2 extends BlockJUnit4ClassRunner {

    private final FrameworkField testSubjectField;

    public WrapperTestRunnerV2(Class<?> testClass) throws InitializationError {
        super(testClass);
        final List<FrameworkField> annotatedFields = getTestClass().getAnnotatedFields(TestSubject.class);
        if(annotatedFields.size() != 1) {
            throw new InitializationError("There should be only one @TestSubject per test");
        }
        testSubjectField = annotatedFields.get(0);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> testMethods = new ArrayList<FrameworkMethod>();
        testMethods.addAll(super.computeTestMethods());
        final List<FrameworkField> annotatedFields = getTestClass().getAnnotatedFields(MockTarget.class);
        for (FrameworkField annotatedField : annotatedFields) {
            for (Annotation annotation : annotatedField.getAnnotations()) {
                if(MockTarget.class.isAssignableFrom(annotation.getClass())) {
                    for (Class contract : ((MockTarget) annotation).value()) {
                        for (Method method : contract.getDeclaredMethods()) {
                            testMethods.add(new WrapperFrameworkMethod(method, annotatedField));
                        }
                    }
                }
            }
        }
        return testMethods;
    }

    @Override
    protected Description describeChild(FrameworkMethod method) {
        if(WrapperFrameworkMethod.class.isAssignableFrom(method.getClass())) {
            final WrapperFrameworkMethod wrapperFrameworkMethod = (WrapperFrameworkMethod) method;
            return Description.createTestDescription(getTestClass().getJavaClass(), wrapperFrameworkMethod.createTestName());
        } else {
            return super.describeChild(method);
        }
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        if(WrapperFrameworkMethod.class.isAssignableFrom(method.getClass())) {
            final Description description = describeChild(method);
            final WrapperFrameworkMethod wrapperFrameworkMethod = (WrapperFrameworkMethod) method;
            try {
                final Object test = createTest();
                if(wrapperFrameworkMethod.getMethod().getParameterTypes().length == 0) {
                    wrapperFrameworkMethod.getMethod().invoke(getTestSubject());
                } else {
                    notifier.fireTestIgnored(description);
                }
            } catch (Exception e) {
                notifier.fireTestFailure(new Failure(description, e));
            }
        } else {
            super.runChild(method, notifier);
        }
    }

    private Object getTestSubject() throws Exception {
        return testSubjectField.get(createTest());
    }

}
