package org.example.monad.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SplitParseDivideTest {

    private SplitParseDivide underTest;

    @BeforeEach
    void setUp() {
        underTest = new SplitParseDivide();
    }

    @ParameterizedTest
    @MethodSource("testValues")
    void splitParseDivide(String input, Double expected) {
        var actual1 = underTest.split(input);
        var actual2 = underTest.parse(actual1);
        var actual3 = underTest.divide(actual2);
        Assertions.assertEquals(expected, actual3);
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