/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.encoding.EncodingManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.charset.Charset;

public class JBehaveStoryType extends LanguageFileType {

    public static final JBehaveStoryType INSTANCE = new JBehaveStoryType();

    protected JBehaveStoryType() {
        super(JBehaveLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "jBehave";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "jBehave Language Plugin";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "story";
    }

    @Override
    public Icon getIcon() {
        return JBehaveIcons.STORY;
    }

    public String getCharset(@NotNull VirtualFile file, final byte[] content) {
        Charset charset = EncodingManager.getInstance().getDefaultCharsetForPropertiesFiles(file);
        String defaultCharsetName = charset == null ? CharsetToolkit.getDefaultSystemCharset().name() : charset.name();
        return defaultCharsetName;
    }

}
