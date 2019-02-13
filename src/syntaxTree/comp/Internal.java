package syntaxTree.comp;

import java.util.ArrayList;

import syntaxTree.comp.Node;

public class Internal extends Node {
	
	private ArrayList<Node> childList;
	
	public Internal(String op, Node...sons) {
		super(op);
		if(childList == null)
			this.childList = new ArrayList<>();
		for(Node son : sons) {
			childList.add(son);
		}
	}
	
	public Internal(String op, String value) {
		super(op);
		if(childList == null)
			this.childList = new ArrayList<>();
		childList.add(new Leaf(op, value));
	}
}