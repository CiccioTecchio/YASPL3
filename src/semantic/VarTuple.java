package semantic;

import semantic.SymbolTable.Kind;
import semantic.SymbolTable.ParType;
import semantic.SymbolTable.Type;

public class VarTuple extends Tuple {
	
	private Type t;
	
	public VarTuple(Kind kind, Type t) {
		super(kind);
		this.t = t;
	}

	public Type getType() {
		return t;
	}

	public void setType(Type t) {
		this.t = t;
	}
	
	

}
