package juuxel.accesswidener.idea;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import juuxel.accesswidener.idea.psi.AwTypes;

%%

%class AccessWidenerLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\r\n|\n|\r
HORIZONTAL_SPACE=[\ \t]+
COMMENT=("#")[^\r\n]*

HEADER_START=accessWidener
VERSION=v\d+

ACCESS=[a-zA-Z]+
TARGET_TYPE=[a-zA-Z]+

CLASS_NAME=[a-zA-Z0-9\-_$/]+
MEMBER_NAME=[a-zA-Z0-9\-_$<>]+

L_PAREN=\(
R_PAREN=\)
LITERAL_START=L
LITERAL_END=;
ARRAY=\[

PRIMITIVE_DESCRIPTOR=[BSIJFDZV]

%state MAIN
%state HEADER
%state TARGET_TYPE_WHITESPACE
%state TARGET_TYPE
%state CLASS_WHITESPACE
%state CLASS_NAME
%state AFTER_CLASS_NAME
%state MEMBER_NAME
%state MEMBER_DESCRIPTOR
%state MEMBER_DESC_LITERAL
%state PARAM_DESCRIPTOR
%state PARAM_DESC_LITERAL
%state LINE_END

%%

<YYINITIAL> {
    {HEADER_START} { yybegin(HEADER); return AwTypes.HEADER_START; }
}

<MAIN> {
    {ACCESS} { yybegin(TARGET_TYPE_WHITESPACE); return AwTypes.ACCESS; }
}

<HEADER> {
    {VERSION} { yybegin(HEADER); return AwTypes.VERSION; }
    {MEMBER_NAME} { yybegin(MAIN); return AwTypes.NAMESPACE; }
}

<TARGET_TYPE_WHITESPACE> {HORIZONTAL_SPACE} { yybegin(TARGET_TYPE); return TokenType.WHITE_SPACE; }
<TARGET_TYPE> {TARGET_TYPE} { yybegin(CLASS_WHITESPACE); return AwTypes.TARGET_TYPE; }
<CLASS_WHITESPACE> {HORIZONTAL_SPACE} { yybegin(CLASS_NAME); return TokenType.WHITE_SPACE; }
<CLASS_NAME> {CLASS_NAME} { yybegin(AFTER_CLASS_NAME); return AwTypes.CLASS_NAME; }
<AFTER_CLASS_NAME> {HORIZONTAL_SPACE} { yybegin(MEMBER_NAME); return TokenType.WHITE_SPACE; }
<MEMBER_NAME> {MEMBER_NAME} { yybegin(MEMBER_DESCRIPTOR); return AwTypes.MEMBER_NAME; }

<MEMBER_DESCRIPTOR> {
    {ARRAY} { return AwTypes.ARRAY; }
    {L_PAREN} { yybegin(PARAM_DESCRIPTOR); return AwTypes.L_PAREN; }
    {LITERAL_START} { yybegin(MEMBER_DESC_LITERAL); return AwTypes.LITERAL_START; }
    {PRIMITIVE_DESCRIPTOR} { yybegin(LINE_END); return AwTypes.PRIMITIVE_DESCRIPTOR; }
}

<MEMBER_DESC_LITERAL> {
    {CLASS_NAME} { yybegin(MEMBER_DESC_LITERAL); return AwTypes.CLASS_NAME; }
    {LITERAL_END} { yybegin(LINE_END); return AwTypes.SEMICOLON; }
}

<PARAM_DESCRIPTOR> {
    {ARRAY} { return AwTypes.ARRAY; }
    {PRIMITIVE_DESCRIPTOR} { yybegin(PARAM_DESCRIPTOR); return AwTypes.PRIMITIVE_DESCRIPTOR; }
    {LITERAL_START} { yybegin(PARAM_DESC_LITERAL); return AwTypes.LITERAL_START; }
    {R_PAREN} { yybegin(MEMBER_DESCRIPTOR); return AwTypes.R_PAREN; }
}

<PARAM_DESC_LITERAL> {
    {CLASS_NAME} { yybegin(PARAM_DESC_LITERAL); return AwTypes.CLASS_NAME; }
    {LITERAL_END} { yybegin(PARAM_DESCRIPTOR); return AwTypes.SEMICOLON; }
}

{COMMENT} { return AwTypes.COMMENT; }

{CRLF} { yybegin(MAIN); return TokenType.WHITE_SPACE; }
{HORIZONTAL_SPACE} { return TokenType.WHITE_SPACE; }

[^] { return TokenType.BAD_CHARACTER; }
