package com.cicciotecchio.yaspl.syntaxTree.statOp;

import com.cicciotecchio.yaspl.semantic.SymbolTable;
import com.cicciotecchio.yaspl.syntaxTree.Body;
import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.CompStat;
import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class IfThenElseOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private Body b1;
	private Body b2;
	private SymbolTable elseTbl;

	public IfThenElseOp(Location left, Location right, String op, Expr e, Body b1, Body b2) {
		super(left, right, op, e, b1, b2);
		this.op = op;
		this.e = e;
		this.b1 = b1;
		this.b2 = b2;
		this.elseTbl = new SymbolTable();
	}
	
	@Override
	public Object accept(Visitor<?> visitor) { return visitor.visit(this); }

	public String getOp() {
		return op;
	}

	public Expr getE() {
		return e;
	}

	public Body getB1() {
		return b1;
	}

	public Body getB2() {
		return b2;
	}

	public void setElseTbl(SymbolTable elseTbl){ this.elseTbl = elseTbl; }

	public SymbolTable getElseTbl(){ return this.elseTbl; }
	

}
