package syntaxTree.statOp;

import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class WhileOp extends Stat implements Visitable {

	private String op;
	private Expr e;
	private CompStat cs;
	
	public WhileOp(String op, Expr e, CompStat cs) {
		super(op, e, cs);
		this.op = op;
		this.e = e;
		this.cs = cs;
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

	public CompStat getCs() {
		return cs;
	}
	
	

}
