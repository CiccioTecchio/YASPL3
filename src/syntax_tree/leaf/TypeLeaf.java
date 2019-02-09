package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class TypeLeaf extends Leaf implements Visitable{

	public TypeLeaf(String op, String value) {
		super(op, value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TypeLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (TypeLeaf) visitor.visit(this);
	}

	
}
