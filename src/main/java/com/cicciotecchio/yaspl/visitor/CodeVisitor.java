package com.cicciotecchio.yaspl.visitor;

import com.cicciotecchio.yaspl.semantic.*;
import com.cicciotecchio.yaspl.semantic.SymbolTable.ParType;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;

public class CodeVisitor implements Visitor<String> {
	
	private boolean isWrite;
	private Stack<SymbolTable> stack;
	private SymbolTable actualScope;
	private String functionName;
	private TreeMap<String, String> stringInit;
	private final String OPUGUALE = " = ";

	public CodeVisitor() {
		this.isWrite = false;
		this.stack = new Stack<SymbolTable>();
		this.actualScope = new SymbolTable();
		this.functionName = "";
		this.stringInit = new TreeMap<>();
	}
	
	
	@Override
	public String visit(Args n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		List<Expr> list = n.getChildList();
		
		if(isWrite) {
		StringBuilder format = new StringBuilder();
		for(Expr e: list) {
			sb.append(this.needQuotes(e));
			sb.append(",");
			format.append(escapeForC(e.getType()));
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		sb.append("#");
		sb.append(format.toString());
		} 
		else { //args invocata da CallOp
			if(n.getChildList().size()!=0) {
				int size = list.size();
				for(int i = 0; i<size; i++) {
					Expr e = list.get(i);
					DefTuple dt = (DefTuple) lookup(this.functionName);
					List<ParTuple> t = dt.getParArray();
						if(t.get(i).getPt()!= ParType.IN) {
							sb.append("&(");
							sb.append(e.accept(this));
							sb.append(")");
						}else {
							sb.append(e.accept(this));
					}
				sb.append(",");
				}
				sb.deleteCharAt(sb.lastIndexOf(","));
			}
		}
		return sb.toString();
	}

	@Override
	public String visit(Body n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getVd().accept(this));
		sb.append(allocateString());
		sb.append(n.getS().accept(this));
		sb.append(this.freeString());
		this.stack.pop();
		this.actualScope = this.stack.peek();
		return sb.toString();
	}

	@Override
	public String visit(CompStat n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getS().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(Decls n) throws RuntimeException {
		ArrayList<DeclsWrapper> list = n.getChildList();
		StringBuilder sb = new StringBuilder();
		for(DeclsWrapper dw : list) {
			if(dw instanceof VarDecl) {
				sb.append(dw.accept(this));
			}
		}
		sb.append("\n");
		for(DeclsWrapper dw : list) {
			if(!(dw instanceof VarDecl)) {
				sb.append(dw.accept(this));
			}
		}
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public String visit(DefDeclNoPar n) throws RuntimeException {
		this.stack.push(n.getSym());
		this.actualScope = this.stack.peek();
		StringBuilder sb = new StringBuilder();
		sb.append("void ");
		sb.append(n.getId().accept(this));
		sb.append("(){\n");
		sb.append(n.getB().accept(this));
		sb.append("}\n\n");
		return sb.toString();
	}

	@Override
	public String visit(DefDeclPar n) throws RuntimeException {
		this.stack.push(n.getSym());
		this.actualScope = this.stack.peek();
		StringBuilder sb = new StringBuilder();
		sb.append("void ");
		sb.append(n.getId().accept(this).toString());
		sb.append("(");
		sb.append(n.getPd().accept(this));
		sb.append("){\n");
		sb.append(n.getB().accept(this));
		sb.append("}\n\n");
		return sb.toString();
	}

	@Override
	public String visit(ParDecls n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		List<ParDeclSon> list = n.getChildList();
		for(ParDeclSon son: list) {
			sb.append(son.accept(this));
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}

	@Override
	public String visit(Programma n) throws RuntimeException {
		stack.push(n.getSym());
		this.actualScope = this.stack.peek();
		StringBuilder sb = new StringBuilder();
		sb.append("#include <stdio.h>\n");
		sb.append("#include <stdlib.h>\n");
		sb.append("#include <string.h>\n");
		sb.append("#include <unistd.h>\n");
		sb.append("#include <math.h>\n");
		sb.append("\n");
		sb.append("typedef int bool;\n");
		sb.append("#define false 0\n");
		sb.append("#define true 1\n");
		sb.append("#define PB(b)(b?\"true\":\"false\")");
		sb.append("\n");
		sb.append("typedef char* string;\n");
		//visita Decls
		sb.append(n.getD().accept(this));
		sb.append("int main(void){\n");
		sb.append(this.allocateString());
		//visita Statements
		sb.append(n.getS().accept(this));
		sb.append(this.freeString());
		sb.append("return 0;\n}");
		this.stack.pop();
		return sb.toString();
	}

	@Override
	public String visit(Statements n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		List<Stat> list = n.getChildList();
		for(Stat s: list) {
			sb.append(s.accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(VarDecl n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getT().accept(this));
		sb.append(" ");
		sb.append(n.getVdi().accept(this));
		sb.append(";\n");
		return sb.toString();
	}

	@Override
	public String visit(VarDecls n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		List<VarDecl> list = n.getChildList();
		for(VarDecl vd: list) {
			sb.append(vd.accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(VarDeclsInit n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		List<VarDeclsInitWrapper> list = n.getChildList();
		for(VarDeclsInitWrapper vdiw: list) {
			sb.append(vdiw.accept(this));
			sb.append(",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}

	@Override
	public String visit(VarInitValue n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(OPUGUALE);
		sb.append(n.getE().accept(this));
		return sb.toString();
	}

	

	@Override
	public String visit(AddOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" + ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(DivOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" / ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(MultOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" * ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(SubOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" - ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(UminusOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append("-");
		sb.append(n.getE().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(AndOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" && ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(NotOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append("!(");
		sb.append(n.getE().accept(this));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String visit(OrOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" || ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(EqOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		//TODO se n.getE1 è un'IdConst fai lookup per cercarla fra gli scope se è una stringa va confrontata con strcmp
		final boolean E1 = n.getE1().getType() == Type.STRING;
		final boolean E2 = n.getE2().getType() == Type.STRING;
		if(E1 && E2) {
				sb.append("strcmp(")
				.append(n.getE1().accept(this))
				.append(",")
				.append(n.getE2().accept(this))
				.append(")").append(" == 0");
		}else {
		sb.append(n.getE1().accept(this));
		sb.append(" == ");
		sb.append(n.getE2().accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(GeOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		final boolean E1 = n.getE1().getType() == Type.STRING;
		final boolean E2 = n.getE2().getType() == Type.STRING;
		if(E1 && E2) {
				sb.append("strcmp(")
				.append(n.getE1().accept(this))
				.append(",")
				.append(n.getE2().accept(this))
				.append(")").append(" >= 0");
		}else {
		sb.append(n.getE1().accept(this));
		sb.append(" >= ");
		sb.append(n.getE2().accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(GtOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		final boolean E1 = n.getE1().getType() == Type.STRING;
		final boolean E2 = n.getE2().getType() == Type.STRING;
		if(E1 && E2) {
				sb.append("strcmp(")
				.append(n.getE1().accept(this))
				.append(",")
				.append(n.getE2().accept(this))
				.append(")").append(" > 0");
		}else {
		sb.append(n.getE1().accept(this));
		sb.append(" > ");
		sb.append(n.getE2().accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(LeOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		final boolean E1 = n.getE1().getType() == Type.STRING;
		final boolean E2 = n.getE2().getType() == Type.STRING;
		if(E1 && E2) {
				sb.append("strcmp(")
				.append(n.getE1().accept(this))
				.append(",")
				.append(n.getE2().accept(this))
				.append(")").append(" <= 0");
		}else {
		sb.append(n.getE1().accept(this));
		sb.append(" <= ");
		sb.append(n.getE2().accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(LtOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		final boolean E1 = n.getE1().getType() == Type.STRING;
		final boolean E2 = n.getE2().getType() == Type.STRING;
		if(E1 && E2) {
				sb.append("strcmp(")
				.append(n.getE1().accept(this))
				.append(",")
				.append(n.getE2().accept(this))
				.append(")").append(" < 0");
		}else {
		sb.append(n.getE1().accept(this));
		sb.append(" < ");
		sb.append(n.getE2().accept(this));
		}
		return sb.toString();
	}

	@Override
	public String visit(BoolConst n) throws RuntimeException {
		return n.getId().accept(this).toString();
	}

	@Override
	public String visit(IdConst n) throws RuntimeException {
		String id = n.getId().accept(this).toString();
		Tuple t = lookup(id);
		return (t instanceof ParTuple && ((ParTuple)t).getPt()!= ParType.IN)?"*"+id:id;
	}

	@Override
	public String visit(IntConst n) throws RuntimeException {
		return n.getId().accept(this).toString();
	}

	@Override
	public String visit(DoubleConst n) throws RuntimeException {
		return n.getId().accept(this).toString();
	}

	@Override
	public String visit(CharConst n) throws RuntimeException {
		return n.getId().accept(this).toString();
	}

	@Override
	public String visit(StringConst n) throws RuntimeException {
		return n.getId().accept(this).toString();
	}

	@Override
	public String visit(AssignOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		List<Expr> argsList = n.getA().getChildList();
		String id = n.getId().accept(this).toString();
		
		//sb.append(n.getE().accept(this));
		if(argsList.size() == 1) {
			Expr e = argsList.get(0);
			if(e instanceof StringConst || 
			  (e instanceof IdConst && e.getType() == Type.STRING)) {
				sb.append(String.format("strcpy(%s,%s);\n",id, e.accept(this)));
			}else {
			sb.append(id);
			sb.append(OPUGUALE);
			sb.append(argsList.get(0).accept(this));
			sb.append(";\n");
			}
		}else {
			isWrite = true;
			String[] tmp = n.getA().accept(this).toString().split("#");
			String format = tmp[0];
			String value = tmp[1];
			sb.append(String.format("sprintf(%s,\"%s\",%s);\n", id, value, format));
			isWrite = false;
		}
		
		return sb.toString();
	}

	@Override
	public String visit(CallOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		String id = n.getId().accept(this).toString(); 
		sb.append(id);
		sb.append("(");
		this.functionName = id;
		if(n.getA()!=null)sb.append(n.getA().accept(this));
		this.functionName = "";
		sb.append(");\n");
		return sb.toString();
	}

	@Override
	public String visit(IfThenElseOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append("if(");
		sb.append(n.getE().accept(this));
		sb.append("){\n");
		sb.append(n.getCs1().accept(this));
		sb.append("}\nelse{\n");
		sb.append(n.getCs2().accept(this));
		sb.append("}\n");
		return sb.toString();
	}

	@Override
	public String visit(IfThenOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append("if(");
		sb.append(n.getE().accept(this));
		sb.append("){\n");
		sb.append(n.getCs().accept(this));
		sb.append("}\n");
		return sb.toString();
	}
	
	@Override
	  public String visit(Vars n) throws RuntimeException {
	    StringBuilder format = new StringBuilder();
	    StringBuilder value = new StringBuilder();
	    StringBuilder res = new StringBuilder();
	    List<IdConst> list = n.getChildList();
	    for(IdConst id : list) {
	      if(id.getType() != null) {
	        format.append(this.escapeForC(id.getType()));
	        if(id.getType().toString().equalsIgnoreCase("STRING")) {
	        	value.append(id.accept(this));
	        	value.append(",");
	        }else {
	        value.append("&");
	        value.append(id.accept(this));
	        value.append(",");
	        }
	      }
	    }
	    value.deleteCharAt(value.lastIndexOf(","));
	    res.append(format.toString());
	    res.append("#");
	    res.append(value.toString());
	    
	    return res.toString();
	  }

	@Override
	  public String visit(ReadOp n) throws RuntimeException {
	    StringBuilder sb = new StringBuilder();
	    String vars = n.getV().accept(this).toString();
	    String[] temp = vars.split("#");
	    String format = temp[0];
	    String value = temp[1];
	    sb.append("scanf(\"");
	    sb.append(format);
	    sb.append("\", ");
	    sb.append(value);
	    sb.append(");\n");
	    return sb.toString();
	  }

	@Override
	public String visit(WhileOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append("while(");
		sb.append(n.getE().accept(this));
		sb.append("){\n");
		this.stack.push(n.getSym());
		this.actualScope = this.stack.peek();
		sb.append(n.getBody().accept(this));
		sb.append("}\n");
		return sb.toString();
	}

	@Override
	public String visit(WriteOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		this.isWrite = true;
		String args = n.getA().accept(this).toString();
		String[] tmp = args.split("#");
		String value = tmp[0];
		String format = tmp[1];
		sb.append("printf(\"");
		sb.append(format);
		sb.append("\", ");
		//sb.append("\\n\", ");
		sb.append(value);
		sb.append(");\n");
		this.isWrite = false;
		return sb.toString();
	}

	@Override
	public String visit(Leaf n) throws RuntimeException {
		return n.getValue();
	}

	@Override
	public String visit(ParDeclSon n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getTypeLeaf().accept(this));
		sb.append(" ");
		//sb.append(n.getParType().accept(this));
		sb.append(n.getId().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(VarInit n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		String id = n.getId().accept(this).toString();
		String viv = n.getViv().accept(this).toString();
		
		if(n.getId().getType() == Type.STRING) {
			this.stringInit.put(id, viv.replace(OPUGUALE, ""));	
		}
		sb.append(id);
		sb.append(viv);
		return sb.toString();
	}

	@Override
	public String visit(VarNotInit n) throws RuntimeException {
		return n.getId().accept(this).toString();
	}

	@Override
	public String visit(TypeLeaf n) throws RuntimeException {
		return n.getValue().toLowerCase();
	}

	@Override
	public String visit(ParTypeLeaf n) throws RuntimeException {
		return (!(n.getValue().equalsIgnoreCase("in")))?"*":"";
	}

	private String escapeForC(Type type) {
		String tr="";
		switch(type) {
		case INT: tr+="%d";break;
		case DOUBLE: tr+="%lf";break;
		case CHAR: tr+="%c";break;
		case STRING: tr+="%s";break;
		case BOOL: tr+="%s";break;
		}
		return tr;
	}
	
	private String needQuotes(Expr e) {
		StringBuilder sb = new StringBuilder();
		if(e instanceof BoolConst) {
			sb.append("\"");
			sb.append(e.accept(this));
			sb.append("\"");
		}else {
				if((e instanceof IdConst) &&  ((IdConst)e).getType() == Type.BOOL
					|| (e instanceof EqOp || e instanceof NotOp
					||  e instanceof GeOp || e instanceof GtOp
					||  e instanceof LeOp || e instanceof LtOp
					||  e instanceof AndOp || e instanceof OrOp)
						) {
					
					sb.append("PB("+e.accept(this)+")");
				}else
					sb.append(e.accept(this));
			
		}
		return sb.toString();
	}
	
	private Tuple lookup(String id) {
		int i = this.stack.indexOf(actualScope);
		boolean find = false;
		while(!find && i>=0){
			find = this.stack.get(i).containsKey(id);
			i--;
		}
		i++;
		return this.stack.get(i).get(id);
	}
	
	
	private String allocateString() {
		String tr ="";
		for(Entry<String, Tuple>e: this.actualScope.entrySet()) {
			if(e.getValue() instanceof VarTuple && ((VarTuple) e.getValue()).getType() == Type.STRING) {
				tr+=e.getKey()+" = malloc(256 * sizeof(char));\n";
				if(stringInit.containsKey(e.getKey()))
					tr+="strcpy("+e.getKey()+", "+this.stringInit.get(e.getKey())+");\n";
			}
		}
		return tr;
	}
	
	private String freeString() {
		String tr ="";
		for(Entry<String, Tuple>e: this.actualScope.entrySet()) {
			if(e.getValue() instanceof VarTuple && ((VarTuple) e.getValue()).getType() == Type.STRING) {
				tr+="free("+e.getKey()+");\n";
			}
		}
		return tr;
	}
}
