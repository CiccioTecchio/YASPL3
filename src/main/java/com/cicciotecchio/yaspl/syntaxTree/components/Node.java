package com.cicciotecchio.yaspl.syntaxTree.components;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.semantic.SymbolTable;

public class Node {
	
	private String op;
	private SymbolTable.Type type;
	private SymbolTable sym;
	private Location left;
	private Location right;
	
	public Node(Location left, Location right, String op) {
		this.op = op; 
		this.left = left;
		this.right = right;
	}
	
	public Node(Location left, Location right, String op, SymbolTable.Type type, SymbolTable sym) {
		this.op = op;
		this.type = type;
		this.sym = sym;
		this.left = left;
		this.right = right;
	}
	
	public Node(Location left, Location right, String op, SymbolTable.Type type) {
		this.op = op;
		this.type = type;
		this.left = left;
		this.right = right;
	}
	
	public Node(Location left, Location right, String op, SymbolTable sym) {
		this.op = op;
		this.sym = sym;
		this.left = left;
		this.right = right;
	}
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public SymbolTable.Type getType() {
		return type;
	}

	public void setType(SymbolTable.Type t) {
		this.type = t;
	}

	public SymbolTable getSym() {
		return sym;
	}

	public void setSym(SymbolTable sym) {
		this.sym = sym;
	}

	public Location getLeft() {
		return left;
	}

	public Location getRight() {
		return right;
	}

	public void setLocation(Location l, Location r) {
		this.left = l;
		this.right = r;
	}

}
