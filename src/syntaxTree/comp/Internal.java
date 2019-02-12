package syntaxTree.comp;

import java.util.ArrayList;

import syntaxTree.comp.Node;

public class Internal extends Node {
	
	private ArrayList<Node> childList;
	//... consente la creazione di un costruttore con 1 o più Node
	public Internal(String op, Node...sons) {
		super(op);
		if(this.childList == null) this.childList =  new ArrayList<Node>();
		this.childList = new ArrayList<>();
		for(Node son : sons) {
			childList.add(son);
		}
	}
	
	public Internal(String op, String value) {
		super(op);
		if(this.childList == null) this.childList = new ArrayList<Node>();
		this.childList.add(new Leaf(op,value));
	}
	public Internal(String op, Leaf value) {
		super(op);
		if(this.childList == null) this.childList = new ArrayList<Node>();
		this.childList.add(value);
	}
	@Override
	public String toString() {
		String toReturn = "";
		for(Node n: childList) {
			toReturn+="["+n.toString()+"] ";
		}
		return toReturn;
	}
	
}