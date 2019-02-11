package syntaxTree.logicOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class NotOp extends Expr implements Visitable {
	
	private String op;
	private Expr e;
	
	public NotOp(String op, Expr e) {
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

	public Expr getE() {
		return e;
	}

}
