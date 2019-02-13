package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class UminusOp extends Expr implements Visitable {

	private String op;
	private Expr e;
	
	public UminusOp(String op, Expr e) {
		super(op, e);
		this.e = e;
		this.op = op;
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
