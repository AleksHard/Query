package net.hive.controller;
import java.time.LocalDate;
/**
 * Created by kharlashkin on 22.02.2017.
 * Запросы к БД Бастиона / Firebird SQL 2.5
 */
class Zapros {
        // Запросы первой вкладки
    static String zap1(String a, String tabZ, String b, String c, LocalDate d, LocalDate e){
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
                //" and pr.orgid >= 0" +
                " and p.cardstatus " + b +
                " and upper (pr.name) like upper ('" + c + "%')" +
                " and p.createdate > '"+ d +"' " +
                " and ((p.returndate < '"+ e +"') " +" or (p.returndate is null))";
    }
        // Запросы второй вкладки
    static String zap2(String b2, String c2, String a2, String t2, String t21) {
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
                " and person.orgid > 0";
    }
        // Запрос для ежемесячного отчёта / Фабрика
    static String otchetOF(String tt2, String ttt21){
        return  "select c.cardno, max(c.sitecode), max(d.corp_code), max(p.createdate) " +
                " from " +
                " pass p " +
                " join person pr on p.personid = pr.personid " +
                " join dictvals d on pr.depid = d.dictvalid " +
                " join card c on p.cardid = c.cardid " +
                " where   p.cardstatus >= 1 " +         // 0 - заявки;
                                                        // 1 - активен;
                                                        // 3 - архивный;
                " and pr.depid = 781 " +
                                                       // 780 Гостевое АО "Междуречье"
                                                        // 781 Гостевое ОФ "Междуреченская"
                                                        // 779 Гостевое УК "Южная"
                " and p.passtype = 2 " +
                                                        // 1 - постоянные
                                                        // 2 - временные
                " and p.createdate between '" + tt2 + "' and '" + ttt21 + "' " +
                " group by c.cardno ";
    }
        // Запрос для ежемесячного отчёта / УК Южная
    static String otchetUK(String tt2, String tt21){
        return  "select c.cardno, max(c.sitecode), max(d.corp_code), max(p.createdate) " +
                " from " +
                " pass p " +
                " join person pr on p.personid = pr.personid " +
                " join dictvals d on pr.depid = d.dictvalid " +
                " join card c on p.cardid = c.cardid " +
                " where   p.cardstatus >= 1 " +         // 0 - заявки;
                                                        // 1 - активен;
                                                        // 3 - архивный;
                " and pr.depid = 779 " +
                                                        // 780 Гостевое АО "Междуречье"
                                                        // 781 Гостевое ОФ "Междуреченская"
                                                        // 779 Гостевое УК "Южная"
                " and p.passtype = 2 " +
                                                        // 1 - постоянные
                                                        // 2 - временные
                " and p.createdate between '" + tt2 + "' and '" + tt21 + "' " +
                " group by c.cardno ";
    }
}



// Тело запроса в первой вкладке.
/* private String zap(String a, String tabZ, String b, String c){
 return "select  pr.docser, " +              // Ñåðèÿ ïàñïîðòà
 "        pr.docno, " +              // Íîìåð ïàñïîðòà
 "        pr.name, " +               // Ôàìèëèÿ
 "        pr.firstname, " +          // Èìÿ
 "        pr.secondname, " +         // Îò÷åñòâî
 "        pr.tableno, " +            // Òàáåëüíûé íîìåð
 "        p.createdate, " +          // Âðåìÿ âõîäà (ïîëó÷åíèÿ ãîñòåâîãî ïðîïóñêà)
 "        p.returndate " +           // Âðåìÿ âûõîäà (âîçâðàòà ãîñòåâîãî ïðîïóñêà)
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
// Тело запроса во второй вкладке.
/*private String zap2(String b2, String c2, String a2){
 return " select  person.tableno, " +         // Òàáåëüíûé íîìåð
 " person.name, " +                    // Ôàìèëèÿ
 " person.firstname, " +               // Èìÿ
 " person.secondname, " +              // Îò÷åñòâî
 " sourcedev.name, " +                 // Íàçâàíèå óñòðîéñòâà
 " dept.department, " +                // Ïîäðàçäåëåíèå
 " bmsg.datetime, " +                  // Âðåìÿ ñîáûòèÿ
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
