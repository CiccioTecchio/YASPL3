package syntaxTree;

import java.util.ArrayList;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Node;
import syntaxTree.wrapper.DeclsWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class Decls extends DeclsWrapper implements Visitable {

	private ArrayList<DeclsWrapper> childList;
	
	public Decls(String op) {
		super(op);
		this.childList = new ArrayList<DeclsWrapper>();
	}
	
	public ArrayList<DeclsWrapper> addChild(DeclsWrapper dw){
		childList.add(dw);
		return this.childList;
	}
	
	@Override
	public Decls accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Decls) visitor.visit(this);
	}

}
