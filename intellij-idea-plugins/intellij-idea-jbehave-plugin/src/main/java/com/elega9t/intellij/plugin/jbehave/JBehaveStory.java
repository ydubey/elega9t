/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class JBehaveStory extends PsiFileBase implements PsiFile {

    public JBehaveStory(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, JBehaveLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return JBehaveStoryType.INSTANCE;
    }

}
