package com.example.javaresto;

import com.example.javaresto.classes.Dish;
import com.example.javaresto.classes.Ingredient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ingredient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        //Image pizzaImage = new Image("/JavaResto/src/main/resources/com/example/javaresto/image/pizzaMargherita.jpg");
        //Image saladImage = new Image("/JavaResto/src/main/resources/com/example/javaresto/image/saladCesar.jpg");
        //Image pastaImage = new Image("src/main/resources/com/example/javaresto/image/pastaBolo.jpg");


        launch();
    }

}