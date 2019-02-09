package syntax_tree.statOp;

import syntax_tree.wrappers.StatWrapper;
import visitor.Visitable;
import visitor.Visitor;

public class WriteOp extends StatWrapper implements Visitable {

	public WriteOp(String op) {
		super(op);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Object accept(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
