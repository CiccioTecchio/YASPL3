package visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import semantic.DefTuple;
import semantic.ParTuple;
import semantic.SymbolTable;
import semantic.SymbolTable.ParType;
import semantic.SymbolTable.Type;
import semantic.Tuple;
import syntaxTree.Args;
import syntaxTree.Body;
import syntaxTree.CompStat;
import syntaxTree.Decls;
import syntaxTree.Expr;
import syntaxTree.ParDecls;
import syntaxTree.Programma;
import syntaxTree.Stat;
import syntaxTree.Statements;
import syntaxTree.VarDecls;
import syntaxTree.VarDeclsInit;
import syntaxTree.VarInitValue;
import syntaxTree.Vars;
import syntaxTree.arithOp.AddOp;
import syntaxTree.arithOp.DivOp;
import syntaxTree.arithOp.MultOp;
import syntaxTree.arithOp.SubOp;
import syntaxTree.arithOp.UminusOp;
import syntaxTree.components.Leaf;
import syntaxTree.declsOp.DefDeclNoPar;
import syntaxTree.declsOp.DefDeclPar;
import syntaxTree.declsOp.VarDecl;
import syntaxTree.leaf.BoolConst;
import syntaxTree.leaf.CharConst;
import syntaxTree.leaf.DoubleConst;
import syntaxTree.leaf.IdConst;
import syntaxTree.leaf.IntConst;
import syntaxTree.leaf.ParTypeLeaf;
import syntaxTree.leaf.StringConst;
import syntaxTree.leaf.TypeLeaf;
import syntaxTree.logicOp.AndOp;
import syntaxTree.logicOp.NotOp;
import syntaxTree.logicOp.OrOp;
import syntaxTree.relOp.EqOp;
import syntaxTree.relOp.GeOp;
import syntaxTree.relOp.GtOp;
import syntaxTree.relOp.LeOp;
import syntaxTree.relOp.LtOp;
import syntaxTree.statOp.AssignOp;
import syntaxTree.statOp.CallOp;
import syntaxTree.statOp.IfThenElseOp;
import syntaxTree.statOp.IfThenOp;
import syntaxTree.statOp.ReadOp;
import syntaxTree.statOp.WhileOp;
import syntaxTree.statOp.WriteOp;
import syntaxTree.utils.ParDeclSon;
import syntaxTree.varDeclInitOp.VarInit;
import syntaxTree.varDeclInitOp.VarNotInit;
import syntaxTree.wrapper.DeclsWrapper;
import syntaxTree.wrapper.VarDeclsInitWrapper;

public class CodeVisitor implements Visitor<String> {
	
	private boolean isWrite;
	private Stack<SymbolTable> stack;
	private SymbolTable actualScope;
	private String functionName;
	
	public CodeVisitor() {
		this.isWrite = false;
		this.stack = new Stack<SymbolTable>();
		this.actualScope = new SymbolTable();
		this.functionName = "";
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
					if(e instanceof IdConst) {
					String id = ((IdConst) e).getId().getValue();
					Tuple t = lookup(id);
					if(t instanceof ParTuple) {
						ParType pt = ((ParTuple) t).getPt();
						if(pt != ParType.IN) {
							sb.append("&(");
							sb.append(e.accept(this));
							sb.append(")");
						}else sb.append(e.accept(this));
					}else { //caso id dopo start
						DefTuple dt = (DefTuple) lookup(this.functionName);
						List<ParTuple> listTuple = dt.getParArray();
						if(listTuple.get(i).getPt() != ParType.IN) {
							sb.append("&");
						}
						sb.append(e.accept(this));
					}
				}else sb.append(e.accept(this));
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
		sb.append(n.getS().accept(this));
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
		sb.append("\n}\n");
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
		sb.append("\n}\n");
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
		sb.append("#define STRING_CONST 256\n");
		sb.append("#define PB(b)(b?\"true\":\"false\")");
		sb.append("\n");
		sb.append("typedef char string[STRING_CONST];\n");
		//visita Decls
		sb.append(n.getD().accept(this));
		sb.append("int main(void){\n");
		//visita Statements
		sb.append(n.getS().accept(this));
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
		sb.append(" = ");
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
		sb.append(n.getE1().accept(this));
		sb.append(" == ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(GeOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" >= ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(GtOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" > ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(LeOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" <= ");
		sb.append(n.getE2().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(LtOp n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getE1().accept(this));
		sb.append(" < ");
		sb.append(n.getE2().accept(this));
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
		if(n.getE() instanceof StringConst) {
		sb.append("strcpy(")
		.append(n.getId().accept(this))
		.append(", ")
		.append(n.getE().accept(this))
		.append(");\n");
		}else {
		sb.append(n.getId().accept(this));
		sb.append(" = ");
		sb.append(n.getE().accept(this));
		sb.append(";\n");
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
	    for(IdConst id : n.getChildList()) {
	      if(id.getType() != null) {
	        format.append(this.escapeForC(id.getType()));
	        value.append("&");
	        value.append(id.accept(this));
	        value.append(",");
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
		sb.append("\\n\", ");
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
		sb.append(n.getId().accept(this));
		sb.append(n.getViv().accept(this));
		return sb.toString();
	}

	@Override
	public String visit(VarNotInit n) throws RuntimeException {
		StringBuilder sb = new StringBuilder();
		sb.append(n.getId().accept(this));
		return sb.toString();
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
}
