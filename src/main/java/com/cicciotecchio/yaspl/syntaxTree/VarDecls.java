package syntaxTree;

import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Internal;
import syntaxTree.declsOp.VarDecl;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecls extends Internal implements Visitable {
	
	private ArrayList<VarDecl> childList;
	
	//primo nodo
	public VarDecls(Location left, Location right, String op) {
		super(left, right, op);
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
