package com.cicciotecchio.yaspl.syntaxTree.leaf;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.syntaxTree.components.Leaf;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class IntConst extends Expr implements Visitable {
	
	private String op;
	private Leaf id;

	public IntConst(Location left, Location right, String op, Leaf id) {
		super(left, right, op, id);
		this.op = op;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getId() {
		return id;
	}
	
	

}
