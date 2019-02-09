package syntax_tree.wrappers;

import visitor.Visitor;

public class ExprWrapperInternal extends ExprWrapper {
	
	public ExprWrapperInternal(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op, e1, e2);
	}
	
	public ExprWrapperInternal accept(Visitor<?> v) {
		return (ExprWrapperInternal) v.visit(this);
	}
}
