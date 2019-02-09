package syntax_tree;

import syntax_tree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class CompStat extends Internal implements Visitable {
	
	public CompStat(String op, Statements s) {
		super(op, s);
	}

	@Override
	public CompStat accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (CompStat) visitor.visit(this);
	}
	
}