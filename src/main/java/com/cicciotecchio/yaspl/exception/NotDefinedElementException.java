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
public class NotDefinedElementException extends RuntimeException {

	public NotDefinedElementException(String id) {
		System.err.println(String.format("Variabile %s non definita\n", id));
		System.exit(1);
	}
	
	public NotDefinedElementException(String id, Kind t) {
		if(t == Kind.DEFDECL)
			System.err.println(String.format("identificatore %s non è una funzione\n", id));
		System.exit(1);
	}
}
