package net.hive.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
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
public class Controller {
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
    //public MenuItem exExcel;        // Экспорт в Эксель
    //public MenuItem exWord;         // Экспорт в Ворд
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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    // Комплект переменных первой вкладки
    private String a = ">=1";        // 1 - постоянные, 2 - временные
    private String b = ">=0";        // 0 - заявки, 1 - активные, 3 - архивные, >= 3 - Постоянные архивные
    private String c = "";
    private LocalDate time = LocalDate.now().plusDays(1);
    private LocalDate f = LocalDate.now();
    private LocalDate d = LocalDate.now();
    private LocalDate e = LocalDate.now();
    // Комплект переменных второй вкладки
    private String t2 = "";
    private String t21 = "";
    // инициализируем форму данными
    @FXML
    private void initialize() {
        // устанавливаем тип и значение которое должно хранится в колонке
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
    @FXML   // Кнопка "Найти" первой вкладки
    public void onClickMethod() {
        st ="BST";
        c = this.famId.getText();               // Вводим Фамилию
        d = dataDate.getValue();                // Вводим дату начала поиска
        if (d == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
        }
        if (tablo.getText().isEmpty()){
                tabZ = ">=1";
            }else{tabZ = "="+ tablo.getText();} // Вводим табельный номер
        if (d == null){
            d = LocalDate.now();
        }
        e = dataDate1.getValue();               // Вводим дату кончала поиска :-)
        if (e == null){
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            e = time;
        }
        // заполняем таблицу данными
            pojoData.removeAll(pojoData);
        // логика для галочек
            if(postProps.isSelected()) {a = "=1";}
            if(vremProps.isSelected()) {a = "=2";}
            if(postProps.isSelected() && vremProps.isSelected()){a = ">0";}
            if(actProps.isSelected()){b = "=1";}
            if(archProps.isSelected()){b = ">=3";}
            System.out.println(zap(a,tabZ,b,c));
            System.out.println(d + " + " + e);
        baza();

        tableUsers.setItems(pojoData);              // Заполняем таблицу данными
    }
    @FXML   // Кнопка "Найти", второй вкладки
    public void onClickMethod2() {
        za2 = ">=1";                               // 1 - постоянные, 2 - временные
        st = "bprot";
        zc2 = famId2.getText();                    // Вводим Фамилию
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
        // заполняем таблицу данными
        pojoData2.removeAll(pojoData2);
        if(postProps2.isSelected()) {za2 = "=1";} else
        if(vremProps2.isSelected()) {za2 = "=2";} else
        if(postProps2.isSelected() && vremProps2.isSelected()){za2 = ">=1";}
        baza2();
        System.out.println(zap2(zb2,zc2,za2));
        tableUsers2.setItems(pojoData2);
    }
    // Объявляем переменные, чтобы записать в них данные запроса.
    private String tabZ = null;
    private String a1 = null; private String b1 = null; private String c1 = null; private String d1 = null;
    private String e1 = null; private String f1 = null; private String g1 = null; private String h1 = null;
    private String st ="BST";
    private void baza() {
        try { // Создаём соединение с БД
            st ="BST";
            Connection conn = getConnection();
            // Проверяем, есть-ли соединение.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Тело SQL Запроса
            String strSQL = zap(a,tabZ,b,c);
            strSQL = strSQL.toUpperCase();
            // Выполняем SQL запрос.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Смотрим количество колонок в результате SQL запроса.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Форматируем дату.
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
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
        a = ">=1";        // 1 - постоянные, 2 - временные
        b = ">=0";        // 0 - заявки, 1 - активные, 3 - архивные, >= 3 - Постоянные архивные
        c = "";
    }
    // Объявляем переменные, чтобы записать в них данные запроса.
    private String za2 = null; private String zb2 = null; private String zc2 = null;
    private void baza2() {
        try { // Создаём соединение с БД
            st = "bprot";
            Connection conn = getConnection();
            // Проверяем, есть-ли соединение.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Тело SQL Запроса
            String strSQL = zap2(zb2,zc2,za2);
            strSQL = strSQL.toUpperCase();
            // Выполняем SQL запрос.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Смотрим количество колонок в результате SQL запроса.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Форматируем дату.
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            int ch2=0;
            String a2 = null; String b2 = null;  String c2 = null;  String d2 = null;
            String e2 = null; String f2 = null;  String g2 = null;  //String h2 = "ha";
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
    // Тело запроса в первой вкладке.
    private String zap(String a, String tabZ, String b, String c){
            return "select  pr.docser, " +              // Серия паспорта
                    "        pr.docno, " +              // Номер паспорта
                    "        pr.name, " +               // Фамилия
                    "        pr.firstname, " +          // Имя
                    "        pr.secondname, " +         // Отчество
                    "        pr.tableno, " +            // Табельный номер
                    "        p.createdate, " +          // Время входа (получения гостевого пропуска)
                    "        p.returndate " +           // Время выхода (возврата гостевого пропуска)
                    " from   doublepass p" +
                    "        left join doubleperson pr on p.personid = pr.personid " +
                    " where p.passtype " + a +
                    " and pr.tableno " + tabZ + "" +
                    " and pr.orgid = 28" +
                    " and p.cardstatus " + b +
                    " and upper (pr.name) like upper ('" + c + "%')" +
                    " and p.createdate > '"+ d +"' " +
                    " and ((p.returndate < '"+ e +"') " +" or (p.returndate is null))";
        }
    // Тело запроса во второй вкладке.
    private String zap2(String b2, String c2, String a2){
        return " select  person.tableno, " +         // Табельный номер
        " person.name, " +                    // Фамилия
        " person.firstname, " +               // Имя
        " person.secondname, " +              // Отчество
        " sourcedev.name, " +                 // Название устройства
        " dept.department, " +                // Подразделение
        " bmsg.datetime, " +                  // Время события
        " person.post_name " +                // Должность

" from person " +
        " left join dept on dept.depid = person.depid " +
        " left join bmsg on bmsg.personid = person.personid " +
        " left join msgtext on msgtext.msgcode = bmsg.msgcode " +
        " left join sourcedev on sourcedev.sourcedevid = bmsg.sourcedevid " +
" where " +
        "   person.tableno " + b2 + "" +
        " and upper (person.name) like upper ('" + c2 + "%')" +
        " and person.constantaccess " + a2 + " " +
        " and bmsg.datetime between '" + t2 + "' and '" + t21 + "' " +
        " and ((msgtext.msgtextid = 33) or (msgtext.msgtextid = 46))" +
        " and person.orgid = 28";
    }
    // Метод соединения с БД
    private Connection getConnection() throws SQLException {
                try {
                    Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
                } catch (Exception E) {
                    System.err.println("Unable to load driver.");
                    E.printStackTrace();
                }
                // Путь к рабочей БД
                String url = "jdbc:firebirdsql:192.168.99.239/3050:"+st;
                //String url = "jdbc:firebirdsql:localhost/3050:D:/Bastion/DB_for_reports/BD/BASTION.GDB";
                // Данные для соединения с БД
                Properties prop = new Properties();
                prop.setProperty("user", "APP_ADMIN");
                prop.setProperty("password", "!a2345678");
                return DriverManager.getConnection(url, prop);
            }



    // Экспорт данных поиска в Excel файл (Вкладка Сотрудники и гости)
    public void exExcelButton1() throws IOException, NullPointerException  {
        String a = "./queryState.xlsx";
        ForExcel.wrightToExcel1(a,pojoData);
    }
    // Экспорт данных поиска в Excel файл (Вкладка Передвижения)
    public void exExcelButton2() throws IOException, NullPointerException {
        String a = "./queryGo.xlsx";
        ForExcel.wrightToExcel2(a,pojoData2);
    }
    public void exWordButton(ActionEvent actionEvent) {
    }

    public void exWordButton2(ActionEvent actionEvent) {
    }



    public void exWordButton1(ActionEvent actionEvent) {
    }
}