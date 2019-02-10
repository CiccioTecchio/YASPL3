package syntaxTree.relOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class LeOp extends Expr implements Visitable {

	public LeOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LeOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (LeOp) visitor.visit(this);
	}

}
