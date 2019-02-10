package syntaxTree.logicOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class AndOp extends Expr implements Visitable {

	public AndOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public AndOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (AndOp) visitor.visit(this);
	}

}
