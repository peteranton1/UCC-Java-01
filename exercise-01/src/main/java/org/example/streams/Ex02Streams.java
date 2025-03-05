package org.example.streams;

import java.util.stream.IntStream;

public class Ex02Streams {
    public int[] getIntArray(int low, int high, int skipped) {
        return IntStream.rangeClosed(low, high).skip(skipped).toArray();
    }

    public double[] getDoubleArray(int low, int high, int skipped) {
        return IntStream.rangeClosed(low, high).skip(skipped).mapToDouble(i -> (double) i).toArray();
    }

    public String[] getStringArray(int low, int high, int skipped) {
        Object[] objects = IntStream.rangeClosed(low, high)
                .skip(skipped)
                .mapToObj(String::valueOf).toArray();
        String[] output = new String[objects.length];
        for(int i=0;i< objects.length; i++){
            output[i] = objects[i].toString();
        }
        return output;
    }
}
