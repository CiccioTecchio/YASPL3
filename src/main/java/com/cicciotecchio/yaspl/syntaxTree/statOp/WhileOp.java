package com.cicciotecchio.yaspl.syntaxTree.statOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.Body;
import com.cicciotecchio.yaspl.syntaxTree.CompStat;
import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class WhileOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private Body b;
	
	public WhileOp(Location left, Location right, String op, Expr e, Body b) {
		super(left, right, op, e, b);
		this.op = op;
		this.e = e;	
		this.b = b;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Expr getE() {
		return e;
	}
	
	public Body getBody() {
		return b;
	}
	
	

}
