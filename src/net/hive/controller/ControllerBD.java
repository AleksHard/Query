package net.hive.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kharlashkin on 09.03.2017.
 * Это класс-контроллер дополнительного окна интерфейса программы.
 * Надо как-то взять от сюда данные и передать их в контроллер основного окна.
 * Или переделать контроллер основного окна, выкинув из него лишний код, потому что там тварится полный адище...
 */
public class ControllerBD implements Initializable {

    public TextField routToDataBase;            // Путь к базе данных
    public PasswordField password;              // Пароль пользователя
    public TextField login;                     // Имя пользователя
    public String wayToDB;
    public String passDB;
    public String loginDB;

    public void saveChanges() {
        System.out.println(wayToDB);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Firebird SQL v2.5");
        alert.setHeaderText("Путь к БД Бастиона:");
        alert.setContentText(wayToDB);

        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wayToDB = routToDataBase.getText();
        passDB = password.getText();
        loginDB = login.getText();
    }

    //String rout = routToDataBase.getText();
    //String pass = password.getText();

    /*public void saveChanges() {
        url = login.getText();
        if (url.equals(null)){
            url = "APP_ADMIN";
        }
    }*/
}
