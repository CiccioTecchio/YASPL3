package syntax_tree;

import syntax_tree.wrappers.ListParent;
import visitor.Visitable;
import visitor.Visitor;

public class Statements extends ListParent<Stat> implements Visitable{

	public Statements(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Statements accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Statements) visitor.visit(this);
	}

	@Override
	public ListParent<Stat> addChild(Stat node) {
		// TODO Auto-generated method stub
		return super.addChild(node);
	}
	
	

}
