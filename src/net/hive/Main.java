package net.hive;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/net/hive/views/main.fxml"));
        primaryStage.setTitle("Запросы v1.3.1");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.getIcons().add(new Image("/net/hive/agent002.png"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
