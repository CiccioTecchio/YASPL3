package syntax_tree;

import syntax_tree.wrappers.DeclWrapper;
import syntax_tree.wrappers.DefWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class DefDecl extends DeclWrapper implements Visitable{
	
	public DefDecl(String op, DefWrapper dw) {
		super(op, dw);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DefDecl accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DefDecl) visitor.visit(this);
	}
	
}