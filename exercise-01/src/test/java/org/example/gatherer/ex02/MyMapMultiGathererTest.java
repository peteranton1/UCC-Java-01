package org.example.gatherer.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

class MyMapMultiGathererTest {

    @Test
    void mapMultiTest() {
        MyMapMultiGatherer underTest = new MyMapMultiGatherer();
        List<Integer> expected = List.of(1, 2, 3, 4);
        var actual = Stream
                .of(List.of(1, 2), List.of(3, 4))
                .gather(underTest.mapMulti(List::forEach))
                .toList();
        Assertions.assertEquals(expected, actual);
    }
}