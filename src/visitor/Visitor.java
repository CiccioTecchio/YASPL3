package visitor;

import syntaxTree.*;  
import syntaxTree.arithOp.*;
import syntaxTree.comp.*;
import syntaxTree.logicOp.*;
import syntaxTree.relOp.*;
import syntaxTree.statOp.*;
import syntaxTree.wrapper.*;

public interface Visitor<E> {
	
	E visit(Args n);
	E visit(Body n);
	E visit(CompStat n);
	E visit(Decls n);
	E visit(DefDecl n);
	E visit(Expr n);
	E visit(ParDecls n);
	E visit(Programma n);
	//E visit(Stat n);
	E visit(Statements n);
	E visit(VarDecl n);
	E visit(VarDecls n);
	E visit(VarDeclsInit n);
	E visit(VarInitValue n);
	E visit(Vars n);
	
	E visit(AddOp n);
	E visit(DivOp n);
	E visit(MultOp n);
	E visit(SubOp n);
	E visit(UminusOp n);
	
	E visit(AndOp n);
	E visit(NotOp n);
	E visit(OrOp n);
	
	E visit(EqOp n);
	E visit(GeOp n);
	E visit(GtOp n);
	E visit(LeOp n);
	E visit(LtOp n);
	
	E visit(AssignOp n);
	E visit(CallOp n);
	E visit(IfThenElseOp n);
	E visit(IfThenOp n);
	E visit(ReadOp n);
	E visit(WhileOp n);
	E visit(WriteOp n);
	
	E visit(DeclsWrapper n);
	E visit(ParDeclSon n);
	E visit(VarDeclsInitWrapper n);
}