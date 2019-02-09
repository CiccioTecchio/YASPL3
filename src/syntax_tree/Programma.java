package syntax_tree;

import syntax_tree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Programma extends Internal implements Visitable{

	public Programma(String op, Decls d, Statements s) {
		super(op, d, s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Programma accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Programma) visitor.visit(this);
	}

}
