package syntaxTree;

import java.util.ArrayList;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class Vars extends Internal implements Visitable {

	private ArrayList<Leaf> childList;
	
	public Vars(String op, Leaf id) {
		super(op, id);
		this.childList = new ArrayList<Leaf>();
		childList.add(id);
	}
	
	public ArrayList<Leaf> addChild(Leaf id){
		this.childList.add(id);
		return this.childList;
	}

	@Override
	public Vars accept(Visitor<?> visitor) {
		return (Vars) visitor.visit(this);
	}

}
