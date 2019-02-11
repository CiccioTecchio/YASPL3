package syntaxTree;

import syntaxTree.comp.Leaf;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecl extends DeclsWrapper implements Visitable {

	public VarDecl(String op, Leaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
	}

	@Override
	public VarDecl accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDecl)visitor.visit(this);
	}

}
