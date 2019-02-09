package syntax_tree;

import syntax_tree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;
import syntax_tree.leaf.IdLeaf;
import syntax_tree.wrappers.ExprWrapper;

public class Stat extends Internal implements Visitable{

	//ReadOp
	public Stat(String op, Vars v) {
		super(op, v);
	}
	//WriteOp
	public Stat(String op, Args a) {
		super(op, a);
	}
	//AssignOp
	public Stat(String op, IdLeaf id, ExprWrapper e) {
		super(op, id, e);
	}
	//CallOp
	public Stat(String op, IdLeaf id, Args a) {
		super(op, id, a);
	}
	//CallOp
	public Stat(String op, IdLeaf id) {
		super(op, id);
	}
	//IfThenElseOp
	public Stat(String op, ExprWrapper e, CompStat cs1, CompStat cs2) {
		super(op, e, cs1, cs2);
	}
	//IfThenOp && WhileOp (TODO ricorda di controllare String op)
	public Stat(String op, ExprWrapper e, CompStat cs) {
		super(op, e, cs);
	}
	
	@Override
	public Stat accept(Visitor<?> visitor) {
		return (Stat) visitor.visit(this);
	}
	
}