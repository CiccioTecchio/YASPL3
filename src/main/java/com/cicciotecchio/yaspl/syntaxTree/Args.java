package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

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
		return visitor.visit(this);
	}
	
	public ArrayList<Expr> getChildList(){
		return childList;
	}
}
