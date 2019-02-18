package syntaxTree.comp;

import semantic.SymbolTable;
import semantic.Tuple;

public class Node {
	
	private String op;
	private Tuple ref;
	private SymbolTable sym;
	
	public Node(String op) {
		this.op = op; 
	}
	
	public Node(String op, Tuple ref, SymbolTable sym) {
		this.op = op;
		this.ref = ref;
		this.sym = sym;
	}
	
	public Node(String op, Tuple ref) {
		this.op = op;
		this.ref = ref;
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

	public Tuple getRef() {
		return ref;
	}

	public void setRef(Tuple ref) {
		this.ref = ref;
	}

	public SymbolTable getSym() {
		return sym;
	}

	public void setSym(SymbolTable sym) {
		this.sym = sym;
	}

}
