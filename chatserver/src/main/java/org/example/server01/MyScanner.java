package org.example.server01;

import java.io.Closeable;
import java.util.Scanner;

public class MyScanner implements Closeable {
    private final Scanner scanner;

    public MyScanner() {
        this.scanner = new Scanner(System.in);
    }

    public String ask(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
