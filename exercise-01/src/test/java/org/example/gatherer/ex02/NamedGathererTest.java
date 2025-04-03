package org.example.gatherer.ex02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NamedGathererTest {

    @Test
    void limitTest() {
        NamedGatherer underTest = new NamedGatherer();
        List<Integer> expected = List.of(1, 2, 3);
        List<Integer> actual = Stream
                .of(1, 2, 3, 4)
                .gather(underTest.limit(3))
                .toList();
        Assertions.assertEquals(expected, actual);
    }
}