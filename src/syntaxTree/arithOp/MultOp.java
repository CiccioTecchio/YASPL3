package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class MultOp extends Expr implements Visitable {

	public MultOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	@Override
	public MultOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (MultOp) visitor.visit(this);
	}

}
