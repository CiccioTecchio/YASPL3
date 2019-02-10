package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class DivOp extends Expr implements Visitable {

	public DivOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public DivOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DivOp) visitor.visit(this);
	}

}
