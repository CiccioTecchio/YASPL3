package syntaxTree.statOp;

import syntaxTree.Args;
import syntaxTree.Stat;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class CallOp extends Stat implements Visitable {
	
	private String op;
	private Leaf id;
	private Args a;
	
	public CallOp(String op, Leaf id, Args a) {
		super(op, id, a);
		this.op = op;
		this.id = id;
		this.a = a;
	}

	public CallOp(String op, Leaf id) {
		super(op, id);
		this.op = op;
		this.id = id;
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

	public Args getA() {
		return a;
	}

	
}
