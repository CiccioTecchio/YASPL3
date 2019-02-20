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
import java.util.Stack;
import exception.*;
import semantic.*;
import semantic.SymbolTable.*;

public class TypeCheckerVisitor implements Visitor<Object> {
	
	private Stack<SymbolTable> stack;
	
	private SymbolTable.Type[][] sumOpCompTable = {
			{Type.INT, Type.STRING, Type.DOUBLE, Type.CHAR, null, null}, //riga degli interi
			{Type.STRING, Type.STRING, Type.STRING, Type.STRING, Type.STRING, null}, //riga delle stringhe
			{Type.DOUBLE, Type.STRING, Type.DOUBLE, null, null, null}, //riga dei double
			{Type.CHAR, Type.STRING, null, Type.STRING, null, null}, //riga dei char
			{null, Type.STRING, null, null, null, null}, //riga dei bool
	};
	
	private SymbolTable.Type[][] arithOpCompTable = {
			{Type.INT, null, Type.DOUBLE, null, null, null}, //riga degli interi
			{null, null, null, null, null, null}, //riga delle stringhe
			{Type.DOUBLE, null, Type.DOUBLE, null, null, null}, //riga dei double
			{null, null, null, null, null, null}, //riga dei char
			{null, null, null, null, null, null}, //riga dei bool
	};
	
	private SymbolTable.Type[][] compareOpCompTable = {
			{Type.BOOL, null, Type.BOOL, Type.BOOL, null, null}, //riga degli interi
			{null, Type.BOOL, null, null, null, null}, //riga delle stringhe
			{Type.BOOL, null, Type.BOOL, null, null, null}, //riga dei double
			{Type.BOOL, null, null, Type.BOOL, null, null}, //riga dei char
			{null, null, null, null, null, null}, //riga dei bool
	};
	
	private SymbolTable.Type[][] eqOpCompTable = {
			{Type.BOOL, null, Type.BOOL, Type.BOOL, null, null}, //riga degli interi
			{null, Type.BOOL, null, null, null, null}, //riga delle stringhe
			{Type.BOOL, null, Type.BOOL, null, null, null}, //riga dei double
			{Type.BOOL, null, null, Type.BOOL, null, null}, //riga dei char
			{null, null, null, null, Type.BOOL, null}, //riga dei bool
	};
	
	private SymbolTable.Type[][] assignOpCompTable = {
		      {Type.INT, null, Type.INT, Type.INT, null, null},
		      {null, Type.STRING, null, Type.STRING, null, null},
		      {Type.DOUBLE, null, Type.DOUBLE, null, null, null},
		      {Type.CHAR, null, null, Type.CHAR, null, null},
		      {null, null, null, null, Type.BOOL, null}
		  };
	
	private SymbolTable.Type[] uminusCompTable = {Type.INT, null, Type.DOUBLE, null, null};
	
	public TypeCheckerVisitor() {
		this.stack = new Stack<SymbolTable>();

	}
	
	@Override
	public Object visit(Args n) throws RuntimeException {
		ArrayList<SymbolTable.Type> typeArray = new ArrayList<>();
		for (Expr e: n.getChildList()) {
			typeArray.add((Type) e.accept(this));
		}
		n.setType(Type.VOID);
		return typeArray;
	}

	@Override
	public Object visit(Body n) throws RuntimeException {
		Type s = (Type)n.getS().accept(this);
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(CompStat n) throws RuntimeException {
		Type s =(Type) n.getS().accept(this);
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(Decls n) throws RuntimeException {
		for(DeclsWrapper dw: n.getChildList()) {
			dw.accept(this);
		}
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(DefDeclNoPar n) throws RuntimeException {
		stack.push(n.getSym());
		n.getId().accept(this);
		n.getB().accept(this);
		stack.pop();
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(DefDeclPar n) throws RuntimeException {
		stack.push(n.getSym());
		n.getId().accept(this);
		n.getB().accept(this);
		stack.pop();
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(ParDecls n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Programma n) throws RuntimeException {
		this.stack.push(n.getSym());
		n.getD().accept(this);
		n.getS().accept(this);
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(Statements n) throws RuntimeException {
		for(Stat s : n.getChildList()) {
			s.accept(this);
		}
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(VarDecl n) throws RuntimeException {
		Type vdi = (Type)n.getVdi().accept(this);
		n.setType(vdi);
		return n.getType();
	}

	@Override
	public Object visit(VarDecls n) throws RuntimeException {
		for(VarDecl vd: n.getChildList()) {
			vd.accept(this);
		}
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(VarDeclsInit n) throws RuntimeException {
		for(VarDeclsInitWrapper vdiw: n.getChildList()) {
			vdiw.accept(this);
		}
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(VarInitValue n) throws RuntimeException {
		n.setType((Type) n.getE().accept(this));
		return n.getType();
	}

	@Override
	public Object visit(Vars n) throws RuntimeException {
		for(IdConst id : n.getChildList()) {
			id.accept(this);
		}
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(AddOp n) throws TypeMismatchException {
	Type t1 = (Type) n.getE1().accept(this);
	Type t2 = (Type) n.getE2().accept(this);
	Type sumType = sumOpCompTable[gift(t1)][gift(t2)];
	if(sumType!=null) {
		n.setType(sumType);
		return sumType;
	}else {
		throw new TypeMismatchException(n.getOp(),t1,t2);
	}
		
	}

	@Override
	public Object visit(DivOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = arithOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return sumType;
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
	}

	@Override
	public Object visit(MultOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = arithOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return sumType;
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
			
	}

	@Override
	public Object visit(SubOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = arithOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return sumType;
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
			
	}

	@Override
	public Object visit(UminusOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE().accept(this);
		Type sumType = uminusCompTable[gift(t1)];
		if(sumType!=null) {
			n.setType(sumType);
			return sumType;
		}else {
			throw new TypeMismatchException(n.getOp(),t1);
		}
			
	}

	@Override
	public Object visit(AndOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type tr = (t1 == Type.BOOL && t2 == Type.BOOL)? Type.BOOL: null;
		if(tr!=null) {
			n.setType(tr);
			return tr;
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
	}

	@Override
	public Object visit(NotOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE().accept(this);
		Type tr = (t1 == Type.BOOL)? Type.BOOL: null;
		if(tr!=null) {
			n.setType(tr);
			return tr;
		}else {
			throw new TypeMismatchException(n.getOp(),t1);
		}
	}

	@Override
	public Object visit(OrOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type tr = (t1 == Type.BOOL && t2 == Type.BOOL)? Type.BOOL: null;
		if(tr!=null) {
			n.setType(tr);
			return tr;
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}

	}

	@Override
	public Object visit(EqOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = eqOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return n.getType();
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}	
	}

	@Override
	public Object visit(GeOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = compareOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return n.getType();
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
	}

	@Override
	public Object visit(GtOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = compareOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return n.getType();
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
	}

	@Override
	public Object visit(LeOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = compareOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return n.getType();
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
	}

	@Override
	public Object visit(LtOp n) throws TypeMismatchException {
		Type t1 = (Type) n.getE1().accept(this);
		Type t2 = (Type) n.getE2().accept(this);
		Type sumType = compareOpCompTable[gift(t1)][gift(t2)];
		if(sumType!=null) {
			n.setType(sumType);
			return n.getType();
		}else {
			throw new TypeMismatchException(n.getOp(),t1,t2);
		}
	}

	@Override
	public Object visit(BoolConst n) throws RuntimeException {
		n.setType(Type.BOOL);
		return n.getType();
	}

	@Override
	public Object visit(IdConst n) throws RuntimeException {
		Tuple t = lookup(n.getId().getValue());
		if(t instanceof ParTuple) {
			n.setType(((ParTuple )t).getType());
		}else {
			if(t instanceof VarTuple) {
				n.setType(((VarTuple )t).getType());
			}else {
				if(t instanceof DefTuple) {
					n.setType(Type.VOID);
				}
			}
		}
		return n.getType();
	}

	@Override
	public Object visit(IntConst n) throws RuntimeException {
		n.setType(Type.INT);
		return n.getType();
	}

	@Override
	public Object visit(DoubleConst n) throws RuntimeException {
		n.setType(Type.DOUBLE);
		return n.getType();
	}

	@Override
	public Object visit(CharConst n) throws RuntimeException {
		n.setType(Type.CHAR);
		return n.getType();
	}

	@Override
	public Object visit(StringConst n) throws RuntimeException {
		n.setType(Type.STRING);
		return n.getType();
	}

	@Override
	public Object visit(AssignOp n) throws TypeMismatchException {
		Type expr = (Type) n.getE().accept(this);
		Type id = (Type) n.getId().accept(this);
		if(assignOpCompTable[gift(expr)]
				[gift(id)]!=null) {
			n.setType(Type.VOID);
		}else throw new TypeMismatchException(n.getOp(), expr, id);
		return n.getType();
	}

	@Override
	public Object visit(CallOp n) throws RuntimeException {
		n.getId().accept(this);
		String id = n.getId().getId().getValue();
		ArrayList<Type> gettedPar = new ArrayList<>();
		ArrayList<Expr> givenExpr;
		if(n.getA()!= null) {
		givenExpr = n.getA().getChildList();
		} else givenExpr = new ArrayList<>();
		gettedPar= (ArrayList<Type>) n.getA().accept(this);
		Tuple t = lookup(id);
		if(t instanceof DefTuple) {
			DefTuple dt = (DefTuple)t;
			ArrayList<ParTuple> parArray = dt.getParArray();
			int sizeDt = parArray.size();
			int getterSize = gettedPar.size();
			if(sizeDt == getterSize) {
				for(int i =0; i<sizeDt && i< getterSize; i++) {
					if((parArray.get(i).getPt() == ParType.OUT || parArray.get(i).getPt() == ParType.INOUT) &&
						!(givenExpr.get(i) instanceof IdConst)) throw new WrongArgumentException(id, i);
					int pAtype = gift(parArray.get(i).getType());
					int gettedType = gift(gettedPar.get(i));
					if(assignOpCompTable[pAtype][gettedType]!=null) {
						n.setType(Type.VOID);
					}else throw new TypeMismatchException(n.getOp());
				}
			} else throw new WrongArgumentException(id);
		}else throw new NotDefinedElementException(id, Kind.DEFDECL);
		
		return null;
	}

	@Override
	public Object visit(IfThenElseOp n) throws TypeMismatchException {
		Type expr = (Type) n.getE().accept(this);
		Type cs1 = (Type) n.getCs1().accept(this);
		Type cs2 = (Type) n.getCs2().accept(this);
		if(expr==Type.BOOL) {
			n.setType(Type.VOID);
		}else {
			throw new TypeMismatchException(n.getOp(), expr);
		}
		return n.getType();
	}

	@Override
	public Object visit(IfThenOp n) throws RuntimeException {
		Type expr = (Type) n.getE().accept(this);
		Type cs1 = (Type) n.getCs().accept(this);
		if(expr==Type.BOOL) {
			n.setType(Type.VOID);
		}else {
			throw new TypeMismatchException(n.getOp(), expr);
		}
		return n.getType();
	}

	@Override
	public Object visit(ReadOp n) throws RuntimeException {
		Type vars = (Type) n.getV().accept(this);
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(WhileOp n) throws RuntimeException {
		Type expr = (Type) n.getE().accept(this);
		Type cs1 = (Type) n.getCs().accept(this);
		if(expr==Type.BOOL) {
			n.setType(Type.VOID);
		}else {
			throw new TypeMismatchException(n.getOp(), expr);
		}
		return n.getType();
	}

	@Override
	public Object visit(WriteOp n) throws RuntimeException {
		n.getA().accept(this);
		n.setType(Type.VOID);
		return n.getType();
	}

	@Override
	public Object visit(Leaf n) throws RuntimeException {
		return n.getValue();
	}

	@Override
	public Object visit(ParDeclSon n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarInit n) throws RuntimeException {
		Type viv = (Type) n.getViv().accept(this);
		Type id = (Type) n.getId().accept(this);
		if(assignOpCompTable[gift(id)][gift(viv)]!=null) {
			n.setType(Type.VOID);
		}else throw new TypeMismatchException(n.getOp(), viv, id);
		return n.getType();
	}

	@Override
	public Object visit(VarNotInit n) throws RuntimeException {
		n.setType((Type)n.getId().accept(this));
		return n.getType();
	}

	@Override
	public Object visit(TypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ParTypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	//get int from type
	private int gift(SymbolTable.Type t){
		switch(t) {
		case INT: return 0;
		case STRING: return 1;
		case DOUBLE: return 2;
		case CHAR: return 3;
		case BOOL: return 4;
		case VOID: return 5;
		default: return -1;
		}
	}
	
	private Tuple lookup(String id) throws NotDefinedElementException {
		int i=stack.size()-1;
		Tuple toReturn;
		while(i >= 0 && !stack.get(i).containsKey(id)){
			i--;
		}
		if(i<0) throw new NotDefinedElementException(id);
		else {
			toReturn = stack.get(i).get(id);
		}
		
		return toReturn;
	}
	
}
