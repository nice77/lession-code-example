package server;

import java.io.*;
import java.net.Socket;

public class ServerConnection extends Thread {

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private SendMessageToOthers sendMessageToOthers;

    public ServerConnection(Socket socket, SendMessageToOthers sendMessageToOthers) {
        try {
            this.socket = socket;
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.sendMessageToOthers = sendMessageToOthers;
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                message = br.readLine();
                System.out.println(message);
                this.sendMessageToOthers.sendMessageToOthers(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void send(String message) {
        try {
            this.bw.write(message + "\n");
            this.bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
