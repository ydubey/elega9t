/*
 * Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 * ELEGA9T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.Copyright (c) 2011 - 2012. Elega9t Ltd. All rights reserved.
 */

package com.elega9t.intellij.plugin.jbehave.parser;

import com.elega9t.intellij.plugin.jbehave.JBehaveLanguage;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface JBehaveStoryTokenTypes {

    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;

    IElementType COMMENT = new IElementType("COMMENT", JBehaveLanguage.INSTANCE);

    TokenSet COMMENTS = TokenSet.create(COMMENT);
    TokenSet WHITE_SPACES = TokenSet.create(WHITE_SPACE);

}