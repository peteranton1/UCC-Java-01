package org.example.gatherer.ex01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BiggestIntTest {

    @Test
    void gatherBunchOk() {
        var stream = Stream.of(5,4,2,1,6,12,8,9);
        int expected = 12;
        int actual = (int) stream.gather(new BiggestInt(11))
                .findFirst().get();
        Assertions.assertEquals(expected, actual);
    }
}