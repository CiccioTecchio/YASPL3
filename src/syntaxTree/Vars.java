package syntaxTree;

import java.util.ArrayList;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import syntaxTree.leaf.IdConst;
import visitor.Visitable;
import visitor.Visitor;

public class Vars extends Internal implements Visitable {

	private ArrayList<IdConst> childList;
	
	public Vars(String op) {
		super(op);
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
