package com.example.random;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RandomLFSL {

    private final int bitsWide;

    public RandomLFSL(int bitsWide) {
        this.bitsWide = bitsWide;
    }

    public int nextLsfl(int state) {
        final int bitsToCompare = bitsWide;
        int newBit = (state ^ (state >> 1)) & 1;
        return (state >> 1) | (newBit << bitsToCompare - 1);
    }

    public List<String> listValues(int state, int repeats) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < repeats; i++) {
            int next = nextLsfl(state);
            int newBit = (state ^ (state >> 1)) & 1;
            results.add("\n" + displayBits(state, newBit, next));
            state = next;
        }
        return results;
    }

    public String displayBits(int state, int newBit, int next) {
        BinString binStr = BinString.of(bitsWide);
        String stateStr = binStr.toBinStr(state);
        String newBitStr = binStr.toBinStr(newBit);
        String nextStr = binStr.toBinStr(next);
//        return String.format("ImmutableMap.of(0b%s, 0b%s),// newBit = %s",
//                stateStr, nextStr, newBitStr);
        return String.format("0b%s, 0b%s, 0b%s",
                stateStr, nextStr, newBitStr);
    }
}
