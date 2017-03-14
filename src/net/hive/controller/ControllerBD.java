package net.hive.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by kharlashkin on 09.03.2017.
 *
 */
public class ControllerBD implements Initializable {

    public TextField routToDataBase;            // ���� � ���� ������
    public PasswordField password;              // ������ ������������
    public TextField login;                     // ��� ������������
    public String wayToDB;
    public String passDB;
    public String loginDB;

    public void saveChanges() {
        System.out.println(wayToDB);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("Ooops, there was an error!");

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