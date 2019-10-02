package visitor;

import syntaxTree.*;  
import syntaxTree.arithOp.*;
import syntaxTree.components.*;
import syntaxTree.declsOp.*;
import syntaxTree.leaf.*;
import syntaxTree.logicOp.*;
import syntaxTree.relOp.*;
import syntaxTree.statOp.*;
import syntaxTree.utils.ParDeclSon;
import syntaxTree.varDeclInitOp.*;

public interface Visitor<E> {

	E visit(Args n) throws RuntimeException;
	E visit(Body n) throws RuntimeException;
	E visit(CompStat n) throws RuntimeException;
	E visit(Decls n) throws RuntimeException;
	E visit(DefDeclNoPar n) throws RuntimeException;
	E visit(DefDeclPar n) throws RuntimeException;
	E visit(ParDecls n) throws RuntimeException;
	E visit(Programma n) throws RuntimeException;
	E visit(Statements n) throws RuntimeException;
	E visit(VarDecl n) throws RuntimeException;
	E visit(VarDecls n) throws RuntimeException;
	E visit(VarDeclsInit n) throws RuntimeException;
	E visit(VarInitValue n) throws RuntimeException;
	E visit(Vars n) throws RuntimeException;

	E visit(AddOp n) throws RuntimeException;
	E visit(DivOp n) throws RuntimeException;
	E visit(MultOp n) throws RuntimeException;
	E visit(SubOp n) throws RuntimeException;
	E visit(UminusOp n) throws RuntimeException;
	E visit(ModOp n) throws RuntimeException;
	E visit(PowOp n) throws RuntimeException;
	E visit(SqrtOp n) throws RuntimeException;

	E visit(PostFixIncrement n) throws RuntimeException;
	E visit(PostFixDecrement n) throws RuntimeException;
	E visit(PreFixIncrement n) throws RuntimeException;
	E visit(PreFixDecrement n) throws RuntimeException;
	
	E visit(AndOp n) throws RuntimeException;
	E visit(NotOp n) throws RuntimeException;
	E visit(OrOp n) throws RuntimeException;

	E visit(EqOp n) throws RuntimeException;
	E visit(GeOp n) throws RuntimeException;
	E visit(GtOp n) throws RuntimeException;
	E visit(LeOp n) throws RuntimeException;
	E visit(LtOp n) throws RuntimeException;

	E visit(BoolConst n) throws RuntimeException;
	E visit(IdConst n) throws RuntimeException;
	E visit(IntConst n) throws RuntimeException;
	E visit(DoubleConst n) throws RuntimeException;
	E visit(CharConst n) throws RuntimeException;
	E visit(StringConst n) throws RuntimeException;

	E visit(AssignOp n) throws RuntimeException;
	E visit(CallOp n) throws RuntimeException;
	E visit(IfThenElseOp n) throws RuntimeException;
	E visit(IfThenOp n) throws RuntimeException;
	E visit(ReadOp n) throws RuntimeException;
	E visit(WhileOp n) throws RuntimeException;
	E visit(DoWhileOp n) throws RuntimeException;
	E visit(WriteOp n) throws RuntimeException;
	E visit(Leaf n) throws RuntimeException;

	E visit(ParDeclSon n) throws RuntimeException;
	E visit(VarInit n) throws RuntimeException;
	E visit(VarNotInit n) throws RuntimeException;

	E visit(TypeLeaf n) throws RuntimeException;
	E visit(ParTypeLeaf n) throws RuntimeException;
}
