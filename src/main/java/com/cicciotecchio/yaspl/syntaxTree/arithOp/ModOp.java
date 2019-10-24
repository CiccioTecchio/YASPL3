package com.cicciotecchio.yaspl.syntaxTree.arithOp;

import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;


public class ModOp extends Expr implements Visitable {
    private String op;
    private Expr e1;
    private Expr e2;

    public ModOp(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right, String op, Expr e1, Expr e2) {
        super(left, right, op, e1, e2);
        this.op = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public String getOp() {
        return op;
    }

    public Expr getE1() {
        return e1;
    }

    public Expr getE2() {
        return e2;
    }

    @Override
    public Object accept(Visitor<?> visitor) {
        return visitor.visit(this);
    }
}
