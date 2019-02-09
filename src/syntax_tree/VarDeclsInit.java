package syntax_tree;

import syntax_tree.wrappers.ListParent;
import syntax_tree.wrappers.VarDeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDeclsInit extends ListParent<VarDeclsWrapper> implements Visitable{

	public VarDeclsInit(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VarDeclsInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDeclsInit) visitor.visit(this);
	}

	@Override
	public ListParent<VarDeclsWrapper> addChild(VarDeclsWrapper node) {
		// TODO Auto-generated method stub
		return super.addChild(node);
	}
	
	

}
