package syntaxTree;

import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class CompStat extends Internal implements Visitable {

	private String op;
	private Statements s;
	public CompStat(String op, Statements s) {
		super(op, s);
		this.op = op;
		this.s = s;
	}

	public String getOp() {
		return op;
	}

	public Statements getS() {
		return s;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
