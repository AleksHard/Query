package net.hive;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by kharlashkin on 09.03.2017.
 *
 */
public class DataBase {
    public DataBase() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/net/hive/views/routToBD.fxml"));
        Parent root1 = loader.load();
        Stage stage = new Stage ();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.setTitle("Настройки БД");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
