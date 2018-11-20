package com.wszib.SeaBattle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class FXApp extends Application {
    public void start(Stage primaryStage)throws Exception{

        URL url = new File("src/main/resources/fxml/ui_SeaBattle.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Sea Battle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args){
        launch(args);
    }
}
