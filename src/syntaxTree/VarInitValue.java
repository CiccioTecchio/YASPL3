package syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class VarInitValue extends Internal implements Visitable {

	private String op;
	private Expr e;
	
	public VarInitValue(Location left, Location right, String op, Expr e) {
		super(left, right, op, e);
		this.op = op;
		this.e = e;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Expr getE() {
		return e;
	}

	
	

}
