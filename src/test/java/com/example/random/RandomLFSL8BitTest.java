package com.example.random;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class RandomLFSL8BitTest {

    @Test
    void shouldReturnNextLfslWhen1Call() {
        final int bitsWide = 8;

        final List<Map<Integer, Integer>> seedExpecteds = listOfExpecteds();
        RandomLFSL underTest = new RandomLFSL(bitsWide);
        log.info(underTest.listValues(0b00000001, 255).toString());
        seedExpecteds.forEach(pair -> pair.forEach((k, v) -> {
            int actual = underTest.nextLsfl(k);
            int expected = v;
            if (expected != actual) {
                log.info(displayBits(k, v, actual, bitsWide));
            }
            assertEquals(expected, actual);
        }));
    }

    private ImmutableList<Map<Integer, Integer>> listOfExpecteds() {
        return ImmutableList.of(
//                ImmutableMap.of(0b00000001, 0b10000000),// newBit = 00000001,
//                ImmutableMap.of(0b10000000, 0b01000000),// newBit = 00000000,
//                ImmutableMap.of(0b01000000, 0b00100000),// newBit = 00000000,
//                ImmutableMap.of(0b00100000, 0b00010000),// newBit = 00000000,
//                ImmutableMap.of(0b00010000, 0b00001000),// newBit = 00000000,
//                ImmutableMap.of(0b00001000, 0b00000100),// newBit = 00000000,
//                ImmutableMap.of(0b00000100, 0b00000010),// newBit = 00000000,
//                ImmutableMap.of(0b00000010, 0b10000001),// newBit = 00000001,
//                ImmutableMap.of(0b10000001, 0b11000000),// newBit = 00000001,
//                ImmutableMap.of(0b11000000, 0b01100000),// newBit = 00000000,
//                ImmutableMap.of(0b01100000, 0b00110000),// newBit = 00000000,
//                ImmutableMap.of(0b00110000, 0b00011000),// newBit = 00000000,
//                ImmutableMap.of(0b00011000, 0b00001100),// newBit = 00000000,
//                ImmutableMap.of(0b00001100, 0b00000110),// newBit = 00000000,
//                ImmutableMap.of(0b00000110, 0b10000011),// newBit = 00000001,
//                ImmutableMap.of(0b10000011, 0b01000001),// newBit = 00000000,
//                ImmutableMap.of(0b01000001, 0b10100000),// newBit = 00000001,
//                ImmutableMap.of(0b10100000, 0b01010000),// newBit = 00000000,
//                ImmutableMap.of(0b01010000, 0b00101000),// newBit = 00000000,
//                ImmutableMap.of(0b00101000, 0b00010100),// newBit = 00000000,
//                ImmutableMap.of(0b00010100, 0b00001010),// newBit = 00000000,
//                ImmutableMap.of(0b00001010, 0b10000101),// newBit = 00000001,
//                ImmutableMap.of(0b10000101, 0b11000010),// newBit = 00000001,
//                ImmutableMap.of(0b11000010, 0b11100001),// newBit = 00000001,
//                ImmutableMap.of(0b11100001, 0b11110000),// newBit = 00000001,
//                ImmutableMap.of(0b11110000, 0b01111000),// newBit = 00000000,
//                ImmutableMap.of(0b01111000, 0b00111100),// newBit = 00000000,
//                ImmutableMap.of(0b00111100, 0b00011110),// newBit = 00000000,
//                ImmutableMap.of(0b00011110, 0b10001111),// newBit = 00000001,
//                ImmutableMap.of(0b10001111, 0b01000111),// newBit = 00000000,
//                ImmutableMap.of(0b01000111, 0b00100011),// newBit = 00000000,
//                ImmutableMap.of(0b00100011, 0b00010001),// newBit = 00000000,
//                ImmutableMap.of(0b00010001, 0b10001000),// newBit = 00000001,
//                ImmutableMap.of(0b10001000, 0b01000100),// newBit = 00000000,
//                ImmutableMap.of(0b01000100, 0b00100010),// newBit = 00000000,
//                ImmutableMap.of(0b00100010, 0b10010001),// newBit = 00000001,
//                ImmutableMap.of(0b10010001, 0b11001000),// newBit = 00000001,
//                ImmutableMap.of(0b11001000, 0b01100100),// newBit = 00000000,
//                ImmutableMap.of(0b01100100, 0b00110010),// newBit = 00000000,
//                ImmutableMap.of(0b00110010, 0b10011001),// newBit = 00000001,
//                ImmutableMap.of(0b10011001, 0b11001100),// newBit = 00000001,
//                ImmutableMap.of(0b11001100, 0b01100110),// newBit = 00000000,
//                ImmutableMap.of(0b01100110, 0b10110011),// newBit = 00000001,
//                ImmutableMap.of(0b10110011, 0b01011001),// newBit = 00000000,
//                ImmutableMap.of(0b01011001, 0b10101100),// newBit = 00000001,
//                ImmutableMap.of(0b10101100, 0b01010110),// newBit = 00000000,
//                ImmutableMap.of(0b01010110, 0b10101011),// newBit = 00000001,
//                ImmutableMap.of(0b10101011, 0b01010101),// newBit = 00000000,
//                ImmutableMap.of(0b01010101, 0b10101010),// newBit = 00000001,
//                ImmutableMap.of(0b10101010, 0b11010101),// newBit = 00000001,
//                ImmutableMap.of(0b11010101, 0b11101010),// newBit = 00000001,
//                ImmutableMap.of(0b11101010, 0b11110101),// newBit = 00000001,
//                ImmutableMap.of(0b11110101, 0b11111010),// newBit = 00000001,
//                ImmutableMap.of(0b11111010, 0b11111101),// newBit = 00000001,
//                ImmutableMap.of(0b11111101, 0b11111110),// newBit = 00000001,
//                ImmutableMap.of(0b11111110, 0b11111111),// newBit = 00000001,
//                ImmutableMap.of(0b11111111, 0b01111111),// newBit = 00000000,
//                ImmutableMap.of(0b01111111, 0b00111111),// newBit = 00000000,
//                ImmutableMap.of(0b00111111, 0b00011111),// newBit = 00000000,
//                ImmutableMap.of(0b00011111, 0b00001111),// newBit = 00000000,
//                ImmutableMap.of(0b00001111, 0b00000111),// newBit = 00000000,
//                ImmutableMap.of(0b00000111, 0b00000011),// newBit = 00000000,
//                ImmutableMap.of(0b00000011, 0b00000001),// newBit = 00000000,
//                ImmutableMap.of(0b00000001, 0b10000000),// newBit = 00000001,
//                ImmutableMap.of(0b10000000, 0b01000000),// newBit = 00000000,
//                ImmutableMap.of(0b01000000, 0b00100000),// newBit = 00000000,
//                ImmutableMap.of(0b00100000, 0b00010000),// newBit = 00000000,
//                ImmutableMap.of(0b00010000, 0b00001000),// newBit = 00000000,
//                ImmutableMap.of(0b00001000, 0b00000100),// newBit = 00000000,
//                ImmutableMap.of(0b00000100, 0b00000010),// newBit = 00000000,
//                ImmutableMap.of(0b00000010, 0b10000001),// newBit = 00000001,
//                ImmutableMap.of(0b10000001, 0b11000000),// newBit = 00000001,
//                ImmutableMap.of(0b11000000, 0b01100000),// newBit = 00000000,
//                ImmutableMap.of(0b01100000, 0b00110000),// newBit = 00000000,
//                ImmutableMap.of(0b00110000, 0b00011000),// newBit = 00000000,
//                ImmutableMap.of(0b00011000, 0b00001100),// newBit = 00000000,
//                ImmutableMap.of(0b00001100, 0b00000110),// newBit = 00000000,
//                ImmutableMap.of(0b00000110, 0b10000011),// newBit = 00000001,
//                ImmutableMap.of(0b10000011, 0b01000001),// newBit = 00000000,
//                ImmutableMap.of(0b01000001, 0b10100000),// newBit = 00000001,
//                ImmutableMap.of(0b10100000, 0b01010000),// newBit = 00000000,
//                ImmutableMap.of(0b01010000, 0b00101000),// newBit = 00000000,
//                ImmutableMap.of(0b00101000, 0b00010100),// newBit = 00000000,
//                ImmutableMap.of(0b00010100, 0b00001010),// newBit = 00000000,
//                ImmutableMap.of(0b00001010, 0b10000101),// newBit = 00000001,
//                ImmutableMap.of(0b10000101, 0b11000010),// newBit = 00000001,
//                ImmutableMap.of(0b11000010, 0b11100001),// newBit = 00000001,
//                ImmutableMap.of(0b11100001, 0b11110000),// newBit = 00000001,
//                ImmutableMap.of(0b11110000, 0b01111000),// newBit = 00000000,
//                ImmutableMap.of(0b01111000, 0b00111100),// newBit = 00000000,
//                ImmutableMap.of(0b00111100, 0b00011110),// newBit = 00000000,
//                ImmutableMap.of(0b00011110, 0b10001111),// newBit = 00000001,
//                ImmutableMap.of(0b10001111, 0b01000111),// newBit = 00000000,
//                ImmutableMap.of(0b01000111, 0b00100011),// newBit = 00000000,
//                ImmutableMap.of(0b00100011, 0b00010001),// newBit = 00000000,
//                ImmutableMap.of(0b00010001, 0b10001000),// newBit = 00000001,
//                ImmutableMap.of(0b10001000, 0b01000100),// newBit = 00000000,
//                ImmutableMap.of(0b01000100, 0b00100010),// newBit = 00000000,
//                ImmutableMap.of(0b00100010, 0b10010001),// newBit = 00000001,
//                ImmutableMap.of(0b10010001, 0b11001000),// newBit = 00000001,
//                ImmutableMap.of(0b11001000, 0b01100100),// newBit = 00000000,
//                ImmutableMap.of(0b01100100, 0b00110010),// newBit = 00000000,
//                ImmutableMap.of(0b00110010, 0b10011001),// newBit = 00000001,
//                ImmutableMap.of(0b10011001, 0b11001100),// newBit = 00000001,
//                ImmutableMap.of(0b11001100, 0b01100110),// newBit = 00000000,
//                ImmutableMap.of(0b01100110, 0b10110011),// newBit = 00000001,
//                ImmutableMap.of(0b10110011, 0b01011001),// newBit = 00000000,
//                ImmutableMap.of(0b01011001, 0b10101100),// newBit = 00000001,
//                ImmutableMap.of(0b10101100, 0b01010110),// newBit = 00000000,
//                ImmutableMap.of(0b01010110, 0b10101011),// newBit = 00000001,
//                ImmutableMap.of(0b10101011, 0b01010101),// newBit = 00000000,
//                ImmutableMap.of(0b01010101, 0b10101010),// newBit = 00000001,
//                ImmutableMap.of(0b10101010, 0b11010101),// newBit = 00000001,
//                ImmutableMap.of(0b11010101, 0b11101010),// newBit = 00000001,
//                ImmutableMap.of(0b11101010, 0b11110101),// newBit = 00000001,
//                ImmutableMap.of(0b11110101, 0b11111010),// newBit = 00000001,
//                ImmutableMap.of(0b11111010, 0b11111101),// newBit = 00000001,
//                ImmutableMap.of(0b11111101, 0b11111110),// newBit = 00000001,
//                ImmutableMap.of(0b11111110, 0b11111111),// newBit = 00000001,
//                ImmutableMap.of(0b11111111, 0b01111111),// newBit = 00000000,
//                ImmutableMap.of(0b01111111, 0b00111111),// newBit = 00000000,
//                ImmutableMap.of(0b00111111, 0b00011111),// newBit = 00000000,
//                ImmutableMap.of(0b00011111, 0b00001111),// newBit = 00000000,
//                ImmutableMap.of(0b00001111, 0b00000111),// newBit = 00000000,
//                ImmutableMap.of(0b00000111, 0b00000011),// newBit = 00000000,
//                ImmutableMap.of(0b00000011, 0b00000001),// newBit = 00000000,
//                ImmutableMap.of(0b00000001, 0b10000000),// newBit = 00000001,
//                ImmutableMap.of(0b10000000, 0b01000000),// newBit = 00000000,
//                ImmutableMap.of(0b01000000, 0b00100000),// newBit = 00000000,
//                ImmutableMap.of(0b00100000, 0b00010000),// newBit = 00000000,
//                ImmutableMap.of(0b00010000, 0b00001000),// newBit = 00000000,
//                ImmutableMap.of(0b00001000, 0b00000100),// newBit = 00000000,
//                ImmutableMap.of(0b00000100, 0b00000010),// newBit = 00000000,
//                ImmutableMap.of(0b00000010, 0b10000001),// newBit = 00000001,
//                ImmutableMap.of(0b10000001, 0b11000000),// newBit = 00000001,
//                ImmutableMap.of(0b11000000, 0b01100000),// newBit = 00000000,
//                ImmutableMap.of(0b01100000, 0b00110000),// newBit = 00000000,
//                ImmutableMap.of(0b00110000, 0b00011000),// newBit = 00000000,
//                ImmutableMap.of(0b00011000, 0b00001100),// newBit = 00000000,
//                ImmutableMap.of(0b00001100, 0b00000110),// newBit = 00000000,
//                ImmutableMap.of(0b00000110, 0b10000011),// newBit = 00000001,
//                ImmutableMap.of(0b10000011, 0b01000001),// newBit = 00000000,
//                ImmutableMap.of(0b01000001, 0b10100000),// newBit = 00000001,
//                ImmutableMap.of(0b10100000, 0b01010000),// newBit = 00000000,
//                ImmutableMap.of(0b01010000, 0b00101000),// newBit = 00000000,
//                ImmutableMap.of(0b00101000, 0b00010100),// newBit = 00000000,
//                ImmutableMap.of(0b00010100, 0b00001010),// newBit = 00000000,
//                ImmutableMap.of(0b00001010, 0b10000101),// newBit = 00000001,
//                ImmutableMap.of(0b10000101, 0b11000010),// newBit = 00000001,
//                ImmutableMap.of(0b11000010, 0b11100001),// newBit = 00000001,
//                ImmutableMap.of(0b11100001, 0b11110000),// newBit = 00000001,
//                ImmutableMap.of(0b11110000, 0b01111000),// newBit = 00000000,
//                ImmutableMap.of(0b01111000, 0b00111100),// newBit = 00000000,
//                ImmutableMap.of(0b00111100, 0b00011110),// newBit = 00000000,
//                ImmutableMap.of(0b00011110, 0b10001111),// newBit = 00000001,
//                ImmutableMap.of(0b10001111, 0b01000111),// newBit = 00000000,
//                ImmutableMap.of(0b01000111, 0b00100011),// newBit = 00000000,
//                ImmutableMap.of(0b00100011, 0b00010001),// newBit = 00000000,
//                ImmutableMap.of(0b00010001, 0b10001000),// newBit = 00000001,
//                ImmutableMap.of(0b10001000, 0b01000100),// newBit = 00000000,
//                ImmutableMap.of(0b01000100, 0b00100010),// newBit = 00000000,
//                ImmutableMap.of(0b00100010, 0b10010001),// newBit = 00000001,
//                ImmutableMap.of(0b10010001, 0b11001000),// newBit = 00000001,
//                ImmutableMap.of(0b11001000, 0b01100100),// newBit = 00000000,
//                ImmutableMap.of(0b01100100, 0b00110010),// newBit = 00000000,
//                ImmutableMap.of(0b00110010, 0b10011001),// newBit = 00000001,
//                ImmutableMap.of(0b10011001, 0b11001100),// newBit = 00000001,
//                ImmutableMap.of(0b11001100, 0b01100110),// newBit = 00000000,
//                ImmutableMap.of(0b01100110, 0b10110011),// newBit = 00000001,
//                ImmutableMap.of(0b10110011, 0b01011001),// newBit = 00000000,
//                ImmutableMap.of(0b01011001, 0b10101100),// newBit = 00000001,
//                ImmutableMap.of(0b10101100, 0b01010110),// newBit = 00000000,
//                ImmutableMap.of(0b01010110, 0b10101011),// newBit = 00000001,
//                ImmutableMap.of(0b10101011, 0b01010101),// newBit = 00000000,
//                ImmutableMap.of(0b01010101, 0b10101010),// newBit = 00000001,
//                ImmutableMap.of(0b10101010, 0b11010101),// newBit = 00000001,
//                ImmutableMap.of(0b11010101, 0b11101010),// newBit = 00000001,
//                ImmutableMap.of(0b11101010, 0b11110101),// newBit = 00000001,
//                ImmutableMap.of(0b11110101, 0b11111010),// newBit = 00000001,
//                ImmutableMap.of(0b11111010, 0b11111101),// newBit = 00000001,
//                ImmutableMap.of(0b11111101, 0b11111110),// newBit = 00000001,
//                ImmutableMap.of(0b11111110, 0b11111111),// newBit = 00000001,
//                ImmutableMap.of(0b11111111, 0b01111111),// newBit = 00000000,
//                ImmutableMap.of(0b01111111, 0b00111111),// newBit = 00000000,
//                ImmutableMap.of(0b00111111, 0b00011111),// newBit = 00000000,
//                ImmutableMap.of(0b00011111, 0b00001111),// newBit = 00000000,
//                ImmutableMap.of(0b00001111, 0b00000111),// newBit = 00000000,
//                ImmutableMap.of(0b00000111, 0b00000011),// newBit = 00000000,
//                ImmutableMap.of(0b00000011, 0b00000001),// newBit = 00000000,
//                ImmutableMap.of(0b00000001, 0b10000000),// newBit = 00000001,
//                ImmutableMap.of(0b10000000, 0b01000000),// newBit = 00000000,
//                ImmutableMap.of(0b01000000, 0b00100000),// newBit = 00000000,
//                ImmutableMap.of(0b00100000, 0b00010000),// newBit = 00000000,
//                ImmutableMap.of(0b00010000, 0b00001000),// newBit = 00000000,
//                ImmutableMap.of(0b00001000, 0b00000100),// newBit = 00000000,
//                ImmutableMap.of(0b00000100, 0b00000010),// newBit = 00000000,
//                ImmutableMap.of(0b00000010, 0b10000001),// newBit = 00000001,
//                ImmutableMap.of(0b10000001, 0b11000000),// newBit = 00000001,
//                ImmutableMap.of(0b11000000, 0b01100000),// newBit = 00000000,
//                ImmutableMap.of(0b01100000, 0b00110000),// newBit = 00000000,
//                ImmutableMap.of(0b00110000, 0b00011000),// newBit = 00000000,
//                ImmutableMap.of(0b00011000, 0b00001100),// newBit = 00000000,
//                ImmutableMap.of(0b00001100, 0b00000110),// newBit = 00000000,
//                ImmutableMap.of(0b00000110, 0b10000011),// newBit = 00000001,
//                ImmutableMap.of(0b10000011, 0b01000001),// newBit = 00000000,
//                ImmutableMap.of(0b01000001, 0b10100000),// newBit = 00000001,
//                ImmutableMap.of(0b10100000, 0b01010000),// newBit = 00000000,
//                ImmutableMap.of(0b01010000, 0b00101000),// newBit = 00000000,
//                ImmutableMap.of(0b00101000, 0b00010100),// newBit = 00000000,
//                ImmutableMap.of(0b00010100, 0b00001010),// newBit = 00000000,
//                ImmutableMap.of(0b00001010, 0b10000101),// newBit = 00000001,
//                ImmutableMap.of(0b10000101, 0b11000010),// newBit = 00000001,
//                ImmutableMap.of(0b11000010, 0b11100001),// newBit = 00000001,
//                ImmutableMap.of(0b11100001, 0b11110000),// newBit = 00000001,
//                ImmutableMap.of(0b11110000, 0b01111000),// newBit = 00000000,
//                ImmutableMap.of(0b01111000, 0b00111100),// newBit = 00000000,
//                ImmutableMap.of(0b00111100, 0b00011110),// newBit = 00000000,
//                ImmutableMap.of(0b00011110, 0b10001111),// newBit = 00000001,
//                ImmutableMap.of(0b10001111, 0b01000111),// newBit = 00000000,
//                ImmutableMap.of(0b01000111, 0b00100011),// newBit = 00000000,
//                ImmutableMap.of(0b00100011, 0b00010001),// newBit = 00000000,
//                ImmutableMap.of(0b00010001, 0b10001000),// newBit = 00000001,
//                ImmutableMap.of(0b10001000, 0b01000100),// newBit = 00000000,
//                ImmutableMap.of(0b01000100, 0b00100010),// newBit = 00000000,
//                ImmutableMap.of(0b00100010, 0b10010001),// newBit = 00000001,
//                ImmutableMap.of(0b10010001, 0b11001000),// newBit = 00000001,
//                ImmutableMap.of(0b11001000, 0b01100100),// newBit = 00000000,
//                ImmutableMap.of(0b01100100, 0b00110010),// newBit = 00000000,
//                ImmutableMap.of(0b00110010, 0b10011001),// newBit = 00000001,
//                ImmutableMap.of(0b10011001, 0b11001100),// newBit = 00000001,
//                ImmutableMap.of(0b11001100, 0b01100110),// newBit = 00000000,
//                ImmutableMap.of(0b01100110, 0b10110011),// newBit = 00000001,
//                ImmutableMap.of(0b10110011, 0b01011001),// newBit = 00000000,
//                ImmutableMap.of(0b01011001, 0b10101100),// newBit = 00000001,
//                ImmutableMap.of(0b10101100, 0b01010110),// newBit = 00000000,
//                ImmutableMap.of(0b01010110, 0b10101011),// newBit = 00000001,
//                ImmutableMap.of(0b10101011, 0b01010101),// newBit = 00000000,
//                ImmutableMap.of(0b01010101, 0b10101010),// newBit = 00000001,
//                ImmutableMap.of(0b10101010, 0b11010101),// newBit = 00000001,
//                ImmutableMap.of(0b11010101, 0b11101010),// newBit = 00000001,
//                ImmutableMap.of(0b11101010, 0b11110101),// newBit = 00000001,
//                ImmutableMap.of(0b11110101, 0b11111010),// newBit = 00000001,
//                ImmutableMap.of(0b11111010, 0b11111101),// newBit = 00000001,
//                ImmutableMap.of(0b11111101, 0b11111110),// newBit = 00000001,
//                ImmutableMap.of(0b11111110, 0b11111111),// newBit = 00000001,
//                ImmutableMap.of(0b11111111, 0b01111111),// newBit = 00000000,
//                ImmutableMap.of(0b01111111, 0b00111111),// newBit = 00000000,
//                ImmutableMap.of(0b00111111, 0b00011111),// newBit = 00000000,
//                ImmutableMap.of(0b00011111, 0b00001111),// newBit = 00000000,
//                ImmutableMap.of(0b00001111, 0b00000111),// newBit = 00000000,
//                ImmutableMap.of(0b00000111, 0b00000011),// newBit = 00000000,
//                ImmutableMap.of(0b00000011, 0b00000001),// newBit = 00000000,
//                ImmutableMap.of(0b00000001, 0b10000000),// newBit = 00000001,
//                ImmutableMap.of(0b10000000, 0b01000000),// newBit = 00000000,
//                ImmutableMap.of(0b01000000, 0b00100000),// newBit = 00000000]
//                // repeat 1st value
                ImmutableMap.of(0b00000001, 0b10000000)// newBit = 00000001,
        );
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