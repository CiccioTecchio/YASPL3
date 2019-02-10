package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class AddOp extends Expr implements Visitable {

	public AddOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AddOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (AddOp) visitor.visit(this);
	}
}
