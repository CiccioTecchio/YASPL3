package syntax_tree.wrappers;

import syntax_tree.VarInitValue;
import syntax_tree.comp.Internal;
import syntax_tree.comp.Node;
import syntax_tree.leaf.IdLeaf;

public class VarDeclsWrapper extends Internal{
	
	//VarNotInitOp
	public VarDeclsWrapper(String op, IdLeaf id) {
		super(op, id);
		// TODO Auto-generated constructor stub
	}
	//VarInit
	public VarDeclsWrapper(String op, IdLeaf id, VarInitValue viv) {
		super(op, id, viv);
	}
}
