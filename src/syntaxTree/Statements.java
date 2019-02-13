package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Node;
import visitor.Visitable;
import visitor.Visitor;

public class Statements extends Internal implements Visitable {

	private ArrayList<Stat> childList;
	
	public Statements(String op) {
		super(op);
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
