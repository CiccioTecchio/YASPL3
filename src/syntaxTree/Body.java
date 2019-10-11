package syntaxTree;

import syntaxTree.components.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Body extends Internal implements Visitable {

	private String op;
	private VarDecls vd;
	private Statements s;
	
	public Body(String op, VarDecls vd, Statements s) {
		super(op, vd, s);
		this.op = op;
		this.vd = vd;
		this.s = s;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public VarDecls getVd() {
		return vd;
	}

	public Statements getS() {
		return s;
	}
	
}
