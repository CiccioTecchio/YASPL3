package syntax_tree.logicOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class AndOp extends ExprWrapper implements Visitable{

	public AndOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public AndOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (AndOp) visitor.visit(this);
	}
	
}