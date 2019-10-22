package com.cicciotecchio.yaspl.exception;

import com.cicciotecchio.yaspl.syntaxTree.components.Node;

public class DeclaredButNeverUserdException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DeclaredButNeverUserdException(String mess, Node n) {
		System.err.print(String.format("%s linea <%s> colonna <%s>", mess, n.getLeft().getLine(), n.getRight().getColumn()));
	}

}
