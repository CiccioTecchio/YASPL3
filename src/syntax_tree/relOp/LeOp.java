package syntax_tree.relOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class LeOp extends ExprWrapper implements Visitable{

	public LeOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public LeOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (LeOp) visitor.visit(this);
	}
	
}