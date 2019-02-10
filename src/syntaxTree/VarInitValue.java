package syntaxTree;

import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class VarInitValue extends Internal implements Visitable {

	public VarInitValue(String op, Expr e) {
		super(op, e);
	}

	@Override
	public VarInitValue accept(Visitor<?> visitor) {
		return (VarInitValue) visitor.visit(this);
	}

}
