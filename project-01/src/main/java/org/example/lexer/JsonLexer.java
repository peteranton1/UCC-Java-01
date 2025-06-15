package org.example.lexer;

import lombok.Getter;
import org.example.common.ParserException;

import java.util.List;
import java.util.regex.Matcher;

public class JsonLexer {

    @Getter
    private Token lookahead;
    private int rowPos = 0;
    private int colPos = 0;

    private final List<String> source;

    public JsonLexer(List<String> source) {
        this.source = source;
        lookahead = parseToken();
    }

    public Token nextToken() {
        Token current = lookahead;
        if (Token.isEof(current)) {
            lookahead = null;
        } else {
            lookahead = parseToken();
        }
        return current;
    }

    private Token parseToken() {
        final List<TokType> wsTokens = List.of(
            TokType.C_COMMENT,
            TokType.C_WS
        );
        Token token;
        do {
            token = scanToken();
        } while (token != null && wsTokens.contains(token.tokType()));
        return token;
    }

    private Token scanToken() {
        Location location = Location.builder()
            .rowPos(rowPos)
            .colPos(colPos)
            .build();
        if (rowPos >= source.size()) {
            return eofToken(location);
        }
        String inputLine = getInputLine(location);
        String line = getSubstring(inputLine, colPos, 0);
        for (TokType tokType : TokType.values()) {
            Matcher matcher = tokType.getPattern().matcher(line);
            if (matcher.find()) {
                int end = matcher.end();
                String literal = getSubstring(line, 0, end);
                colPos += end;
                if (colPos >= inputLine.length()) {
                    rowPos++;
                    colPos = 0;
                }
                return Token.builder()
                    .tokType(tokType)
                    .literal(literal)
                    .location(location)
                    .build();
            }
        }
        String message = "Token '%s' not recognised. \nInput: %s\n%s"
            .formatted(line, inputLine, location);
        throw new ParserException(message);
    }

    private static Token eofToken(Location location) {
        return Token.builder()
            .tokType(TokType.D_EOF)
            .literal(TokType.D_EOF.regex)
            .location(location)
            .build();
    }

    private String getInputLine(Location location) {
        if (rowPos < source.size()) {
            return source.get(rowPos);
        }
        String message = "Unexpected run out of input. %s".formatted(location);
        throw new ParserException(message);
    }

    private String getSubstring(String inputLine, int start, int end) {
        if (start <= inputLine.length()) {
            if (end > 0 && end <= inputLine.length()) {
                return inputLine.substring(start, end);
            } else {
                return inputLine.substring(start);
            }
        }
        return inputLine;
    }
}
