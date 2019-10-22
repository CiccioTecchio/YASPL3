package com.cicciotecchio.yaspl.syntaxTree;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.VarDeclsInitWrapper;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

import java.util.ArrayList;

public class VarDeclsInit extends Internal implements Visitable {

	private ArrayList<VarDeclsInitWrapper> childList;
	
	//primo nodo
	public VarDeclsInit(Location left, Location right, String op) {
		super(left, right, op);
		this.childList = new ArrayList<>();
	}

	public VarDeclsInit addChild (VarDeclsInitWrapper vdiw){
		this.childList.add(0,vdiw);
		return this;
	}
	

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public ArrayList<VarDeclsInitWrapper> getChildList() {
		return childList;
	}
	
	

}
