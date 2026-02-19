package org.example.server02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashSet;

public record NioServer(String name) {

    public void start(final int portNumber) {
        var clients = new HashSet<SocketChannel>();
        try (var serverSocketChannel = ServerSocketChannel.open();
             var selector = Selector.open()) {

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(portNumber));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            var buffer = ByteBuffer.allocate(1024);
            System.out.println("Server started.");

            while (true) {
                if (selector.select() == 0) {
                    continue;
                }
                for (var key : selector.selectedKeys()) {
                    if (key.isAcceptable()) {
                        if (key.channel() instanceof ServerSocketChannel channel) {
                            var client = channel.accept();
                            var socket = client.socket();
                            var clientInfo = getClientInfo(socket);
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            clients.add(client);
                            System.out.printf("CONNECTED[%s]: %s%n", clients.size(), clientInfo);
                        }
                    } else if (key.isReadable()) {
                        if (key.channel() instanceof SocketChannel client) {
                            var bytesRead = client.read(buffer);
                            if (bytesRead == -1) {
                                var socket = client.socket();
                                var clientInfo = getClientInfo(socket);
                                System.out.printf("DISCONNECTED[%s]: %s%n", clients.size(), clientInfo);
                                client.close();
                                clients.remove(client);
                            }
                            buffer.flip();
                            var data = new String(buffer.array(),
                                buffer.position(), bytesRead);
                            System.out.print(data);
                            while (buffer.hasRemaining()){
                                client.write(buffer);
                            }
                            buffer.clear();
                        } else {
                            throw new RuntimeException("Unknown channel");
                        }
                    }
                }
                selector.selectedKeys().clear();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            for (var client : clients) {
                try {
                    client.close();
                } catch (IOException e) {
                    System.out.printf("Error closing: %s%n", e.getMessage());
                }
            }
        }
    }

    private String getClientInfo(Socket socket) {
        return "[%s:%s]".formatted(
            socket.getInetAddress().getHostAddress(),
            socket.getPort()
        );
    }
}
