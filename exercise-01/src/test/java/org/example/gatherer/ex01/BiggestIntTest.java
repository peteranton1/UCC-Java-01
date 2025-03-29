package org.example.gatherer.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BiggestIntTest {

    @Test
    void gatherBunchWhenStartAt11() {
        var stream = Stream.of(5,4,2,1,6,12,8,9);
        final int myLimit = 11;
        int expected = 12;
        int actual = (int) stream.gather(new BiggestInt(myLimit))
                .findFirst().get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void gatherBunchWhenStartAt2() {
        var stream = Stream.of(5,4,2,1,6,12,8,9);
        final int myLimit = 2;
        int expected = 12;
        int actual = (int) stream.gather(new BiggestInt(myLimit))
                .findFirst().orElse(1);
        Assertions.assertEquals(expected, actual);
    }
}