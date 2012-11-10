/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave.actions;

import com.elega9t.intellij.plugin.jbehave.JBehaveIcons;
import com.elega9t.intellij.plugin.jbehave.JBehaveMessageBundle;
import com.intellij.ide.fileTemplates.*;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.util.IncorrectOperationException;

import java.util.Properties;

public class JBehaveTemplatesFactory implements FileTemplateGroupDescriptorFactory
{
    private static final String JBEHAVE_STORY_FILE = "jBehave Story.story";
    static final String NAME_TEMPLATE_PROPERTY = "NAME";
    static final String LOW_CASE_NAME_TEMPLATE_PROPERTY = "lowCaseName";


    @Override
    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        System.out.println("FileTemplateGroupDescriptor called" );
        FileTemplateGroupDescriptor group = new FileTemplateGroupDescriptor(JBehaveMessageBundle.message("file.template.group.title.jbehave"), JBehaveIcons.STORY);

        group.addTemplate(new FileTemplateDescriptor (JBEHAVE_STORY_FILE, JBehaveIcons.STORY));

        return group;
    }

    protected static PsiFile createFileFromTemplate (final PsiDirectory directory, String className, String templateName, String... parameters) throws IncorrectOperationException {
        String name = StringUtil.trimEnd(className, ".story");
        String fileName = name + ".story";
        FileTemplate template = FileTemplateManager.getInstance().getTemplate(templateName);
        Properties properties = new Properties(FileTemplateManager.getInstance().getDefaultProperties());
        JavaTemplateUtil.setPackageNameAttribute(properties, directory);
        properties.setProperty(NAME_TEMPLATE_PROPERTY, name);
        properties.setProperty(LOW_CASE_NAME_TEMPLATE_PROPERTY, name.substring(0, 1).toLowerCase() + name.substring(1));

        for (int i = 0; i < parameters.length; i += 2) {
            properties.setProperty(parameters[i], parameters[i + 1]);
        }

        String text;

        try {
            text = template.getText(properties);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load template for " + FileTemplateManager.getInstance().internalTemplateToSubject(templateName), e);
        }

        final PsiFileFactory factory = PsiFileFactory.getInstance(directory.getProject());
        final PsiFile file = factory.createFileFromText(fileName, text);

        return (PsiFile) directory.add(file);
    }

}
