package syntaxTree.stat;

import syntaxTree.Args;
import syntaxTree.Stat;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class CallOp extends Stat implements Visitable {

	public CallOp(String op, Leaf id, Args a) {
		super(op, id, a);
		// TODO Auto-generated constructor stub
	}

	public CallOp(String op, Leaf id) {
		super(op, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public CallOp accept(Visitor<?> visitor) {
		return (CallOp) visitor.visit(this);
	}

}
