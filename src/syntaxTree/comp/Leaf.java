package syntaxTree.comp;

import syntaxTree.comp.Node;
import visitor.Visitable;
import visitor.Visitor;

public class Leaf extends Node implements Visitable{

	private String value;
	
	public Leaf(String op, String value) {
		super(op);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return value;
	}

	@Override
	public String toString() {
		return "[ "+this.getOp()+" , "+value+" ]";
	}
	
	

}
