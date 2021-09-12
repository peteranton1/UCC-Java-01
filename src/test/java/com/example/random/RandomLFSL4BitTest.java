package com.example.random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class RandomLFSL4BitTest {

    @Test
    void shouldReturnNextLfslWhen1Call() {
        final int bitsWide = 4;

        final List<Map<Integer, Integer>> seedExpecteds = ImmutableList.of(
                ImmutableMap.of(0b1001, 0b1100),// newBit = 0001
                ImmutableMap.of(0b1100, 0b0110),// newBit = 0000
                ImmutableMap.of(0b0110, 0b1011),// newBit = 0001
                ImmutableMap.of(0b1011, 0b0101),// newBit = 0000
                ImmutableMap.of(0b0101, 0b1010),// newBit = 0001
                ImmutableMap.of(0b1010, 0b1101),// newBit = 0001
                ImmutableMap.of(0b1101, 0b1110),// newBit = 0001
                ImmutableMap.of(0b1110, 0b1111),// newBit = 0001
                ImmutableMap.of(0b1111, 0b0111),// newBit = 0000
                ImmutableMap.of(0b0111, 0b0011),// newBit = 0000
                ImmutableMap.of(0b0011, 0b0001),// newBit = 0000
                ImmutableMap.of(0b0001, 0b1000),// newBit = 0001
                ImmutableMap.of(0b1000, 0b0100),// newBit = 0000
                ImmutableMap.of(0b0100, 0b0010),// newBit = 0000
                ImmutableMap.of(0b0010, 0b1001),// newBit = 0001
                ImmutableMap.of(0b1001, 0b1100),// newBit = 0001
                // repeat 1st value
                ImmutableMap.of(0b00001001, 0b00001100)// newBit = 00000001
        );
        RandomLFSL underTest = new RandomLFSL(bitsWide);
        seedExpecteds.forEach(pair -> pair.forEach((k, v) -> {
            int actual = underTest.nextLsfl(k);
            int expected = v;
            if (expected != actual) {
                log.info(displayBits(k, v, actual, bitsWide));
            }
            assertEquals(expected, actual);
        }));
    }

    private String displayBits(int state, int expected, int actual, int bitsWide) {
        BinString binStr = BinString.of(bitsWide);
        String stateStr = binStr.toBinStr(state);
        String expectedStr = binStr.toBinStr(expected);
        String actualStr = binStr.toBinStr(actual);
        return String.format("state: %s, expected: %s, actual: %s",
                stateStr, expectedStr, actualStr);
    }
}