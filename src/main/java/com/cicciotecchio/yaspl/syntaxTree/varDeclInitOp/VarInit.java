package com.cicciotecchio.yaspl.syntaxTree.varDeclInitOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.VarInitValue;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.VarDeclsInitWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class VarInit extends VarDeclsInitWrapper implements Visitable {

	private String op;
	private IdConst id;
	private VarInitValue viv;


	public VarInit(Location left, Location right, String op, IdConst id, VarInitValue viv) {
		super(left, right, op,id,viv);
		this.op = op;
		this.id = id;
		this.viv = viv;
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

	public VarInitValue getViv() {
		return viv;
	}
	
	

}
