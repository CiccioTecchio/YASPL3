package syntax_tree.wrappers;

import syntax_tree.VarDeclsInit;
import syntax_tree.comp.Internal;
import syntax_tree.leaf.TypeLeaf;
import visitor.Visitable;
import visitor.Visitor;

public class DeclWrapper extends Internal implements Visitable{

	//Costruttore per VarDecl
	public DeclWrapper(String op, TypeLeaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
		// TODO Auto-generated constructor stub
	}
	//Costruttore per DefDecl
	public DeclWrapper(String op, DefWrapper dw) {
		super(op, dw);
	}
	
	@Override
	public DeclWrapper accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DeclWrapper)visitor.visit(this);
	}

}
