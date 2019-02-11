package syntaxTree;

import syntaxTree.comp.Internal;
import syntaxTree.comp.Leaf;
import visitor.Visitable;
import visitor.Visitor;

public class Stat extends Internal /*implements Visitable*/{

	private String op;
	private Vars v;
	private Args a;
	private Leaf id;
	private Expr e;
	private CompStat cs1;
	private CompStat cs2;
	
	//ReadOp
	public Stat(String op, Vars v) {
		super(op, v);
		this.op = op;
	}
	//WriteOp
	public Stat(String op, Args a) {
		super(op, a);
		this.op = op;
		this.a = a;
	}
	//AssignOp
	public Stat(String op, Leaf id, Expr e) {
		super(op, id, e);
		this.op = op;
		this.id = id;
		this.e = e;
	}
	//CallOp
	public Stat(String op, Leaf id, Args a) {
		super(op, id, a);
		this.op = op;
		this.id = id;
		this.a = a;
	}
	//CallOp
	public Stat(String op, Leaf id) {
		super(op, id);
		this.op = op;
		this.id = id;
	}
	//IfThenElseOp
	public Stat(String op, Expr e, CompStat cs1, CompStat cs2) {
		super(op, e, cs1, cs2);
		this.op = op;
		this.e = e;
		this.cs1 = cs1;
		this.cs2 = cs2;
	}
	//IfThenOp && WhileOp (TODO ricorda di controllare String op)
	public Stat(String op, Expr e, CompStat cs) {
		super(op, e, cs);
		this.op = op;
		this.e = e;
		this.cs1 = cs;
	}
	/*
	@Override
	public Object accept(Visitor<?> visitor) {
		return visitor.visit(this);
	}*/
	
	public String getOp() {
		return op;
	}
	public Vars getV() {
		return v;
	}
	public Args getA() {
		return a;
	}
	public Leaf getId() {
		return id;
	}
	public Expr getE() {
		return e;
	}
	public CompStat getCs1() {
		return cs1;
	}
	public CompStat getCs2() {
		return cs2;
	}
	
	
	
}