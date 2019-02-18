
package lexer;

import java_cup.runtime.*;
import java.io.IOException;

import lexer.LexerSym;
import static lexer.LexerSym.*;

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
	private Symbol sym(int type)
	{
		return sym(type, yytext());
	}

	private Symbol sym(int type, Object value)
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

id 			= [:jletter:]([:jletter:]|[:jdigit:])*
digit 		= [0-9]
intConst 	= {digit}+
doubleConst = {intConst}("."{intConst})?
any			= .
stringConst = \"~\"
charConst	= '({any})?'
whitespace = [ \r\n\t\f]

%%
{whitespace} 	{ /* ignore */ }
"head"			{ return new Symbol(LexerSym.HEAD); }
"start"			{ return new Symbol(LexerSym.START); }
";"				{ return new Symbol(LexerSym.SEMI); }
"int"			{ return new Symbol(LexerSym.INT); }
"bool"			{ return new Symbol(LexerSym.BOOL); }
"double"		{ return new Symbol(LexerSym.DOUBLE); }
"string"		{ return new Symbol(LexerSym.STRING); }
"char"			{ return new Symbol(LexerSym.CHAR); }
","				{ return new Symbol(LexerSym.COMMA); }
"def"			{ return new Symbol(LexerSym.DEF); }
"("				{ return new Symbol(LexerSym.LPAR); }
")"				{ return new Symbol(LexerSym.RPAR); }
"{"				{ return new Symbol(LexerSym.LGPAR); }
"}"				{ return new Symbol(LexerSym.RGPAR); }
"<-"			{ return new Symbol(LexerSym.READ); }
"->"			{ return new Symbol(LexerSym.WRITE); }
"+"				{ return new Symbol(LexerSym.PLUS); }
"-"				{ return new Symbol(LexerSym.MINUS); }
"*"				{ return new Symbol(LexerSym.TIMES); }
"/"				{ return new Symbol(LexerSym.DIV); }
{intConst}		{ return new Symbol(LexerSym.INT_CONST, yytext()); }
{doubleConst}	{ return new Symbol(LexerSym.DOUBLE_CONST, yytext()); }
{stringConst}	{ return new Symbol(LexerSym.STRING_CONST, yytext()); }
{charConst}		{ return new Symbol(LexerSym.CHAR_CONST, yytext()); }
"true"			{ return new Symbol(LexerSym.TRUE); }
"false"			{ return new Symbol(LexerSym.FALSE); }
"="				{ return new Symbol(LexerSym.ASSIGN); }
"if"			{ return new Symbol(LexerSym.IF); }
"then"			{ return new Symbol(LexerSym.THEN);  }
"while"			{ return new Symbol(LexerSym.WHILE); }
"do"			{ return new Symbol(LexerSym.DO); }
"else"			{ return new Symbol(LexerSym.ELSE); }
">"				{ return new Symbol(LexerSym.GT); }
">="			{ return new Symbol(LexerSym.GE); }
"<"				{ return new Symbol(LexerSym.LT); }
"<="			{ return new Symbol(LexerSym.LE); }
"=="			{ return new Symbol(LexerSym.EQ); }
"not"			{ return new Symbol(LexerSym.NOT); }
"and"			{ return new Symbol(LexerSym.AND); }
"or"			{ return new Symbol(LexerSym.OR); }
"in"			{ return new Symbol(LexerSym.IN); }
"out"			{ return new Symbol(LexerSym.OUT); }
"intout"		{ return new Symbol(LexerSym.INOUT); }
{id}			{ return new Symbol(LexerSym.ID, yytext()); }
[^]				{  throw new Error("Illegal character <"+yytext()+"> at line "+yyline+", column "+yycolumn);}
