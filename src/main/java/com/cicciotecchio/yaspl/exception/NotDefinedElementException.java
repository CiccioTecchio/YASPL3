/**
 * 
 */
package com.cicciotecchio.yaspl.exception;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Kind;

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
			System.err.println(String.format("identificatore %s non � una funzione\n", id));
		System.exit(1);
	}
}
