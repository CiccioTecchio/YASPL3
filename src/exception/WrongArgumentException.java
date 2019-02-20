/**
 * 
 */
package exception;

import semantic.SymbolTable.Kind;
import semantic.SymbolTable.Type;

/**
 * @author ferdi
 *
 */
public class WrongArgumentException extends RuntimeException {
	
	public WrongArgumentException(String id) {
		System.err.println(String.format("Numero argomenti errato per func %s\n", id));
		System.exit(1);
	}
	
	public WrongArgumentException(String id, Type t1, Type t2) {
		String toPrint = String.format("Tipo argomento errato in func %s expected %s find %s", id, t1, t2);
		System.err.println(toPrint);
		System.exit(1);
	}
	
	public WrongArgumentException(String id, int i) {
		String toPrint = String.format("Non � possibile passare espressioni o costanti alle variabili di output alla func %s ", id);
		System.err.println(toPrint);
		System.exit(1);
	}
}
