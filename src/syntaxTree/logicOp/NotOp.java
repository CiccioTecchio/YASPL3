package syntaxTree.logicOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class NotOp extends Expr implements Visitable {

	public NotOp(String op, Expr e) {
		super(op, e);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public NotOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (NotOp) visitor.visit(this);
	}

}
