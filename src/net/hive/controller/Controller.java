package net.hive.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.hive.DataBase;
import net.hive.License;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
/**
 * Created by kharlashkin on 20.02.2017.
 * Класс-контроллер, управляющий всей фигнёй.
 */
public class Controller implements Initializable{
    // Tab 1
    public TableView<Pojo> tableUsers;
    public CheckBox postProps;      // Штат (V)
    public CheckBox vremProps;      // Гости (V)
    public CheckBox actProps;       // Актив (V)
    public CheckBox archProps;      // Архив (V)
    public TextField tablo;         // Табельный номер (textfield)
    public TextField famId;         // Фамилия (textfield)
    public DatePicker dataDate;     // Дата выдачи пропуска (datePicker)
    public DatePicker dataDate1;    // Дата изъятия пропуска (datePicker)
    public TextField stroki;        // Количество строк (textfield)
    public MenuItem exExcel1;
    public MenuItem exExcel2;
    private ObservableList<Pojo> pojoData = FXCollections.observableArrayList();
    public TableColumn<Pojo, String> serColumn;
    public TableColumn<Pojo, String> nomColumn;
    public TableColumn<Pojo, String> famColumn;
    public TableColumn<Pojo, String> nameColumn;
    public TableColumn<Pojo, String> otchColumn;
    public TableColumn<Pojo, String> cartserColumn;
    public TableColumn<Pojo, String> inColumn;
    public TableColumn<Pojo, String> outColumn;
    // TAB 2
    public TableView<Pojo> tableUsers2;
    public CheckBox postProps2;     // Штат (V)
    public CheckBox vremProps2;     // Гости (V)
    public TextField tablo2;        // Табельный номер (textfield)
    public TextField famId2;        // Фамилия (textfield)
    public DatePicker dataDate2;    // Дата выдачи пропуска (datePicker)
    public DatePicker dataDate21;   // Дата изъятия пропуска (datePicker)
    public TextField stroki2;       // Количество строк (textfield)
    private ObservableList<Pojo> pojoData2 = FXCollections.observableArrayList();
    public TableColumn<Pojo, String> serColumn2;
    public TableColumn<Pojo, String> nomColumn2;
    public TableColumn<Pojo, String> famColumn2;
    public TableColumn<Pojo, String> nameColumn2;
    public TableColumn<Pojo, String> otchColumn2;
    public TableColumn<Pojo, String> cartserColumn2;
    public TableColumn<Pojo, String> inColumn2;
    private String pattern = "dd.MM.yyyy";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    // Комплект переменных первой вкладки
    private String postVremBird = ">=1";        // 1 - постоянные, 2 - временные
    private String activArhivBird = ">=0";        // 0 - заявки, 1 - активные, 3 - архивные, >= 3 - Постоянные архивные
    private String familiyaInput = "";
    private LocalDate time = LocalDate.now().plusDays(1);
    private LocalDate f = LocalDate.now();
    private LocalDate d = LocalDate.now();
    private LocalDate e = LocalDate.now();
    // Комплект переменных второй вкладки
    static String t2 = "";      // Дата создания карточки
    static String t21 = "";     // Дата возврата карточки
    // Инициализируем форму данными
    /*@FXML
    private void initialize() {
    }*/
    @FXML   // Кнопка "Найти" первой вкладки
    public void onClickMethod() throws IOException {
        st ="BST";
        familiyaInput = this.famId.getText();               // Вводим Фамилию
        d = dataDate.getValue();                            // Вводим дату начала поиска
        famId2.setText(familiyaInput);                      // Перемещаем введёную фамилию во вторую вкладку
        if (d == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
        }
        if (tablo.getText().isEmpty()){
                tabZ = ">=1";
            }else{tabZ = "="+ tablo.getText();}             // Вводим табельный номер
        if (d == null){
            d = LocalDate.now();
        }
        e = dataDate1.getValue();                           // Вводим дату кончала поиска :-)
        if (e == null){
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            e = time;
        }
        // Чистим таблицу от данных
            pojoData.removeAll(pojoData);
        // логика для галочек
            if(postProps.isSelected()) {postVremBird = "=1";}
            if(vremProps.isSelected()) {postVremBird = "=2";}
            if(postProps.isSelected() && vremProps.isSelected()){postVremBird = ">0";}
            if(actProps.isSelected()){activArhivBird = "=1";}
            if(archProps.isSelected()){activArhivBird = ">=3";}
            System.out.println(Zapros.zap1(postVremBird,tabZ,activArhivBird,familiyaInput,d,e));
            System.out.println(d + " + " + e);
        baza();

        tableUsers.setItems(pojoData);              // Заполняем таблицу данными
    }
    @FXML   // Кнопка "Найти", второй вкладки
    public void onClickMethod2() throws IOException {
        za2 = ">=1";                               // 1 - постоянные, 2 - временные
        st = "bprot";
        zc2 = famId2.getText();                    // Вводим Фамилию
        famId.setText(zc2);                        // Перемещаем введёную фамилию в первую вкладку
        if (tablo2.getText().isEmpty()){
            zb2 = ">=1";
        }else{zb2 = "="+ tablo2.getText();}                 // Вводим табельный номер
        LocalDate d2 = dataDate2.getValue();                // Вводим дату начала поиска
        if (d2 == null){
            d2 = LocalDate.now();
            String dat = f.format(formatter);
            dataDate2.setPromptText(dat);}
        t2 = formatter.format(d2);
        LocalDate e2 = dataDate21.getValue();               // Вводим дату кончала поиска :-)
        if (e2 == null){e2 = time;
            String dat = f.format(formatter);
            dataDate21.setPromptText(dat);}
            e2 = e2.plusDays(1);
        t21 = formatter.format(e2);
        // Чистим коллекцию от данных
        pojoData2.removeAll(pojoData2);
        if(postProps2.isSelected()) {za2 = "=1";} else
        if(vremProps2.isSelected()) {za2 = "=2";} else
        if(postProps2.isSelected() && vremProps2.isSelected()){za2 = ">=1";}
        baza2();
        System.out.println(Zapros.zap2(zb2,zc2,za2,t2,t21));
        // Заполняем данными таблицу приложения
        tableUsers2.setItems(pojoData2);
    }
    // Объявляем переменные, чтобы записать в них данные запроса.
    private String tabZ = null;
    private String a1 = null; private String b1 = null; private String c1 = null; private String d1 = null;
    private String e1 = null; private String f1 = null; private String g1 = null; private String h1 = null;
    private String st ="BST";
    private void baza() throws IOException, NullPointerException {
        try { // Создаём соединение с БД
            st ="BST";
            Connection conn = getConnection();
            assert conn != null;                                    // Проверяем, есть-ли соединение.
            Statement stmt = conn.createStatement();
            String strSQL = Zapros.zap1(postVremBird,tabZ,activArhivBird,familiyaInput,d,e);    // Тело SQL Запроса
            strSQL = strSQL.toUpperCase();                          // Переводим запрос в верхний регистр
            ResultSet rs = stmt.executeQuery(strSQL);               // Выполняем SQL запрос.
            int nColumnsCount = rs.getMetaData().getColumnCount();  // Смотрим количество колонок в результате SQL запроса.
            int ch=0;
            while (rs.next()) {
                for (int n = 1; n < nColumnsCount + 1; n++) {
                    Object obj = rs.getObject(n);
                    if (n == 1) {a1 = (String) obj;ch++;} if (n == 2) {b1 = (String) obj;} if (n == 3) {c1 = (String) obj;}
                    if (n == 4) {d1 = (String) obj;} if (n == 5) {e1 = (String) obj;} if (n == 6) {f1 = (String) obj;}
                    if (n == 7) {if ( obj != null) {g1 = dateFormat.format((Date) obj);}
                        else g1 = " ";}
                    if (n == 8) {if ( obj != null) {h1 = dateFormat.format((Date) obj);}
                        else h1 = " ";}
                }
                pojoData.add(new Pojo(a1, b1, c1, d1, e1, f1, g1, h1));
                stroki.setText("Количество строк: "+ ch);
            }
            //Освобождаем ресурсы.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("Обработка NullPointerException");
        }
        postVremBird = ">=1";           // 1 - постоянные, 2 - временные
        activArhivBird = ">=0";         // 0 - заявки, 1 - активные, 3 - архивные, >= 3 - Постоянные архивные
        familiyaInput = "";
    }
    // Объявляем переменные, чтобы записать в них данные запроса.
    private String za2 = null;  private String a2 = null;   private String d2 = null;   private String g2 = null;
    private String zb2 = null;  private String b2 = null;   private String e2 = null;
    private String zc2 = null;  private String c2 = null;   private String f2 = null;
    private void baza2() throws IOException {
        try { // Создаём соединение с БД
            st = "bprot";
            Connection conn = getConnection();
            // Проверяем, есть-ли соединение.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Тело SQL Запроса
            String strSQL = Zapros.zap2(zb2,zc2,za2,t2,t21);    //String strSQL = zap2(zb2,zc2,za2);
            strSQL = strSQL.toUpperCase();
            // Выполняем SQL запрос.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Смотрим количество колонок в результате SQL запроса.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Форматируем дату.
            int ch2=0;
            while (rs.next()) {
                for (int n = 1; n < nColumnsCount + 1; n++) {
                    Object obj = rs.getObject(n);
                    if (n == 1) {a2 = (String) obj;} if (n == 2) {b2 = (String) obj;ch2++;} if (n == 3) {c2 = (String) obj;}
                    if (n == 4) {d2 = (String) obj;} if (n == 5) {e2 = (String) obj;} if (n == 6) {f2 = (String) obj;}
                    if (n == 7) {if ( obj != null) {g2 = dateFormat.format((Date) obj);} else g2 = " ";}
                    //if (n == 8) {if ( obj != null) {h2 = (String) obj;} else h2 = "ha";}
                }
                pojoData2.add(new Pojo(a2, b2, c2, d2, e2, f2, g2));
                stroki2.setText("Количество строк: "+ ch2);
            }
            //Освобождаем ресурсы.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("Обработка NullPointerException");
        }
    }
    // Отчёт по фабрике
    private String a3 = null; private Integer b3 = null;  private String c3 = null;  private String d3 = null;
    private void baza3(String test) throws IOException {
        try { // Создаём соединение с БД

            st = "BST";
            Connection conn = getConnection();
            // Проверяем, есть-ли соединение.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Тело SQL Запроса
            String strSQL = test;
            //String strSQL = Zapros.otchetOF();
            strSQL = strSQL.toUpperCase();
            // Выполняем SQL запрос.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Смотрим количество колонок в результате SQL запроса.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Форматируем дату.
            while (rs.next()) {
                System.out.println();
                for (int n = 1; n < nColumnsCount + 1; n++) {
                    Object obj = rs.getObject(n);
                    if (n == 1) {a3 = (String) obj; System.out.print(a3 +" | ");}
                    if (n == 2) {if ( obj != null) {b3 = (Integer) obj;} else b3 = 0; System.out.print(b3 +" | ");}
                    if (n == 3) {c3 = (String) obj; System.out.print(c3 +" | ");}
                    if (n == 4) {if ( obj != null) {d3 = dateFormat.format((Date) obj);} else d3 = " ";}
                    System.out.print(d3 +" | ");
                }
                pojoData.add(new Pojo(a3, b3, c3, d3));
            }
            //Освобождаем ресурсы.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("Обработка NullPointerException");
        }
    }
    // Метод соединения с БД
    String urDriver = "jdbc:firebirdsql:";

    //String urSite = routToDataBase.getText();
    String urSite = "192.168.99.239/3050:";

    private Connection getConnection() throws SQLException, IOException {
                try {
                    Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
                } catch (Exception E) {
                    System.err.println("Unable to load driver.");
                    E.printStackTrace();
                }
                // Путь к рабочей БД
                String url = urDriver + urSite + st;
                //String url = "jdbc:firebirdsql:localhost/3050:D:/Bastion/DB_for_reports/BD/BASTION.GDB";
                // Данные для соединения с БД
                Properties prop = new Properties();
                prop.setProperty("user", "APP_ADMIN");
                prop.setProperty("password", "!a2345678");
                return DriverManager.getConnection(url, prop);
            }
    static String tt2=null;                 // Дата начала
    static String tt21=null;                // На вывод пользователю (Дата конца)
    private static String ttt21=null;       // В запрос, в БАЗУ      (Дата конца)
    // Экспорт данных поиска в Excel файл (Вкладка "Сотрудники и гости")
    public void exExcelButton1() throws IOException, NullPointerException  {
        String a = "./queryState.xlsx";
        LocalDate d2 = dataDate.getValue();                // Вводим дату начала поиска
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
            tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Вводим дату кончала поиска :-)
        if (e2 == null){e2 = f;
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);
        }else{
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);}
        ForExcel.wrightToExcel1(a,pojoData);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel файл успешно создан.");
        alert.showAndWait();
    }
    // Экспорт данных поиска в Excel файл (Вкладка "Передвижения")
    public void exExcelButton2() throws IOException, NullPointerException {
        String a = "./queryGo.xlsx";
        LocalDate d2 = dataDate.getValue();                // Вводим дату начала поиска
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
            tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Вводим дату кончала поиска :-)
        if (e2 == null){e2 = f;
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);
        }else{
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);}
        //pojoData.removeAll(pojoData);                       // Чистим коллекцию, чтоб не отображался мусор в таблице приложения
        ForExcel.wrightToExcel2(a,pojoData2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel файл успешно создан.");
        alert.showAndWait();
    }
    // Отчёт по ОФ Междуреченская
    public void exOtchetOFButton() throws IOException, NullPointerException  {
        // Чистим коллекцию от данных
        pojoData.removeAll(pojoData);
        String a = "./queryFabrika.xlsx";
        LocalDate d2 = dataDate.getValue();                // Вводим дату начала поиска
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
        tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Вводим дату кончала поиска :-)
        if (e2 == null){e2 = f;
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);
        }else{
        tt21 = formatter.format(e2);
        e2 = e2.plusDays(1);
        ttt21 = formatter.format(e2);}
        System.out.println(Zapros.otchetOF(tt2, ttt21));
        baza3(Zapros.otchetOF(tt2, ttt21));                 // Вызываем метод запроса к БД.
        ForExcel.otchetOF(a,pojoData);                      // Вызываем метод для записи данных в файл "queryFabrika.xlsx"
        pojoData.removeAll(pojoData);                       // Чистим коллекцию, чтоб не отображался мусор в таблице приложения
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel файл успешно создан.");
        alert.showAndWait();

    }
    // Отчёт по УК Южная
    public void exOtchetUKButton() throws IOException, NullPointerException {
        // Чистим коллекцию от данных
        pojoData.removeAll(pojoData);
        String a = "./queryUzhnaya.xlsx";
        LocalDate d2 = dataDate.getValue();                // Вводим дату начала поиска
        if (d2 == null) {
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
        tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Вводим дату кончала поиска :-)
        if (e2 == null) {
            e2 = f;
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);
        }else{
            tt21 = formatter.format(e2);
            e2 = e2.plusDays(1);
            ttt21 = formatter.format(e2);}
        System.out.println(Zapros.otchetUK(tt2, ttt21));
        baza3(Zapros.otchetUK(tt2, ttt21));
        ForExcel.otchetUK(a,pojoData);
        pojoData.removeAll(pojoData);       // Чистим коллекцию, чтоб не отображался мусор в таблице приложения
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel файл успешно создан.");
        alert.showAndWait();
    }


    // Окно информации о лицензии и копирайт
    public void license() throws IOException {
        try{
            new License();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
    // Окно настройки доступа к БД
    public void openContrDB() throws IOException {
        try{
            new DataBase();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // устанавливаем тип и значение которое должно храниться в колонке
        serColumn.setCellValueFactory(new PropertyValueFactory<>("serpas"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nompas"));
        famColumn.setCellValueFactory(new PropertyValueFactory<>("famil"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        otchColumn.setCellValueFactory(new PropertyValueFactory<>("otch"));
        cartserColumn.setCellValueFactory(new PropertyValueFactory<>("cartser"));
        inColumn.setCellValueFactory(new PropertyValueFactory<>("vhod"));
        outColumn.setCellValueFactory(new PropertyValueFactory<>("vihod"));

        serColumn2.setCellValueFactory(new PropertyValueFactory<>("serpas"));
        nomColumn2.setCellValueFactory(new PropertyValueFactory<>("nompas"));
        famColumn2.setCellValueFactory(new PropertyValueFactory<>("famil"));
        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        otchColumn2.setCellValueFactory(new PropertyValueFactory<>("otch"));
        inColumn2.setCellValueFactory(new PropertyValueFactory<>("vhod"));
        cartserColumn2.setCellValueFactory(new PropertyValueFactory<>("cartser"));
        //deviseIn2.setCellValueFactory(new PropertyValueFactory<>("devIn"));
    }
}