
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
    return sym(LexerSym.EOF);
%eofval}  

%%

"0"			{	return sym(LexerSym.ZERO);	}
"1"			{	return sym(LexerSym.ONE);	}
