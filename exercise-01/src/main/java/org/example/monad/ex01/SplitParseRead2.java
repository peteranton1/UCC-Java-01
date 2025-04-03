package org.example.monad.ex01;

import java.util.Optional;

public class SplitParseRead2 {
    /*
    Ex01 - Perform the following actions on a string:

    1. Accept a String such as "10,5" and convert to a pair of strings.
    2. Convert the two strings to two doubles
    3. Divide the first double by the second double
    4. Handle exceptions and null values
     */
    public record Pair<T>(T a, T b) {
    }

    public Pair<String> split(String input) {
        if(input == null){
            return null;
        }
        String[] strings = input.split(",");
        if (strings.length != 2) {
            return null;
        }
        return new Pair<>(strings[0], strings[1]);
    }

    public Pair<Double> parse(Pair<String> input) {
        Double a = str2dbl(input.a());
        Double b = str2dbl(input.b());
        return new Pair<>(a, b);
    }

    public Double divide(Pair<Double> input) {
        Double a = input.a();
        Double b = input.b();
        if(a == null || b == null || b == 0){
            return null;
        }
        return input.a / input.b;
    }

    private Double str2dbl(String input) {
        return Double.parseDouble(input);
    }
}
