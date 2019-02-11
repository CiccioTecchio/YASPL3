package syntaxTree;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class CompStat extends Internal implements Visitable {

	public CompStat(String op, Statements s) {
		super(op, s);
	}

	@Override
	public CompStat accept(Visitor<?> visitor) {
		return (CompStat) visitor.visit(this);
	}

}
