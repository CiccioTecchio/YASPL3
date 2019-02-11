package syntaxTree.wrapper;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParDeclSon extends Internal implements Visitable {

	private String op;
	private Leaf parType;
	private Leaf type;
	private Leaf id;
	
	public ParDeclSon(String op, Leaf parType, Leaf type, Leaf id) {
		super(op, parType, type, id);
		this.op = op;
		this.parType = parType;
		this.type = type;
		this.id = id;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getParType() {
		return parType;
	}

	public Leaf getType() {
		return type;
	}

	public Leaf getId() {
		return id;
	}
	

}
