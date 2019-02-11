package syntaxTree;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Node;
import visitor.Visitable;
import visitor.Visitor;

public class Programma extends Internal implements Visitable {
	
	private String op;
	private Decls d;
	private Statements s;
	
	public Programma(String op, Decls d, Statements s) {
		super(op, d, s);
		this.op = op;
		this.d = d;
		this.s = s;
	}
	
	

	public String getOp() {
		return op;
	}



	public void setOp(String op) {
		this.op = op;
	}



	public Decls getD() {
		return d;
	}



	public void setD(Decls d) {
		this.d = d;
	}



	public Statements getS() {
		return s;
	}



	public void setS(Statements s) {
		this.s = s;
	}



	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
