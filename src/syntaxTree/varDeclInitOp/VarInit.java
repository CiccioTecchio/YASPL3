package syntaxTree.varDeclInitOp;

import syntaxTree.VarInitValue;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import syntaxTree.leaf.IdConst;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarInit extends VarDeclsInitWrapper implements Visitable {

	private String op;
	private IdConst id;
	private VarInitValue viv;


	public VarInit(String op, IdConst id, VarInitValue viv) {
		super(op,id,viv);
		this.op = op;
		this.id = id;
		this.viv = viv;
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

	public VarInitValue getViv() {
		return viv;
	}
	
	

}
