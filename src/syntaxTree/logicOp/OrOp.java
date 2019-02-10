package syntaxTree.logicOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class OrOp extends Expr implements Visitable {

	public OrOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public OrOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (OrOp) visitor.visit(this);
	}

}
