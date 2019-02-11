package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDeclsInit extends Internal implements Visitable {

	private ArrayList<VarDeclsInitWrapper> childList;
	
	//primo nodo
	public VarDeclsInit(String op) {
		super(op);
		this.childList = new ArrayList<>();
	}

	public VarDeclsInit addChild (VarDeclsInitWrapper vdiw){
		this.childList.add(vdiw);
		return this;
	}
	

	@Override
	public VarDeclsInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDeclsInit) visitor.visit(this);
	}

}
