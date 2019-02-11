package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class Decls extends Internal implements Visitable {

	private ArrayList<DeclsWrapper> childList;
	private String op;
	
	public Decls(String op) {
		super(op);
		this.childList = new ArrayList<DeclsWrapper>();
		this.op = op;
	}
	
	public String getOp() {
		return op;
	}

	public Decls addChild(DeclsWrapper dw){
		childList.add(dw);
		return this;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
	public ArrayList<DeclsWrapper> getChildList() {
		return childList;
	}

}
