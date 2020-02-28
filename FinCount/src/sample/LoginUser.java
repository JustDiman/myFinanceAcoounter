package sample;

import java.io.*;
import java.net.URL;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginUser {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button newUser;

    @FXML
    private Button sigInButton;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static User regUser;

    @FXML
    void initialize() {

        //Слушатель на создание нового пользователя
        newUser.setOnAction(actionEvent -> {
                newUser.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/registerUser.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
        });

        //Слушатель на подтверждение логина и пароля
        sigInButton.setOnAction(actionEvent -> {

            try {
                Library.filePath = "src/sample/" + login_field.getText() + ".json";
                JsonReader parser = new Gson().newJsonReader(new FileReader("src/sample/" + login_field.getText() + ".json"));
                regUser = GSON.fromJson(parser, User.class);
                if (regUser == null) {Library.error("Пользоватeль не найден"); return;}

                if (regUser.getPassword().equals(password_field.getText()) ){
                    sigInButton.getScene().getWindow().hide();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/app.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));

                    stage.setOnCloseRequest(event -> {
                        try {
                            FileWriter fileWriter = new FileWriter(Library.filePath);
                            fileWriter.append(GSON.toJson(regUser));
                            fileWriter.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    stage.showAndWait();
                }else Library.error("Не верно введен пароль");

            } catch (FileNotFoundException e){
                Library.error("Пользоватль не найден");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

