package syntaxTree.leaf;

import java_cup.runtime.ComplexSymbolFactory.Location;
import syntaxTree.components.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParTypeLeaf extends Leaf implements Visitable {

	public ParTypeLeaf(Location left, Location right, String op, String value) {
		super(left, right, op, value);
	}
	
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
