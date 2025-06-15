package org.example.lexer;

import lombok.Builder;

@Builder
public record Token(TokType tokType, String literal, Location location) {

    public static boolean isEof(Token token) {
        return token == null || TokType.D_EOF.equals(token.tokType());
    }
}
