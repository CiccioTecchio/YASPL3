package syntax_tree.logicOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class OrOp extends ExprWrapper implements Visitable{

	public OrOp(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public OrOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (OrOp) visitor.visit(this);
	}
	
}