package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class UminusOp extends Expr implements Visitable {

	public UminusOp(String op, Expr e) {
		super(op, e);
		// TODO Auto-generated constructor stub
	}

	@Override
	public UminusOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (UminusOp) visitor.visit(this);
	}
}
