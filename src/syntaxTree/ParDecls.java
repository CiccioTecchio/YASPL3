package syntaxTree;

import java.util.ArrayList;
import syntaxTree.comp.Internal;
import syntaxTree.wrapper.ParDeclSon;
import visitor.Visitable;
import visitor.Visitor;

public class ParDecls extends Internal implements Visitable {

	private ArrayList<ParDeclSon> childList;
	private String op;
	
	public ParDecls(String op) {
		super(op);
		childList = new ArrayList<ParDeclSon>();
		this.op = op;
	}
	
	public ParDecls addChild(ParDeclSon pd){
		this.childList.add(pd);
		return this;
	}

	public ArrayList<ParDeclSon> getChildList() {
		return childList;
	}

	public String getOp() {
		return op;
	}

	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}

}
