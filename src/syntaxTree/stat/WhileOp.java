package syntaxTree.stat;

import syntaxTree.CompStat;
import syntaxTree.Expr;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class WhileOp extends Stat implements Visitable {

	public WhileOp(String op, Expr e, CompStat cs) {
		super(op, e, cs);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public WhileOp accept(Visitor<?> visitor) {
		return (WhileOp) visitor.visit(this);
	}

}
