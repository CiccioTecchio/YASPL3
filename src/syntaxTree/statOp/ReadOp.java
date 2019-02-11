package syntaxTree.statOp;

import syntaxTree.Stat;
import syntaxTree.Vars;
import visitor.Visitable;
import visitor.Visitor;

public class ReadOp extends Stat implements Visitable {

	public ReadOp(String op, Vars v) {
		super(op, v);
	}

	@Override
	public ReadOp accept(Visitor<?> visitor) {
		return (ReadOp) visitor.visit(this);
	}
	
}
