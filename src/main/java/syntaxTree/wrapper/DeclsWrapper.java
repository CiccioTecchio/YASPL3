package syntaxTree.wrapper;

import java_cup.runtime.ComplexSymbolFactory.Location;
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
	
	public DeclsWrapper(Location left, Location right, String op, IdConst id, ParDecls pd, Body b) {
		super(left, right, op, id, pd, b);
	}
	
	public DeclsWrapper(Location left, Location right, String op, IdConst id, Body b) {
		super(left, right, op, id, b);
	}

	public DeclsWrapper(Location left, Location right, String op, TypeLeaf t, VarDeclsInit vdi) {
		super(left, right, op, t, vdi);
	}

	public abstract Object accept(Visitor<?> visitor);

}
