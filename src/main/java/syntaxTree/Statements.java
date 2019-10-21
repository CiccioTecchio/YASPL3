package syntaxTree;

import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Statements extends Internal implements Visitable {

	private ArrayList<Stat> childList;
	
	public Statements(Location left, Location right, String op) {
		super(left, right, op);
		this.childList = new ArrayList<Stat>();
	}
	
	public Statements addChild(Stat s){
		this.childList.add(0,s);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<Stat> getChildList() {
		return childList;
	}
	
	
	
	

}
