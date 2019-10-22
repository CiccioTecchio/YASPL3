package com.cicciotecchio.yaspl.exception;

import com.cicciotecchio.yaspl.syntaxTree.components.Node;

public class IllegalParamOperationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IllegalParamOperationException(String mess, Node n) {
		System.err.println(String.format("%s line <%s> column <%s>", mess, n.getLeft().getLine(), n.getRight().getColumn()));
		System.exit(1);
	}
	

}
