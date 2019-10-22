package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

public class Statements extends Internal implements Visitable {

	private ArrayList<Stat> childList;
	
	public Statements(Location left, Location right, String op) {
		super(left, right, op);
		this.childList = new ArrayList<Stat>();
	}
	
	public Statements addChild(Stat s){
		this.childList.add(0,s);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<Stat> getChildList() {
		return childList;
	}
	
	
	
	

}
