package com.cicciotecchio.yaspl.syntaxTree.declsOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.Body;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.DeclsWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class DefDeclNoPar extends DeclsWrapper implements Visitable {

	private String op;
	private IdConst id;
	private Body b;
	
	//Definizione senza parametri
	public DefDeclNoPar(Location left, Location right, String op, IdConst id, Body b) {
		super(left, right, op, id, b);
		this.op = op;
		this.id = id;
		this.b = b;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public IdConst getId() {
		return id;
	}

	public Body getB() {
		return b;
	}
	
	

}
