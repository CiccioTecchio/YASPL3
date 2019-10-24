package com.cicciotecchio.yaspl.visitor;

import com.cicciotecchio.yaspl.syntaxTree.*;
import com.cicciotecchio.yaspl.syntaxTree.arithOp.*;
import com.cicciotecchio.yaspl.syntaxTree.components.*;
import com.cicciotecchio.yaspl.syntaxTree.declsOp.*;
import com.cicciotecchio.yaspl.syntaxTree.leaf.*;
import com.cicciotecchio.yaspl.syntaxTree.logicOp.*;
import com.cicciotecchio.yaspl.syntaxTree.relOp.*;
import com.cicciotecchio.yaspl.syntaxTree.statOp.*;
import com.cicciotecchio.yaspl.syntaxTree.utils.*;
import com.cicciotecchio.yaspl.syntaxTree.varDeclInitOp.*;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.*;

public class ASTVisitor implements Visitor<String> {
	private static final String LT = "&lt;";
	private static final String GT = "&gt;";
	private static final String AMP= "&amp;";
	private static final String QUOT= "&quot;";
	private static final String APOS= "&apos;";
	
	@Override
	public String visit(Args n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(Expr e: n.getChildList())
			toReturn += e.accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(Body n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getVd().accept(this);
		toReturn += n.getS().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(CompStat n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getS().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(Decls n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(DeclsWrapper e: n.getChildList()) 
			toReturn += e.accept(this);
			
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(DefDeclNoPar n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += n.getB().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}
	
	@Override
	public String visit(DefDeclPar n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += n.getPd().accept(this);
		toReturn += n.getB().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}
	
	@Override
	public String visit(ParDecls n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(ParDeclSon e: n.getChildList())
			toReturn += e.accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(Programma n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getD().accept(this);
		toReturn += n.getS().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}
	
	@Override
	public String visit(Statements n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(Stat s: n.getChildList())
			toReturn += s.accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(VarDecl n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getT().accept(this);
		toReturn += n.getVdi().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(VarDecls n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(VarDecl e: n.getChildList())
			toReturn += e.accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(VarDeclsInit n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(VarDeclsInitWrapper e: n.getChildList())
			toReturn += e.accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(VarInitValue n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(Vars n) {
		String toReturn = "<"+n.getOp()+">\n";
		for(IdConst e: n.getChildList())
			toReturn += e.accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(AddOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(DivOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(ModOp n) throws RuntimeException {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(MultOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(SubOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(UminusOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(AndOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(NotOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(OrOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(EqOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(GeOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(GtOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(LeOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}
	
	@Override
	public String visit(LtOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE1().accept(this);
		toReturn += n.getE2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(AssignOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += n.getA().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(CallOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		if(n.getOp().equals("CallOpWithArgs"))
			toReturn += n.getA().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(IfThenElseOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE().accept(this);
		toReturn += n.getB1().accept(this);
		toReturn += n.getB2().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(IfThenOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE().accept(this);
		toReturn += n.getB().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(ReadOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getV().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(WhileOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getE().accept(this);
		//toReturn += n.getCs().accept(this);
		toReturn += n.getBody().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(ForOp n) throws RuntimeException {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += n.getStart().accept(this);
		toReturn += n.getEnd().accept(this);
		toReturn += (n.getMinus())?"-"+n.getIncr().accept(this):n.getIncr().accept(this);
		//toReturn += n.getCs().accept(this);
		toReturn += n.getB().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(WriteOp n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getA().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(PreFixInc n) throws RuntimeException {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(PostFixInc n) throws RuntimeException {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(PreFixDec n) throws RuntimeException {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(PostFixDec n) throws RuntimeException {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}


	public String visit(Leaf n) {
		String toReturn = "";
		toReturn += ""+n.getValue()+"\n";
		toReturn += "";
		return toReturn;
	}

	@Override
	public String visit(ParDeclSon n) {
		String toReturn = "";
		toReturn += n.getParType().accept(this);
		toReturn += n.getTypeLeaf().accept(this);
		toReturn += n.getId().accept(this);
		return toReturn;
	}

	@Override
	public String visit(VarInit n) {
		String toReturn = "";
		toReturn += n.getId().accept(this);
		toReturn += n.getViv().accept(this);
		return toReturn;
	}
	
	@Override
	public String visit(VarNotInit n) {
		String toReturn = "";
		toReturn += n.getId().accept(this);
		return toReturn;
	}

	@Override
	public String visit(BoolConst n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += ""+n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(IdConst n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += ""+n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(IntConst n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += ""+n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(DoubleConst n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += ""+n.getId().accept(this);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(CharConst n) {
		String toReturn = "<"+n.getOp()+">\n";
		String strConst = ""+n.getId().accept(this);
		toReturn += replaceEscape(strConst);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(StringConst n) {
		String toReturn = "<"+n.getOp()+">\n";
		String strConst = ""+n.getId().accept(this);
		toReturn += replaceEscape(strConst);
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(TypeLeaf n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += ""+n.getValue()+"\n";
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	@Override
	public String visit(ParTypeLeaf n) {
		String toReturn = "<"+n.getOp()+">\n";
		toReturn += ""+n.getValue()+"\n";
		toReturn += "</"+n.getOp()+">\n";
		return toReturn;
	}

	private String replaceEscape(String s) {
		s = s.replace("<", LT);
		s = s.replace(">", GT);
		s = s.replace("&", AMP);
		s = s.replace("\"", QUOT);
		s = s.replace("\'", APOS);
		return s;
	}
	
}
