package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.visitor.Visitor;

public abstract class Stat extends Internal{
	
	//ReadOp
	public Stat(Location left, Location right, String op, Vars v) {
		super(left, right, op, v);
	}
	//WriteOp
	public Stat(Location left, Location right, String op, Args a) {
		super(left, right, op, a);
	}
	//AssignOp
	public Stat(Location left, Location right, String op, IdConst id, Expr e) {
		super(left, right, op, id, e);
	}
	//CallOp
	public Stat(Location left, Location right, String op, IdConst id, Args a) {
		super(left, right, op, id, a);
	}
	//CallOp increment and decrement
	public Stat(Location left, Location right, String op, IdConst id) {
		super(left, right, op, id);
	}
	//IfThenElseOp
	public Stat(Location left, Location right, String op, Expr e, CompStat cs1, CompStat cs2) {
		super(left, right, op, e, cs1, cs2);
	}
	//IfThenOp && WhileOp
	public Stat(Location left, Location right, String op, Expr e, CompStat cs) {
		super(left, right, op, e, cs);
	}
	
	//IfThenOp && WhileOp with scope
	public Stat(Location left, Location right, String op, Expr e, Body b) {
		super(left, right, op, e, b);
	}
	
	public abstract Object accept(Visitor<?> visitor);
	
}