package syntaxTree.wrapper;

import syntaxTree.Body;
import syntaxTree.ParDecls;
import syntaxTree.VarDeclsInit;
import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import syntaxTree.leaf.TypeLeaf;
import visitor.Visitable;
import visitor.Visitor;

public abstract class DeclsWrapper extends Internal{
	
	public DeclsWrapper(String op, IdConst id, ParDecls pd, Body b) {
		super(op, id, pd, b);
	}
	
	public DeclsWrapper(String op, IdConst id, Body b) {
		super(op, id, b);
	}

	public DeclsWrapper(String op, TypeLeaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
	}

	public abstract Object accept(Visitor<?> visitor);

}
