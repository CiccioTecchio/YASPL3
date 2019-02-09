package syntax_tree.arithOp;

import syntax_tree.wrappers.ExprWrapper;
import syntax_tree.wrappers.ExprWrapperInternal;
import visitor.Visitable;
import visitor.Visitor;

public class DivOp extends ExprWrapperInternal implements Visitable{

	public DivOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DivOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DivOp) visitor.visit(this);
	}
	
}