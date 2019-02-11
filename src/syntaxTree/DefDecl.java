package syntaxTree;

import syntaxTree.comp.Leaf;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class DefDecl extends DeclsWrapper implements Visitable {

	private String op;
	private Leaf id;
	private ParDecls pd;
	private Body b;
	
	//Definizione con parametri
	public DefDecl(String op, Leaf id, ParDecls pd, Body b) {
		super(op, id, pd, b);
		this.op = op;
		this.id = id;
		this.pd = pd;
		this.b  = b;
	}
	
	//Definizione senza parametri
	public DefDecl(String op, Leaf id, Body b) {
		super(op, id, b);
		this.op = op;
		this.id = id;
		this.b  = b;
	}
	
	public String getOp() {
		return op;
	}

	public Leaf getId() {
		return id;
	}

	public ParDecls getPd() {
		return pd;
	}

	public Body getB() {
		return b;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
