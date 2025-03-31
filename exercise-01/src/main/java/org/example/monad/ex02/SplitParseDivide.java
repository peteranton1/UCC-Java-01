package org.example.monad.ex02;

public class SplitParseDivide {

    public Pair<String> split(String input) {
        if (input == null || !input.contains(",")) {
            String format = String.format("Error split() invalid: %s", input);
            throw new GenericException(format);
        }
        String[] split = input.split(",");
        if (split.length == 2) {
            return new Pair<>(split[0], split[1]);
        } else {
            String format = String.format("Error split() != 2: %s", input);
            throw new GenericException(format);
        }
    }

    public Pair<Double> parse(Pair<String> input) {
        if (input == null || input.a() == null || input.b() == null) {
            String format = String.format("Error parse() invalid: %s", input);
            throw new GenericException(format);
        }
        Double a = Str2Dbl.str2dbl(input.a());
        Double b = Str2Dbl.str2dbl(input.b());
        return new Pair<>(a, b);
    }

    public Double divide(Pair<Double> input) {
        if (input == null ||
                input.a() == null ||
                input.b() == null ||
                input.b() == 0.0) {
            String format = String.format("Error divide() invalid: %s", input);
            throw new GenericException(format);
        }
        return input.a() / input.b() ;
    }
}
