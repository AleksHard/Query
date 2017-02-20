package net.hive.controller;

/**
 * Êëàññ, ñîäåðæàùèé çàïðîñû ê ÁÄ.
 */
class Zapros {
    // Âñå, ïðèõîäèâøèå ñ 20.01.2017
    // 1 - ïîñòîÿííûå pass
    // 2 - âðåìåííûå  pass
    static String zapros() {
        String zapSQL = "select  pr.docser AS Seria, " +
                "        pr.docno AS Nomer, " +
                "        pr.name AS Familia, " +
                "        pr.firstname AS Name, " +
                "        pr.secondname AS Otchestvo, " +
                "        pr.tableno AS Tabelnomer, " +
                //"        di.attributeval AS Organization, " +
                "        cast(p.createdate AS VARCHAR(400)), " +
                "        cast(p.returndate AS VARCHAR(400)) " +              //ÍÅ ÇÀÁÓÄÜ ÏÎÑÒÀÂÈÒÜ ÇÀÏßÒÓÞ!!!
                //"        c.sitecode AS CartSeriya, " +
                //"        c.cardno AS CartNomber, " +
                //"        a.accesslevelname " +
                "from    doublepass p" +
                "        left join doubleperson pr on p.personid = pr.personid " +
                //"        left join dictvals di on pr.orgid = di.dictvalid " +
                //"        left join card c on p.cardid = c.cardid " +
                //"        left join acclname a on p.accesslevel = a.accesslevel " +
                "where   p.passtype = 2 " +
                " and p.cardstatus >= 3 " +
                " and upper(pr.name) like upper ('Á%')" +
                " and p.createdate > '20.01.2017' " +
                " and pr.orgid = 28";
        return zapSQL;
    }

    static String zapSQL() {
        String zap = "select  pr.docser AS Seria, " +
                "        pr.docno AS Nomer, " +
                "        pr.name AS Familia, " +
                "        pr.firstname AS Name, " +
                "        pr.secondname AS Otchestvo, " +
                "        pr.tableno AS Tabelnomer, " +
                //"        di.attributeval AS Organization, " +
                "        p.createdate, " +
                "        p.returndate " +              //ÍÅ ÇÀÁÓÄÜ ÏÎÑÒÀÂÈÒÜ ÇÀÏßÒÓÞ!!!
                //"        c.sitecode AS CartSeriya, " +
                //"        c.cardno AS CartNomber, " +
                //"        a.accesslevelname " +
                "from    doublepass p" +
                "        left join doubleperson pr on p.personid = pr.personid " +
                //"        left join dictvals di on pr.orgid = di.dictvalid " +
                //"        left join card c on p.cardid = c.cardid " +
                //"        left join acclname a on p.accesslevel = a.accesslevel " +
                " where p.passtype = 2 " +
                " and p.cardstatus >= 3 " +
                " and pr.name like 'Á%'" +
                " and p.createdate > '20.01.2017' " +
                " and pr.orgid = 28";
        return zap;
    }
}
