package com.cicciotecchio.yaspl.exception;

import com.cicciotecchio.yaspl.syntaxTree.components.Node;

public class NotDeclaredException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotDeclaredException(String id, String scopeName, Node n) {
		System.err.println(String.format("%s non dichiarata nel %s scope linea <%s> colonna <%s>",
				id,
				scopeName,
				n.getLeft().getLine(),
				n.getRight().getColumn()
		));
		System.exit(1);
	}
}
