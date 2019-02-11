package syntaxTree;

import java.util.ArrayList;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class Vars extends Internal implements Visitable {

	private ArrayList<Leaf> childList;
	private String op;
	public Vars(String op) {
		super(op);
		this.childList = new ArrayList<Leaf>();
		this.op = op;
	}
	
	public Vars addChild(Leaf id){
		this.childList.add(id);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<Leaf> getChildList() {
		return childList;
	}

	public String getOp() {
		return op;
	}

}
