package syntaxTree;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Node;
import visitor.Visitable;
import visitor.Visitor;

public class Programma extends Internal implements Visitable {

	public Programma(String op, Decls d, Statements s) {
		super(op, d, s);
	}

	@Override
	public Programma accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Programma) visitor.visit(this);
	}

}
