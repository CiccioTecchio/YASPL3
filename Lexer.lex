
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

separator 	= "{"|"}"|"("|")"|";"|","
relOp 		= "="|"<"|">"|"<="|">="|"=="
keyword 	= "head"|"start"|"def"
controlOp 	= "if"| "then" | "while" | "do" | "else"
type 		= "int" | "bool" | "double" | "string" | "char"
boolVal 	= "true" | "false"
logicalOp	= "and" | "or" | "not"
id 			= [:jletter:]([:jletter:]|[:jdigit:])*
mathOp 		= "+"|"-"|"*"|"/"
sign 		= "+"|"-"
intConst 	= 0?|{sign}?[1-9][0-9]*
doubleConst = 0?|{sign}?[1-9]+.0*[1-9]+ 
any			= .
stringConst = \"({any})*\"
charConst	= '({any})?'
paramType	= "in" | "out" | "inout"


whitespace = [ \r\n\t\f]
%%
{whitespace} { /* ignore */ }
{separator}	{ return new Symbol(LexerSym.SEPARATOR); }
{relOp}		{ return new Symbol(LexerSym.RELOP); }
"<-"		{ return new Symbol(LexerSym.READ); }
"->"		{ return new Symbol(LexerSym.WRITE); }
{keyword}	{ return new Symbol(LexerSym.KEYWORD); }
{controlOp}	{ return new Symbol(LexerSym.CONTROLOP); }
{type}		{ return new Symbol(LexerSym.TYPE); }
{boolVal}	{ return new Symbol(LexerSym.BOOLVAL); }
{paramType}	{ return new Symbol(LexerSym.PARAMTYPE); }
{logicalOp}	{ return new Symbol(LexerSym.LOGICALOP); }
{id}		{ return new Symbol(LexerSym.ID); }
{mathOp}	{ return new Symbol(LexerSym.MATHOP); }
{intConst}	{ return new Symbol(LexerSym.INTCONST); }
{doubleConst} { return new Symbol(LexerSym.DOUBLECONST); }
{stringConst} { return new Symbol(LexerSym.STRINGCONST); }
{charConst} { return new Symbol(LexerSym.CHARCONST); }

[^]			{ return new Symbol(LexerSym.error);}
<<EOF>> {return new Symbol(LexerSym.EOF);}
