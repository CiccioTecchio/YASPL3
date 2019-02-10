package syntaxTree.comp;

import java.util.ArrayList;

import syntaxTree.comp.Node;

public class Internal extends Node {
	
	private ArrayList<Node> nodeList;
	//... consente la creazione di un costruttore con 1 o più Node
	public Internal(String op, Node...sons) {
		super(op);
		this.nodeList = new ArrayList<>();
		for(Node son : sons) {
			nodeList.add(son);
		}
	}

	@Override
	public String toString() {
		String toReturn = "";
		for(Node n: nodeList) {
			toReturn+="["+n.toString()+"] ";
		}
		return toReturn;
	}	
}