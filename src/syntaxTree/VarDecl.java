package syntaxTree;

import syntaxTree.comp.Leaf;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecl extends DeclsWrapper implements Visitable {

	private String op;
	private Leaf t;
	private VarDeclsInit vdi;
	
	public VarDecl(String op, Leaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
		this.op = op;
		this.t = t;
		this.vdi = vdi;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getT() {
		return t;
	}

	public VarDeclsInit getVdi() {
		return vdi;
	}
	
}
