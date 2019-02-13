package syntaxTree.declsOp;

import syntaxTree.VarDeclsInit;
import syntaxTree.leaf.TypeLeaf;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecl extends DeclsWrapper implements Visitable {

	private String op;
	private TypeLeaf t;
	private VarDeclsInit vdi;
	
	public VarDecl(String op, TypeLeaf t, VarDeclsInit vdi) {
		super(op, t, vdi);
		this.op = op;
		this.t = t;
		this.vdi = vdi;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public TypeLeaf getT() {
		return t;
	}

	public VarDeclsInit getVdi() {
		return vdi;
	}
	
	

}
