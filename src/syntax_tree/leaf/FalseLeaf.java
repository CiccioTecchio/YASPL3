package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class FalseLeaf extends Leaf implements Visitable{

	public FalseLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public FalseLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (FalseLeaf) visitor.visit(this);
	}

}