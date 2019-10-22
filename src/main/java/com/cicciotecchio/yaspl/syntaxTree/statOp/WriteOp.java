package syntaxTree.statOp;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.Args;
import syntaxTree.Stat;
import visitor.Visitable;
import visitor.Visitor;

public class WriteOp extends Stat implements Visitable {

	private String op;
	private Args a;
	
	public WriteOp(Location left, Location right, String op, Args a) {
		super(left, right, op, a);
		this.op = op;
		this.a = a;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public Args getA() {
		return a;
	}
	
	



}
