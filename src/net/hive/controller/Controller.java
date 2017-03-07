package net.hive.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
/**
 * Created by kharlashkin on 20.02.2017.
 * Êëàññ-êîíòðîëëåð, óïðàâëÿþùèé âñåé ôèãí¸é.
 */
public class Controller {
    // Tab 1
    public TableView<Pojo> tableUsers;
    public CheckBox postProps;      // Øòàò (V)
    public CheckBox vremProps;      // Ãîñòè (V)
    public CheckBox actProps;       // Àêòèâ (V)
    public CheckBox archProps;      // Àðõèâ (V)
    public TextField tablo;         // Òàáåëüíûé íîìåð (textfield)
    public TextField famId;         // Ôàìèëèÿ (textfield)
    public DatePicker dataDate;     // Äàòà âûäà÷è ïðîïóñêà (datePicker)
    public DatePicker dataDate1;    // Äàòà èçúÿòèÿ ïðîïóñêà (datePicker)
    public TextField stroki;        // Êîëè÷åñòâî ñòðîê (textfield)
    //public MenuItem exExcel;        // Ýêñïîðò â Ýêñåëü
    //public MenuItem exWord;         // Ýêñïîðò â Âîðä
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
    public CheckBox postProps2;     // Øòàò (V)
    public CheckBox vremProps2;     // Ãîñòè (V)
    public TextField tablo2;        // Òàáåëüíûé íîìåð (textfield)
    public TextField famId2;        // Ôàìèëèÿ (textfield)
    public DatePicker dataDate2;    // Äàòà âûäà÷è ïðîïóñêà (datePicker)
    public DatePicker dataDate21;   // Äàòà èçúÿòèÿ ïðîïóñêà (datePicker)
    public TextField stroki2;       // Êîëè÷åñòâî ñòðîê (textfield)
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
    // Êîìïëåêò ïåðåìåííûõ ïåðâîé âêëàäêè
    private String a = ">=1";        // 1 - ïîñòîÿííûå, 2 - âðåìåííûå
    private String b = ">=0";        // 0 - çàÿâêè, 1 - àêòèâíûå, 3 - àðõèâíûå, >= 3 - Ïîñòîÿííûå àðõèâíûå
    private String c = "";
    private LocalDate time = LocalDate.now().plusDays(1);
    private LocalDate f = LocalDate.now();
    private LocalDate d = LocalDate.now();
    private LocalDate e = LocalDate.now();
    // Êîìïëåêò ïåðåìåííûõ âòîðîé âêëàäêè
    static String t2 = "";
    static String t21 = "";
    // Èíèöèàëèçèðóåì ôîðìó äàííûìè
    @FXML
    private void initialize() {
        // óñòàíàâëèâàåì òèï è çíà÷åíèå êîòîðîå äîëæíî õðàíèòüñÿ â êîëîíêå
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
    @FXML   // Êíîïêà "Íàéòè" ïåðâîé âêëàäêè
    public void onClickMethod() throws IOException {
        st ="BST";
        c = this.famId.getText();               // Ââîäèì Ôàìèëèþ
        d = dataDate.getValue();                // Ââîäèì äàòó íà÷àëà ïîèñêà
        famId2.setText(c);                      // Ïåðåìåùàåì ââåä¸íóþ ôàìèëèþ âî âòîðóþ âêëàäêó
        if (d == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
        }
        if (tablo.getText().isEmpty()){
                tabZ = ">=1";
            }else{tabZ = "="+ tablo.getText();} // Ââîäèì òàáåëüíûé íîìåð
        if (d == null){
            d = LocalDate.now();
        }
        e = dataDate1.getValue();               // Ââîäèì äàòó êîí÷àëà ïîèñêà :-)
        if (e == null){
            String dat = f.format(formatter);
            dataDate1.setPromptText(dat);
            e = time;
        }
        // ×èñòèì òàáëèöó îò äàííûõ
            pojoData.removeAll(pojoData);
        // ëîãèêà äëÿ ãàëî÷åê
            if(postProps.isSelected()) {a = "=1";}
            if(vremProps.isSelected()) {a = "=2";}
            if(postProps.isSelected() && vremProps.isSelected()){a = ">0";}
            if(actProps.isSelected()){b = "=1";}
            if(archProps.isSelected()){b = ">=3";}
            System.out.println(Zapros.zap1(a,tabZ,b,c,d,e));
            System.out.println(d + " + " + e);
        baza();

        tableUsers.setItems(pojoData);              // Çàïîëíÿåì òàáëèöó äàííûìè
    }
    @FXML   // Êíîïêà "Íàéòè", âòîðîé âêëàäêè

    public void onClickMethod2() throws IOException {
        za2 = ">=1";                               // 1 - ïîñòîÿííûå, 2 - âðåìåííûå
        st = "bprot";
        zc2 = famId2.getText();                    // Ââîäèì Ôàìèëèþ
        famId.setText(zc2);                        // Ïåðåìåùàåì ââåä¸íóþ ôàìèëèþ â ïåðâóþ âêëàäêó
        if (tablo2.getText().isEmpty()){
            zb2 = ">=1";
        }else{zb2 = "="+ tablo2.getText();}                 // Ââîäèì òàáåëüíûé íîìåð
        LocalDate d2 = dataDate2.getValue();                // Ââîäèì äàòó íà÷àëà ïîèñêà
        if (d2 == null){
            d2 = LocalDate.now();
            String dat = f.format(formatter);
            dataDate2.setPromptText(dat);}
        t2 = formatter.format(d2);
        LocalDate e2 = dataDate21.getValue();               // Ââîäèì äàòó êîí÷àëà ïîèñêà :-)
        if (e2 == null){e2 = time;
            String dat = f.format(formatter);
            dataDate21.setPromptText(dat);}
            e2 = e2.plusDays(1);
        t21 = formatter.format(e2);
        // ×èñòèì êîëëåêöèþ îò äàííûõ
        pojoData2.removeAll(pojoData2);
        if(postProps2.isSelected()) {za2 = "=1";} else
        if(vremProps2.isSelected()) {za2 = "=2";} else
        if(postProps2.isSelected() && vremProps2.isSelected()){za2 = ">=1";}
        baza2();
        System.out.println(Zapros.zap2(zb2,zc2,za2,t2,t21));
        // Çàïîëíÿåì äàííûìè òàáëèöó ïðèëîæåíèÿ
        tableUsers2.setItems(pojoData2);
    }
    // Îáúÿâëÿåì ïåðåìåííûå, ÷òîáû çàïèñàòü â íèõ äàííûå çàïðîñà.
    private String tabZ = null;
    private String a1 = null; private String b1 = null; private String c1 = null; private String d1 = null;
    private String e1 = null; private String f1 = null; private String g1 = null; private String h1 = null;
    private String st ="BST";
    private void baza() throws IOException {
        try { // Ñîçäà¸ì ñîåäèíåíèå ñ ÁÄ
            st ="BST";
            Connection conn = getConnection();
            // Ïðîâåðÿåì, åñòü-ëè ñîåäèíåíèå.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Òåëî SQL Çàïðîñà
            String strSQL = Zapros.zap1(a,tabZ,b,c,d,e);    //String strSQL = zap(a,tabZ,b,c);
            strSQL = strSQL.toUpperCase();
            // Âûïîëíÿåì SQL çàïðîñ.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Ñìîòðèì êîëè÷åñòâî êîëîíîê â ðåçóëüòàòå SQL çàïðîñà.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Ôîðìàòèðóåì äàòó.
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
                stroki.setText("Êîëè÷åñòâî ñòðîê: "+ ch);
            }
            //Îñâîáîæäàåì ðåñóðñû.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("Îáðàáîòêà NullPointerException");
        }
        a = ">=1";        // 1 - ïîñòîÿííûå, 2 - âðåìåííûå
        b = ">=0";        // 0 - çàÿâêè, 1 - àêòèâíûå, 3 - àðõèâíûå, >= 3 - Ïîñòîÿííûå àðõèâíûå
        c = "";
    }
    // Îáúÿâëÿåì ïåðåìåííûå, ÷òîáû çàïèñàòü â íèõ äàííûå çàïðîñà.
    private String za2 = null;  private String a2 = null;   private String d2 = null;   private String g2 = null;
    private String zb2 = null;  private String b2 = null;   private String e2 = null;
    private String zc2 = null;  private String c2 = null;   private String f2 = null;
    private void baza2() throws IOException {
        try { // Ñîçäà¸ì ñîåäèíåíèå ñ ÁÄ
            st = "bprot";
            Connection conn = getConnection();
            // Ïðîâåðÿåì, åñòü-ëè ñîåäèíåíèå.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Òåëî SQL Çàïðîñà
            String strSQL = Zapros.zap2(zb2,zc2,za2,t2,t21);    //String strSQL = zap2(zb2,zc2,za2);
            strSQL = strSQL.toUpperCase();
            // Âûïîëíÿåì SQL çàïðîñ.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Ñìîòðèì êîëè÷åñòâî êîëîíîê â ðåçóëüòàòå SQL çàïðîñà.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Ôîðìàòèðóåì äàòó.
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
                stroki2.setText("Êîëè÷åñòâî ñòðîê: "+ ch2);
            }
            //Îñâîáîæäàåì ðåñóðñû.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("Îáðàáîòêà NullPointerException");
        }
    }
    // Îò÷¸ò ïî ôàáðèêå
    private String a3 = null; private Integer b3 = null;  private String c3 = null;  private String d3 = null;
    private void baza3(String test) throws IOException {
        try { // Ñîçäà¸ì ñîåäèíåíèå ñ ÁÄ

            st = "BST";
            Connection conn = getConnection();
            // Ïðîâåðÿåì, åñòü-ëè ñîåäèíåíèå.
            assert conn != null;
            Statement stmt = conn.createStatement();
            // Òåëî SQL Çàïðîñà
            String strSQL = test;
            //String strSQL = Zapros.otchetOF();
            strSQL = strSQL.toUpperCase();
            // Âûïîëíÿåì SQL çàïðîñ.
            ResultSet rs = stmt.executeQuery(strSQL);
            // Ñìîòðèì êîëè÷åñòâî êîëîíîê â ðåçóëüòàòå SQL çàïðîñà.
            int nColumnsCount = rs.getMetaData().getColumnCount();
            // Ôîðìàòèðóåì äàòó.
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
            //Îñâîáîæäàåì ðåñóðñû.
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (NullPointerException ex){
            System.out.println("Îáðàáîòêà NullPointerException");
        }
    }
    // Ìåòîä ñîåäèíåíèÿ ñ ÁÄ
    private Connection getConnection() throws SQLException, IOException {
                try {
                    Class.forName("org.firebirdsql.jdbc.FBDriver").newInstance();
                } catch (Exception E) {
                    System.err.println("Unable to load driver.");
                    E.printStackTrace();
                }
                String fileName = "./pass.txt";
                FileWorker stroka = new FileWorker();
                String poisk = stroka.readUsingFiles(fileName);
                // Ïóòü ê ðàáî÷åé ÁÄ
                //String url = poisk + st;
                String url = "jdbc:firebirdsql:192.168.99.239/3050:"+st;
                //String url = "jdbc:firebirdsql:localhost/3050:D:/Bastion/DB_for_reports/BD/BASTION.GDB";
                // Äàííûå äëÿ ñîåäèíåíèÿ ñ ÁÄ
                Properties prop = new Properties();
                prop.setProperty("user", "APP_ADMIN");
                prop.setProperty("password", "!a2345678");
                return DriverManager.getConnection(url, prop);
            }
    static String tt2=null;                 // Äàòà íà÷àëà
    static String tt21=null;                // Íà âûâîä ïîëüçîâàòåëþ (Äàòà êîíöà)
    private static String ttt21=null;       // Â çàïðîñ, â ÁÀÇÓ      (Äàòà êîíöà)
    // Ýêñïîðò äàííûõ ïîèñêà â Excel ôàéë (Âêëàäêà Ñîòðóäíèêè è ãîñòè)
    public void exExcelButton1() throws IOException, NullPointerException  {
        String a = "./queryState.xlsx";
        LocalDate d2 = dataDate.getValue();                // Ââîäèì äàòó íà÷àëà ïîèñêà
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
            tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Ââîäèì äàòó êîí÷àëà ïîèñêà :-)
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
    }
    // Ýêñïîðò äàííûõ ïîèñêà â Excel ôàéë (Âêëàäêà Ïåðåäâèæåíèÿ)
    public void exExcelButton2() throws IOException, NullPointerException {
        String a = "./queryGo.xlsx";
        LocalDate d2 = dataDate.getValue();                // Ââîäèì äàòó íà÷àëà ïîèñêà
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
            tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Ââîäèì äàòó êîí÷àëà ïîèñêà :-)
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
        //pojoData.removeAll(pojoData);                       // ×èñòèì êîëëåêöèþ, ÷òîá íå îòîáðàæàëñÿ ìóñîð â òàáëèöå ïðèëîæåíèÿ
        ForExcel.wrightToExcel2(a,pojoData2);
    }
    public void exWordButton2() {
    }
    public void exWordButton1() {
    }
    public void exOtchetOFButton() throws IOException, NullPointerException  {
        // ×èñòèì êîëëåêöèþ îò äàííûõ
        pojoData.removeAll(pojoData);
        String a = "./queryFabrika.xlsx";
        LocalDate d2 = dataDate.getValue();                // Ââîäèì äàòó íà÷àëà ïîèñêà
        if (d2 == null){
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
        tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Ââîäèì äàòó êîí÷àëà ïîèñêà :-)
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
        baza3(Zapros.otchetOF(tt2, ttt21));                 // Âûçûâàåì ìåòîä çàïðîñà ê ÁÄ.
        ForExcel.otchetOF(a,pojoData);                      // Âûçûâàåì ìåòîä äëÿ çàïèñè äàííûõ â ôàéë "queryFabrika.xlsx"
        pojoData.removeAll(pojoData);                       // ×èñòèì êîëëåêöèþ, ÷òîá íå îòîáðàæàëñÿ ìóñîð â òàáëèöå ïðèëîæåíèÿ
    }
    public void exOtchetUKButton() throws IOException, NullPointerException {
        // ×èñòèì êîëëåêöèþ îò äàííûõ
        pojoData.removeAll(pojoData);
        String a = "./queryUzhnaya.xlsx";
        LocalDate d2 = dataDate.getValue();                // Ââîäèì äàòó íà÷àëà ïîèñêà
        if (d2 == null) {
            String dat = f.format(formatter);
            dataDate.setPromptText(dat);
            tt2 = dat;
        }else{
        tt2 = formatter.format(d2);}
        LocalDate e2 = dataDate1.getValue();               // Ââîäèì äàòó êîí÷àëà ïîèñêà :-)
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
        pojoData.removeAll(pojoData);       // ×èñòèì êîëëåêöèþ, ÷òîá íå îòîáðàæàëñÿ ìóñîð â òàáëèöå ïðèëîæåíèÿ
    }
    // Добавлена сцена лицензии
    public void license() throws IOException {
        System.out.println("You clicked me!");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/net/hive/views/license.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage ();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ëèöåíçèÿ");
        stage.setScene(new Scene(root1));
        stage.show();
    }
}