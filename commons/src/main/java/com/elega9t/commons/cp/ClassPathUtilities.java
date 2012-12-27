/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.cp;

import com.elega9t.commons.util.JarUtilities;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassPathUtilities {

    private static final String CLASSPATH_PROPERTY = "java.class.path";
    private static final String PATH_SEPARATOR_PROPERTY = "path.separator";

    public static List<String> getClassPathElements() {
        String property = System.getProperty(CLASSPATH_PROPERTY);
        String[] paths = property.split(System.getProperty(PATH_SEPARATOR_PROPERTY));
        return Arrays.asList(paths);
    }

    public static List<ClassPathResource> getClassPathResources() {
        List<String> classPathElements = getClassPathElements();
        List<ClassPathResource> classPathResources = new ArrayList<ClassPathResource>();
        for (String path : classPathElements) {
            File classPathElement = new File(path);
            if(classPathElement.isFile() && classPathElement.getName().toLowerCase().endsWith(".jar")) {
                classPathResources.add(new FileClassPathResource(classPathElement));
            } else if (classPathElement.isDirectory()) {
                classPathResources.add(new FolderClassPathResource(classPathElement));
            }
        }
        return classPathResources;
    }

    public static void main(String[] args) throws IOException {
        List<String> classPathElements = getClassPathElements();
        System.out.println(classPathElements);
        System.out.println(JarUtilities.listEntries(new File(classPathElements.get(0)), new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith("Filter.class");
            }
        }));
    }

}
