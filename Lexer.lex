package lexer;

import java_cup.runtime.*;
import java.io.IOException;

import parser.LexerSym;
import static parser.LexerSym.*;

%%

%class LexerLex

%unicode
%line
%column

%public
%final
// %abstract 
%cupsym lexer.LexerSym 

%cup
// %cupdebug
%init{
	// TODO: code that goes to constructor
%init}

%{

	private Symbol symbol(int type)
	{
		return symbol(type, yytext());
	}

	private Symbol symbol(int type, Object value)
	{
		return new Symbol(type, yyline, yycolumn, value);
	}

	private void error()
	throws IOException
	{
		throw new IOException("illegal text at line = "+yyline+", column = "+yycolumn+", text = '"+yytext()+"'");
	}
%}

%eofval{
	return new Symbol(LexerSym.EOF);
%eofval}

id 			= ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*
digit 		= [0-9]
intConst 	= {digit}+
doubleConst = {intConst}("."{intConst})?
any			= .
stringConst = \"~\"
// singleLineComment = "//"~"\n"
// multilineComment = "/*"~"*/"
//comment 	= {singleLineComment} | {multilineComment}
charConst	= '({any})?'
// whitespace = [ \r\n\t\f]

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?

%%
{WhiteSpace} 	{ /* ignore */ }
{Comment}		{ /* ignore */ }
"head"			{ return symbol(LexerSym.HEAD); }
"start"			{ return symbol(LexerSym.START); }
";"				{ return symbol(LexerSym.SEMI); }
"int"			{ return symbol(LexerSym.INT); }
"bool"			{ return symbol(LexerSym.BOOL); }
"double"		{ return symbol(LexerSym.DOUBLE); }
"string"		{ return symbol(LexerSym.STRING); }
"char"			{ return symbol(LexerSym.CHAR); }
","				{ return symbol(LexerSym.COMMA); }
"def"			{ return symbol(LexerSym.DEF); }
"("				{ return symbol(LexerSym.LPAR); }
")"				{ return symbol(LexerSym.RPAR); }
"{"				{ return symbol(LexerSym.LGPAR); }
"}"				{ return symbol(LexerSym.RGPAR); }
"<-"			{ return symbol(LexerSym.READ); }
"->"			{ return symbol(LexerSym.WRITE); }
"+"				{ return symbol(LexerSym.PLUS); }
"-"				{ return symbol(LexerSym.MINUS); }
"*"				{ return symbol(LexerSym.TIMES); }
"/"				{ return symbol(LexerSym.DIV); }
{intConst}		{ return symbol(LexerSym.INT_CONST, yytext()); }
{doubleConst}	{ return symbol(LexerSym.DOUBLE_CONST, yytext()); }
{stringConst}	{ return symbol(LexerSym.STRING_CONST, yytext()); }
{charConst}		{ return symbol(LexerSym.CHAR_CONST, yytext()); }
"true"			{ return symbol(LexerSym.TRUE); }
"false"			{ return symbol(LexerSym.FALSE); }
"="				{ return symbol(LexerSym.ASSIGN); }
"if"			{ return symbol(LexerSym.IF); }
"then"			{ return symbol(LexerSym.THEN);  }
"while"			{ return symbol(LexerSym.WHILE); }
"do"			{ return symbol(LexerSym.DO); }
"else"			{ return symbol(LexerSym.ELSE); }
">"				{ return symbol(LexerSym.GT); }
">="			{ return symbol(LexerSym.GE); }
"<"				{ return symbol(LexerSym.LT); }
"<="			{ return symbol(LexerSym.LE); }
"=="			{ return symbol(LexerSym.EQ); }
"not"			{ return symbol(LexerSym.NOT); }
"and"			{ return symbol(LexerSym.AND); }
"or"			{ return symbol(LexerSym.OR); }
"in"			{ return symbol(LexerSym.IN); }
"out"			{ return symbol(LexerSym.OUT); }
"inout"			{ return symbol(LexerSym.INOUT); }
{id}			{ return symbol(LexerSym.ID, yytext()); }
[^]				{  throw new Error("Illegal character <"+yytext()+"> at line "+yyline+", column "+yycolumn);}
