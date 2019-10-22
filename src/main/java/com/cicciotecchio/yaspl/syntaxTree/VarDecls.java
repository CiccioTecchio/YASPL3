package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.declsOp.VarDecl;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

public class VarDecls extends Internal implements Visitable {
	
	private ArrayList<VarDecl> childList;
	
	//primo nodo
	public VarDecls(Location left, Location right, String op) {
		super(left, right, op);
		this.childList = new ArrayList<VarDecl>();
	}
	
	public VarDecls addChild(VarDecl n){
		this.childList.add(0,n);
		return this;
	}	

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

	public ArrayList<VarDecl> getChildList() {
		return childList;
	}

	
}
