package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class TrueLeaf extends Leaf implements Visitable{

	public TrueLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public TrueLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (TrueLeaf) visitor.visit(this);
	}

}