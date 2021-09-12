package com.example.random;

import java.util.ArrayList;
import java.util.List;

public class BinString {
    int bitsWide;
    List<String> binStrs;

    public BinString(int bitsWide) {
        this.bitsWide = bitsWide;
        this.binStrs = new ArrayList<>();
    }

    public static BinString of(int bitsWide) {
        return new BinString(bitsWide);
    }

    public BinString add(String prefix, int number) {
        binStrs.add(prefix + toBinStr(number));
        return this;
    }

    public List<String> toList() {
        return binStrs;
    }

    public String toBinStr(int number) {
        String binaryString = Integer.toBinaryString(number);
        String outValue = String.format("%32s", binaryString)
                .replace(' ', '0');
        int actualLen = outValue.length();
        if (actualLen > bitsWide) {
            outValue = outValue.substring(actualLen - bitsWide);
        }
        return outValue;
    }
}
