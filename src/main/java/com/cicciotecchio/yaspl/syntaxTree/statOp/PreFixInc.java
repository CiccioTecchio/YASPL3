package com.cicciotecchio.yaspl.syntaxTree.statOp;

import com.cicciotecchio.yaspl.syntaxTree.Stat;
import com.cicciotecchio.yaspl.syntaxTree.leaf.IdConst;
import com.cicciotecchio.yaspl.visitor.Visitable;
import com.cicciotecchio.yaspl.visitor.Visitor;
import java_cup.runtime.ComplexSymbolFactory;

public class PreFixInc extends Stat implements Visitable {

    private IdConst id;
    private String op;

    public PreFixInc(ComplexSymbolFactory.Location left, ComplexSymbolFactory.Location right, String op, IdConst id) {
        super(left, right, op, id);
        this.id = id;
        this.op = op;
    }

    @Override
    public Object accept(Visitor<?> visitor) {
        return visitor.visit(this);
    }

    public IdConst getId() {
        return id;
    }

    @Override
    public String getOp() {
        return op;
    }


}
