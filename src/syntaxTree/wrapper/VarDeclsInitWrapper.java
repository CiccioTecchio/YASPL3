package syntaxTree.wrapper;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.VarInitValue;
import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public abstract class VarDeclsInitWrapper extends Internal {

	//VarNotInit
	public VarDeclsInitWrapper(Location left, Location right, String op, IdConst id) {
		super(left, right, op,id);
	}
	//VarInit
	public VarDeclsInitWrapper(Location left, Location right, String op, IdConst id, VarInitValue viv) {
		super(left, right, op,id,viv);
	}

	public abstract Object accept(Visitor<?> visitor);

	

}
