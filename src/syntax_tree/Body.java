package syntax_tree;

import syntax_tree.comp.*;
import visitor.Visitable;
import visitor.Visitor;

public class Body  extends Internal implements Visitable{

	
	public Body(String op, VarDecls vd, Statements s ) {
		super(op, vd, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Body accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Body) visitor.visit(this);
	}
	
}