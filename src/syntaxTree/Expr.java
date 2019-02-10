package syntaxTree;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class Expr extends Internal implements Visitable {
	
	//Operazioni binarie
	public Expr(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
	}
	
	//Operazioni unarie
	public Expr(String op, Expr e) {
		super(op, e);
	}
	
	//non creo IdLeaf così facendo non ho necessità di creare un nodo interno per contenere un IdLeaf
	public Expr(String op, Leaf id) {
		super(op, id);
	}

	@Override
	public Expr accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Expr) visitor.visit(this);
	}

}
