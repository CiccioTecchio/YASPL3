package syntax_tree.arithOp;

import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class UminusOp extends ExprWrapper implements Visitable{

	public UminusOp(String op, ExprWrapper e) {
		super(op, e);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public UminusOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (UminusOp) visitor.visit(this);
	}
	
}