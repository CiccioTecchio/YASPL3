package syntax_tree;

import syntax_tree.wrappers.ListParent;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecls extends ListParent<VarDecls> implements Visitable{

	public VarDecls(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public VarDecls accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDecls) visitor.visit(this);
	}
	
}