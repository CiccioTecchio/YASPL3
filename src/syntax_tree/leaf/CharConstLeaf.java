package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class CharConstLeaf extends Leaf implements Visitable{

	public CharConstLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public CharConstLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (CharConstLeaf) visitor.visit(this);
	}

}