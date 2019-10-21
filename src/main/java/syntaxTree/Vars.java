package syntaxTree;

import java.util.ArrayList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Internal;
import syntaxTree.components.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public class Vars extends Internal implements Visitable {

	private ArrayList<IdConst> childList;
	
	public Vars(Location left, Location right, String op) {
		super(left, right, op);
		this.childList = new ArrayList<IdConst>();
	}
	
	public Vars addChild(IdConst id){
		this.childList.add(0,id);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<IdConst> getChildList() {
		return childList;
	}
	
	

}
