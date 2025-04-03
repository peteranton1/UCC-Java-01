package org.example.gatherer.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyMapGathererTest {

    @Test
    void mapTest() {
        MyMapGatherer underTest = new MyMapGatherer();
        List<Integer> expected = List.of(2,3,4,5);
        List<Integer> actual = Stream.of(1,2,3,4)
                .gather(underTest.map(i -> i + 1))
                .toList();
        Assertions.assertEquals(expected, actual);
    }
}