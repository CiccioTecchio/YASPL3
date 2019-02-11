package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDeclsInit extends Internal implements Visitable {

	private ArrayList<VarDeclsInitWrapper> childList;
	private String op;
	
	//primo nodo
	public VarDeclsInit(String op) {
		super(op);
		this.childList = new ArrayList<>();
		this.op = op;
	}

	public VarDeclsInit addChild (VarDeclsInitWrapper vdiw){
		this.childList.add(vdiw);
		return this;
	}
	

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public ArrayList<VarDeclsInitWrapper> getChildList() {
		return childList;
	}

	public String getOp() {
		return op;
	}

}
