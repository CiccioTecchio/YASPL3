package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class VarDecls extends Internal implements Visitable {
	
	private ArrayList<VarDecl> childList;
	private String op;
	
	//primo nodo
	public VarDecls(String op) {
		super(op);
		this.childList = new ArrayList<VarDecl>();
		this.op = op;
	}
	
	public VarDecls addChild(VarDecl n){
		this.childList.add(n);
		return this;
	}	

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<VarDecl> getChildList() {
		return childList;
	}

	public String getOp() {
		return op;
	}
	
}
