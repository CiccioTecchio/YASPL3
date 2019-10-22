package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

public class Vars extends Internal implements Visitable {

	private ArrayList<IdConst> childList;
	
	public Vars(Location left, Location right, String op) {
		super(left, right, op);
		this.childList = new ArrayList<IdConst>();
	}
	
	public Vars addChild(IdConst id){
		this.childList.add(0,id);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<IdConst> getChildList() {
		return childList;
	}
	
	

}
