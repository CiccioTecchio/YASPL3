package com.cicciotecchio.yaspl.semantic;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Kind;
import com.cicciotecchio.yaspl.semantic.SymbolTable.Type;

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

	@Override
	public String toString() {
		return "VarTuple [t=" + t + "]";
	}
	
	

}
