package com.cicciotecchio.yaspl.visitor;

import com.cicciotecchio.yaspl.syntaxTree.*;
import com.cicciotecchio.yaspl.syntaxTree.arithOp.*;
import com.cicciotecchio.yaspl.syntaxTree.components.*;
import com.cicciotecchio.yaspl.syntaxTree.declsOp.*;
import com.cicciotecchio.yaspl.syntaxTree.leaf.*;
import com.cicciotecchio.yaspl.syntaxTree.logicOp.*;
import com.cicciotecchio.yaspl.syntaxTree.relOp.*;
import com.cicciotecchio.yaspl.syntaxTree.statOp.*;
import com.cicciotecchio.yaspl.syntaxTree.utils.ParDeclSon;
import com.cicciotecchio.yaspl.syntaxTree.varDeclInitOp.*;

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
	E visit(WriteOp n) throws RuntimeException;
	E visit(PreFixInc n) throws RuntimeException;
	E visit(PostFixInc n) throws RuntimeException;
	E visit(PreFixDec n) throws RuntimeException;
	E visit(PostFixDec n) throws RuntimeException;

	E visit(Leaf n) throws RuntimeException;

	E visit(ParDeclSon n) throws RuntimeException;
	E visit(VarInit n) throws RuntimeException;
	E visit(VarNotInit n) throws RuntimeException;

	E visit(TypeLeaf n) throws RuntimeException;
	E visit(ParTypeLeaf n) throws RuntimeException;
}
