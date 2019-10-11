package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class SqrtOp extends Expr implements Visitable {

	private String op;
	private Expr e;
	
	public SqrtOp(String op, Expr e) {
		super(op, e);
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

	public void setOp(String op) {
		this.op = op;
	}

	public Expr getE() {
		return e;
	}

	public void setE(Expr e) {
		this.e = e;
	}

}
