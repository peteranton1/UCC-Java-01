package org.example.streams;

import java.util.stream.IntStream;

public class Ex01Streams {

    public int[] getIntArray(int low, int high) {
        return IntStream.rangeClosed(low, high).toArray();
    }

    public double[] getDoubleArray(int low, int high) {
        return IntStream.rangeClosed(low, high).mapToDouble(i -> (double) i).toArray();
    }

    public String[] getStringArray(int low, int high) {
        return (String[]) IntStream.rangeClosed(low, high)
                .mapToObj(String::valueOf).toArray();
    }

}
