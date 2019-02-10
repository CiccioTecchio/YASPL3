package syntaxTree.wrapper;

import syntaxTree.DefDecl;
import syntaxTree.VarDecl;
import syntaxTree.comp.Internal;
import visitor.Visitable;
import visitor.Visitor;

public class DeclsWrapper extends Internal implements Visitable {

	public DeclsWrapper(String op) {
		super(op);
	}
	
	public DeclsWrapper(String op, DefDecl dd) {
		super(op,dd);
	}

	public DeclsWrapper(String op, VarDecl vd) {
		super(op,vd);
	}

	@Override
	public DeclsWrapper accept(Visitor<?> visitor) {
		// TODO Auto-generated method stub
		return (DeclsWrapper) visitor.visit(this);
	}

}
