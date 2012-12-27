/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.cp;

import com.elega9t.commons.util.JarUtilities;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileClassPathResource extends AbstractClassPathResource {

    private File file;

    public FileClassPathResource(File file) {
        this.file = file;
    }

    @Override
    public List<Class> listClasses(final FilenameFilter classNameFilter, ClassFilter filter) throws IOException, ClassNotFoundException {
        List<String> classFiles = JarUtilities.listEntries(file, new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".class") && classNameFilter.accept(dir, name);
            }
        });
        List<Class> classes = new ArrayList<Class>();
        for (String classFile : classFiles) {
            String className = classFile.substring(0, classFile.length() - 6);
            className = className.replace("/", ".");
            Class<?> clazz = Class.forName(className);
            if(filter.accept(clazz)) {
                classes.add(clazz);
            }
        }
        return classes;
    }

    @Override
    public List<InputStream> list(final FilenameFilter fileNameFilter) throws IOException {
        return JarUtilities.list(file, fileNameFilter);
    }

    @Override
    public String toString() {
        return file.getAbsolutePath();
    }

}
