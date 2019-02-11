package syntaxTree;

import syntaxTree.comp.Leaf;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class DefDecl extends DeclsWrapper implements Visitable {

	//Definizione con parametri
	public DefDecl(String op, Leaf id, ParDecls pd, Body b) {
		super(op, id, pd, b);
	}
	
	//Definizione senza parametri
	public DefDecl(String op, Leaf id, Body b) {
		super(op, id, b);
	}
	
	@Override
	public DefDecl accept(Visitor<?> visitor) {
		return (DefDecl) visitor.visit(this);
	}

}
