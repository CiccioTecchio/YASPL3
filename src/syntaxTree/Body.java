package syntaxTree;

import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Body extends Internal implements Visitable {

	public Body(String op, VarDecls vd, Statements s) {
		super(op, vd, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Body accept(Visitor<?> visitor) {
		return (Body) visitor.visit(this);
	}

}
