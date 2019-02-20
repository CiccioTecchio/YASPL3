package syntaxTree.utils;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import syntaxTree.leaf.IdConst;
import syntaxTree.leaf.ParTypeLeaf;
import syntaxTree.leaf.TypeLeaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParDeclSon extends Internal implements Visitable {
	
	private String op;
	private ParTypeLeaf parType;
	private TypeLeaf type;
	private IdConst id;

	public ParDeclSon(String op, ParTypeLeaf parType, TypeLeaf type, IdConst id) {
		super(op, parType, type, id);
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
