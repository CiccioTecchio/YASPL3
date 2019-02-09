package syntax_tree.arithOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class MultOp extends ExprWrapper implements Visitable{

	public MultOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public MultOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (MultOp) visitor.visit(this);
	}
	
}