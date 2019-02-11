package syntaxTree.varDeclsInit;

import syntaxTree.comp.Leaf;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarNotInit extends VarDeclsInitWrapper implements Visitable {

	public VarNotInit(String op, Leaf id) {
		super(op, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VarNotInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarNotInit) visitor.visit(this);
	}

}
