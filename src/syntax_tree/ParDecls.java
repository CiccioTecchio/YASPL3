package syntax_tree;

import syntax_tree.wrappers.ListParent;
import visitor.Visitable;
import visitor.Visitor;

public class ParDecls extends ListParent<ParDecls> implements Visitable{

	public ParDecls(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ParDecls accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (ParDecls) visitor.visit(this);
	}
	
}