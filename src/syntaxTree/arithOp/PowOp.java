package syntaxTree.arithOp;

import syntaxTree.Expr;
import visitor.Visitable;
import visitor.Visitor;

public class PowOp extends Expr implements Visitable{

	private String op;
	private Expr base;
	private Expr esp;
	
	public PowOp(String op, Expr base, Expr esp) {
		super(op, base, esp);
		this.op = op;
		this.base = base;
		this.esp = esp;
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

	public Expr getBase() {
		return base;
	}

	public void setBase(Expr base) {
		this.base = base;
	}

	public Expr getEsp() {
		return esp;
	}

	public void setEsp(Expr esp) {
		this.esp = esp;
	}
	
	
}
