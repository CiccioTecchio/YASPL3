package syntaxTree.wrapper;

import syntaxTree.VarInitValue;
import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public abstract class VarDeclsInitWrapper extends Internal {

	//VarNotInit
	public VarDeclsInitWrapper(String op, IdConst id) {
		super(op,id);
	}
	//VarInit
	public VarDeclsInitWrapper(String op, IdConst id, VarInitValue viv) {
		super(op,id,viv);
	}

	public abstract Object accept(Visitor<?> visitor);

	

}
