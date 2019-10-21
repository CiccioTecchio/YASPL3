package syntaxTree.declsOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.Body;
import syntaxTree.ParDecls;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import syntaxTree.leaf.TypeLeaf;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class DefDeclPar extends DeclsWrapper implements Visitable {

	private String op;
	private IdConst id;
	private ParDecls pd;
	private Body b;
	
	public DefDeclPar(Location left, Location right, String op, IdConst id, ParDecls pd, Body b) {
		super(left, right, op, id, pd, b);
		this.op = op;
		this.id = id;
		this.pd = pd;
		this.b = b;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public IdConst getId() {
		return id;
	}

	public ParDecls getPd() {
		return pd;
	}

	public Body getB() {
		return b;
	}
	
	

}
