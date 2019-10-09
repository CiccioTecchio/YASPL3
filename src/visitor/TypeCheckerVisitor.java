package visitor;

import syntaxTree.*;
import syntaxTree.arithOp.*;
import syntaxTree.comp.*;
import syntaxTree.declsOp.*;
import syntaxTree.leaf.*;
import syntaxTree.logicOp.*;
import syntaxTree.relOp.*;
import syntaxTree.statOp.*;
import syntaxTree.utils.*;
import syntaxTree.varDeclInitOp.*;
import syntaxTree.wrapper.DeclsWrapper;
import syntaxTree.wrapper.VarDeclsInitWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import exception.*;
import semantic.*;
import semantic.SymbolTable.*;

public class TypeCheckerVisitor implements Visitor<Object> {

	private Stack<SymbolTable> stack;
	private SymbolTable actualScope;
	private String ast;
	
	private Logger logger=Logger.getLogger("TypeChecker");
	
	private SymbolTable.Type[][] sumOpCompOp = {
			{Type.INT, Type.STRING, Type.DOUBLE, null, null, null},	//riga interi
			{Type.STRING, Type.STRING, Type.STRING, Type.STRING, Type.STRING, null },	//riga stringa
			{Type.DOUBLE, Type.STRING, Type.DOUBLE, null, null, null},	//riga double
			{null, Type.STRING, null, Type.STRING, null, null},	//riga char
			{null, Type.STRING, null, null, null, null}	//riga bool
	};
	
	
	private SymbolTable.Type[][] arithOpCompOp = { // it's sub, mult and divOp
			{Type.INT, null, Type.DOUBLE, null, null, null},	//riga interi
			{null, null, null, null, null, null},	//riga stringa
			{Type.DOUBLE, null, Type.DOUBLE, null, null, null},	//riga double
			{null, null, null, null, null, null},	//riga char
			{null, null, null, null, null, null},	//riga bool
	};
	
	private SymbolTable.Type[][] eqOpCompOp = {
			{Type.BOOL, null, Type.BOOL, null, null, null},	//riga interi
			{null, Type.BOOL, null, null, null,  null},	//riga stringa
			{Type.BOOL, null, Type.BOOL, null, null, null},	//riga double
			{null, null, null, Type.BOOL, null, null},	//riga char
			{null, null, null, null, Type.BOOL, null}	//riga bool
	};
	
	private SymbolTable.Type[][] relOpCompOp = { //it's relOp
			{Type.BOOL, null, Type.BOOL, null, null, null},	//riga interi
			{null, Type.BOOL, null, null, null, null},	//riga stringa
			{Type.BOOL, null, Type.BOOL, null, null, null},	//riga double
			{null, null, null, Type.BOOL, null, null},	//riga char
			{null, null, null, null, null, null},	//riga bool
	};
	
	private SymbolTable.Type[][] assignOpCompOp = {
			{Type.INT, null, Type.INT, null, null, null},	//riga interi
			{null, Type.STRING, null, Type.STRING, null, null},	//riga stringa
			{Type.DOUBLE, null, Type.DOUBLE, null, null, null},	//riga double
			{null, null, null, Type.CHAR, null, null},	//riga char
			{null, null, null, null, Type.BOOL, null}	//riga bool
	};
	
	private SymbolTable.Type[] uminusOpCompOp = {
			Type.INT, null, Type.DOUBLE, null, Type.BOOL,  null
	};	
	
	public TypeCheckerVisitor() {
		this.stack = new Stack<>();
		this.ast = "";
	}

	@Override
	public Object visit(Args n) throws RuntimeException {
		ArrayList<Type> parTypes = new ArrayList<>();
		for(Expr e : n.getChildList()) {
			parTypes.add((Type)e.accept(this));
		}
		n.setType(Type.VOID);
		return parTypes;
	}



	@Override
	public Object visit(Body n) throws RuntimeException {
		n.getVd().accept(this);
		n.getS().accept(this);
		n.setType(Type.VOID);
		return n.getType();
	}



	@Override
	public Object visit(CompStat n) throws RuntimeException {
		n.setType(Type.VOID);
		n.getS().accept(this);
		return Type.VOID;
	}



	@Override
	public Object visit(Decls n) throws RuntimeException {
		n.setType(Type.VOID);
		for(DeclsWrapper dw: n.getChildList()) {
			dw.accept(this);
		}
		return Type.VOID;
	}



	@Override
	public Object visit(DefDeclNoPar n) throws RuntimeException {
		n.setType(Type.VOID);
		this.stack.push(n.getSym());
		this.actualScope = this.stack.peek();
		n.getId().accept(this);
		n.getB().accept(this);
		this.stack.pop();
		this.actualScope = this.stack.peek();
		return n.getType();
	}



	@Override
	public Object visit(DefDeclPar n) throws RuntimeException {
		n.setType(Type.VOID);
		this.stack.push(n.getSym());
		this.actualScope = this.stack.peek();
		n.getId().accept(this);
		n.getB().accept(this);
		this.stack.pop();
		this.actualScope = this.stack.peek();
		return n.getType();
	}



	@Override
	public Object visit(ParDecls n) throws RuntimeException {
		n.setType(Type.VOID);
		for(ParDeclSon son: n.getChildList()) {
			son.accept(this);
		}
		return Type.VOID;
		
	}



	@Override
	public Object visit(Programma n) throws RuntimeException {
		n.setType(Type.VOID);
		SymbolTable tbl = n.getSym();
		stack.push(tbl);
		this.actualScope = stack.peek();
		n.getD().accept(this);
		n.getS().accept(this);
		return Type.VOID;
	}



	@Override
	public Object visit(Statements n) throws RuntimeException {
		n.setType(Type.VOID);
		for(Stat s: n.getChildList() ) {
			s.accept(this);
		}
		return Type.VOID;
	}



	@Override
	public Object visit(VarDecl n) throws RuntimeException {
		n.setType(Type.VOID);
		n.getVdi().accept(this);
		return Type.VOID;
	}



	@Override
	public Object visit(VarDecls n) throws RuntimeException {
		n.setType(Type.VOID);
		for(DeclsWrapper dw: n.getChildList()) {
			dw.accept(this);
		}
		return Type.VOID;
	}



	@Override
	public Object visit(VarDeclsInit n) throws RuntimeException {
		n.setType(Type.VOID);
		for(VarDeclsInitWrapper vdiw: n.getChildList()) {
			vdiw.accept(this);
		}
		return Type.VOID;
	}



	@Override
	public Object visit(VarInitValue n) throws RuntimeException {
		n.setType((Type)n.getE().accept(this));
		return n.getType();
	}



	@Override
	public Object visit(Vars n) throws RuntimeException {
		n.setType(Type.VOID);
		for(IdConst id: n.getChildList()) {
			id.accept(this);
		}
		return Type.VOID;
	}



	@Override
	public Object visit(AddOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.sumOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(DivOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.arithOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(MultOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.arithOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(SubOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.arithOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(UminusOp n) throws RuntimeException {
		Type t = (Type) n.getE().accept(this);
		Type toReturn = this.uminusOpCompOp[gift(t)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(AndOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = (t1 == t2) && (t2 == Type.BOOL)? Type.BOOL : null;
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(NotOp n) throws RuntimeException {
		Type t = (Type) n.getE().accept(this);
		Type toReturn = (t == Type.BOOL)? Type.BOOL : null;
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t);
		}else
		n.setType(toReturn);
		return n.getType();
	}



	@Override
	public Object visit(OrOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = (t1 == t2) && (t2 == Type.BOOL)? Type.BOOL : null;
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(EqOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.eqOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;	
	}



	@Override
	public Object visit(GeOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.relOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(GtOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.relOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(LeOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.relOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(LtOp n) throws RuntimeException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type toReturn = this.relOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(toReturn);
		return toReturn;
	}



	@Override
	public Object visit(BoolConst n) throws RuntimeException {
		n.setType(Type.BOOL);
		return Type.BOOL;
	}



	@Override
	public Object visit(IdConst n) throws RuntimeException {
		Tuple t = lookup(n.getId().getValue());
		if(t == null) throw new NotDefinedElementException(n.getId().getValue()+"Non definita");
		else {
			if(t instanceof DefTuple) n.setType(Type.VOID);
			else  if(t instanceof ParTuple) n.setType(((ParTuple) t).getType());
			else n.setType(((VarTuple) t).getType());
		}
		//System.out.println(n.getType());
		return n.getType();
	}



	@Override
	public Object visit(IntConst n) throws RuntimeException {
		n.setType(Type.INT);
		return Type.INT;
	}



	@Override
	public Object visit(DoubleConst n) throws RuntimeException {
		n.setType(Type.DOUBLE);
		return Type.DOUBLE;
	}



	@Override
	public Object visit(CharConst n) throws RuntimeException {
		n.setType(Type.CHAR);
		return Type.CHAR;
	}



	@Override
	public Object visit(StringConst n) throws RuntimeException {
		n.setType(Type.STRING);
		return Type.STRING;
	}



	@Override
	public Object visit(AssignOp n) throws RuntimeException {
		Type t1 = (Type) n.getId().accept(this);
		Type t2 = (Type) n.getE().accept(this);
		Type toReturn = this.assignOpCompOp[gift(t1)][gift(t2)];
		if(toReturn == null) {
			throw new TypeMismatchException(n.getOp(), t1, t2);
		}else
		n.setType(Type.VOID);
		return Type.VOID;
	}



	@SuppressWarnings("unchecked")
	@Override
	public Object visit(CallOp n) throws RuntimeException {
		n.setType(Type.VOID);
		n.getId().accept(this);
		
		ArrayList<Type> typesArgs;
		ArrayList<Expr> exprOfArgs;
		typesArgs = (n.getA()!= null)? (ArrayList<Type>) n.getA().accept(this): new ArrayList<Type>();
		exprOfArgs= (n.getA() != null)? n.getA().getChildList(): new ArrayList<Expr>();
		String id = n.getId().getId().getValue();
		Tuple t = lookup(id);
		if(t instanceof DefTuple) {
			DefTuple dt = (DefTuple) t;
			if(dt.getParArray().size() != typesArgs.size()) {
				throw new WrongArgumentException(id);
			}
			else {
				ArrayList<ParTuple> format = dt.getParArray();
				int len = format.size();
				for(int i =0; i < len; i++) {
					if((format.get(i).getPt() == ParType.OUT || format.get(i).getPt() == ParType.INOUT)&& !(exprOfArgs.get(i) instanceof IdConst))
					{
						throw new WrongArgumentException(id);
					}
					if(assignOpCompOp[gift(format.get(i).getType())][gift(typesArgs.get(i))] == null) {
						throw new WrongArgumentException(id, i, format.get(i).getType(), typesArgs.get(i));
					}
				}
			}
		} else throw new NotDefinedElementException(id, Kind.DEFDECL);
		return Type.VOID;
	}



	@Override
	public Object visit(IfThenElseOp n) throws RuntimeException {
		Type expr = (Type) n.getE().accept(this);
		n.getCs1().accept(this);
		n.getCs2().accept(this);
		if(expr == Type.BOOL) {
			n.setType(Type.VOID);
		}else throw new TypeMismatchException(n.getOp(), expr);
		return Type.VOID;
	}



	@Override
	public Object visit(IfThenOp n) throws RuntimeException {
		Type expr = (Type) n.getE().accept(this);
		n.getCs().accept(this);
		if(expr == Type.BOOL) {
			n.setType(Type.VOID);
		}else throw new TypeMismatchException(n.getOp(), expr);
		return Type.VOID;
	}



	@Override
	public Object visit(ReadOp n) throws RuntimeException {
		n.setType(Type.VOID);
		n.getV().accept(this);
		return Type.VOID;
	}



	@Override//aggiunta scope WhileOp
	public Object visit(WhileOp n) throws RuntimeException {
		this.stack.push(n.getSym());
		Type expr = (Type) n.getE().accept(this);
		n.getBody().accept(this);
		this.stack.pop();
		this.actualScope = this.stack.peek();
		if(expr == Type.BOOL) {
			n.setType(Type.VOID);
		}else throw new TypeMismatchException(n.getOp(), expr);
		return Type.VOID;
	}



	@Override
	public Object visit(WriteOp n) throws RuntimeException {
		n.setType(Type.VOID);
		n.getA().accept(this);
		return Type.VOID;
	}



	@Override
	public Object visit(Leaf n) throws RuntimeException {
		return n.getValue();
	}



	@Override
	public Object visit(ParDeclSon n) throws RuntimeException {
		n.setType(Type.VOID);
		n.getParType().accept(this);
		n.getTypeLeaf().accept(this);
		n.getId().accept(this);
		return Type.VOID;
	}



	@Override
	public Object visit(VarInit n) throws RuntimeException {
		Type t1 = (Type) n.getId().accept(this);
		Type t2 = (Type) n.getViv().accept(this);
		Type toReturn = this.assignOpCompOp[gift(t1)][gift(t2)];
		if(toReturn != null) {
			n.setType(toReturn);
		}else throw new TypeMismatchException(n.getOp(), t1, t2);
		return toReturn;
	}



	@Override
	public Object visit(VarNotInit n) throws RuntimeException {
		n.setType((Type) n.getId().accept(this));
		return n.getType();
	}



	@Override
	public Object visit(TypeLeaf n) throws RuntimeException {
		return n.getValue();
	}



	@Override
	public Object visit(ParTypeLeaf n) throws RuntimeException {
		return n.getValue();
	}
	
	private Tuple lookup(String id) throws NotDefinedElementException{
		List<SymbolTable> list = stack;
		Tuple toReturn;
		int i = list.size() -1;
		while(i >= 0 && !list.get(i).containsKey(id)) i--;
		if (i < 0) throw new NotDefinedElementException(id); else toReturn =list.get(i).get(id);
		return toReturn;
		}
	
	private int gift(SymbolTable.Type t) {
		int toReturn;
		switch(t) {
		case INT: 	 toReturn = 0; break;
		case STRING: toReturn = 1; break;
		case DOUBLE: toReturn = 2; break;
		case CHAR: 	 toReturn = 3; break;
		case BOOL: 	 toReturn = 4; break;
		case VOID: 	 toReturn = 5; break;
		default: 	 toReturn = -1;
		}
		return toReturn;
	}
	
	public String getAST() {
		return ast;
	}
	
	private void astBuilder(String op, Type t) {
		 this.ast+="<"+op+" type = \""+t+"\">\n";
	}
	
	private void closeTag(String op) {
		 this.ast+="</"+op+">\n";
	}
	
	private void astBuilderWithSymTbl(String op, String scopeName, Type t ) {
		 this.ast+="<"+op+" symTbl = \""+ scopeName +"\" type = \""+t+"\">\n";
	}
	
	private void appendValue(Object value) {
		 this.ast+=value+"\n";
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
