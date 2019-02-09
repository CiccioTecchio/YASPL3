package syntax_tree.wrappers;

import syntax_tree.comp.Leaf;
import visitor.Visitor;
import syntax_tree.comp.Internal;

public class ExprWrapper extends Internal {

	//Operazioni binarie
	public ExprWrapper(String op, ExprWrapper e1, ExprWrapper e2) {
		super(op,e1,e2);
		// TODO Auto-generated constructor stub
	}
	//Operazioni unarie
	public ExprWrapper(String op, ExprWrapper e) {
		super(op,e);
	}
	
	public ExprWrapper(String op, Leaf l) {
		super(op, l);
	}
	
	public ExprWrapper accept(Visitor<?> v) {
		return (ExprWrapper) v.visit(this);
	}
}
