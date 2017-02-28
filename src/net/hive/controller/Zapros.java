package net.hive.controller;
import java.time.LocalDate;
/**
 * Created by kharlashkin on 22.02.2017.
 * Çàïğîñû ê ÁÄ Áàñòèîíà / Firebird SQL 2.5
 */
class Zapros {
        // Çàïğîñû ïåğâîé âêëàäêè
    static String zap1(String a, String tabZ, String b, String c, LocalDate d, LocalDate e){
        return "select  pr.docser, " +              // Ñåğèÿ ïàñïîğòà
                "        pr.docno, " +              // Íîìåğ ïàñïîğòà
                "        pr.name, " +               // Ôàìèëèÿ
                "        pr.firstname, " +          // Èìÿ
                "        pr.secondname, " +         // Îò÷åñòâî
                "        pr.tableno, " +            // Òàáåëüíûé íîìåğ
                "        p.createdate, " +          // Âğåìÿ âõîäà (ïîëó÷åíèÿ ãîñòåâîãî ïğîïóñêà)
                "        p.returndate " +           // Âğåìÿ âûõîäà (âîçâğàòà ãîñòåâîãî ïğîïóñêà)
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
        // Çàïğîñû âòîğîé âêëàäêè
    static String zap2(String b2, String c2, String a2, String t2, String t21) {
        return " select  person.tableno, " +         // Òàáåëüíûé íîìåğ
                " person.name, " +                    // Ôàìèëèÿ
                " person.firstname, " +               // Èìÿ
                " person.secondname, " +              // Îò÷åñòâî
                " sourcedev.name, " +                 // Íàçâàíèå óñòğîéñòâà
                " dept.department, " +                // Ïîäğàçäåëåíèå
                " bmsg.datetime, " +                  // Âğåìÿ ñîáûòèÿ
                " person.post_name " +                // Äîëæíîñòü

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
        // Çàïğîñ äëÿ åæåìåñÿ÷íîãî îò÷¸òà / Ôàáğèêà
    static String otchetOF(String t2, String t21){
        return  "select c.cardno, max(c.sitecode), max(d.corp_code), max(p.createdate) " +
                " from " +
                " pass p " +
                " join person pr on p.personid = pr.personid " +
                " join dictvals d on pr.depid = d.dictvalid " +
                " join card c on p.cardid = c.cardid " +
                " where   p.cardstatus >= 1 " +         // 0 - çàÿâêè;
                                                        // 1 - àêòèâåí;
                                                        // 3 - àğõèâíûé;
                " and pr.depid = 781 " +
                                                        // 780 Ãîñòåâîå ÀÎ "Ìåæäóğå÷üå"
                                                        // 781 Ãîñòåâîå ÎÔ "Ìåæäóğå÷åíñêàÿ"
                                                        // 779 Ãîñòåâîå ÓÊ "Şæíàÿ"
                " and p.passtype = 2 " +
                                                        // 1 - ïîñòîÿííûå
                                                        // 2 - âğåìåííûå
                " and p.createdate between '" + t2 + "' and '" + t21 + "' " +
                " group by c.cardno ";
    }
        // Çàïğîñ äëÿ åæåìåñÿ÷íîãî îò÷¸òà / ÓÊ Şæíàÿ
    static String otchetUK(String t2, String t21){
        return  "select c.cardno, max(c.sitecode), max(d.corp_code), max(p.createdate) " +
                " from " +
                " pass p " +
                " join person pr on p.personid = pr.personid " +
                " join dictvals d on pr.depid = d.dictvalid " +
                " join card c on p.cardid = c.cardid " +
                " where   p.cardstatus >= 1 " +         // 0 - çàÿâêè;
                                                        // 1 - àêòèâåí;
                                                        // 3 - àğõèâíûé;
                " and pr.depid = 779 " +
                                                        // 780 Ãîñòåâîå ÀÎ "Ìåæäóğå÷üå"
                                                        // 781 Ãîñòåâîå ÎÔ "Ìåæäóğå÷åíñêàÿ"
                                                        // 779 Ãîñòåâîå ÓÊ "Şæíàÿ"
                " and p.passtype = 2 " +
                                                        // 1 - ïîñòîÿííûå
                                                        // 2 - âğåìåííûå
                " and p.createdate between '" + t2 + "' and '" + t21 + "' " +
                " group by c.cardno ";
    }
}



// Òåëî çàïğîñà â ïåğâîé âêëàäêå.
/* private String zap(String a, String tabZ, String b, String c){
 return "select  pr.docser, " +              // Ñåğèÿ ïàñïîğòà
 "        pr.docno, " +              // Íîìåğ ïàñïîğòà
 "        pr.name, " +               // Ôàìèëèÿ
 "        pr.firstname, " +          // Èìÿ
 "        pr.secondname, " +         // Îò÷åñòâî
 "        pr.tableno, " +            // Òàáåëüíûé íîìåğ
 "        p.createdate, " +          // Âğåìÿ âõîäà (ïîëó÷åíèÿ ãîñòåâîãî ïğîïóñêà)
 "        p.returndate " +           // Âğåìÿ âûõîäà (âîçâğàòà ãîñòåâîãî ïğîïóñêà)
 " from   doublepass p" +
 "        left join doubleperson pr on p.personid = pr.personid " +
 " where p.passtype " + a +
 " and pr.tableno " + tabZ + "" +
 " and pr.orgid = 28" +
 " and p.cardstatus " + b +
 " and upper (pr.name) like upper ('" + c + "%')" +
 " and p.createdate > '"+ d +"' " +
 " and ((p.returndate < '"+ e +"') " +" or (p.returndate is null))";
 }*/
// Òåëî çàïğîñà âî âòîğîé âêëàäêå.
/*private String zap2(String b2, String c2, String a2){
 return " select  person.tableno, " +         // Òàáåëüíûé íîìåğ
 " person.name, " +                    // Ôàìèëèÿ
 " person.firstname, " +               // Èìÿ
 " person.secondname, " +              // Îò÷åñòâî
 " sourcedev.name, " +                 // Íàçâàíèå óñòğîéñòâà
 " dept.department, " +                // Ïîäğàçäåëåíèå
 " bmsg.datetime, " +                  // Âğåìÿ ñîáûòèÿ
 " person.post_name " +                // Äîëæíîñòü

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
 }*/