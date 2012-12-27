/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.commons.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

public class JarUtilities {

    private static final String NAME_EXTENSION_SEPERATOR = ".";

    private static final Logger LOGGER = Logger.getLogger(JarUtilities.class.getName());

    public static void extractInNewFolder(File file, File outputFolder) throws IOException {
        String folderName = file.getName();
        if(folderName.contains(NAME_EXTENSION_SEPERATOR)) {
            folderName = folderName.substring(0, folderName.lastIndexOf(NAME_EXTENSION_SEPERATOR));
        }
        extract(file, new File(outputFolder, folderName));
    }

    public static void extract(File file, File outputFolder) throws IOException {
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> entries = jarFile.entries();
        while(entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            File entryFile = new File(outputFolder, entry.getName());
            entryFile.getParentFile().mkdirs();
            if(!entry.isDirectory()) {
                LOGGER.finest("Expanding " + entryFile.getPath() + " ... ");

                InputStream entryInputStream = jarFile.getInputStream(entry);
                byte[] buffer = new byte[1024];
                OutputStream fileOutputStream = new FileOutputStream(entryFile);
                int read;
                while((read = entryInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, read);
                }
                entryInputStream.close();
                fileOutputStream.close();

                LOGGER.finest("Finished expanding " + entryFile.getPath());
            }
        }
    }

    public static List<String> listEntries(File file, FilenameFilter filter) throws IOException {
        try {
            JarFile jarFile = new JarFile(file);
            List<String> list = new ArrayList<String>();
            Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if(filter.accept(file, entry.getName())) {
                    list.add(entry.getName());
                }
            }
            return list;
        } catch(IOException e) {
            throw new IOException(file.getAbsolutePath(), e);
        }
    }

    public static List<InputStream> list(File file, FilenameFilter filter) throws IOException {
        try {
            JarFile jarFile = new JarFile(file);
            List<InputStream> list = new ArrayList<InputStream>();
            Enumeration<JarEntry> entries = jarFile.entries();
            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if(filter.accept(file, entry.getName())) {
                    list.add(jarFile.getInputStream(entry));
                }
            }
            return list;
        } catch(IOException e) {
            throw new IOException(file.getAbsolutePath(), e);
        }
    }

}
