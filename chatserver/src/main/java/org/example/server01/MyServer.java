package org.example.server01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

/**
 * Server using blocking IO and virtual threads
 */
public record MyServer(String name) {

    public void start(final int portNumber) {
        try (var serverSocket = new ServerSocket(portNumber)) {
            System.out.println(name + " : waiting for a client");
            try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                while(true) {
                    var client = serverSocket.accept();
                    executor.submit(() -> {
                            var clientIp = client.getInetAddress().getHostAddress();
                            var clientPort = client.getPort();
                            try (var clientInput = new BufferedReader(
                                new InputStreamReader(client.getInputStream()));
                                 var output = new PrintWriter(client.getOutputStream(), true)) {
                                for (String inputLine; (inputLine = clientInput.readLine()) != null; ) {
                                    String msgTemplate = "[%s:%s] %s";
                                    String msg = String.format(msgTemplate, clientIp, clientPort, inputLine);
                                    System.out.println(msg);
                                    output.println(new StringBuilder(inputLine).reverse());
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
