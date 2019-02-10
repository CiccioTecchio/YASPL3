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
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<VarDecl> childList(VarDecl n){
		this.childList.add(n);
		return this.childList;
	}
	
	

	@Override
	public VarDecls accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (VarDecls) visitor.visit(this);
	}

}
