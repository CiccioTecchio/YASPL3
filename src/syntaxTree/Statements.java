package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Statements extends Internal implements Visitable {

	private ArrayList<Stat> childList;
	
	public Statements(String op) {
		super(op);
		this.childList = new ArrayList<Stat>();
	}
	
	public ArrayList<Stat> addChild(Stat s){
		this.childList.add(s);
		return this.childList;
	}

	@Override
	public Statements accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Statements) visitor.visit(this);
	}

}
