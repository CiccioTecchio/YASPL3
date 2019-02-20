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

public class SymTableVisitor implements Visitor<Object> {
	
	private Stack<SymbolTable> stack;
	private SymbolTable actualScope;
	
	public SymTableVisitor() {
		this.stack = new Stack<SymbolTable>();
	}
	
	@Override
	public Object visit(Args n) {
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
		System.out.println(this.stack.pop().toString());
		this.actualScope = this.stack.lastElement();
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
	public Object visit(ParDeclSon n) throws AlreadyDeclaredException {
		ParType x = this.getValueOfParTypeLeaf((String)n.getParType().accept(this));
		ParTuple t = new ParTuple(Kind.VARDECL, x, this.getValueOfLeaf(n.getTypeLeaf()));
		String id = (String)n.getId().accept(this);
		if(!this.actualScope.containsKey(id))
		this.actualScope.put(id, t);
		else throw new AlreadyDeclaredException("Variabile "+ id+ " già dichiarata");
		return t;
	}
	
	@Override
	public Object visit(VarDecl n)throws AlreadyDeclaredException  {
		ArrayList<String> idList = (ArrayList<String>) n.getVdi().accept(this);
		for(String s: idList) {
			if(!this.actualScope.containsKey(s)) {
			VarTuple t = new VarTuple(Kind.VARDECL, this.getValueOfLeaf(n.getT()));
			this.actualScope.put(s, t);
			} else {
				throw new AlreadyDeclaredException("AlreadyDeclaredException");
			}
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DefDeclNoPar n) {
		SymbolTable sc = new SymbolTable();
		DefTuple t = new DefTuple(Kind.DEFDECL);
		this.actualScope.put((String)n.getId().accept(this), t);
		this.stack.push(sc);
		this.actualScope = this.stack.lastElement();
		n.setSym(actualScope);
		n.getB().accept(this);
		this.stack.pop();
		return null;
	}

	@Override
	public Object visit(DefDeclPar n) {
		SymbolTable sc = new SymbolTable();
		DefTuple t = new DefTuple(Kind.DEFDECL);
		this.actualScope.put((String)n.getId().accept(this), t);
		this.stack.push(sc);
		this.actualScope = this.stack.lastElement();
		n.setSym(actualScope);
		t.setParArray((ArrayList<ParTuple>) n.getPd().accept(this));
		n.getB().accept(this);
		this.stack.pop();
		return null;
	}

	
	@Override
	public Object visit(CompStat n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Programma n) {
		this.stack.push(new SymbolTable());
		this.actualScope = this.stack.lastElement();
		n.setSym(actualScope);
		n.getD().accept(this);
		return null;
	}

	@Override
	public Object visit(Statements n) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Vars n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AddOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DivOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MultOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(SubOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(UminusOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AndOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(NotOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(OrOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(EqOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(GeOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(GtOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LeOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LtOp n) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CallOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IfThenElseOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IfThenOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ReadOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(WhileOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(WriteOp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Leaf n) {
		return n.getValue();
	}



	@Override
	public Object visit(VarInit n) {
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

}
