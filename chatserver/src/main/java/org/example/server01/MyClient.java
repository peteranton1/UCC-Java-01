package org.example.server01;

import org.example.MyScanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public record MyClient(String name) {

    public void start(final int portNumber, final MyScanner scanner) {
        System.out.println(name + " : connecting to server on port " + portNumber);
        try (var socket = new Socket("localhost", portNumber);
             var writer = new PrintWriter(socket.getOutputStream(), true);
             var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

            System.out.println("Socket created.");
            for (String userInput; !(userInput = scanner.ask("input: ")).isEmpty(); ) {
                writer.println(userInput);
                System.out.println("Response: " + reader.readLine());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
