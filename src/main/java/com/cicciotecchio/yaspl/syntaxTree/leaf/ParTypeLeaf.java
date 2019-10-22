package com.cicciotecchio.yaspl.syntaxTree.leaf;

import java_cup.runtime.ComplexSymbolFactory.Location;
import com.cicciotecchio.yaspl.syntaxTree.components.Leaf;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;

public class ParTypeLeaf extends Leaf implements Visitable {

	public ParTypeLeaf(Location left, Location right, String op, String value) {
		super(left, right, op, value);
	}
	
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
