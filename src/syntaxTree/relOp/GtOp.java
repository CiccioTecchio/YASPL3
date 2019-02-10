package syntaxTree.relOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class GtOp extends Expr implements Visitable {

	public GtOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GtOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (GtOp) visitor.visit(this);
	}

}
