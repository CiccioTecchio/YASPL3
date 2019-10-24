package com.cicciotecchio.yaspl.syntaxTree;

import com.cicciotecchio.yaspl.syntaxTree.leaf.IntConst;
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
	public Stat(Location left, Location right, String op, Expr e, Body b1, Body b2) { super(left, right, op, e, b1, b2); }
	//IfThenOp && WhileOp with scope
	public Stat(Location left, Location right, String op, Expr e, Body b) {
		super(left, right, op, e, b);
	}
	//ForOp
	public Stat(Location left, Location right, String op, IdConst id, Expr start, Expr end, IntConst incr, Body b) { super(left, right, op, id, start, end, incr, b); }
	//ForOp Minus
	public Stat(Location left, Location right, String op, IdConst id, Expr start, Expr end, boolean minus, IntConst incr, Body b) { super(left, right, op, id, start, end, incr, b); }
	public abstract Object accept(Visitor<?> visitor);
	
}