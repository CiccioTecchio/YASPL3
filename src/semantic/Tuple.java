package semantic;

import semantic.SymbolTable.Kind;
import semantic.SymbolTable.ParType;
import semantic.SymbolTable.Type;

public class Tuple {
	
	private Kind kind;
	//private boolean isUsed;
	
	public Tuple(Kind kind) {
		this.kind = kind;
		//this.isUsed = false;
	}
	
	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}
/*
	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
*/	

}
