package syntaxTree.relOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class LtOp extends Expr implements Visitable {

	public LtOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LtOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (LtOp) visitor.visit(this);
	}

}
