package com.cicciotecchio.yaspl.exception;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Type;
import com.cicciotecchio.yaspl.syntaxTree.components.Node;

public class TypeMismatchException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public TypeMismatchException(String op, Type t1, Node n) {
		String toPrint = String.format("Conflitto di tipo in %s, %s non compatibile linea <%s> colonna <%s>", op,
				t1.toString(),
				n.getLeft().getLine(),
				n.getRight().getColumn());
		System.err.println(toPrint);
		System.exit(1);
	}

	public TypeMismatchException(String op, Type richiesto, Type fornito, Node n) {
		String toPrint = String.format("Conflitto di tipo in %s, richiesto %s fornito %s linea <%s> colonna <%s>", op,
				richiesto.toString(),
				fornito.toString(),
				n.getLeft().getLine(),
				n.getRight().getColumn());
		System.err.println(toPrint);
		System.exit(1);
	}

}
