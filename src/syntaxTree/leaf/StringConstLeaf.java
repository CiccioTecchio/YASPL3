package syntaxTree.leaf;

import syntaxTree.Expr;
import syntaxTree.comp.Leaf;
import visitor.Visitable;

public class StringConstLeaf extends Expr implements Visitable {

	public StringConstLeaf(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	public StringConstLeaf(String op, Expr e) {
		super(op, e);
		// TODO Auto-generated constructor stub
	}

	public StringConstLeaf(String op, Leaf id) {
		super(op, id);
		// TODO Auto-generated constructor stub
	}

}
