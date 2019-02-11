package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Args extends Internal implements Visitable {

	private ArrayList<Expr> childList;
	
	public Args(String op) {
		super(op);
		this.childList = new ArrayList<>();
	}
	
	public Args addChild(Expr e){
		this.childList.add(e);
		return this;
	}

	@Override
	public Args accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Args) visitor.visit(this);
	}

}
