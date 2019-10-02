package syntaxTree;

import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public abstract class Expr extends Internal {
	
	private String op;
	
	//Operazioni binarie
	public Expr(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
	}
	
	//Operazioni unarie
	public Expr(String op, Expr e) {
		super(op, e);
	}
	
	//Increment and Decrement
	public Expr(String op, IdConst id) {
		super(op, id);
	}
	
	public Expr(String op, Leaf id) {
		super(op, id);
	}
	
	
	
	public abstract Object accept(Visitor<?> visitor);

	public String getOp() {
		return op;
	}

	
		

}
