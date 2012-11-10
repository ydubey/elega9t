/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave;

import com.intellij.lang.FileASTNode;
import com.intellij.lang.Language;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class JBehaveStory implements PsiFile {

    @Override
    public VirtualFile getVirtualFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public String getName() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String s) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ItemPresentation getPresentation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean processChildren(PsiElementProcessor<PsiFileSystemItem> psiFileSystemItemPsiElementProcessor) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiDirectory getContainingDirectory() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDirectory() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public Project getProject() throws PsiInvalidElementAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public Language getLanguage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiManager getManager() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public PsiElement[] getChildren() {
        return new PsiElement[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiDirectory getParent() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getFirstChild() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getLastChild() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getNextSibling() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getPrevSibling() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TextRange getTextRange() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getStartOffsetInParent() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getTextLength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement findElementAt(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiReference findReferenceAt(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getTextOffset() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public char[] textToCharArray() {
        return new char[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getNavigationElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getOriginalElement() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean textMatches(@NotNull @NonNls CharSequence charSequence) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean textMatches(@NotNull PsiElement psiElement) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean textContains(char c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void accept(@NotNull PsiElementVisitor psiElementVisitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void acceptChildren(@NotNull PsiElementVisitor psiElementVisitor) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement copy() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement add(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement addBefore(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement1) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement addAfter(@NotNull PsiElement psiElement, @Nullable PsiElement psiElement1) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkAdd(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement addRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement addRangeBefore(@NotNull PsiElement psiElement, @NotNull PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement addRangeAfter(PsiElement psiElement, PsiElement psiElement1, PsiElement psiElement2) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkDelete() throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteChildRange(PsiElement psiElement, PsiElement psiElement1) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement replace(@NotNull PsiElement psiElement) throws IncorrectOperationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isWritable() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiReference getReference() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        return new PsiReference[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T getCopyableUserData(Key<T> tKey) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> void putCopyableUserData(Key<T> tKey, @Nullable T t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean processDeclarations(@NotNull PsiScopeProcessor psiScopeProcessor, @NotNull ResolveState resolveState, @Nullable PsiElement psiElement, @NotNull PsiElement psiElement1) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiElement getContext() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isPhysical() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public GlobalSearchScope getResolveScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public SearchScope getUseScope() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public long getModificationStamp() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public PsiFile getOriginalFile() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public PsiFile[] getPsiRoots() {
        return new PsiFile[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public FileViewProvider getViewProvider() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FileASTNode getNode() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEquivalentTo(PsiElement psiElement) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void subtreeChanged() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void navigate(boolean b) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canNavigate() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean canNavigateToSource() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void checkSetName(String s) throws IncorrectOperationException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Icon getIcon(@IconFlags int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> T getUserData(@NotNull Key<T> tKey) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> void putUserData(@NotNull Key<T> tKey, @Nullable T t) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
