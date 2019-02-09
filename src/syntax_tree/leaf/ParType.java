package syntax_tree.leaf;

import syntax_tree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParType extends Leaf implements Visitable{

	public ParType(String op, String value) {
		super(op, value);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ParType accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (ParType) visitor.visit(this);
	}
	
}