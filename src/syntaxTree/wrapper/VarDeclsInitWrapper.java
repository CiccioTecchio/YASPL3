package syntaxTree.wrapper;

import syntaxTree.VarInitValue;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class VarDeclsInitWrapper extends Internal implements Visitable {

	private String op;
	private Leaf id;
	private VarInitValue viv;
	
	public VarDeclsInitWrapper(String op) {
		super(op);
		this.op = op;
	}
	
	//VarNotInit
	public VarDeclsInitWrapper(String op, Leaf id) {
		super(op,id);
		this.op = op;
		this.id = id;
	}
	//VarInit
	public VarDeclsInitWrapper(String op, Leaf id, VarInitValue viv) {
		super(op,id,viv);
		this.op=op;
		this.id=id;
		this.viv=viv;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getId() {
		return id;
	}

	public VarInitValue getViv() {
		return viv;
	}
	

}
