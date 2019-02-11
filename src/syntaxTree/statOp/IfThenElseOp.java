package syntaxTree.statOp;

import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class IfThenElseOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private CompStat cs1;
	private CompStat cs2;
	
	public IfThenElseOp(String op, Expr e, CompStat cs1, CompStat cs2) {
		super(op, e, cs1, cs2);
		this.op = op;
		this.e = e;
		this.cs1 = cs1;
		this.cs2 = cs2;
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

	public CompStat getCs1() {
		return cs1;
	}

	public CompStat getCs2() {
		return cs2;
	}
	
	

}
