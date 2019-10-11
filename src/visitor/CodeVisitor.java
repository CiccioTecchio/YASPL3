package visitor;

import java.util.ArrayList;
import java.util.Stack;

import semantic.SymbolTable;
import syntaxTree.Args;
import syntaxTree.Body;
import syntaxTree.CompStat;
import syntaxTree.Decls;
import syntaxTree.ParDecls;
import syntaxTree.Programma;
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

public class CodeVisitor implements Visitor<String> {

	private Stack<SymbolTable> stack;
	
	
	public CodeVisitor() {
		this.stack = new Stack<>();
	}
	
	@Override
	public String visit(Args n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Body n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(CompStat n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DefDeclPar n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ParDecls n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Programma n) throws RuntimeException {
		this.stack.push(n.getSym());
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
		sb.append("\n");
		sb.append("typedef char string[STRING_CONST];\n");
		sb.append("string yasplBuffer;\n");
		sb.append("string toParse;\n\n");
		//visita Decls
		sb.append(n.getD().accept(this));
		sb.append("int main(void){\n");
		//visita Statements
		sb.append(n.getS().accept(this));
		sb.append("return 0;\n}");
		return sb.toString();
	}

	@Override
	public String visit(Statements n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarDecl n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarDecls n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarDeclsInit n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarInitValue n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Vars n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(AddOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DivOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(MultOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(SubOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(UminusOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(AndOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(NotOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(OrOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(EqOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(GeOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(GtOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LeOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(LtOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(BoolConst n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(IdConst n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(IntConst n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(DoubleConst n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(CharConst n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(StringConst n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(AssignOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(CallOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(IfThenElseOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(IfThenOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ReadOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(WhileOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(WriteOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Leaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ParDeclSon n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarInit n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarNotInit n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(TypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ParTypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
