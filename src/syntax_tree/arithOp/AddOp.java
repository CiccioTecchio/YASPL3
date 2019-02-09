package syntax_tree.arithOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class AddOp extends ExprWrapper implements Visitable{

	public AddOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1,e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public AddOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (AddOp) visitor.visit(this);
	}
	
}