package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class IntConstLeaf extends Leaf implements Visitable{

	public IntConstLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public IntConstLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (IntConstLeaf) visitor.visit(this);
	}

}