package org.example.server01;

public class MyMain {

    private static final int PORT_NUMBER = 12345;
    private static final String SERVER = "server";

    public static void main(String[] args) {
        try (var scanner = new MyScanner()) {
            String nameQn = scanner.ask("Client number (empty for server) : ");
            String name = (nameQn == null || nameQn.isBlank() ? SERVER : nameQn);

            if(name.equalsIgnoreCase(SERVER)) {
                new MyServer(name).start(PORT_NUMBER);
            } else {
                new MyClient(name).start(PORT_NUMBER, scanner);
            }
        }
    }
}
