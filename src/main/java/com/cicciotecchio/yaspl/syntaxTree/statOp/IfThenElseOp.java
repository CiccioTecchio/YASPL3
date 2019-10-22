package com.cicciotecchio.yaspl.syntaxTree.statOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.CompStat;
import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class IfThenElseOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private CompStat cs1;
	private CompStat cs2;
	
	public IfThenElseOp(Location left, Location right, String op, Expr e, CompStat cs1, CompStat cs2) {
		super(left, right, op, e, cs1, cs2);
		this.op = op;
		this.e = e;
		this.cs1 = cs1;
		this.cs2 = cs2;
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

	public CompStat getCs1() {
		return cs1;
	}

	public CompStat getCs2() {
		return cs2;
	}
	
	

}
