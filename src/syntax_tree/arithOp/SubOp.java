package syntax_tree.arithOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class SubOp extends ExprWrapper implements Visitable{

	public SubOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public SubOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (SubOp) visitor.visit(this);
	}
	
}