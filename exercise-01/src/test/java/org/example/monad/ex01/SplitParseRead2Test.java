package org.example.monad.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

class SplitParseRead2Test {

    @ParameterizedTest
    @MethodSource("provideCasesOk")
    void splitParseDivide(String input, Double expected) {
        SplitParseRead2 underTest = new SplitParseRead2();
        Optional<String> inputOpt =
                Optional.ofNullable(input);
        var ans1 = inputOpt
                .map(i -> tryer(_ -> underTest.split(i), i))
                .map(i -> tryer(_ -> underTest.parse(i), i))
                .map(i -> tryer(_ -> underTest.divide(i), i))
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

    private <T, R> R tryer(Function<T, R> fn, T t) {
        try {
            if (t != null) {
                return fn.apply(t);
            }
        } catch (Exception _) {
            // ignore exception
        }
        return null;
    }
}