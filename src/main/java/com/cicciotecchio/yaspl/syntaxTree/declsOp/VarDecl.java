package com.cicciotecchio.yaspl.syntaxTree.declsOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.VarDeclsInit;
import com.cicciotecchio.yaspl.syntaxTree.leaf.TypeLeaf;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.DeclsWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class VarDecl extends DeclsWrapper implements Visitable {

	private String op;
	private TypeLeaf t;
	private VarDeclsInit vdi;
	
	public VarDecl(Location left, Location right, String op, TypeLeaf t, VarDeclsInit vdi) {
		super(left, right, op, t, vdi);
		this.op = op;
		this.t = t;
		this.vdi = vdi;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public TypeLeaf getT() {
		return t;
	}

	public VarDeclsInit getVdi() {
		return vdi;
	}
	
	

}
