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
	public ParDecls(String op, Leaf par, Leaf id) {
		super(op,par,id);
		this.childList = new ArrayList<ParDecls>();
		this.addChild(new ParDecls(op, par, id));
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
