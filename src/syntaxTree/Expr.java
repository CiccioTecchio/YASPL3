package syntaxTree;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
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
	
	public Expr(String op, Leaf id) {
		super(op, id);
	}
	
	public abstract Object accept(Visitor<?> visitor);

	public String getOp() {
		return op;
	}

	
		

}
