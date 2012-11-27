/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.cp;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FolderClassPathResource extends AbstractClassPathResource {

    private File folder;

    public FolderClassPathResource(File folder) {
        this.folder = folder;
    }

    @Override
    public List<Class> listClasses(final FilenameFilter classNameFilter, ClassFilter filter) throws IOException, ClassNotFoundException {
        return listClasses(folder, folder, new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory() || (file.getName().endsWith(".class") && classNameFilter.accept(file.getParentFile(), file.getName()));
            }
        }, filter);
    }

    private List<Class> listClasses(File parent, File folder, FileFilter classNameFilter, ClassFilter filter) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        File[] files = folder.listFiles(classNameFilter);
        for (File file : files) {
            if(file.isDirectory()) {
                classes.addAll(listClasses(parent, file, classNameFilter, filter));
            } else if(classNameFilter.accept(file)) {
                String classFile = file.getAbsolutePath();
                String parentPath = parent.getAbsolutePath();
                if(!parentPath.endsWith("/")) {
                    parentPath = parentPath + "/";
                }
                String className = classFile.substring(parentPath.length(), classFile.length() - 6);
                className = className.replace("/", ".");
                Class<?> clazz = Class.forName(className);
                if(filter.accept(clazz)) {
                    classes.add(clazz);
                }
            }
        }
        return classes;
    }

}
