package syntaxTree;

import java.util.ArrayList;

import syntaxTree.components.Internal;
import syntaxTree.components.Node;
import visitor.Visitable;
import visitor.Visitor;

public class Args extends Internal implements Visitable {

	private ArrayList<Expr> childList;
	
	public Args(String op) {
		super(op);
		this.childList = new ArrayList<>();
	}
	
	public Args addChild(Expr e){
		this.childList.add(0,e);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}
	
	public ArrayList<Expr> getChildList(){
		return childList;
	}
}
