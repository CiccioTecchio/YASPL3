package syntax_tree;


import syntax_tree.comp.Internal;
import syntax_tree.wrappers.ExprWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarInitValue extends Internal implements Visitable{
	
	public VarInitValue(String op, ExprWrapper expr) {
		super(op, expr);
	}

	@Override
	public VarInitValue accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarInitValue) visitor.visit(this);
	}

}
