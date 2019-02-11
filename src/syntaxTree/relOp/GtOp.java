package syntaxTree.relOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class GtOp extends Expr implements Visitable {

	private String op;
	private Expr e1;
	private Expr e2;
	
	public GtOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		this.op = op;
		this.e1 = e1;
		this.e2 = e2;
	}

	public String getOp() {
		return op;
	}

	public Expr getE1() {
		return e1;
	}

	public Expr getE2() {
		return e2;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	
}
