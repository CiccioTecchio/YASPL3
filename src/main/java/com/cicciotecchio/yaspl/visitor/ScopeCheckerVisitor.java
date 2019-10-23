package com.cicciotecchio.yaspl.visitor;

import com.cicciotecchio.yaspl.exception.*;
import com.cicciotecchio.yaspl.semantic.*;
import com.cicciotecchio.yaspl.semantic.SymbolTable.Kind;
import com.cicciotecchio.yaspl.semantic.SymbolTable.ParType;
import com.cicciotecchio.yaspl.semantic.SymbolTable.Type;
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
import com.cicciotecchio.yaspl.syntaxTree.wrapper.DeclsWrapper;
import com.cicciotecchio.yaspl.syntaxTree.wrapper.VarDeclsInitWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ScopeCheckerVisitor implements Visitor<Object> {
	
	private Stack<SymbolTable> stack;
	private SymbolTable actualScope;
	private Logger logger = Logger.getLogger("SymbolTable");
	private FileHandler fh;
	private SimpleFormatter formatter;
	private String pathToPrintScope;
	
	public ScopeCheckerVisitor(String pathToPrintScope) throws SecurityException, IOException {
		this.stack = new Stack<SymbolTable>();
		this.pathToPrintScope = pathToPrintScope;
		this.fh = new FileHandler(this.pathToPrintScope);
		logger.setUseParentHandlers(false); // remove log message from stdout
		logger.addHandler(fh);
		this.formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
	
	public ScopeCheckerVisitor() {
		this.stack = new Stack<SymbolTable>();
		this.pathToPrintScope = "";
	}
	
	@Override
	public Object visit(Args n) {
		for(Expr e : n.getChildList()) {
			if(checkDx(e).equals("")) e.accept(this);
		}
		return null;
	}

	@Override
	public Object visit(Decls n) {
		for(DeclsWrapper dw : n.getChildList()) {
			dw.accept(this);
		}
		return null;
	}
	
	@Override
	public Object visit(Body n) {
		n.getVd().accept(this);
		n.getS().accept(this);
		if(pathToPrintScope.equals("")) {
			this.stack.pop();
		}else {
			logger.info(this.stack.pop().toString());
		}
		this.actualScope = this.stack.peek();
		return null;
	}
	
	@Override
	public Object visit(ParDecls n) {
		ArrayList<ParTuple> parArray = new ArrayList<>();
		for(ParDeclSon s : n.getChildList()) {
			parArray.add((ParTuple) s.accept(this));
		}
		
		return parArray;
	}
	
	@Override
	public Object visit(ParDeclSon n){
		String id = (String)n.getId().accept(this);
		checkAlreadyDeclared(id, n);
		ParType x = this.getValueOfParTypeLeaf((String)n.getParType().accept(this));
		ParTuple t = new ParTuple(Kind.VARDECL, x, this.getValueOfLeaf(n.getTypeLeaf()));
		this.actualScope.put(id, t);
		
		return t;
	}
	
	@Override
	public Object visit(VarDecl n){
		ArrayList<String> idList = (ArrayList<String>) n.getVdi().accept(this);	
		for(String s: idList) {
			checkAlreadyDeclared(s, n);
			VarTuple t = new VarTuple(Kind.VARDECL, this.getValueOfLeaf(n.getT()));
			this.actualScope.put(s, t);
		}
		return null;
	}
	
	@Override
	public Object visit(VarDeclsInit n) {
		ArrayList<String> idList = new ArrayList<>();
		for(VarDeclsInitWrapper vdiw: n.getChildList()) {			
			idList.add(0, (String)vdiw.accept(this));
		}
		return idList;
	}
	
	@Override
	public Object visit(TypeLeaf n) {
		return n.getValue();
	}

	@Override
	public Object visit(DefDeclNoPar n) {
		DefTuple t = new DefTuple(Kind.DEFDECL);
		String defName = (String)n.getId().accept(this);
		this.checkAlreadyDeclared(defName,n);
		this.actualScope.put(defName, t);
		SymbolTable sc = new SymbolTable(defName);
		this.stack.push(sc);
		this.actualScope = this.stack.peek();
		n.setSym(actualScope);
		n.getB().accept(this);
		return null;
	}

	@Override
	public Object visit(DefDeclPar n) {
		DefTuple t = new DefTuple(Kind.DEFDECL);
		String defName = (String)n.getId().accept(this);
		this.checkAlreadyDeclared(defName, n);
		this.actualScope.put(defName, t);
		SymbolTable sc = new SymbolTable(defName);
		this.stack.push(sc);
		this.actualScope = this.stack.peek();
		n.setSym(actualScope);
		t.setParArray((ArrayList<ParTuple>) n.getPd().accept(this));
		n.getB().accept(this);
		return null;
	}

	
	@Override
	public Object visit(CompStat n) {
		n.getS().accept(this);
		return null;
	}

	@Override
	public Object visit(Programma n) {
		this.stack.push(new SymbolTable("Globale"));
		this.actualScope = this.stack.peek();
		n.setSym(actualScope);
		n.getD().accept(this);
		n.getS().accept(this);
		if(pathToPrintScope.equals("")) {
			this.stack.peek();
		}else {
			logger.info(this.stack.peek().toString());
		}
		this.stack.pop();
		return n;
	}

	@Override
	public Object visit(Statements n) {
		ArrayList<Stat> list = n.getChildList();
		for(Stat s: list) {
			s.accept(this);
		}
		return null;
	}



	@Override
	public Object visit(VarDecls n) {
		for(VarDecl vd: n.getChildList()) {
			vd.accept(this);
		}
		return null;
	}



	@Override
	public Object visit(VarInitValue n) {
		String id = checkDx(n.getE());
		if(id.equals("")) n.getE().accept(this);
		else {
			this.checkParams(id, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", id), n);
		}
		return null;
	}

	@Override
	public Object visit(Vars n) {
		ArrayList<String> list = new ArrayList<>();
		for(IdConst id: n.getChildList()) {
			list.add(0, (String)id.accept(this));
		}
		return list;
	}

	@Override
	public Object visit(AddOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(DivOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}
	
	@Override
	public Object visit(MultOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(SubOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(UminusOp n) {
		visitOfUnaryExpr(n.getE());
		return null;
	}

	@Override
	public Object visit(AndOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(NotOp n) {
		visitOfUnaryExpr(n.getE());
		return null;
	}

	@Override
	public Object visit(OrOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(EqOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(GeOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(GtOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(LeOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(LtOp n) {
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(BoolConst n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IdConst n) {
		return n.getId().accept(this);
	}

	@Override
	public Object visit(IntConst n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DoubleConst n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CharConst n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(StringConst n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AssignOp n) {
		n.getId().accept(this);
		n.getA().accept(this);
		//checkParams(id, ParType.IN, String.format("%s is a IN parameter, cannot write in IN parameter", id));
		//if(checkDx(n.getE()).equals(""))n.getE().accept(this);
		return null;
	}

	@Override
	public Object visit(CallOp n){
		String callId = n.getId().accept(this)+"";
		int indexCallOp = checkNotDeclared(callId,n);
		if(n.getA() != null && this.stack.indexOf(actualScope)>0) {
			//DefTuple della funzione che effettua la callOp
			DefTuple callDefTuple = (DefTuple)this.stack.elementAt(indexCallOp).get(callId);
			ArrayList<ParTuple> callParams = callDefTuple.getParArray();
			
			int i = this.stack.indexOf(actualScope);
			boolean find = false;
			//SymbolTable della funzione che contiene la CallOp
			SymbolTable callSym = actualScope;
			while(i>= 0 && !find) {
				callSym = this.stack.get(i);
				Set<String> set = callSym.keySet();
				for(String s: set) {
					if(callSym.get(s) instanceof ParTuple) {find = true;break;}
				}
				i--;
			}
			
			if(n.getA().getChildList().size() != callParams.size()) throw new WrongArgumentException(callId, callParams.size(), n.getA().getChildList().size(), n);
			//ArrayList<String> argsList = (ArrayList<String>) n.getA().accept(this);
			ArrayList<Expr> argsList = n.getA().getChildList();
			int j=0;
			
			for(Expr e : argsList) {
				String id = e.accept(this)+"";
				if(callSym.get(id) instanceof ParTuple) {
					ParTuple t= (ParTuple) callSym.get(id);
					//if(callParams.get(j).getPt() != t.getPt()) throw new IllegalParamOperationException(String.format("Variabile %s del tipo %s, tipo richiesto %s", id,t.getPt().toString(),callParams.get(j).getPt().toString()));
					if(callParams.get(j).getPt() != t.getPt() && 
					   ((callParams.get(j).getPt() != ParType.INOUT || t.getPt() != ParType.INOUT)) &&  
					   (t.getPt() != ParType.INOUT)) 
						throw new IllegalParamOperationException(String.format("Variabile %s del tipo %s, tipo richiesto %s", id,t.getPt().toString(),callParams.get(j).getPt().toString()), n);
				}
				j++;
			}
			n.getA().accept(this);
		}else {
			if(n.getA()!=null)n.getA().accept(this);
		}
		return null;
	}

	@Override
	public Object visit(IfThenElseOp n) {
		if(checkDx(n.getE()).equals("")) n.getE().accept(this);
		this.stack.push(new SymbolTable("Then scope - hashCode: "+n.hashCode()));
		this.actualScope = this.stack.peek();
		n.setSym(actualScope);
		n.getB1().accept(this);
		this.stack.push(new SymbolTable("Else scope - hashCode: "+n.hashCode()));
		this.actualScope = this.stack.peek();
		n.setElseTbl(actualScope);
		n.getB2().accept(this);
		return null;
	}

	@Override
	public Object visit(IfThenOp n) {
		if(checkDx(n.getE()).equals("")) n.getE().accept(this);
		this.stack.push(new SymbolTable("Then - hashCode: "+n.hashCode()));
		this.actualScope = this.stack.peek();
		n.setSym(actualScope);
		n.getB().accept(this);
		return null;
	}

	@Override
	public Object visit(ReadOp n) {
		ArrayList<String> list = (ArrayList<String>) n.getV().accept(this);
		for(String s: list) {
			checkParams(s, ParType.IN, String.format("%s is a IN parameter, cannot write in IN parameter", s), n);
		}
		return null;
	}

	@Override
	//esempio aggiunta scope
	public Object visit(WhileOp n) {
		if(checkDx(n.getE()).equals("")) n.getE().accept(this);
		this.stack.push(new SymbolTable("WhileScope - hashCode: "+n.hashCode()));
		this.actualScope = this.stack.peek();
		n.setSym(actualScope);
		n.getBody().accept(this);
		return null;
	}

	@Override
	public Object visit(WriteOp n) {
		n.getA().accept(this);
		return null;
	}

	@Override
	public Object visit(PreFixInc n) throws RuntimeException {
		checkNotDeclared(n.getId().accept(this).toString(), n);
		return null;
	}

	@Override
	public Object visit(PostFixInc n) throws RuntimeException {
		checkNotDeclared(n.getId().accept(this).toString(), n);
		return null;
	}

	@Override
	public Object visit(PreFixDec n) throws RuntimeException {
		checkNotDeclared(n.getId().accept(this).toString(), n);
		return null;
	}

	@Override
	public Object visit(PostFixDec n) throws RuntimeException {
		checkNotDeclared(n.getId().accept(this).toString(), n);
		return null;
	}

	@Override
	public Object visit(Leaf n) {
		return n.getValue();
	}



	@Override
	public Object visit(VarInit n) {
		n.getViv().accept(this);
		return n.getId().accept(this);
	}

	@Override
	public Object visit(VarNotInit n) {
		return n.getId().accept(this);
	}

	

	@Override
	public Object visit(ParTypeLeaf n) {
		return n.getValue();
	}
	
	private SymbolTable.Type getValueOfLeaf(TypeLeaf t){
		switch (t.getValue()) {
		case "BOOL": return Type.BOOL;
		case "DOUBLE": return Type.DOUBLE;
		case "INT":return Type.INT;
		case "CHAR": return Type.CHAR;
		case "STRING": return Type.STRING;
		default: return null;
		}
	}
	
	private SymbolTable.ParType getValueOfParTypeLeaf(String t){
		switch (t) {
		case "IN": return ParType.IN;
		case "OUT": return ParType.OUT;
		case "INOUT":return ParType.INOUT;
		default: return null;
		}
	}

	//usato nei visit che contengono dichiarazioni
	private void checkAlreadyDeclared(String id, Node n) throws AlreadyDeclaredException {
		if(this.actualScope.containsKey(id)) {
			throw new AlreadyDeclaredException(id, this.actualScope.getScopeName(), n);
		}
	}
	
	private int checkNotDeclared(String id, Node n) throws NotDeclaredException {
		int i = this.stack.indexOf(actualScope);
		boolean find = false;
		
		while(!find && i>=0) {
			SymbolTable app = this.stack.elementAt(i);
			find = app.containsKey(id);
			i--;
		}
		if(!find) {
			throw new NotDeclaredException(id, this.actualScope.getScopeName(), n);
		}
		i++;
		return i;
	}
	
	private String checkDx(Expr e) {
		String id = "";
		if(e instanceof IdConst) {
			id = ""+e.accept(this);
			checkNotDeclared(id, e);
		}
		return id;
	}
	
	private void checkParams(String id, ParType check, String mess, Node n) {
		int position = this.checkNotDeclared(id, n);
		if(this.stack.elementAt(position).get(id) instanceof ParTuple) {
			ParTuple pt = (ParTuple) this.stack.elementAt(position).get(id);
			if(pt.getPt() == check) throw new IllegalParamOperationException(mess, n);
		}
	}
	
	private void visitOfBinaryExpr(Expr e1, Expr e2) {
		String idE1 = checkDx(e1);
		String idE2 = checkDx(e2);
		if(idE1.equals("")) {
			e1.accept(this);
		}else {
			this.checkParams(idE1, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", idE1), e1);
		}
		
		if(idE2.equals("")) {
			e2.accept(this);
		}else {
			this.checkParams(idE2, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", idE2), e2);
		}
	}
	
	private void visitOfUnaryExpr(Expr e) {
		String id =  checkDx(e);
		if(id.equals("")) {
			e.accept(this);
		}else {
			this.checkParams(id, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", id), e);
		}
	}
	
}