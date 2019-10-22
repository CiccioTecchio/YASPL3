package com.cicciotecchio.yaspl.exception;

public class SyntaxErrorException extends RuntimeException {

private static final long serialVersionUID = 1L;

	public SyntaxErrorException(String val, int line, int column) {
		String toPrint = String.format("Errore di sintassi su %s linea:%d colonna:%d",val, line, column);
		System.err.println(toPrint);
		System.exit(1);
	}

	public SyntaxErrorException(int line, int column) {
		String toPrint = String.format("Errore di sintassi linea:%d colonna:%d", line, column);
		System.err.println(toPrint);
		System.exit(1);
	}
}
