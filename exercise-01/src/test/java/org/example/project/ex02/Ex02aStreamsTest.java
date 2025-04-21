package org.example.project.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Ex02aStreamsTest {

    @ParameterizedTest
    @MethodSource("smartyPants")
    void charStreamWhenExamples(String input, List<String> expected) {
        Ex02aStreams underTest = new Ex02aStreams();
        List<String> actual = underTest.charStream(input);
        Assertions.assertEquals(expected,actual);
    }

    public static Stream<Arguments> smartyPants() {
        return Stream.of(
            Arguments.of(null, List.of()),
            Arguments.of("", List.of()),
            Arguments.of("Hello", List.of("e=1", "h=1", "l=2", "o=1")),
            Arguments.of("Java is cool!", List.of(
                "a=2", "c=1", "i=1", "j=1", "l=1", "o=2", "s=1", "v=1"
            ))
        );
    }
}