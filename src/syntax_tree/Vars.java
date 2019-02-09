package syntax_tree;

import syntax_tree.leaf.IdLeaf;
import syntax_tree.wrappers.ListParent;
import visitor.Visitable;
import visitor.Visitor;

public class Vars extends ListParent<IdLeaf> implements Visitable{

	public Vars(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Vars accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Vars) visitor.visit(this);
	}
	
}