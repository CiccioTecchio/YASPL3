package syntaxTree.stat;

import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class IfThenElseOp extends Stat implements Visitable {

	public IfThenElseOp(String op, Expr e, CompStat cs1, CompStat cs2) {
		super(op, e, cs1, cs2);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public IfThenElseOp accept(Visitor<?> visitor) {
		return (IfThenElseOp) visitor.visit(this);
	}

}
