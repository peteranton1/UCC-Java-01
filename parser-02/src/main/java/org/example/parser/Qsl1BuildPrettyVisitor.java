package org.example.parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Qsl1BuildPrettyVisitor extends Qsl1BaseVisitor<String> {

    public static final String SPACE = " ";

    @Override
    public String visitProg(Qsl1Parser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public String visitExpr(Qsl1Parser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }

    @Override
    public String visitObject(Qsl1Parser.ObjectContext ctx) {
        return super.visitObject(ctx);
    }

    @Override
    public String visitListExpr(Qsl1Parser.ListExprContext ctx) {
        return super.visitChildren(ctx);
    }

    @Override
    public String visitLablExpr(Qsl1Parser.LablExprContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitNumExpr(Qsl1Parser.NumExprContext ctx) {
        List<TerminalNode> opPlus = ctx.OP_PLUS();
        List<TerminalNode> opMinus = ctx.OP_MINUS();
        // ACTION : do something with op
        return visitChildren(ctx);
    }

    @Override
    public String visitTerm(Qsl1Parser.TermContext ctx) {
        List<TerminalNode> opMult = ctx.OP_MULT();
        List<TerminalNode> opDiv = ctx.OP_DIV();
        // ACTION : do something with ops
        return visitChildren(ctx);
    }

    @Override
    public String visitFactor(Qsl1Parser.FactorContext ctx) {
        TerminalNode number = ctx.NUMBER();
        TerminalNode id = ctx.ID();
        TerminalNode lbrack = ctx.LBRACK();
        TerminalNode rbrack = ctx.RBRACK();
        if(number != null) {
            return SPACE + number.getText() + SPACE;
        } else if(id != null) {
            return SPACE + id.getText() + SPACE;
        } else {
            return SPACE + lbrack + visitChildren(ctx) + rbrack + SPACE;
        }
    }

    @Override
    public String visitTextExpr(Qsl1Parser.TextExprContext ctx) {
        String text = ctx.TEXT().getText();
        return SPACE + text + SPACE;
    }

    @Override
    public String visitIdent(Qsl1Parser.IdentContext ctx) {
        String id = ctx.ID().getText();
        String colon = ctx.COLON().getText();
        return SPACE + id + SPACE + colon + SPACE;
    }
}
