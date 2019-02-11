package syntaxTree;

import java.util.ArrayList;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class Vars extends Internal implements Visitable {

	private ArrayList<Leaf> childList;
	
	public Vars(String op) {
		super(op);
		this.childList = new ArrayList<Leaf>();
	}
	
	public Vars addChild(Leaf id){
		this.childList.add(id);
		return this;
	}

	@Override
	public Vars accept(Visitor<?> visitor) {
		return (Vars) visitor.visit(this);
	}

}
