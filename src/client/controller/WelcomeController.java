package client.controller;

import client.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class WelcomeController {
    @FXML
    public TextField textField;
    @FXML
    public Button submitBtn;

    @FXML
    public void initialize() {
        this.submitBtn.setOnMouseClicked(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/client/view/main.fxml"));
            Scene scene;

            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            MainController mainController = fxmlLoader.getController();
            System.out.println("MC: " + mainController);
            mainController.setNaym(this.textField.getText());
            Main.setScene(scene);
        });
    }

}
