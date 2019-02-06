package syntax_tree;

public class Leaf extends Node {

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
	public String toString() {
		return "[ "+this.getOp()+" , "+value+" ]";
	}
	
	

}
