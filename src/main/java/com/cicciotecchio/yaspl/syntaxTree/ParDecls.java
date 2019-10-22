package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.utils.ParDeclSon;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

public class ParDecls extends Internal implements Visitable {

	private ArrayList<ParDeclSon> childList;
	
	public ParDecls(Location left, Location right, String op) {
		super(left, right,op);
		childList = new ArrayList<ParDeclSon>();
	}
	
	public ParDecls addChild(ParDeclSon pd){
		this.childList.add(0,pd);
		return this;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<ParDeclSon> getChildList() {
		return childList;
	}

}
