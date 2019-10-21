package syntaxTree.statOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.Body;
import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class WhileOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private CompStat cs;
	private Body b;
	
	public WhileOp(Location left, Location right, String op, Expr e, CompStat cs) {
		super(left, right, op, e, cs);
		this.op = op;
		this.e = e;
		this.cs = cs;
	}
	
	public WhileOp(Location left, Location right, String op, Expr e, Body b) {
		super(left, right, op, e, b);
		this.op = op;
		this.e = e;	
		this.b = b;
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

	public CompStat getCs() {
		return cs;
	}
	
	public Body getBody() {
		return b;
	}
	
	

}
