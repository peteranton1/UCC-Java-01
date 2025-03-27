package org.example.monad.ex01;

import java.util.Optional;

public class SplitParseRead1 {
    /*
    Ex01 - Perform the following actions on a string:

    1. Accept a String such as "10,5" and convert to a pair of strings.
    2. Convert the two strings to two doubles
    3. Divide the first double by the second double
    4. Handle exceptions and null values
     */
    public record Pair<T>(T a, T b) {
    }

    public Optional<Pair<String>> split(String input) {
        if(input == null){
            return Optional.empty();
        }
        String[] strings = input.split(",");
        if (strings.length != 2) {
            return Optional.empty();
        }
        return Optional.of(new Pair<>(strings[0], strings[1]));
    }

    public Optional<Pair<Double>> parse(Pair<String> input) {
        Double a = str2dbl(input.a());
        Double b = str2dbl(input.b());
        if(a == null || b == null){
            return Optional.empty();
        }
        return Optional.of(new Pair<>(a, b));
    }

    public Optional<Double> divide(Pair<Double> input) {
        Double a = input.a();
        Double b = input.b();
        if(a == null || b == null || b == 0){
            return Optional.empty();
        }
        return Optional.of(input.a / input.b);
    }

    private Double str2dbl(String input) {
        try {
            return Double.parseDouble(input);
        } catch(NumberFormatException e){
            return null;
        }
    }
}
