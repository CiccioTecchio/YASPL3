package syntax_tree.wrappers;

import syntax_tree.comp.Leaf;
import visitor.Visitor;

public class ExprWrapperLeaf extends ExprWrapper {

	public ExprWrapperLeaf(String op, Leaf l) {
		super(op,l);
	}
	
	public ExprWrapperLeaf accept(Visitor<?> v) {
		return (ExprWrapperLeaf) v.visit(this);
	}
}