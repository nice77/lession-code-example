package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class Server {

    private List<ServerConnection> serverConnections;
    private ServerSocket serverSocket;

    public Server() {
        this.serverConnections = new LinkedList<>();
        try {
            this.serverSocket = new ServerSocket(8000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            while (true) {
                this.serverConnections.add(
                            new ServerConnection(this.serverSocket.accept(),
                            this::sendMessage
                        ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        for (ServerConnection serverConnection : this.serverConnections) {
            serverConnection.send(message);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
