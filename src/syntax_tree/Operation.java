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
	
}
