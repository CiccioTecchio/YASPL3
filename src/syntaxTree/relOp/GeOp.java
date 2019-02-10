package syntaxTree.relOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class GeOp extends Expr implements Visitable {

	public GeOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public GeOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (GeOp) visitor.visit(this);
	}

}
