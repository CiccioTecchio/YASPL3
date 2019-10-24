package com.cicciotecchio.yaspl.syntaxTree.statOp;

import com.cicciotecchio.yaspl.syntaxTree.Body;
import com.cicciotecchio.yaspl.syntaxTree.Expr;
import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IntConst;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;
public class ForOp extends Stat implements Visitable {

    private String op;
    private IdConst id;
    private Expr start;
    private Expr end;
    private IntConst incr;
    private Body b;
    private boolean minus;

    public ForOp(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right, String op, IdConst id, Expr start, Expr end, IntConst incr, Body b) {
        super(left, right, op, id, start, end, incr, b);
        this.op = op;
        this.id = id;
        this.start = start;
        this.end = end;
        this.incr = incr;
        this.b = b;
        this.minus = false;
    }

    public ForOp(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right, String op, IdConst id, Expr start, Expr end, boolean minus, IntConst incr, Body b) {
        super(left, right, op, id, start, end, minus, incr, b);
        this.op = op;
        this.id = id;
        this.start = start;
        this.end = end;
        this.incr = incr;
        this.b = b;
        this.minus = minus;
    }

    @Override
    public String getOp() {
        return op;
    }

    public IdConst getId() {
        return id;
    }

    public Expr getStart() {
        return start;
    }

    public Expr getEnd() {
        return end;
    }

    public boolean getMinus(){return minus;}

    public IntConst getIncr() {
        return incr;
    }

    public Body getB() {
        return b;
    }

    @Override
    public Object accept(Visitor<?> visitor) {
        return visitor.visit(this);
    }


}
