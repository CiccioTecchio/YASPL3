package syntaxTree.wrapper;

import syntaxTree.Body;
import syntaxTree.ParDecls;
import syntaxTree.VarDeclsInit;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class DeclsWrapper extends Internal implements Visitable {

	private String op;
	private Leaf id;
	private Leaf t; 
	private ParDecls pd;
	private Body b;
	private VarDeclsInit vdi;
	
	public DeclsWrapper(String op) {
		super(op);
		this.op = op;
	}
	
	public DeclsWrapper(String op, Leaf id, ParDecls pd, Body b) {
		super(op, id, pd, b);
		this.op = op;
		this.id = id;
		this.pd = pd;
		this.b = b;
	}
	
	//Definizione senza parametri
	public DeclsWrapper(String op, Leaf id, Body b) {
		super(op, id, b);
		this.op = op;
		this.id = id;
		this.b = b;
	}

	public DeclsWrapper(String op,  Leaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
		this.op = op;
		this.t = t;
		this.vdi = vdi;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getId() {
		return id;
	}

	public Leaf getT() {
		return t;
	}

	public ParDecls getPd() {
		return pd;
	}

	public Body getB() {
		return b;
	}

	public VarDeclsInit getVdi() {
		return vdi;
	}

	
	
}
