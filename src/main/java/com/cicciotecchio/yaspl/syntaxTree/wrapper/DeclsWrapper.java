package com.cicciotecchio.yaspl.syntaxTree.wrapper;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.Body;
import com.cicciotecchio.yaspl.syntaxTree.ParDecls;
import com.cicciotecchio.yaspl.syntaxTree.VarDeclsInit;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.leaf.TypeLeaf;
import com.cicciotecchio.yaspl.visitor.Visitor;

public abstract class DeclsWrapper extends Internal{
	
	public DeclsWrapper(Location left, Location right, String op, IdConst id, ParDecls pd, Body b) {
		super(left, right, op, id, pd, b);
	}
	
	public DeclsWrapper(Location left, Location right, String op, IdConst id, Body b) {
		super(left, right, op, id, b);
	}

	public DeclsWrapper(Location left, Location right, String op, TypeLeaf t, VarDeclsInit vdi) {
		super(left, right, op, t, vdi);
	}

	public abstract Object accept(Visitor<?> visitor);

}
