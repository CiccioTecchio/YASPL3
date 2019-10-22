package com.cicciotecchio.yaspl.syntaxTree.declsOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.Body;
import com.cicciotecchio.yaspl.syntaxTree.ParDecls;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.DeclsWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class DefDeclPar extends DeclsWrapper implements Visitable {

	private String op;
	private IdConst id;
	private ParDecls pd;
	private Body b;
	
	public DefDeclPar(Location left, Location right, String op, IdConst id, ParDecls pd, Body b) {
		super(left, right, op, id, pd, b);
		this.op = op;
		this.id = id;
		this.pd = pd;
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

	public ParDecls getPd() {
		return pd;
	}

	public Body getB() {
		return b;
	}
	
	

}
