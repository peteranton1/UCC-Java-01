package org.example.monad.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SplitParseDivideWithEitherTest {

    private SplitParseDivide underTest;

    @BeforeEach
    void setUp() {
        underTest = new SplitParseDivide();
    }

    @ParameterizedTest
    @MethodSource("testValues")
    void splitParseDivideMethod1(String input, Double expected) {
        Double actual = null;
        var actual1 = Either
                .of(() -> underTest.split(input));
        if (actual1 instanceof Right<Pair<String>>(Pair<String> result)) {
            var actual2 = Either
                    .of(() -> underTest.parse(result));
            if (actual2 instanceof Right<Pair<Double>>(Pair<Double> result2)) {
                var actual3 = Either
                        .of(() -> underTest.divide(result2));
                if (actual3 instanceof Right<Double>(Double result3)) {
                    actual = result3;
                }
            }
        }
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("testValues")
    void splitParseDivideMethod2(String input, Double expected) {
        Double actual = Either
                .of(() -> underTest.split(input))
                .map(result -> underTest.parse(result))
                .map(result -> underTest.divide(result))
                .orElse(null);
        Assertions.assertEquals(expected, actual);
    }

    public static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.of("10,2", 5.0),
                Arguments.of("10,4", 2.5),
                Arguments.of("10 4", null),
                Arguments.of("A10,4", null),
                Arguments.of("10,0", null)
        );
    }

}