package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class SubOp extends Expr implements Visitable {

	public SubOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SubOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (SubOp) visitor.visit(this);
	}

}
