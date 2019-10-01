package syntaxTree.statOp;

import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class DoWhileOp extends Stat implements Visitable {

	private String op;
	private CompStat cs;
	private Expr  e;
	
	public DoWhileOp(String op, CompStat cs, Expr e) {
		super(op, cs, e);
		this.op = op;
		this.cs = cs;
		this.e = e;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public CompStat getCs() {
		return cs;
	}

	public void setCs(CompStat cs) {
		this.cs = cs;
	}

	public Expr getE() {
		return e;
	}

	public void setE(Expr e) {
		this.e = e;
	}

}
