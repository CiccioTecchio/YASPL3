package syntax_tree.relOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class LtOp extends ExprWrapper implements Visitable{

	public LtOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LtOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (LtOp) visitor.visit(this);
	}
	
}