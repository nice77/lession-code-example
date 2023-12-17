package client;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageListener extends Thread {

    private TextArea textArea;
    private BufferedReader br;

    public MessageListener(TextArea textArea, BufferedReader br) {
        this.textArea = textArea;
        this.br = br;
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                message = this.br.readLine();
                this.textArea.appendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
