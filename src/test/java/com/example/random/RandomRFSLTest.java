package com.example.random;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RandomRFSLTest {

    @Test
    void shouldReturnNextIntWhen1Call() {
        final Map<Integer, Integer> seedExpecteds = ImmutableMap.of(
                1234567, 1234567,
                1234568, 12345680
        );
        seedExpecteds.forEach((k,v) -> {
            RandomRFSL underTest = new RandomRFSL(k);
            int actual = underTest.nextInt();
            int expected = v;
            assertEquals(expected, actual);
        });
    }
}