package syntaxTree.varDeclInitOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.VarInitValue;
import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarInit extends VarDeclsInitWrapper implements Visitable {

	private String op;
	private IdConst id;
	private VarInitValue viv;


	public VarInit(Location left, Location right, String op, IdConst id, VarInitValue viv) {
		super(left, right, op,id,viv);
		this.op = op;
		this.id = id;
		this.viv = viv;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
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
