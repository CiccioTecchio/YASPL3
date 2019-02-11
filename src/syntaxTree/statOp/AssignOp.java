package syntaxTree.statOp;

import syntaxTree.Expr;
import syntaxTree.Stat;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class AssignOp extends Stat implements Visitable {

	private String op;
	private Leaf id;
	private Expr e;
	public AssignOp(String op, Leaf id, Expr e) {
		super(op, id, e);
		this.op = op;
		this.id = id;
		this.e = e;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getId() {
		return id;
	}

	public Expr getE() {
		return e;
	}
	
	
}
