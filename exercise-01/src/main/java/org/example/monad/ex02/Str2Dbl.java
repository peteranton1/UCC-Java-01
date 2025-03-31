package org.example.monad.ex02;

public class Str2Dbl {
    public static double str2dbl(String input) {
        try {
            return Double.parseDouble(input);
        } catch(NumberFormatException e) {
            throw new GenericException(e);
        }
    }
}
