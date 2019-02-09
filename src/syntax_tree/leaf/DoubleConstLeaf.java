package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class DoubleConstLeaf extends Leaf implements Visitable{

	public DoubleConstLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public DoubleConstLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DoubleConstLeaf) visitor.visit(this);
	}

}