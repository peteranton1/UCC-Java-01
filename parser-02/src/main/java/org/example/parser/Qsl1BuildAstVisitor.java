package org.example.parser;

public class Qsl1BuildAstVisitor  extends Qsl1BaseVisitor<String> {
    @Override
    public String visitProg(Qsl1Parser.ProgContext ctx) {
        return super.visitProg(ctx);
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
    public String visitExpr(Qsl1Parser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }
}
