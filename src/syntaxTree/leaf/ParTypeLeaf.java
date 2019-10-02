package syntaxTree.leaf;

import syntaxTree.components.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParTypeLeaf extends Leaf implements Visitable {

	public ParTypeLeaf(String op, String value) {
		super(op, value);
		// TODO Auto-generated constructor stub
	}
	
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
