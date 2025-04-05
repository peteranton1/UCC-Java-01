package org.example.generic.ex01;

public class GenericsExample {
    public static void main(String[] args) {
        IntegerPrinter printerA1 = new IntegerPrinter(123);
        DoublePrinter printerA2 = new DoublePrinter(1.11);
        StringPrinter printerA3 = new StringPrinter("Hello");
        printerA1.print();
        printerA2.print();
        printerA3.print();

        Printer<Integer> printer1 = new Printer<>(123);
        Printer<Double> printer2 = new Printer<>(1.11);
        Printer<String> printer3 = new Printer<>("Hello");
        printer1.print();
        printer2.print();
        printer3.print();
    }
}
