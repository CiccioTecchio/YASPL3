package syntax_tree;

import java.util.ArrayList;

public class Operation extends Node {
	
	private ArrayList<Node> nodeList;
	//... consente la creazione di un costruttore con 1 o più Node
	public Operation(String op, Node...sons) {
		super(op);
		this.nodeList = new ArrayList<>();
		for(Node son : sons) {
			nodeList.add(son);
		}
	}
	
	public ArrayList<Node> getNodeList(){
		return nodeList;
	}
	
	public Operation addChild(Node n) {
		nodeList.add(n);
		return this;
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
