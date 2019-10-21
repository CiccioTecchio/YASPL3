package syntaxTree.logicOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class NotOp extends Expr implements Visitable {

	private String op;
	private Expr e;
	
	public NotOp(Location left, Location right, String op, Expr e) {
		super(left, right, op, e);
		this.op = op;
		this.e = e;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Expr getE() {
		return e;
	}
	
	

}
