package syntaxTree.statOp;

import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class IfThenOp extends Stat implements Visitable {

	public IfThenOp(String op, Expr e, CompStat cs) {
		super(op, e, cs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public IfThenOp accept(Visitor<?> visitor) {
		return (IfThenOp) visitor.visit(this);
	}

}
