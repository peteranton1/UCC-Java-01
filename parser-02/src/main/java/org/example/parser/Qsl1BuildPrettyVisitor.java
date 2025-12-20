package org.example.parser;

import java.util.List;

public class Qsl1BuildPrettyVisitor extends Qsl1BaseVisitor<String> {

    public static final String CRLF = "\r\n";

    @Override
    public String visitProg(Qsl1Parser.ProgContext ctx) {
        List<Qsl1Parser.LablExprContext> lablExprs = ctx.lablExpr();
        StringBuilder str = new StringBuilder();
        for (Qsl1Parser.LablExprContext lablExpr : lablExprs) {
            str.append(visitLablExpr(lablExpr))
                .append(CRLF);
        }
        return str.toString();
    }

    @Override
    public String visitLablExpr(Qsl1Parser.LablExprContext ctx) {
        Qsl1Parser.IdentContext ident = ctx.ident();
        Qsl1Parser.ExprContext expr = ctx.expr();
        StringBuilder str = new StringBuilder();
        if (ident != null && !ident.isEmpty()) {
            str.append(ident.getText());
        }
        if (expr != null && !expr.isEmpty()) {
            str.append(expr.getText());
        }
        return str.toString();
    }

    @Override
    public String visitExpr(Qsl1Parser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public String visitIdent(Qsl1Parser.IdentContext ctx) {
        return super.visitIdent(ctx);
    }

    @Override
    public String visitObject(Qsl1Parser.ObjectContext ctx) {
        return super.visitObject(ctx);
    }

    @Override
    public String visitListExpr(Qsl1Parser.ListExprContext ctx) {
        return super.visitListExpr(ctx);
    }

    @Override
    public String visitNumExpr(Qsl1Parser.NumExprContext ctx) {
        return super.visitNumExpr(ctx);
    }

    @Override
    public String visitTerm(Qsl1Parser.TermContext ctx) {
        return super.visitTerm(ctx);
    }

    @Override
    public String visitFactor(Qsl1Parser.FactorContext ctx) {
        return super.visitFactor(ctx);
    }
}
