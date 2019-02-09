package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class IdLeaf extends Leaf implements Visitable{

	public IdLeaf(String op, String value) {
		super(op,value);
	}
	
	@Override
	public IdLeaf accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (IdLeaf) visitor.visit(this);
	}

}