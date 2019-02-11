package syntaxTree.statOp;

import syntaxTree.Args;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class WriteOp extends Stat implements Visitable {

	private String op;
	private Args a;
	
	public WriteOp(String op, Args a) {
		super(op, a);
		this.op = op;
		this.a = a;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Args getA() {
		return a;
	}
	
	



}
