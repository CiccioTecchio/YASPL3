package visitor;

import syntaxTree.*;
import syntaxTree.arithOp.*;
import syntaxTree.components.*;
import syntaxTree.declsOp.*;
import syntaxTree.leaf.*;
import syntaxTree.logicOp.*;
import syntaxTree.relOp.*;
import syntaxTree.statOp.*;
import syntaxTree.utils.*;
import syntaxTree.varDeclInitOp.*;
import syntaxTree.wrapper.DeclsWrapper;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import exception.*;
import semantic.*;
import semantic.SymbolTable.*;

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
			String id = checkDx(e);
			if(id.equals("")) e.accept(this);
			else {
				checkParams(id, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", id));
				}
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
		checkAlreadyDeclared(id);
		ParType x = this.getValueOfParTypeLeaf((String)n.getParType().accept(this));
		ParTuple t = new ParTuple(Kind.VARDECL, x, this.getValueOfLeaf(n.getTypeLeaf()));
		this.actualScope.put(id, t);
		
		return t;
	}
	
	@Override
	public Object visit(VarDecl n){
		ArrayList<String> idList = (ArrayList<String>) n.getVdi().accept(this);
		
		for(String s: idList) {
			checkAlreadyDeclared(s);
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
		this.checkAlreadyDeclared(defName);
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
		this.checkAlreadyDeclared(defName);
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
		//this.stack.pop();
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
			this.checkParams(id, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", id));
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
		
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(DivOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}
	
	@Override
	public Object visit(MultOp n) {
		/*String idE1 = checkDx(n.getE1());
		String idE2 = checkDx(n.getE2());
		if(idE1.equals("")) {
			n.getE1().accept(this);
		}else {
			this.checkParams(idE1, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", idE1));
		}
		
		if(idE2.equals("")) {
			n.getE2().accept(this);
		}else {
			this.checkParams(idE2, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", idE2));
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(SubOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(UminusOp n) {
		/*if(checkDx(n.getE()).equals("")) {
			n.getE().accept(this);
		}*/
		visitOfUnaryExpr(n.getE());
		return null;
	}

	@Override
	public Object visit(AndOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(NotOp n) {
		/*if(checkDx(n.getE()).equals("")) {
			n.getE().accept(this);
		}*/
		visitOfUnaryExpr(n.getE());
		return null;
	}

	@Override
	public Object visit(OrOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(EqOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(GeOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(GtOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(LeOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
		visitOfBinaryExpr(n.getE1(), n.getE2());
		return null;
	}

	@Override
	public Object visit(LtOp n) {
		/*if(checkDx(n.getE1()).equals("")) {
			n.getE1().accept(this);
		}
		if(checkDx(n.getE2()).equals("")) {
			n.getE2().accept(this);
		}*/
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
		String id = (String)n.getId().accept(this);
		checkParams(id, ParType.IN, String.format("%s is a IN parameter, cannot write in IN parameter", id));
		if(checkDx(n.getE()).equals(""))n.getE().accept(this);
		return null;
	}

	@Override
	public Object visit(CallOp n) {
		checkNotDeclared((String)n.getId().accept(this));
		if(n.getA() != null) {
			n.getA().accept(this);
		}
		return null;
	}

	@Override
	public Object visit(IfThenElseOp n) {
		if(checkDx(n.getE()).equals("")) n.getE().accept(this);
		n.getCs1().accept(this);
		n.getCs2().accept(this);
		return null;
	}

	@Override
	public Object visit(IfThenOp n) {
		if(checkDx(n.getE()).equals("")) n.getE().accept(this);
		n.getCs().accept(this);
		return null;
	}

	@Override
	public Object visit(ReadOp n) {
		
		ArrayList<String> list = (ArrayList<String>) n.getV().accept(this);
		for(String s: list) {
			checkParams(s, ParType.IN, String.format("%s is a IN parameter, cannot write in IN parameter", s));
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
	
	private void checkAlreadyDeclared(String id) throws AlreadyDeclaredException {
		if(this.actualScope.containsKey(id)) {
			throw new AlreadyDeclaredException(String.format("%s already declared in %s scope",
															  id,
															  this.actualScope.getScopeName()
												));
		}
	}
	
	private int checkNotDeclared(String id) throws NotDeclaredException {
		int i = this.stack.indexOf(actualScope);
		boolean find = false;
		
		while(!find && i>=0) {
			SymbolTable app = this.stack.elementAt(i);
			find = app.containsKey(id);
			i--;
		}
		if(!find) {
			throw new NotDeclaredException(String.format("%s undeclared in %s scope",
															  id,
															  this.actualScope.getScopeName()
												));
		}
		i++;
		return i;
	}
	
	private String checkDx(Expr e) {
		String id = "";
		if(e instanceof IdConst) {
			id = ""+e.accept(this);
			checkNotDeclared(id);
		}
		return id;
	}
	
	private void checkParams(String id, ParType check, String mess) {
		int position = this.checkNotDeclared(id);
		if(this.stack.elementAt(position).get(id) instanceof ParTuple) {
			ParTuple pt = (ParTuple) this.stack.elementAt(position).get(id);
			if(pt.getPt() == check) throw new IllegalParamOperationException(mess);
		}
	}
	
	private void visitOfBinaryExpr(Expr e1, Expr e2) {
		String idE1 = checkDx(e1);
		String idE2 = checkDx(e2);
		if(idE1.equals("")) {
			e1.accept(this);
		}else {
			this.checkParams(idE1, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", idE1));
		}
		
		if(idE2.equals("")) {
			e2.accept(this);
		}else {
			this.checkParams(idE2, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", idE2));
		}
	}
	
	private void visitOfUnaryExpr(Expr e) {
		String id =  checkDx(e);
		if(id.equals("")) {
			e.accept(this);
		}else {
			this.checkParams(id, ParType.OUT, String.format("%s is a OUT parameter, cannot read in OUT parameter", id));
		}
	}
	
}