package syntaxTree.statOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.Stat;
import syntaxTree.Vars;
import visitor.Visitable;
import visitor.Visitor;

public class ReadOp extends Stat implements Visitable {

	private String op;
	private Vars v;
	
	public ReadOp(Location left, Location right, String op, Vars v) {
		super(left, right, op, v);
		this.op = op;
		this.v = v;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Vars getV() {
		return v;
	}
	
	
	
}
