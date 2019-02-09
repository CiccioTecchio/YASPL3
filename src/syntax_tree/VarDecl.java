package syntax_tree;

import syntax_tree.leaf.TypeLeaf;
import syntax_tree.wrappers.DeclWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecl extends DeclWrapper implements Visitable{

	public VarDecl(String op, TypeLeaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VarDecl accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDecl) visitor.visit(this);
	}

}
