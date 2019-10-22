package com.cicciotecchio.yaspl.semantic;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Kind;
import com.cicciotecchio.yaspl.semantic.SymbolTable.ParType;
import com.cicciotecchio.yaspl.semantic.SymbolTable.Type;

public class ParTuple extends Tuple {

	private ParType pt;
	private Type type;
	
	public ParType getPt() {
		return pt;
	}

	public void setPt(ParType pt) {
		this.pt = pt;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	

	public ParTuple(Kind kind, ParType pt, Type type ) {
		super(kind);
		this.pt = pt;
		this.type = type;
	}

	@Override
	public String toString() {
		return "ParTuple [pt=" + pt + ", type=" + type + "]";
	}

	
	
}
