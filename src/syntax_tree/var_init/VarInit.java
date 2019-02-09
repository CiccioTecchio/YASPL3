package syntax_tree.var_init;

import syntax_tree.VarInitValue;
import syntax_tree.leaf.IdLeaf;
import syntax_tree.wrappers.VarDeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarInit extends VarDeclsWrapper implements Visitable{

	public VarInit(String op, IdLeaf id, VarInitValue viv) {
		super(op, id,viv);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VarInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarInit) visitor.visit(this);
	}

}
