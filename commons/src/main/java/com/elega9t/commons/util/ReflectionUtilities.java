/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.util;

import com.elega9t.commons.cp.ClassFilter;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

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

    public static void invokeDeclaredMethodWithAnnotation(Class<? extends Annotation> annotationClass, Object target) throws InvocationTargetException, IllegalAccessException {
        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            Annotation annotation = declaredMethod.getAnnotation(annotationClass);
            if(annotation != null) {
                invokeMethod(declaredMethod, target, new Object[] {});
            }
        }
    }

    public static Object invokeMethod(Method method, Object target, Object[] parameters) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        return method.invoke(target, parameters);
    }

    public static List<File> getPackageContent(String packageName) throws IOException {
        final String packageFolder = packageName.replace(".", "/");
        List<File> list = new ArrayList<File>();
        Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                .getResources(packageFolder);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if("jar".equalsIgnoreCase(url.getProtocol())) {
                int separatorIndex = url.getFile().indexOf("!");
                File file = new File(url.getFile().substring(5, separatorIndex));
                final String root = url.getFile().substring(separatorIndex + 2);
                List<String> entriesList = JarUtilities.listEntries(file, new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.startsWith(root) && name.substring(root.length() + 1).indexOf('/') == -1;
                    }
                });
                for (String entry : entriesList) {
                    list.add(new File(entry));
                }
                list.size();
            } else {
                File dir = new File(url.getFile());
                for (File file : dir.listFiles()) {
                    list.add(file);
                }
            }
        }
        return list;
    }

    public static List<Class> getClasses(String... packageNames) throws IOException, ClassNotFoundException {
        return getClasses(ACCEPT_ALL_CLASSES, packageNames);
    }

    public static List<Class> getClasses(ClassFilter classFilter, String... packageNames) throws IOException, ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        for (String packageName : packageNames) {
            final String packageFolder = packageName.replace(".", "/");
            List<File> resourcesInPackage = getPackageContent(packageFolder);
            for (File file : resourcesInPackage) {
                final String fileName = file.getAbsolutePath();
                if(fileName.endsWith(".class")) {
                    final int index = fileName.lastIndexOf(packageFolder);
                    String className = fileName.substring(index);
                    className = className.substring(0, className.length() - 6);
                    className = className.replace("/", ".");
                    final Class<?> aClass = Class.forName(className);
                    if(classFilter.accept(aClass)) {
                        classes.add(aClass);
                    }
                }
            }
        }
        return classes;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(getClasses("com.elega9t.commons.shell.intrprtr.cmd"));
    }

    public static final ClassFilter ACCEPT_ALL_CLASSES = new ClassFilter() {
        @Override
        public boolean accept(Class aClass) {
            return true;
        }
    };

}
