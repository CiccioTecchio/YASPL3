package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecls extends Internal implements Visitable {
	
	private ArrayList<VarDecl> childList;
	
	//primo nodo
	public VarDecls(String op) {
		super(op);
		this.childList = new ArrayList<VarDecl>();
	}
	
	public ArrayList<VarDecl> addChild(VarDecl n){
		this.childList.add(n);
		return this.childList;
	}
	
	

	@Override
	public VarDecls accept(Visitor<?> visitor) {
		return (VarDecls) visitor.visit(this);
	}

}
