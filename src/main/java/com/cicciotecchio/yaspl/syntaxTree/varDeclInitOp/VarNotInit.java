package com.cicciotecchio.yaspl.syntaxTree.varDeclInitOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.VarDeclsInitWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class VarNotInit extends VarDeclsInitWrapper implements Visitable {

	private String op;
	private IdConst id;
	
	public VarNotInit(Location left, Location right, String op, IdConst id) {
		super(left, right, op, id);
		this.op = op;
		this.id = id;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public IdConst getId() {
		return id;
	}

}
