package org.example.lexer;

import org.example.common.TokenStringsHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Disabled
class JsonLexerTest {


    @Test
    void getLookaheadWithOk() {
        List<String> source = List.of("{\"somekey\": \"somevalue\"}");
        JsonLexer underTest = new JsonLexer(source);
        Token current;
        Token lookahead;
        do {
            lookahead = underTest.getLookahead();
            current = underTest.nextToken();
        } while (current != null && !Token.isEof(current));
        Assertions.assertEquals(lookahead, current);
    }

    @ParameterizedTest
    @MethodSource("sourceArgs")
    void nextTokenWithInputs(List<String> source, List<Token> expected) {
        JsonLexer underTest = new JsonLexer(source);
        List<Token> actualTokens = new ArrayList<>();
        Token token;
        do {
            token = underTest.nextToken();
            if (token != null) {
                actualTokens.add(token);
            }
        } while (token != null && !Token.isEof(token));
        TokenStringsHelper.printTokenBuilderList(actualTokens);
        Assertions.assertEquals(expected.size(), actualTokens.size());
    }

    public static Stream<Arguments> sourceArgs() {
        return Stream.of(
            Arguments.of(
                List.of("{\"somekey\": \"somevalue\"}"),
                List.of(
                    Token.builder()
                        .tokType(TokType.B_LBRACE)
                        .literal("{")
                        .location(Location.builder()
                            .rowPos(0)
                            .colPos(0).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somekey\"")
                        .location(Location.builder()
                            .rowPos(0)
                            .colPos(1).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_COLON)
                        .literal(":")
                        .location(Location.builder()
                            .rowPos(0)
                            .colPos(10).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somevalue\"")
                        .location(Location.builder()
                            .rowPos(0)
                            .colPos(12).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_RBRACE)
                        .literal("}")
                        .location(Location.builder()
                            .rowPos(0)
                            .colPos(23).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.D_EOF)
                        .literal("EOF")
                        .location(Location.builder()
                            .rowPos(1)
                            .colPos(0).build())
                        .build()

                )
            ),
            Arguments.of(
                List.of("{",
                    "   \"somekey1\":    \"somevalue1\",   ",
                    "   \"somekey2\":    \"somevalue2\",   ",
                    "   \"somekey3\":    \"somevalue3\"    ",
                    "}"),
                List.of(
                    Token.builder()
                        .tokType(TokType.B_LBRACE)
                        .literal("{")
                        .location(Location.builder()
                            .rowPos(0)
                            .colPos(0).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somekey1\"")
                        .location(Location.builder()
                            .rowPos(1)
                            .colPos(0).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_COLON)
                        .literal(":")
                        .location(Location.builder()
                            .rowPos(1)
                            .colPos(10).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somevalue1\"")
                        .location(Location.builder()
                            .rowPos(1)
                            .colPos(12).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_COMMA)
                        .literal(",")
                        .location(Location.builder()
                            .rowPos(1)
                            .colPos(24).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somekey2\"")
                        .location(Location.builder()
                            .rowPos(2)
                            .colPos(0).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_COLON)
                        .literal(":")
                        .location(Location.builder()
                            .rowPos(2)
                            .colPos(10).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somevalue2\"")
                        .location(Location.builder()
                            .rowPos(2)
                            .colPos(12).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_COMMA)
                        .literal(",")
                        .location(Location.builder()
                            .rowPos(2)
                            .colPos(24).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somekey3\"")
                        .location(Location.builder()
                            .rowPos(3)
                            .colPos(0).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_COLON)
                        .literal(":")
                        .location(Location.builder()
                            .rowPos(3)
                            .colPos(10).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.A_STRING)
                        .literal("\"somevalue3\"")
                        .location(Location.builder()
                            .rowPos(3)
                            .colPos(12).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.B_RBRACE)
                        .literal("}")
                        .location(Location.builder()
                            .rowPos(4)
                            .colPos(0).build())
                        .build(),
                    Token.builder()
                        .tokType(TokType.D_EOF)
                        .literal("EOF")
                        .location(Location.builder()
                            .rowPos(5)
                            .colPos(0).build())
                        .build()
                )
            )
        );
    }
}