package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.DeclsWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

public class Decls extends Internal implements Visitable {

	private ArrayList<DeclsWrapper> childList;
	
	public Decls(Location left, Location right,String op) {
		super(left, right, op);
		this.childList = new ArrayList<DeclsWrapper>();
	}
	
	public Decls addChild(DeclsWrapper dw){
		childList.add(0,dw);
		return this;
	}
	
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<DeclsWrapper> getChildList() {
		return childList;
	}
	
	

}
