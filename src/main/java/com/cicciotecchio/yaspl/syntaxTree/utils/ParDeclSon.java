package com.cicciotecchio.yaspl.syntaxTree.utils;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Internal;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.leaf.ParTypeLeaf;
import com.cicciotecchio.yaspl.syntaxTree.leaf.TypeLeaf;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class ParDeclSon extends Internal implements Visitable {
	
	private String op;
	private ParTypeLeaf parType;
	private TypeLeaf type;
	private IdConst id;

	public ParDeclSon(Location left, Location right, String op, ParTypeLeaf parType, TypeLeaf type, IdConst id) {
		super(left, right, op, parType, type, id);
		this.op = op;
		this.parType = parType;
		this.type = type;
		this.id = id;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

	public String getOp() {
		return op;
	}

	public ParTypeLeaf getParType() {
		return parType;
	}

	public TypeLeaf getTypeLeaf() {
		return type;
	}

	public IdConst getId() {
		return id;
	}
	
	

}
