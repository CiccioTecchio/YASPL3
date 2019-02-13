package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class Decls extends Internal implements Visitable {

	private ArrayList<DeclsWrapper> childList;
	
	public Decls(String op) {
		super(op);
		this.childList = new ArrayList<DeclsWrapper>();
	}
	
	public Decls addChild(DeclsWrapper dw){
		childList.add(0,dw);
		return this;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<DeclsWrapper> getChildList() {
		return childList;
	}
	
	

}
