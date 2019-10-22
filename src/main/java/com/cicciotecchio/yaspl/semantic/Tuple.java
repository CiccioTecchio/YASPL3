package com.cicciotecchio.yaspl.semantic;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Kind;

public class Tuple {
	
	private Kind kind;
	
	public Tuple(Kind kind) {
		this.kind = kind;
	}
	
	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

}
