package syntax_tree;

import syntax_tree.wrappers.DeclWrapper;
import syntax_tree.wrappers.ListParent;
import visitor.Visitable;
import visitor.Visitor;

public class Decls extends ListParent<DeclWrapper> implements Visitable{

	public Decls(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Decls accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Decls) visitor.visit(this);
	}

	@Override
	public ListParent<DeclWrapper> addChild(DeclWrapper node) {
		// TODO Auto-generated method stub
		return super.addChild(node);
	}
	
	

}
