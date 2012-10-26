/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.util;

import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionUtilities {

    public static Object getField(String fieldName, Object parent) throws NoSuchFieldException, IllegalAccessException {
        Field field = parent.getClass().getField(fieldName);
        return getField(field, parent);
    }

    public static Object getDeclaredField(String fieldName, Object parent) throws NoSuchFieldException, IllegalAccessException {
        Field field = parent.getClass().getDeclaredField(fieldName);
        return getField(field, parent);
    }

    public static Object getDeclaredField(String fieldName, Class parentClass, Object parent) throws NoSuchFieldException, IllegalAccessException {
        Field field = parentClass.getDeclaredField(fieldName);
        return getField(field, parent);
    }

    public static Object getDeclaredField(String fieldName, Class clazz) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        return getField(field, null);
    }

    public static void setField(String fieldName, Object parent, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = parent.getClass().getField(fieldName);
        setField(field, parent, value);
    }

    public static void setField(String fieldName, Class parentClass, Object parent, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = parentClass.getField(fieldName);
        setField(field, parent, value);
    }

    public static void setDeclaredField(String fieldName, Object parent, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = parent.getClass().getDeclaredField(fieldName);
        setField(field, parent, value);
    }

    public static void setDeclaredField(String fieldName, Class clazz, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getDeclaredField(fieldName);
        setField(field, null, value);
    }

    public static void setDeclaredField(String fieldName, Class parentClass, Object parent, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = parentClass.getDeclaredField(fieldName);
        setField(field, parent, value);
    }

    public static Object getField(Field field, @Nullable Object parent) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(parent);
    }

    // TODO: Set final fields?
    public static void setField(Field field, @Nullable Object parent, Object value) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(parent, value);
    }

    public static <T extends Annotation> Map<T, Field> getDeclaredFieldsWithAnnotation(Class<T> annotationClass, Class targetClass) {
        Map<T, Field> fieldsWithAnnotation = new HashMap<T, Field>();
        Field[] declaredFields = targetClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            T annotation = declaredField.getAnnotation(annotationClass);
            if (annotation != null) {
                fieldsWithAnnotation.put(annotation, declaredField);
            }
        }
        return fieldsWithAnnotation;
    }

    public static Object invokeDeclaredMethod(String methodName, Object target, Class[] parameterTypes, Object[] parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = target.getClass().getDeclaredMethod(methodName, parameterTypes);
        return invokeMethod(method, target, parameters);
    }

    public static Object invokeMethod(Method method, Object target, Object[] parameters) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        return method.invoke(target, parameters);
    }

}
