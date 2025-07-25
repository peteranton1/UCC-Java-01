package org.example;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.parser.MathBaseVisitor;
import org.example.parser.MathParser;

import java.util.List;

public class PrettyMathVisitor extends MathBaseVisitor<String> {
    @Override
    public String visitProg(MathParser.ProgContext ctx) {
        return ctx.expr() == null ?
            "" :
            "("
                + this.visitExprs(ctx.expr())
                + ")";
    }

    public String visitExprs(List<MathParser.ExprContext> ctxs) {
        StringBuilder str = new StringBuilder();
        if (ctxs != null && !ctxs.isEmpty()) {
            str.append("\n")
                .append(this.visitExpr(ctxs.getFirst()));
            for (int i = 1; i < ctxs.size(); i++) {
                MathParser.ExprContext exprCtx = ctxs.get(i);
                TerminalNode anInt = exprCtx.INT();
                if (anInt != null){
                    str.append(" ")
                        .append(anInt.getText());
                } else {
                    str.append(this.visitExprs(exprCtx.expr()));
                }
            }
        }
        return str.toString();
    }

    @Override
    public String visitExpr(MathParser.ExprContext ctx) {
        TerminalNode anInt = ctx.INT();
        if (anInt != null){
            return anInt.getText();
        } else {
            return this.visitExprs(ctx.expr());
        }
    }
}
