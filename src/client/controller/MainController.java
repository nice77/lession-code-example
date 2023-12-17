package client.controller;

import client.MessageListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.Socket;

public class MainController {

    @FXML
    private Button connectBtn;
    @FXML
    private TextArea textArea;
    @FXML
    private Button submitBtn;
    @FXML
    private TextField messageInput;
    @FXML
    private Label naem;

    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private String naym;


    @FXML
    public void initialize() {
        System.out.println("Initializing...");
        this.naym = "";

        this.naem.setText("Name: " + this.naym);

        connectBtn.setOnMouseClicked(event -> {
            connect();
            this.submitBtn.setDisable(false);
            MessageListener messageListener = new MessageListener(this.textArea, this.br);
            messageListener.start();
        });

        submitBtn.setOnMouseClicked(event -> {
            try {
                this.bw.write(this.messageInput.getText() + "\n");
                this.bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.messageInput.setText("");
        });
    }

    private void connect() {
        try {
            this.socket = new Socket("localhost", 8000);
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNaym(String naym) {
        this.naym = naym;
        this.naem.setText("Name: " + this.naym);
    }
}
