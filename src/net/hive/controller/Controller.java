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
 * �����-����������, ����������� ���� �����.
 */
public class Controller implements Initializable{
    // Tab 1
    public TableView<Pojo> tableUsers;
    public CheckBox postProps;      // ���� (V)
    public CheckBox vremProps;      // ����� (V)
    public CheckBox actProps;       // ����� (V)
    public CheckBox archProps;      // ����� (V)
    public TextField tablo;         // ��������� ����� (textfield)
    public TextField famId;         // ������� (textfield)
    public DatePicker dataDate;     // ���� ������ �������� (datePicker)
    public DatePicker dataDate1;    // ���� ������� �������� (datePicker)
    public TextField stroki;        // ���������� ����� (textfield)
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
    public CheckBox postProps2;     // ���� (V)
    public CheckBox vremProps2;     // ����� (V)
    public TextField tablo2;        // ��������� ����� (textfield)
    public TextField famId2;        // ������� (textfield)
    public DatePicker dataDate2;    // ���� ������ �������� (datePicker)
    public DatePicker dataDate21;   // ���� ������� �������� (datePicker)
    public TextField stroki2;       // ���������� ����� (textfield)
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
    // �������� ���������� ������ �������
    private String postVremBird = ">=1";        // 1 - ����������, 2 - ���������
    private String activArhivBird = ">=0";        // 0 - ������, 1 - ��������, 3 - ��������, >= 3 - ���������� ��������
    private String familiyaInput = "";
    private LocalDate time = LocalDate.now().plusDays(1);
    private LocalDate f = LocalDate.now();
    private LocalDate d = LocalDate.now();
    private LocalDate e = LocalDate.now();
    // �������� ���������� ������ �������
    static String t2 = "";      // ���� �������� ��������
    static String t21 = "";     // ���� �������� ��������
    // �������������� ����� �������
    /*@FXML
    private void initialize() {
    }*/
    @FXML   // ������ "�����" ������ �������
    public void onClickMethod() throws IOException {
        st ="BST";
        familiyaInput = this.famId.getText();               // ������ �������
        d = dataDate.getValue();                            // ������ ���� ������ ������
        famId2.setText(familiyaInput);                      // ���������� ������� ������� �� ������ �������
        if (d == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
        }
        if (tablo.getText().isEmpty()){
                tabZ = ">=1";
            }else{tabZ = "="+ tablo.getText();}             // ������ ��������� �����
        if (d == null){
            d = LocalDate.now();
        }
        e = dataDate1.getValue();                           // ������ ���� ������� ������ :-)
        if (e == null){
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            e = time;
        }
        // ������ ������� �� ������
            pojoData.removeAll(pojoData);
        // ������ ��� �������
            if(postProps.isSelected()) {postVremBird = "=1";}
            if(vremProps.isSelected()) {postVremBird = "=2";}
            if(postProps.isSelected() && vremProps.isSelected()){postVremBird = ">0";}
            if(actProps.isSelected()){activArhivBird = "=1";}
            if(archProps.isSelected()){activArhivBird = ">=3";}
            System.out.println(Zapros.zap1(postVremBird,tabZ,activArhivBird,familiyaInput,d,e));
            System.out.println(d + " + " + e);
        baza();

        tableUsers.setItems(pojoData);              // ��������� ������� �������
    }
    @FXML   // ������ "�����", ������ �������
    public void onClickMethod2() throws IOException {
        za2 = ">=1";                               // 1 - ����������, 2 - ���������
        st = "bprot";
        zc2 = famId2.getText();                    // ������ �������
        famId.setText(zc2);                        // ���������� ������� ������� � ������ �������
        if (tablo2.getText().isEmpty()){
            zb2 = ">=1";
        }else{zb2 = "="+ tablo2.getText();}                 // ������ ��������� �����
        LocalDate d2 = dataDate2.getValue();                // ������ ���� ������ ������
        if (d2 == null){
            d2 = LocalDate.now();
            String dat = f.format(formatter);
            dataDate2.setPromptText(dat);}
        t2 = formatter.format(d2);
        LocalDate e2 = dataDate21.getValue();               // ������ ���� ������� ������ :-)
        if (e2 == null){e2 = time;
            String dat = f.format(formatter);
            dataDate21.setPromptText(dat);}
            e2 = e2.plusDays(1);
        t21 = formatter.format(e2);
        // ������ ��������� �� ������
        pojoData2.removeAll(pojoData2);
        if(postProps2.isSelected()) {za2 = "=1";} else
        if(vremProps2.isSelected()) {za2 = "=2";} else
        if(postProps2.isSelected() && vremProps2.isSelected()){za2 = ">=1";}
        baza2();
        System.out.println(Zapros.zap2(zb2,zc2,za2,t2,t21));
        // ��������� ������� ������� ����������
        tableUsers2.setItems(pojoData2);
    }
    // ��������� ����������, ����� �������� � ��� ������ �������.
    private String tabZ = null;
    private String a1 = null; private String b1 = null; private String c1 = null; private String d1 = null;
    private String e1 = null; private String f1 = null; private String g1 = null; private String h1 = null;
    private String st ="BST";
    private void baza() throws IOException, NullPointerException {
        try { // ������ ���������� � ��
            st ="BST";
            Connection conn = getConnection();
            assert conn != null;                                    // ���������, ����-�� ����������.
            Statement stmt = conn.createStatement();
            String strSQL = Zapros.zap1(postVremBird,tabZ,activArhivBird,familiyaInput,d,e);    // ���� SQL �������
            strSQL = strSQL.toUpperCase();                          // ��������� ������ � ������� �������
            ResultSet rs = stmt.executeQuery(strSQL);               // ��������� SQL ������.
            int nColumnsCount = rs.getMetaData().getColumnCount();  // ������� ���������� ������� � ���������� SQL �������.
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
                stroki.setText("���������� �����: "+ ch);
            }
            //����������� �������.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("��������� NullPointerException");
        }
        postVremBird = ">=1";           // 1 - ����������, 2 - ���������
        activArhivBird = ">=0";         // 0 - ������, 1 - ��������, 3 - ��������, >= 3 - ���������� ��������
        familiyaInput = "";
    }
    // ��������� ����������, ����� �������� � ��� ������ �������.
    private String za2 = null;  private String a2 = null;   private String d2 = null;   private String g2 = null;
    private String zb2 = null;  private String b2 = null;   private String e2 = null;
    private String zc2 = null;  private String c2 = null;   private String f2 = null;
    private void baza2() throws IOException {
        try { // ������ ���������� � ��
            st = "bprot";
            Connection conn = getConnection();
            // ���������, ����-�� ����������.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // ���� SQL �������
            String strSQL = Zapros.zap2(zb2,zc2,za2,t2,t21);    //String strSQL = zap2(zb2,zc2,za2);
            strSQL = strSQL.toUpperCase();
            // ��������� SQL ������.
            ResultSet rs = stmt.executeQuery(strSQL);
            // ������� ���������� ������� � ���������� SQL �������.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // ����������� ����.
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
                stroki2.setText("���������� �����: "+ ch2);
            }
            //����������� �������.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("��������� NullPointerException");
        }
    }
    // ����� �� �������
    private String a3 = null; private Integer b3 = null;  private String c3 = null;  private String d3 = null;
    private void baza3(String test) throws IOException {
        try { // ������ ���������� � ��

            st = "BST";
            Connection conn = getConnection();
            // ���������, ����-�� ����������.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // ���� SQL �������
            String strSQL = test;
            //String strSQL = Zapros.otchetOF();
            strSQL = strSQL.toUpperCase();
            // ��������� SQL ������.
            ResultSet rs = stmt.executeQuery(strSQL);
            // ������� ���������� ������� � ���������� SQL �������.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // ����������� ����.
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
            //����������� �������.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("��������� NullPointerException");
        }
    }
    // ����� ���������� � ��
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
                // ���� � ������� ��
                String url = urDriver + urSite + st;
                //String url = "jdbc:firebirdsql:localhost/3050:D:/Bastion/DB_for_reports/BD/BASTION.GDB";
                // ������ ��� ���������� � ��
                Properties prop = new Properties();
                prop.setProperty("user", "APP_ADMIN");
                prop.setProperty("password", "!a2345678");
                return DriverManager.getConnection(url, prop);
            }
    static String tt2=null;                 // ���� ������
    static String tt21=null;                // �� ����� ������������ (���� �����)
    private static String ttt21=null;       // � ������, � ����      (���� �����)
    // ������� ������ ������ � Excel ���� (������� "���������� � �����")
    public void exExcelButton1() throws IOException, NullPointerException  {
        String a = "./queryState.xlsx";
        LocalDate d2 = dataDate.getValue();                // ������ ���� ������ ������
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
            tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // ������ ���� ������� ������ :-)
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
        alert.setContentText("Excel ���� ������� ������.");
        alert.showAndWait();
    }
    // ������� ������ ������ � Excel ���� (������� "������������")
    public void exExcelButton2() throws IOException, NullPointerException {
        String a = "./queryGo.xlsx";
        LocalDate d2 = dataDate.getValue();                // ������ ���� ������ ������
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
            tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // ������ ���� ������� ������ :-)
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
        //pojoData.removeAll(pojoData);                       // ������ ���������, ���� �� ����������� ����� � ������� ����������
        ForExcel.wrightToExcel2(a,pojoData2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel ���� ������� ������.");
        alert.showAndWait();
    }
    // ����� �� �� ��������������
    public void exOtchetOFButton() throws IOException, NullPointerException  {
        // ������ ��������� �� ������
        pojoData.removeAll(pojoData);
        String a = "./queryFabrika.xlsx";
        LocalDate d2 = dataDate.getValue();                // ������ ���� ������ ������
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
        tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // ������ ���� ������� ������ :-)
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
        baza3(Zapros.otchetOF(tt2, ttt21));                 // �������� ����� ������� � ��.
        ForExcel.otchetOF(a,pojoData);                      // �������� ����� ��� ������ ������ � ���� "queryFabrika.xlsx"
        pojoData.removeAll(pojoData);                       // ������ ���������, ���� �� ����������� ����� � ������� ����������
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel ���� ������� ������.");
        alert.showAndWait();

    }
    // ����� �� �� �����
    public void exOtchetUKButton() throws IOException, NullPointerException {
        // ������ ��������� �� ������
        pojoData.removeAll(pojoData);
        String a = "./queryUzhnaya.xlsx";
        LocalDate d2 = dataDate.getValue();                // ������ ���� ������ ������
        if (d2 == null) {
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
        tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // ������ ���� ������� ������ :-)
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
        pojoData.removeAll(pojoData);       // ������ ���������, ���� �� ����������� ����� � ������� ����������
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export complete");
        alert.setHeaderText("Woo-Hoo!!!");
        alert.setContentText("Excel ���� ������� ������.");
        alert.showAndWait();
    }


    // ���� ���������� � �������� � ��������
    public void license() throws IOException {
        try{
            new License();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }
    // ���� ��������� ������� � ��
    public void openContrDB() throws IOException {
        try{
            new DataBase();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ������������� ��� � �������� ������� ������ ��������� � �������
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