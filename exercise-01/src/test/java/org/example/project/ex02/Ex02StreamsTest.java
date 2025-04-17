package org.example.project.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Ex02StreamsTest {

    private Ex02Streams underTest;

    @BeforeEach
    void setUp() {
        underTest = new Ex02Streams();
    }

    @ParameterizedTest
    @MethodSource("argPump")
    void charSorter(String input, List<String> expected) {
        List<String> actual = underTest.charSorter(input);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> argPump() {
        return Stream.of(
            Arguments.of(
                "Hello", List.of("e=1", "h=1", "l=2", "o=1")),
            Arguments.of(
                "Java is cool!", List.of("a=2", "c=1", "i=1", "j=1", "l=1", "o=2", "s=1", "v=1"))
        );
    }
}