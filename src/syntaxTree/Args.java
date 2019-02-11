package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Args extends Internal implements Visitable {

	private ArrayList<Expr> childList;
	private String op;
	
	public Args(String op) {
		super(op);
		this.childList = new ArrayList<>();
		this.op = op;
	}
	
	public String getOp() {
		return op;
	}

	public Args addChild(Expr e){
		this.childList.add(e);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}
	
	public ArrayList<Expr> getChildList() {
		return childList;
	}

}
