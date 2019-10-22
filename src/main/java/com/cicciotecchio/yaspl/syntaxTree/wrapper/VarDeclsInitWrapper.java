package com.cicciotecchio.yaspl.syntaxTree.wrapper;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.VarInitValue;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.visitor.Visitor;

public abstract class VarDeclsInitWrapper extends Internal {

	//VarNotInit
	public VarDeclsInitWrapper(Location left, Location right, String op, IdConst id) {
		super(left, right, op,id);
	}
	//VarInit
	public VarDeclsInitWrapper(Location left, Location right, String op, IdConst id, VarInitValue viv) {
		super(left, right, op,id,viv);
	}

	public abstract Object accept(Visitor<?> visitor);

	

}
