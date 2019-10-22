package com.cicciotecchio.yaspl.syntaxTree.statOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.Args;
import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class AssignOp extends Stat implements Visitable {

	private String op;
	private IdConst id;
	//private Expr e;
	private Args a;
	
	/*public AssignOp(String op, IdConst id, Expr e) {
		super(op, id, e);
		this.op = op;
		this.id = id;
		this.e = e;
	}*/

	public AssignOp(Location left, Location right, String op, IdConst id, Args a) {
		super(left, right, op, id, a);
		this.op = op;
		this.id = id;
		this.a = a;
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

	public Args getA() {
		return a;
	}
	
	
}
