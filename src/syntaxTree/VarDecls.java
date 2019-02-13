package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.declsOp.VarDecl;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecls extends Internal implements Visitable {
	
	private ArrayList<VarDecl> childList;
	
	//primo nodo
	public VarDecls(String op) {
		super(op);
		this.childList = new ArrayList<VarDecl>();
	}
	
	public VarDecls addChild(VarDecl n){
		this.childList.add(0,n);
		return this;
	}	

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<VarDecl> getChildList() {
		return childList;
	}

	
}
