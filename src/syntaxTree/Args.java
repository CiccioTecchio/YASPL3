package syntaxTree;

import java.util.ArrayList;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Node;
import visitor.Visitable;
import visitor.Visitor;

public class Args extends Internal implements Visitable {

	private ArrayList<Expr> childList;
	
	public Args(String op) {
		super(op);
		this.childList = new ArrayList<>();
	}
	
	public ArrayList<Expr> addChild(Expr e){
		this.childList.add(e);
		return this.childList;
	}

	@Override
	public Args accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (Args) visitor.visit(this);
	}

}
