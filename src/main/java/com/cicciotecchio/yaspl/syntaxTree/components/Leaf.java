package com.cicciotecchio.yaspl.syntaxTree.components;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class Leaf extends Node implements Visitable{

	private String value;
	
	public Leaf(Location left, Location right,String op, String value) {
		super(left, right, op);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Object accept(Visitor<?> visitor){
		return visitor.visit(this);
	}
}
