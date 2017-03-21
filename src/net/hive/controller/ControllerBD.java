package net.hive.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by kharlashkin on 09.03.2017.
 * Контроллер, для изменения настроек БД
 */
public class ControllerBD {

    public TextField routToDataBase;            // Путь к базе данных
    public PasswordField password;              // Пароль пользователя
    public TextField login;                     // Имя пользователя
    private String wayToDB;
    private String passDB;
    private String loginDB;

    public void saveChanges() {
        System.out.println(wayToDB);
        initialize();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Firebird SQL v2.5");
        alert.setHeaderText("Путь к БД Бастиона:");
        alert.setContentText(wayToDB);
        alert.showAndWait();
        System.out.println(passDB);
        System.out.println(loginDB);
    }

    public void initialize() {
        wayToDB = routToDataBase.getText();
        passDB = password.getText();
        loginDB = login.getText();
        if (passDB != null){
            password.setText(passDB);
        }
    }
}
