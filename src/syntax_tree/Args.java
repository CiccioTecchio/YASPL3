package syntax_tree;

import syntax_tree.wrappers.ExprWrapper;
import syntax_tree.wrappers.ListParent;
import visitor.Visitable;
import visitor.Visitor;

public class Args  extends ListParent<ExprWrapper> implements Visitable{
	
	
	public Args(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Args accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Args)visitor.visit(this);
	}
	
}