package syntaxTree.varDeclsInit;

import syntaxTree.VarInitValue;
import syntaxTree.comp.Leaf;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarInit extends VarDeclsInitWrapper implements Visitable {

	public VarInit(String op, Leaf id, VarInitValue viv) {
		super(op, id, viv);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public VarInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarInit) visitor.visit(this);
	}

}
