package com.cicciotecchio.yaspl.visitor;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Type;
import com.cicciotecchio.yaspl.syntaxTree.*;
import com.cicciotecchio.yaspl.syntaxTree.arithOp.*;
import com.cicciotecchio.yaspl.syntaxTree.components.Leaf;
import com.cicciotecchio.yaspl.syntaxTree.declsOp.DefDeclNoPar;
import com.cicciotecchio.yaspl.syntaxTree.declsOp.DefDeclPar;
import com.cicciotecchio.yaspl.syntaxTree.declsOp.VarDecl;
import com.cicciotecchio.yaspl.syntaxTree.leaf.*;
import com.cicciotecchio.yaspl.syntaxTree.logicOp.AndOp;
import com.cicciotecchio.yaspl.syntaxTree.logicOp.NotOp;
import com.cicciotecchio.yaspl.syntaxTree.logicOp.OrOp;
import com.cicciotecchio.yaspl.syntaxTree.relOp.*;
import com.cicciotecchio.yaspl.syntaxTree.statOp.*;
import com.cicciotecchio.yaspl.syntaxTree.utils.ParDeclSon;
import com.cicciotecchio.yaspl.syntaxTree.varDeclInitOp.VarInit;
import com.cicciotecchio.yaspl.syntaxTree.varDeclInitOp.VarNotInit;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.DeclsWrapper;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.VarDeclsInitWrapper;

public class EnrichASTVisitor implements Visitor<String> {

	@Override
	public String visit(Args n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		for(Expr e: n.getChildList()) {
			tr += appendValue(e.accept(this));
		}
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(Body n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getVd().accept(this));
		tr += appendValue(n.getS().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(CompStat n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getS().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(Decls n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		for(DeclsWrapper dw: n.getChildList()) {
			tr += appendValue(dw.accept(this));
		}
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(DefDeclNoPar n) throws RuntimeException {
		String tr = astBuilderWithSymTbl(n.getOp(), n.getSym().getScopeName(), n.getType());
		tr += appendValue(n.getId().accept(this));
		tr += appendValue(n.getB().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(DefDeclPar n) throws RuntimeException {
		String tr = astBuilderWithSymTbl(n.getOp(), n.getSym().getScopeName(), n.getType());
		tr += appendValue(n.getId().accept(this));
		tr += appendValue(n.getPd().accept(this));
		tr += appendValue(n.getB().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(ParDecls n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		for(ParDeclSon son: n.getChildList()) {
			tr += son.accept(this);
		}
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(Programma n) throws RuntimeException {
		String tr = astBuilderWithSymTbl(n.getOp(), n.getSym().getScopeName(), n.getType());
		tr+= appendValue(n.getD().accept(this));
		tr+= appendValue(n.getS().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(Statements n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		for(Stat s: n.getChildList()) {
			tr += appendValue(s.accept(this));
		}
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(VarDecl n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getT().accept(this));
		tr += appendValue(n.getVdi().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(VarDecls n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		for(VarDecl vd : n.getChildList()) {
			tr += appendValue(vd.accept(this));
		}
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(VarDeclsInit n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		for(VarDeclsInitWrapper vdiw: n.getChildList()) {
			tr += appendValue(vdiw.accept(this));
		}
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(VarInitValue n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += n.getE().accept(this);
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(Vars n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		for(IdConst ids: n.getChildList()) {
			tr += ids.accept(this);
		}
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(AddOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(DivOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(MultOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(SubOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(UminusOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(AndOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(NotOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(OrOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(EqOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(GeOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(GtOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(LeOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(LtOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE1().accept(this));
		tr += appendValue(n.getE2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(BoolConst n) throws RuntimeException {
		String tr  = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(IdConst n) throws RuntimeException {
		String tr = (n.getType()!=null)?
					astBuilder(n.getOp(), n.getType()):
					astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr+= closeTag(n.getOp());
	}

	@Override
	public String visit(IntConst n) throws RuntimeException {
		String tr  = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(DoubleConst n) throws RuntimeException {
		String tr  = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(CharConst n) throws RuntimeException {
		String tr  = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(StringConst n) throws RuntimeException {
		String tr  = astBuilder(n.getOp());
		tr += appendValue(replaceEscape((String)n.getId().accept(this)));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(AssignOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getId().accept(this));
		tr += appendValue(n.getA().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(CallOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getId().accept(this));
		tr += (n.getOp() == "CallOpWithArgs")? appendValue(n.getA().accept(this)): "";
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(IfThenElseOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE().accept(this));
		tr += appendValue(n.getB1().accept(this));
		tr += appendValue(n.getB2().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(IfThenOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getE().accept(this));
		tr += appendValue(n.getB().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(ReadOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getV().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(WhileOp n) throws RuntimeException {
		String tr = astBuilderWithSymTbl(n.getOp(), n.getSym().getScopeName(), n.getType());
		tr += appendValue(n.getE().accept(this));
		tr += appendValue(n.getBody().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(ForOp n) throws RuntimeException {
		String tr = astBuilderWithSymTbl(n.getOp(), n.getSym().getScopeName(), n.getType());
		tr += appendValue(n.getId().accept(this));
		tr += appendValue(n.getStart().accept(this));
		tr += appendValue(n.getEnd().accept(this));
		tr += (n.getMinus())?"-"+appendValue(n.getIncr().accept(this)):appendValue(n.getIncr().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(WriteOp n) throws RuntimeException {
		String tr = astBuilder(n.getOp(), n.getType());
		tr += appendValue(n.getA().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(PreFixInc n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(PostFixInc n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(PreFixDec n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(PostFixDec n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr+=closeTag(n.getOp());
	}

	@Override
	public String visit(Leaf n) throws RuntimeException {
		return n.getValue();
	}

	@Override
	public String visit(ParDeclSon n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += n.getParType().accept(this);
		tr += n.getTypeLeaf().accept(this);
		tr += n.getId().accept(this);
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(VarInit n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		tr += appendValue(n.getViv().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(VarNotInit n) throws RuntimeException {
		String tr = astBuilder(n.getOp());
		tr += appendValue(n.getId().accept(this));
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(TypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		String tr = astBuilder(n.getOp());
		tr+= n.getValue()+"\n";
		return tr += closeTag(n.getOp());
	}

	@Override
	public String visit(ParTypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		String tr = astBuilder(n.getOp());
		tr += n.getValue()+"\n";
		return tr+= closeTag(n.getOp());
	}
	
	private String astBuilder(String op, Type t) {
		 return "<"+op+" type = \""+t+"\">\n";
	}
	
	private String astBuilder(String op) {
		 return "<"+op+">\n";
	}
	
	private String closeTag(String op) {
		 return "</"+op+">\n";
	}
	
	private String astBuilderWithSymTbl(String op, String scopeName, Type t ) {
		 return "<"+op+" symTbl = \""+ scopeName +"\" type = \""+t+"\">\n";
	}
	
	private String appendValue(Object value) {
		 return value+"\n";
	}
	
	private static final String LT = "&lt;";
	private static final String GT = "&gt;";
	private static final String AMP= "&amp;";
	private static final String QUOT= "&quot;";
	private static final String APOS= "&apos;";
	
	private String replaceEscape(String s) {
		s = s.replace("<", LT);
		s = s.replace(">", GT);
		s = s.replace("&", AMP);
		s = s.replace("\"", QUOT);
		s = s.replace("\'", APOS);
		return s;
	}
}
