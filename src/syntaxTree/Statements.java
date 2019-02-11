package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Statements extends Internal implements Visitable {

	private ArrayList<Stat> childList;
	private String op;
	
	public Statements(String op) {
		super(op);
		this.childList = new ArrayList<Stat>();
		this.op = op;
	}
	
	public Statements addChild(Stat s){
		this.childList.add(s);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<Stat> getChildList() {
		return childList;
	}

	public String getOp() {
		return op;
	}
	
	

}
