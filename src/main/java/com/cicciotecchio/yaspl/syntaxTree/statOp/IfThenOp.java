package com.cicciotecchio.yaspl.syntaxTree.statOp;

import com.cicciotecchio.yaspl.syntaxTree.Body;
import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.CompStat;
import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class IfThenOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private Body b;
	
	public IfThenOp(Location left, Location right, String op, Expr e, Body b) {
		super(left, right, op, e, b);
		this.op = op;
		this.e = e;
		this.b = b;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Expr getE() {
		return e;
	}

	public Body getB() {
		return b;
	}
	

}
