package syntax_tree.defDeclOp;

import syntax_tree.Body;
import syntax_tree.leaf.IdLeaf;
import syntax_tree.wrappers.DefWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class DefDeclOp extends DefWrapper implements Visitable {

	public DefDeclOp(String op, IdLeaf id, Body b) {
		super(op, id, b);
		// TODO Auto-generated constructor stub
	}
	@Override
	public DefDeclOp accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DefDeclOp)visitor.visit(this);
	}
}