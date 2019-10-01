package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class ModOp extends Expr implements Visitable {

	private String op;
	private Expr e1;
	private Expr e2;
	
	public ModOp(String op, Expr e1, Expr e2) {
		super(op, e1, e2);
		this.op = op;
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Expr getE1() {
		return e1;
	}

	public void setE1(Expr e1) {
		this.e1 = e1;
	}

	public Expr getE2() {
		return e2;
	}

	public void setE2(Expr e2) {
		this.e2 = e2;
	}

}
