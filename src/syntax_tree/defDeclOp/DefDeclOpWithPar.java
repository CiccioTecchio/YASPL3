package syntax_tree.defDeclOp;

import syntax_tree.Body;
import syntax_tree.ParDecls;
import syntax_tree.leaf.IdLeaf;
import syntax_tree.wrappers.DefWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class DefDeclOpWithPar extends DefWrapper implements Visitable {

	public DefDeclOpWithPar(String op, IdLeaf id, ParDecls par, Body b) {
		super(op, id, par, b);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public DefDeclOpWithPar accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DefDeclOpWithPar) visitor.visit(this);
	}

}
