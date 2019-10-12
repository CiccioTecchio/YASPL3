/**
 * 
 */
package exception;

import semantic.SymbolTable.Type;

/**
 * @author ferdi
 *
 */
public class WrongArgumentException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public WrongArgumentException(String id) {
		System.err.println(String.format("Numero argomenti errato per func %s\n", id));
		System.exit(1);
	}
	
	public WrongArgumentException(String id, int i, Type t1, Type t2) {
		String toPrint = String.format("Tipo argomento #%d errato in func %s expected %s find %s",i, id, t1, t2);
		System.err.println(toPrint);
		System.exit(1);
	}
	
	public WrongArgumentException(String id, int i) {
		String toPrint = String.format("Non è possibile passare espressioni o costanti alle variabili di output alla func %s ", id);
		System.err.println(toPrint);
		System.exit(1);
	}
}
