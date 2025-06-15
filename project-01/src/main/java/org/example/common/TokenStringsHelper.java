package org.example.common;

import org.example.lexer.Token;

import java.util.List;

public class TokenStringsHelper {
    public static String tokenBuilderString(Token token) {
        if (token == null) {
            return null;
        }
        return "Token.builder()\n"
            + ".tokType(TokType."
            + token.tokType().name()
            + ")\n"
            + ".literal(\"" + escapeQuotes(token.literal()) + "\")\n"
            + ".location(Location.builder()\n\t.rowPos("
            + token.location().rowPos()
            + ")\n\t.colPos("
            + token.location().colPos()
            + ").build())\n"
            + ".build(),";
    }

    private static String escapeQuotes(String literal) {
        String output = literal;
        if (literal.startsWith("\"")){
            output = "\\\"" + literal.substring(1,literal.length()-1) + "\\\"";
        }
        return output;
    }

    public static void printTokenBuilderList(List<Token> tokens) {
        tokens.forEach(t -> {
            String builder = TokenStringsHelper.tokenBuilderString(t);
            System.out.println(builder);
        });
    }
}
