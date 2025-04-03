package org.example.monad.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

class SplitParseRead3Test {

    @ParameterizedTest
    @MethodSource("provideCasesOk")
    void splitParseDivide(String input, Double expected) {
        SplitParseRead3 underTest = new SplitParseRead3();
        Optional<String> inputOpt =
                Optional.ofNullable(input);
        var ans1 = inputOpt
                .map(underTest::split)
                .map(underTest::parse)
                .map(underTest::divide)
                .orElse(null);
        Assertions.assertEquals(expected, ans1, "Test " + input);
    }

    private static Stream<Arguments> provideCasesOk() {
        return Stream.of(
                Arguments.of("1,1", 1.0),
                Arguments.of("2,2", 1.0),
                Arguments.of("10,5", 2.0),
                Arguments.of("10,4", 2.5),
                Arguments.of("10 4", null),
                Arguments.of("A10,4", null),
                Arguments.of("10,0", null)
        );
    }
}