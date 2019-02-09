package syntax_tree.relOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class GtOp extends ExprWrapper implements Visitable{

	public GtOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public GtOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (GtOp) visitor.visit(this);
	}
	
}