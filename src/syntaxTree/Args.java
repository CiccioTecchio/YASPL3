package syntaxTree;

import java.util.ArrayList;
import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class Args extends Internal implements Visitable {

	private ArrayList<Expr> childList;
	
	public Args(Location left, Location right, String op) {
		super(left, right, op);
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
