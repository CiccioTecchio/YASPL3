package syntax_tree.var_init;

import syntax_tree.leaf.IdLeaf;
import syntax_tree.wrappers.VarDeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarNotInit extends VarDeclsWrapper implements Visitable{

	public VarNotInit(String op, IdLeaf id) {
		super(op, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VarNotInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarNotInit) visitor.visit(this);
	}

}
