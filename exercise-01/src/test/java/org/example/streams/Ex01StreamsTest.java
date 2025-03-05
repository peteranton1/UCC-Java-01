package org.example.streams;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Ex01StreamsTest {

    private Ex01Streams underTest;

    @BeforeEach
    void setUp() {
        underTest = new Ex01Streams();
    }

    @Test
    void getIntArrayRange1to10() {
        int[] expected = new int[]{1,2,3,4,5,6,7,8,9,10};
        Assertions.assertArrayEquals(expected, underTest.getIntArray(1, 10));
    }

    @Test
    void getDoubleArrayRange1to10() {
        double[] expected = new double[]{1,2,3,4,5,6,7,8,9,10};
        Assertions.assertArrayEquals(expected, underTest.getDoubleArray(1, 10));
    }

    @Test
    void getStringArrayRange1to10() {
        String[] expected = new String[]{"1","2","3","4","5","6","7","8","9","10"};
        Assertions.assertArrayEquals(expected, underTest.getStringArray(1, 10));
    }
}