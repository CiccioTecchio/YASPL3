package syntaxTree.comp;

import semantic.SymbolTable;
import semantic.Tuple;

public class Node {
	
	private String op;
	private SymbolTable.Type type;
	private SymbolTable sym;
	
	public Node(String op) {
		this.op = op; 
	}
	
	public Node(String op, SymbolTable.Type type, SymbolTable sym) {
		this.op = op;
		this.type = type;
		this.sym = sym;
	}
	
	public Node(String op, SymbolTable.Type type) {
		this.op = op;
		this.type = type;
	}
	
	public Node(String op, SymbolTable sym) {
		this.op = op;
		this.sym = sym;
	}
	
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public SymbolTable.Type getType() {
		return type;
	}

	public void setType(SymbolTable.Type t) {
		this.type = t;
	}

	public SymbolTable getSym() {
		return sym;
	}

	public void setSym(SymbolTable sym) {
		this.sym = sym;
	}

}
