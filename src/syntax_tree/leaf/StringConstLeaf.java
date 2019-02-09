package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class StringConstLeaf extends Leaf implements Visitable{

	public StringConstLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public StringConstLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (StringConstLeaf) visitor.visit(this);
	}

}