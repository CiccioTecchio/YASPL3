package syntaxTree;

import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Internal;
import syntaxTree.utils.ParDeclSon;
import visitor.Visitable;
import visitor.Visitor;

public class ParDecls extends Internal implements Visitable {

	private ArrayList<ParDeclSon> childList;
	
	public ParDecls(Location left, Location right, String op) {
		super(left, right,op);
		childList = new ArrayList<ParDeclSon>();
	}
	
	public ParDecls addChild(ParDeclSon pd){
		this.childList.add(0,pd);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<ParDeclSon> getChildList() {
		return childList;
	}

}
