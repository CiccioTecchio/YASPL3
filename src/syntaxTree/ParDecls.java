package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class ParDecls extends Internal implements Visitable {

	private ArrayList<ParDecls> childList;
	
	public ParDecls(String op, Leaf parType, Leaf type, Leaf id) {
		super(op, parType, type, id);
	}
	
	//primo nodo
	public ParDecls(String op) {
		super(op);
		this.childList = new ArrayList<ParDecls>();
	}
	
	public ArrayList<ParDecls> addChild(ParDecls pd){
		this.childList.add(pd);
		return this.childList;
	}

	@Override
	public ParDecls accept(Visitor<?> visitor) {
		return (ParDecls) visitor.visit(this);
	}

}
