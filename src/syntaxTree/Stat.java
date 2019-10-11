package syntaxTree;

import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public abstract class Stat extends Internal{
	
	//ReadOp
	public Stat(String op, Vars v) {
		super(op, v);
	}
	//WriteOp
	public Stat(String op, Args a) {
		super(op, a);
	}
	//AssignOp
	public Stat(String op, IdConst id, Expr e) {
		super(op, id, e);
	}
	//CallOp
	public Stat(String op, IdConst id, Args a) {
		super(op, id, a);
	}
	//CallOp increment and decrement
	public Stat(String op, IdConst id) {
		super(op, id);
	}
	//IfThenElseOp
	public Stat(String op, Expr e, CompStat cs1, CompStat cs2) {
		super(op, e, cs1, cs2);
	}
	//IfThenOp && WhileOp
	public Stat(String op, Expr e, CompStat cs) {
		super(op, e, cs);
	}
	
	//IfThenOp && WhileOp with scope
		public Stat(String op, Expr e, Body b) {
			super(op, e, b);
		}
	
	public abstract Object accept(Visitor<?> visitor);
	
}