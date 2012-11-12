package com.elega9t.intellij.plugin.jbehave.parser;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

%%

%class _JBehaveStoryLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

CRLF= \n | \r | \r\n
WHITE_SPACE_CHAR=[\ \n\r\t\f]
COMMENT=!--[^\r\n]*

%state IN_VALUE
%state IN_KEY_VALUE_SEPARATOR

%%

<YYINITIAL> {COMMENT} { yybegin(YYINITIAL); return JBehaveStoryTokenTypes.COMMENT; }
. { return JBehaveStoryTokenTypes.BAD_CHARACTER; }