package syntaxTree.leaf;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.Expr;
import syntaxTree.components.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class CharConst extends Expr implements Visitable {
	
	private String op;
	private Leaf id;

	public CharConst(Location left, Location right, String op, Leaf id) {
		super(left, right, op, id);
		this.op = op;
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Leaf getId() {
		return id;
	}
	
	

}
