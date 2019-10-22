package com.cicciotecchio.yaspl.syntaxTree.components;

import java_cup.runtime.ComplexSymbolFactory.Location;

import java.util.ArrayList;

public class Internal extends Node {
	
	private ArrayList<Node> childList;
	
	public Internal(Location left, Location right, String op, Node...sons) {
		super(left, right, op);
		if(childList == null)
			this.childList = new ArrayList<>();
		for(Node son : sons) {
			childList.add(son);
		}
	}
	
	public Internal(Location left, Location right, String op, String value) {
		super(left, right, op);
		if(childList == null)
			this.childList = new ArrayList<>();
		childList.add(new Leaf(left, right, op, value));
	}
}