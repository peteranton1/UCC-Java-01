package com.example.random;

public class RandomRFSL {
    private int seed;

    public RandomRFSL(int seed) {
        this.seed = seed;
    }

    public int nextInt() {
        return seed;
    }
}
