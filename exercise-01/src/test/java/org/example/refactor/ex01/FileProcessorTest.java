package org.example.refactor.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FileProcessorTest {

    private FileProcessor underTest;
    private final String PARENT_PATH = "build/resources/test/refactor/ex01/";

    private static Stream<Arguments> allArgs() {
        return Stream.of(
            Arguments.of(
                new String[]{
                    "test-a.txt",
                    "test-b.txt"
                }, new FileProcessor.FileResult(FileProcessor.Result.SUCCESS,
                    "Complete. Files Written: 2")),
            Arguments.of(
                new String[]{
                }, new FileProcessor.FileResult(FileProcessor.Result.BAD_ARGS,
                    "Input args were null")),
            Arguments.of(
                new String[]{
                    "Does Not Exist"
                }, new FileProcessor.FileResult(FileProcessor.Result.NOT_FOUND,
                    "FileNotFound: build/resources/test/refactor/ex01/" +
                        "Does Not Exist (No such file or directory)"))
        );
    }

    @BeforeEach
    void setUp() {
        underTest = new FileProcessor();
    }

    @ParameterizedTest
    @MethodSource("allArgs")
    void processToCsvWithMethod1(String[] filenames, FileProcessor.FileResult expected) {
        var actual = underTest.processToCsvMethod1(filenames, PARENT_PATH);
        Assertions.assertEquals(expected, actual);
    }


    @ParameterizedTest
    @MethodSource("allArgs")
    void processToCsvWithMethod2(String[] filenames, FileProcessor.FileResult expected) {
        var actual = underTest.processToCsvMethod2(filenames, PARENT_PATH);
        Assertions.assertEquals(expected, actual);
    }

}