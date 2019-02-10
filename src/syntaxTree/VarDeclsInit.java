package syntaxTree;

import java.util.ArrayList;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class VarDeclsInit extends VarDeclsInitWrapper implements Visitable {

	private ArrayList<VarDeclsInitWrapper> childList;
	
	//primo nodo
	public VarDeclsInit(String op) {
		super(op);
		this.childList = new ArrayList<>();
	}
/*	
	//VarInit
	public VarDeclsInit(String op, Leaf id, VarInitValue viv) {
		super(op, id, viv);
	}
	
	//VarNotInit
	public VarDeclsInit(String op, Leaf id) {
		super(op, id);
	}
*/
	public ArrayList<VarDeclsInitWrapper> addChild (VarDeclsInitWrapper vdiw){
		this.childList.add(vdiw);
		return this.childList;
	}
	

	@Override
	public VarDeclsInit accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDeclsInit) visitor.visit(this);
	}

}
