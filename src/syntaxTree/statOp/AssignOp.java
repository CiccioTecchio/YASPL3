package syntaxTree.statOp;

import syntaxTree.Expr;
import syntaxTree.Stat;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class AssignOp extends Stat implements Visitable {

	public AssignOp(String op, Leaf id, Expr e) {
		super(op, id, e);
	}

	@Override
	public AssignOp accept(Visitor<?> visitor) {
		return (AssignOp) visitor.visit(this);
	}
}
