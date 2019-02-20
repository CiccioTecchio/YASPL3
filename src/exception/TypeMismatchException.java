package exception;

import semantic.SymbolTable.Type;

public class TypeMismatchException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public TypeMismatchException(String op, Type...t) {
		String toPrint = String.format("The operation %s is impossibile for the follow type ", op);
		for(Type tt : t) {
			toPrint += tt + " ";
		}
		System.err.println(toPrint);
		System.exit(1);
	}

}
