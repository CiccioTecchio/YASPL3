package syntaxTree.statOp;

import syntaxTree.Args;
import syntaxTree.Expr;
import syntaxTree.Stat;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public class AssignOp extends Stat implements Visitable {

	private String op;
	private IdConst id;
	//private Expr e;
	private Args a;
	
	/*public AssignOp(String op, IdConst id, Expr e) {
		super(op, id, e);
		this.op = op;
		this.id = id;
		this.e = e;
	}*/

	public AssignOp(String op, IdConst id, Args a) {
		super(op, id, a);
		this.op = op;
		this.id = id;
		this.a = a;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public IdConst getId() {
		return id;
	}

	public Args getA() {
		return a;
	}
	
	
}
