package com.cicciotecchio.yaspl.exception;

public class SyntaxErrorException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public SyntaxErrorException(int line, int col, String val) {
		System.err.println("Syntax error at line "+line+", column "+col+", symbol "+val);
		System.exit(1);
	}
}
