/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave.actions;

import com.elega9t.intellij.plugin.jbehave.JBehaveIcons;
import com.elega9t.intellij.plugin.jbehave.JBehaveMessageBundle;
import com.intellij.CommonBundle;
import com.intellij.ide.actions.CreateElementActionBase;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import org.jetbrains.annotations.NotNull;

public class CreateNewStoryAction extends CreateElementActionBase {

    protected CreateNewStoryAction() {
        super(JBehaveMessageBundle.message("newfile.menu.action.text"), JBehaveMessageBundle.message("newfile.menu.action.description"), JBehaveIcons.FEATURE_ICON);
    }

    @NotNull
    @Override
    protected PsiElement[] invokeDialog(Project project, PsiDirectory directory) {
        MyInputValidator validator = new MyInputValidator(project, directory);
        Messages.showInputDialog (project, "File name:", "jBehave Feature File", Messages.getQuestionIcon(), "", validator);
        return validator.getCreatedElements();
    }

    @NotNull
    @Override
    protected PsiElement[] create(String name, PsiDirectory directory) throws Exception {
        return new PsiElement[] {PsiFileFactory.getInstance(directory.getProject()).createFileFromText(name, "Hi There")};
    }

    @Override
    protected String getErrorTitle() {
        return CommonBundle.getErrorTitle();
    }

    @Override
    protected String getCommandName() {
        return JBehaveMessageBundle.message("newfile.command.name");
    }

    @Override
    protected String getActionName(PsiDirectory directory, String s) {
        return JBehaveMessageBundle.message("newfile.menu.action.text");
    }

}
