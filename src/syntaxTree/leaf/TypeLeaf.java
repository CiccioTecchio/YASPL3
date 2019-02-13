package syntaxTree.leaf;

import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class TypeLeaf extends Leaf implements Visitable {

	public TypeLeaf(String op, String value) {
		super(op, value);
		// TODO Auto-generated constructor stub
	}
	
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}
}
