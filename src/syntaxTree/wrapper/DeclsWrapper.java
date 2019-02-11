package syntaxTree.wrapper;

import syntaxTree.Body;
import syntaxTree.ParDecls;
import syntaxTree.VarDeclsInit;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class DeclsWrapper extends Internal implements Visitable {

	public DeclsWrapper(String op) {
		super(op);
	}
	
	public DeclsWrapper(String op, Leaf id, ParDecls pd, Body b) {
		super(op, id, pd, b);
	}
	
	//Definizione senza parametri
	public DeclsWrapper(String op, Leaf id, Body b) {
		super(op, id, b);
	}

	public DeclsWrapper(String op,  Leaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
	}

	@Override
	public DeclsWrapper accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DeclsWrapper) visitor.visit(this);
	}

}
