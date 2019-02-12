package visitor;

import syntaxTree.*;
import syntaxTree.arithOp.*;
import syntaxTree.comp.Leaf;
import syntaxTree.logicOp.*;
import syntaxTree.relOp.*;
import syntaxTree.statOp.AssignOp;
import syntaxTree.statOp.CallOp;
import syntaxTree.statOp.IfThenElseOp;
import syntaxTree.statOp.IfThenOp;
import syntaxTree.statOp.ReadOp;
import syntaxTree.statOp.WhileOp;
import syntaxTree.statOp.WriteOp;
import syntaxTree.wrapper.*;

public class TreePrinterVisitor implements Visitor<String> {

	public TreePrinterVisitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String visit(Args n) {
		String toReturn = n.getOp();
		for(Expr e : n.getChildList()) {
			toReturn += e.accept(this);
		}
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(Body n) {
		String toReturn = n.getOp();
		toReturn += n.getVd().accept(this);
		toReturn += n.getS().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(CompStat n) {
		String toReturn = n.getOp();
		toReturn += n.getS().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}


	@Override
	public String visit(Decls n) {
		String toReturn = n.getOp();
		for(DeclsWrapper e: n.getChildList()) {
			if(e instanceof VarDecl)
				toReturn += ((VarDecl)e).accept(this);
			if(e instanceof DefDecl)
				toReturn += ((DefDecl)e).accept(this);
		}
			
		toReturn +=  n.getOp();
		return toReturn;
}

	@Override
	public String visit(DefDecl n) {
		String toReturn = n.getOp();
		toReturn += n.getB().accept(this);
		toReturn += n.getId().accept(this);
		toReturn += (n.getPd()!=null)?n.getPd().accept(this):"";
		toReturn +=  n.getOp();
		return toReturn;
}

	/*@Override
	public String visit(Expr n) {
		String toReturn = n.getOp();
		if(n.getId()!=null) {
			toReturn += n.getId();
		}else {
			if(n.getE2()!= null) {
				toReturn += n.getE1().accept(this);
				toReturn += n.getE2().accept(this);
			}else {
				toReturn += n.getE1();
			}
		}
		toReturn +=  n.getOp();
		return toReturn;
	}*/
	
	@Override
	public String visit(Expr n) {
		String toReturn = n.getOp();
		
		if(n instanceof AndOp)
			toReturn += ((AndOp)n).accept(this);
		if(n instanceof NotOp)
			toReturn += ((NotOp)n).accept(this);
		if(n instanceof OrOp)
			toReturn += ((OrOp)n).accept(this);
		
		if(n instanceof EqOp)
			toReturn += ((EqOp)n).accept(this);
		if(n instanceof GeOp)
			toReturn += ((GeOp)n).accept(this);
		if(n instanceof GtOp)
			toReturn += ((GtOp)n).accept(this);
		if(n instanceof LeOp)
			toReturn += ((LeOp)n).accept(this);
		if(n instanceof LtOp)
			toReturn += ((LtOp)n).accept(this);
		
		if(n instanceof AddOp)
			toReturn += ((AddOp)n).accept(this);
		if(n instanceof DivOp)
			toReturn += ((DivOp)n).accept(this);
		if(n instanceof MultOp)
			toReturn += ((MultOp)n).accept(this);
		if(n instanceof SubOp)
			toReturn += ((SubOp)n).accept(this);
		if(n.getId() != null)
			toReturn += n.getId().accept(this);
		
		
		toReturn +=  n.getOp();
		return toReturn;
}

	@Override
	public String visit(ParDecls n) {
		String toReturn = n.getOp();
		for(ParDeclSon e : n.getChildList()) {
			toReturn += e.accept(this);
		}
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(Programma n) {
		String toReturn = n.getOp();
		toReturn += n.getD().accept(this);
		toReturn += n.getS().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	/*@Override
	public String visit(Stat n) {
		String toReturn = n.getOp();
		if(n.getV()!= null) {
			toReturn += n.getV().accept(this);
		}else {
		if(n.getA()!=null) {
			toReturn += n.getA().accept(this);
		}else {
		if(n.getId() != null & n.getE()!= null) {
			toReturn += n.getId().accept(this);
			toReturn += n.getE().accept(this);
		}else {
		if(n.getId() != null & n.getA()!= null) {
			toReturn += n.getId().accept(this);
			toReturn += n.getA().accept(this);
		}else {
		if(n.getId() != null) {
			toReturn += n.getId().accept(this);
		}else {
			
		}
		}
		}
		}
		}
		toReturn +=  n.getOp();
		return toReturn;
	}*/

	@Override
	public String visit(Statements n) {
		String toReturn = n.getOp();
		for(Stat e: n.getChildList()) {
			if(e instanceof AssignOp)
				toReturn += ((AssignOp)e).accept(this);
			if(e instanceof CallOp)
				toReturn += ((CallOp)e).accept(this);
			if(e instanceof IfThenElseOp)
				toReturn += ((IfThenElseOp)e).accept(this);
			if(e instanceof IfThenOp)
				toReturn += ((IfThenOp)e).accept(this);
			if(e instanceof ReadOp)
				toReturn += ((ReadOp)e).accept(this);
			if(e instanceof WhileOp)
				toReturn += ((WhileOp)e).accept(this);
			if(e instanceof WriteOp)
				toReturn += ((WriteOp)e).accept(this);
		}
		toReturn +=  n.getOp();
		return toReturn;
}

	@Override
	public String visit(VarDecl n) {
		String toReturn = n.getOp();
		toReturn += n.getT().accept(this);
		toReturn += n.getVdi().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(VarDecls n) {
		String toReturn = n.getOp();
		for(VarDecl e : n.getChildList()) {
			toReturn += e.accept(this);
		}
		toReturn += n.getOp();
		return toReturn;
	}

	@Override
	public String visit(VarDeclsInit n) {
		String toReturn = n.getOp();
		for(VarDeclsInitWrapper e: n.getChildList()) {
			toReturn += e.getId().accept(this);
			if(e.getOp().equals("VarInitOp"))
				toReturn += e.getViv().accept(this);
		}
		toReturn +=  n.getOp();
		return toReturn;
}

	@Override
	public String visit(VarInitValue n) {
		String toReturn = n.getOp();
		n.getE().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(Vars n) {
		String toReturn = n.getOp();
		for(Leaf e : n.getChildList()) {
			toReturn += e.accept(this);
		}
		toReturn += n.getOp();
		return toReturn;
	}

	@Override
	public String visit(AddOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(DivOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(MultOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(SubOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(UminusOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(AndOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(NotOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(OrOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(EqOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(GeOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(GtOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(LeOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(LtOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(AssignOp n) {
		String toReturn = n.getOp();
		toReturn += n.getId().accept(this);
		toReturn += n.getE().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(CallOp n) {
		String toReturn = n.getOp();
		if(n.getA()!=null) {
			toReturn += n.getId().accept(this);
			toReturn += n.getA().accept(this);
		}else {
			toReturn += n.getId().accept(this);
		}
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(IfThenElseOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE().accept(this);
		toReturn += n.getCs1().accept(this);
		toReturn += n.getCs2().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(IfThenOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE().accept(this);
		toReturn += n.getCs().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(ReadOp n) {
		String toReturn = n.getOp();
		toReturn += n.getV().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(WhileOp n) {
		String toReturn = n.getOp();
		toReturn += n.getE().accept(this);
		toReturn += n.getCs().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(WriteOp n) {
		String toReturn = n.getOp();
		toReturn += n.getA().accept(this);
		toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(DeclsWrapper n) {
		//String toReturn = n.getOp();
		String toReturn = "";
		if(n.getId() != null && n.getB()!=null && n.getPd() == null ) {
			toReturn += n.getId().accept(this);
			toReturn += n.getB().accept(this);
		}else if(n.getId() != null && n.getB()!=null && n.getPd() != null ) {
			toReturn += n.getId().accept(this);
			toReturn += n.getPd().accept(this);
			toReturn += n.getB().accept(this);
		}else {
			toReturn += n.getT().accept(this);
			toReturn += n.getVdi().accept(this);
		}
		//toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(ParDeclSon n) {
		String toReturn = ""/*= n.getOp()+">\n"*/;
		toReturn += n.getParType().accept(this);
		toReturn += n.getType().accept(this);
		toReturn += n.getId().accept(this);
		//toReturn +=  n.getOp();
		return toReturn;
	}

	@Override
	public String visit(VarDeclsInitWrapper n) {
		//String toReturn = n.getOp();
		String toReturn ="";
		if(n.getViv() != null) {
			toReturn += n.getId().accept(this);
			toReturn += n.getViv().accept(this);
		}else {
			toReturn += n.getId().accept(this);
		}
		//toReturn +=  n.getOp();
		return toReturn;
	}
	
	@Override
	public String visit(Leaf n) {
		String toReturn = n.getOp();
		toReturn += n.getValue();
		toReturn +=  n.getOp();
		return toReturn;
	}

}
