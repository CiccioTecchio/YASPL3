package syntax_tree.wrappers;

import syntax_tree.leaf.IdLeaf;
import syntax_tree.Body;
import syntax_tree.ParDecls;
import syntax_tree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class DefWrapper extends Internal implements Visitable {

	//DefDeclOp
	public DefWrapper(String op, IdLeaf id, Body b) {
		super(op, id, b);
		// TODO Auto-generated constructor stub
	}
	
	//DefDeclOpWithPar
	public DefWrapper(String op, IdLeaf id, ParDecls pd,  Body b) {
		super(op, id, pd, b);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DefWrapper accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DefWrapper) visitor.visit(this);
	}

}
