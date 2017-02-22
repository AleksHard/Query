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
 * �����-����������, ����������� ���� �����.
 */
public class Controller {
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
    //public MenuItem exExcel;        // ������� � ������
    //public MenuItem exWord;         // ������� � ����
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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    // �������� ���������� ������ �������
    private String a = ">=1";        // 1 - ����������, 2 - ���������
    private String b = ">=0";        // 0 - ������, 1 - ��������, 3 - ��������, >= 3 - ���������� ��������
    private String c = "";
    private LocalDate time = LocalDate.now().plusDays(1);
    private LocalDate f = LocalDate.now();
    private LocalDate d = LocalDate.now();
    private LocalDate e = LocalDate.now();
    // �������� ���������� ������ �������
    private String t2 = "";
    private String t21 = "";
    // �������������� ����� �������
    @FXML
    private void initialize() {
        // ������������� ��� � �������� ������� ������ �������� � �������
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
    @FXML   // ������ "�����" ������ �������
    public void onClickMethod() {
        st ="BST";
        c = this.famId.getText();               // ������ �������
        d = dataDate.getValue();                // ������ ���� ������ ������
        if (d == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
        }
        if (tablo.getText().isEmpty()){
                tabZ = ">=1";
            }else{tabZ = "="+ tablo.getText();} // ������ ��������� �����
        if (d == null){
            d = LocalDate.now();
        }
        e = dataDate1.getValue();               // ������ ���� ������� ������ :-)
        if (e == null){
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            e = time;
        }
        // ��������� ������� �������
            pojoData.removeAll(pojoData);
        // ������ ��� �������
            if(postProps.isSelected()) {a = "=1";}
            if(vremProps.isSelected()) {a = "=2";}
            if(postProps.isSelected() && vremProps.isSelected()){a = ">0";}
            if(actProps.isSelected()){b = "=1";}
            if(archProps.isSelected()){b = ">=3";}
            System.out.println(zap(a,tabZ,b,c));
            System.out.println(d + " + " + e);
        baza();

        tableUsers.setItems(pojoData);              // ��������� ������� �������
    }
    @FXML   // ������ "�����", ������ �������
    public void onClickMethod2() {
        za2 = ">=1";                               // 1 - ����������, 2 - ���������
        st = "bprot";
        zc2 = famId2.getText();                    // ������ �������
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
        // ��������� ������� �������
        pojoData2.removeAll(pojoData2);
        if(postProps2.isSelected()) {za2 = "=1";} else
        if(vremProps2.isSelected()) {za2 = "=2";} else
        if(postProps2.isSelected() && vremProps2.isSelected()){za2 = ">=1";}
        baza2();
        System.out.println(zap2(zb2,zc2,za2));
        tableUsers2.setItems(pojoData2);
    }
    // ��������� ����������, ����� �������� � ��� ������ �������.
    private String tabZ = null;
    private String a1 = null; private String b1 = null; private String c1 = null; private String d1 = null;
    private String e1 = null; private String f1 = null; private String g1 = null; private String h1 = null;
    private String st ="BST";
    private void baza() {
        try { // ������ ���������� � ��
            st ="BST";
            Connection conn = getConnection();
            // ���������, ����-�� ����������.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // ���� SQL �������
            String strSQL = zap(a,tabZ,b,c);
            strSQL = strSQL.toUpperCase();
            // ��������� SQL ������.
            ResultSet rs = stmt.executeQuery(strSQL);
            // ������� ���������� ������� � ���������� SQL �������.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // ����������� ����.
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
        a = ">=1";        // 1 - ����������, 2 - ���������
        b = ">=0";        // 0 - ������, 1 - ��������, 3 - ��������, >= 3 - ���������� ��������
        c = "";
    }
    // ��������� ����������, ����� �������� � ��� ������ �������.
    private String za2 = null; private String zb2 = null; private String zc2 = null;
    private void baza2() {
        try { // ������ ���������� � ��
            st = "bprot";
            Connection conn = getConnection();
            // ���������, ����-�� ����������.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // ���� SQL �������
            String strSQL = zap2(zb2,zc2,za2);
            strSQL = strSQL.toUpperCase();
            // ��������� SQL ������.
            ResultSet rs = stmt.executeQuery(strSQL);
            // ������� ���������� ������� � ���������� SQL �������.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // ����������� ����.
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
    // ���� ������� � ������ �������.
    private String zap(String a, String tabZ, String b, String c){
            return "select  pr.docser, " +              // ����� ��������
                    "        pr.docno, " +              // ����� ��������
                    "        pr.name, " +               // �������
                    "        pr.firstname, " +          // ���
                    "        pr.secondname, " +         // ��������
                    "        pr.tableno, " +            // ��������� �����
                    "        p.createdate, " +          // ����� ����� (��������� ��������� ��������)
                    "        p.returndate " +           // ����� ������ (�������� ��������� ��������)
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
    // ���� ������� �� ������ �������.
    private String zap2(String b2, String c2, String a2){
        return " select  person.tableno, " +         // ��������� �����
        " person.name, " +                    // �������
        " person.firstname, " +               // ���
        " person.secondname, " +              // ��������
        " sourcedev.name, " +                 // �������� ����������
        " dept.department, " +                // �������������
        " bmsg.datetime, " +                  // ����� �������
        " person.post_name " +                // ���������

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
    // ����� ���������� � ��
    private Connection getConnection() throws SQLException {
                try {
                    Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
                } catch (Exception E) {
                    System.err.println("Unable to load driver.");
                    E.printStackTrace();
                }
                // ���� � ������� ��
                String url = "jdbc:firebirdsql:192.168.99.239/3050:"+st;
                //String url = "jdbc:firebirdsql:localhost/3050:D:/Bastion/DB_for_reports/BD/BASTION.GDB";
                // ������ ��� ���������� � ��
                Properties prop = new Properties();
                prop.setProperty("user", "APP_ADMIN");
                prop.setProperty("password", "!a2345678");
                return DriverManager.getConnection(url, prop);
            }



    // ������� ������ ������ � Excel ���� (������� ���������� � �����)
    public void exExcelButton1() throws IOException, NullPointerException  {
        String a = "./queryState.xlsx";
        ForExcel.wrightToExcel1(a,pojoData);
    }
    // ������� ������ ������ � Excel ���� (������� ������������)
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