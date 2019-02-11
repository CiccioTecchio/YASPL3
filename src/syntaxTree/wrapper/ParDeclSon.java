package syntaxTree.wrapper;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParDeclSon extends Internal implements Visitable {

	public ParDeclSon(String op, Leaf parType, Leaf type, Leaf id) {
		super(op, parType, type, id);
	}

	@Override
	public ParDeclSon accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (ParDeclSon) visitor.visit(this);
	}

}
