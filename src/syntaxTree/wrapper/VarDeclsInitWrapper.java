package syntaxTree.wrapper;

import syntaxTree.VarInitValue;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class VarDeclsInitWrapper extends Internal implements Visitable {

	public VarDeclsInitWrapper(String op) {
		super(op);
	}
	
	//VarNotInit
	public VarDeclsInitWrapper(String op, Leaf id) {
		super(op,id);
	}
	//VarInit
	public VarDeclsInitWrapper(String op, Leaf id, VarInitValue viv) {
		super(op,id,viv);
	}

	@Override
	public VarDeclsInitWrapper accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDeclsInitWrapper) visitor.visit(this);
	}

}
