package org.example.server02;

import org.example.MyScanner;

public class NioMain {
    private static final int PORT_NUMBER = 12345;
    private static final String SERVER = "server";

    public static void main(String[] args) {
        try (var scanner = new MyScanner()) {
            String nameQn = scanner.ask("Client number (empty for server) : ");
            String name = (nameQn == null || nameQn.isBlank() ? SERVER : nameQn);

            if (name.equalsIgnoreCase(SERVER)) {
                new NioServer(name).start(PORT_NUMBER);
            } else {
                new NioClient(name).start(PORT_NUMBER, scanner);
            }
        }
    }
}
