package com.example.random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeriodCalculatorTest {

    @Test
    void calc16Bit() {
        PeriodCalculator underTest = new PeriodCalculator();
        int startState = 0xACE1;
        int expected = 357913941;
        int actual = underTest.calc16Bit(startState);
        Assertions.assertEquals(expected, actual);
    }
}