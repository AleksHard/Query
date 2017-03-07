package net.hive.controller;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.poi.ss.usermodel.BorderStyle.MEDIUM;
import static org.apache.poi.ss.usermodel.BorderStyle.THIN;
import static org.apache.poi.ss.usermodel.VerticalAlignment.CENTER;
import static org.apache.poi.ss.usermodel.VerticalAlignment.forInt;

/**
 * Created by kharlashkin on 20.02.2017.
 * Êëàññ äëÿ óïðàâëåíèÿ Excel ôàéëîì (ôàéëàìè).
 */
class ForExcel {
    // Ýêñïîðò â ïåðâîé âêëàäêå
    static void wrightToExcel1(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Âûäà÷à ðîïóñêîâ");
        int nColumnCount = data.size();             // Êîëè÷åñòâî êîëîíîê â çàïðîñå
        sheet.setDefaultColumnWidth(17);

        // Create a new font
        XSSFFont font = workbook.createFont();
        font.setFontHeight(15);
        // Ñòèëè çàãîëîâêà ëèñòà
        XSSFCellStyle styleList = workbook.createCellStyle();
        styleList.setVerticalAlignment(CENTER);
        styleList.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleList.setFont(font);
        styleList.setWrapText(true);
        // Ñòèëè çàãîëîâêà òàáëèöû
        XSSFCellStyle styleZag = workbook.createCellStyle();
        styleZag.setBorderTop(MEDIUM);
        styleZag.setBorderLeft(MEDIUM);
        styleZag.setBorderRight(MEDIUM);
        styleZag.setBorderBottom(MEDIUM);
        styleZag.setVerticalAlignment(CENTER);
        styleZag.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleZag.setWrapText(true);
        // ß÷åéêè òàáëèöû

        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setVerticalAlignment(CENTER);
        //style.setAlignment(HorizontalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        // Øàïêà ëèñòà
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Âûäà÷à ïðîïóñêîâ ñîòðóäíèêàì è ãîñòÿì" +
                " çà ïåðèîä ñ " + Controller.tt2 + " ïî " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));
        golova.setCellStyle(styleList);
        int c=1;
        XSSFRow row;
        Pojo pojo;
        XSSFRow zagolovok = sheet.createRow(1);
        XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("Ñåðèÿ ïàñïîðòà");       zag0.setCellStyle(styleZag);
        XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("Íîìåð ïàñïîðòà");       zag1.setCellStyle(styleZag);
        XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("Ôàìèëèÿ");              zag2.setCellStyle(styleZag);
        XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("Èìÿ");                  zag3.setCellStyle(styleZag);
        XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("Îò÷åñòâî");             zag4.setCellStyle(styleZag);
        XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("Òàáåëüíûé íîìåð");      zag5.setCellStyle(styleZag);
        XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("Âûäà÷à ïðîïóñêà");      zag6.setCellStyle(styleZag);
        XSSFCell zag7 = zagolovok.createCell(7); zag7.setCellValue("Èçúÿòèå ïðîïóñêà");     zag7.setCellStyle(styleZag);
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
            XSSFCell cel0 = row.createCell(0);  cel0.setCellValue(pojo.getSerpas());    cel0.setCellStyle(style);
            XSSFCell cel1 = row.createCell(1);  cel1.setCellValue(pojo.getNompas());    cel1.setCellStyle(style);
            XSSFCell cel2 = row.createCell(2);  cel2.setCellValue(pojo.getFamil());     cel2.setCellStyle(style);
            XSSFCell cel3 = row.createCell(3);  cel3.setCellValue(pojo.getName());      cel3.setCellStyle(style);
            XSSFCell cel4 = row.createCell(4);  cel4.setCellValue(pojo.getOtch());      cel4.setCellStyle(style);
            XSSFCell cel5 = row.createCell(5);  cel5.setCellValue(pojo.getCartser());   cel5.setCellStyle(style);
            XSSFCell cel6 = row.createCell(6);  cel6.setCellValue(pojo.getVhod());      cel6.setCellStyle(style);
            XSSFCell cel7 = row.createCell(7);  cel7.setCellValue(pojo.getVihod());     cel7.setCellStyle(style);
            c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Èòîãî: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ôàéë óñïåøíî ñîçäàí!");
        System.out.println("Ðàçìåð äàòû = " + nColumnCount);
    }
    // Ýêñïîðò âî âòîðîé âêëàäêå
    static void wrightToExcel2(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Âêëàäêà2");
        int nColumnCount = data.size();             // Êîëè÷åñòâî êîëîíîê â çàïðîñå
        //sheet.setColumnWidth(0,500*25);
        //sheet.setColumnWidth(4,600*10);
        sheet.setDefaultColumnWidth(17);
        // Create a new font
        XSSFFont font = workbook.createFont();
        font.setFontHeight(15);
        // Ñòèëè çàãîëîâêà ëèñòà
        XSSFCellStyle styleList = workbook.createCellStyle();
        styleList.setVerticalAlignment(CENTER);
        styleList.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleList.setFont(font);
        styleList.setWrapText(true);
        // Ñòèëè çàãîëîâêà òàáëèöû
        XSSFCellStyle styleZag = workbook.createCellStyle();
        styleZag.setBorderTop(MEDIUM);
        styleZag.setBorderLeft(MEDIUM);
        styleZag.setBorderRight(MEDIUM);
        styleZag.setBorderBottom(MEDIUM);
        styleZag.setVerticalAlignment(CENTER);
        styleZag.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleZag.setWrapText(true);
        // Ñòèëè ÿ÷ååê
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        XSSFCellStyle styleHead = workbook.createCellStyle();
        styleHead.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        //styleHead.fo
        int c=1;
        XSSFRow row;
        Pojo pojo;
        // Øàïêà
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Âûáîðêà ïî ïðîõîäàì" +
                " çà ïåðèîä ñ " + Controller.tt2 + " ïî " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
        golova.setCellStyle(styleList);
        // Çàãîëîâîê òàáëèöû
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("Òàáåëüíûé íîìåð");   zag0.setCellStyle(styleZag);
            XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("Ôàìèëèÿ");           zag1.setCellStyle(styleZag);
            XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("Èìÿ");               zag2.setCellStyle(styleZag);
            XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("Îò÷åñòâî");          zag3.setCellStyle(styleZag);
            XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("Ïîäðàçäåëåíèå");     zag4.setCellStyle(styleZag);
            XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("Òî÷êà ïðîõîäà");     zag5.setCellStyle(styleZag);
            XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("Âðåìÿ ïðîõîäà");     zag6.setCellStyle(styleZag);
        // Òåëî òàáëèöû
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
                XSSFCell cel0 = row.createCell(0);  cel0.setCellValue(pojo.getSerpas());    cel0.setCellStyle(style);
                XSSFCell cel1 = row.createCell(1);  cel1.setCellValue(pojo.getNompas());    cel1.setCellStyle(style);
                XSSFCell cel2 = row.createCell(2);  cel2.setCellValue(pojo.getFamil());     cel2.setCellStyle(style);
                XSSFCell cel3 = row.createCell(3);  cel3.setCellValue(pojo.getName());      cel3.setCellStyle(style);
                XSSFCell cel4 = row.createCell(4);  cel4.setCellValue(pojo.getCartser());   cel4.setCellStyle(style);
                XSSFCell cel5 = row.createCell(5);  cel5.setCellValue(pojo.getOtch());      cel5.setCellStyle(style);
                XSSFCell cel6 = row.createCell(6);  cel6.setCellValue(pojo.getVhod());      cel6.setCellStyle(style);
                c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Èòîãî ïðîõîäîâ: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ôàéë óñïåøíî ñîçäàí!");
        System.out.println("Ðàçìåð äàòû = " + nColumnCount);
    }
    // Îò÷¸ò ïî Ôàáðèêå
    static void otchetOF(String file, ObservableList<Pojo> data)throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Ôàáðèêà");
        int nColumnCount = data.size();                 // Êîëè÷åñòâî ñòðîê â çàïðîñå
        int c=1;
        XSSFRow row;
        Pojo pojo;
        sheet.setColumnWidth(3,800*15);
        sheet.setColumnWidth(4,600*10);
        // Create a new font
        XSSFFont font = workbook.createFont();
        font.setFontHeight(15);
        // Ñòèëè çàãîëîâêà ëèñòà
        XSSFCellStyle styleList = workbook.createCellStyle();
        styleList.setVerticalAlignment(CENTER);
        styleList.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleList.setFont(font);
        styleList.setWrapText(true);
        // Ñòèëè çàãîëîâêà òàáëèöû
        XSSFCellStyle styleZag = workbook.createCellStyle();
        styleZag.setBorderTop(MEDIUM);
        styleZag.setBorderLeft(MEDIUM);
        styleZag.setBorderRight(MEDIUM);
        styleZag.setBorderBottom(MEDIUM);
        styleZag.setVerticalAlignment(CENTER);
        styleZag.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleZag.setWrapText(true);
        // Ñòèëè ÿ÷ååê
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        // Ñòèëè çàãîëîâêà òàáëèöû
        XSSFCellStyle styleZag = workbook.createCellStyle();
        styleZag.setBorderTop(MEDIUM);
        styleZag.setBorderLeft(MEDIUM);
        styleZag.setBorderRight(MEDIUM);
        styleZag.setBorderBottom(MEDIUM);
        styleZag.setVerticalAlignment(CENTER);
        styleZag.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleZag.setWrapText(true);

        // Øàïêà
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Îò÷¸ò îá èñïîëüçîâàíèè ãîñòåâûõ êàðò íà ÎÔ 'Ìåæäóðå÷åíñêàÿ'" +
                " çà ïåðèîä ñ " + Controller.tt2 + " ïî " + Controller.tt21);

        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        golova.setCellStyle(styleList);
        // Çàãîëîâîê òàáëèöû
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0);zag0.setCellValue("¹ ï/ï");            zag0.setCellStyle(styleZag);
            XSSFCell zag1 = zagolovok.createCell(1);zag1.setCellValue("Ñåðèÿ êàðòû");       zag1.setCellStyle(styleZag);
            XSSFCell zag2 = zagolovok.createCell(2);zag2.setCellValue("Íîìåð êàðòû");       zag2.setCellStyle(styleZag);
            XSSFCell zag3 = zagolovok.createCell(3);zag3.setCellValue("Ïîäðàçäåëåíèå");     zag3.setCellStyle(styleZag);
            XSSFCell zag4 = zagolovok.createCell(4);zag4.setCellValue("Äàòà ñîçäàíèÿ");     zag4.setCellStyle(styleZag);
        // Òåëî òàáëèöû
        for (Pojo aData : data) {
            pojo = aData;
            row = sheet.createRow(c + 1);
            XSSFCell cel0 = row.createCell(0);
            cel0.setCellValue(c);
            cel0.setCellStyle(style);
            XSSFCell cel1 = row.createCell(1);
            cel1.setCellValue(pojo.getSerpas());
            cel1.setCellStyle(style);
            XSSFCell cel2 = row.createCell(2);
            cel2.setCellValue(pojo.getNomer());
            cel2.setCellStyle(style);
            XSSFCell cel3 = row.createCell(3);
            cel3.setCellValue(pojo.getFamil());
            cel3.setCellStyle(style);
            XSSFCell cel4 = row.createCell(4);
            cel4.setCellValue(pojo.getName());
            cel4.setCellStyle(style);
            c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Èòîãî: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ôàéë óñïåøíî ñîçäàí!");
        System.out.println("Ðàçìåð pojo = " + nColumnCount);
    }
    // Îò÷¸ò ïî ÓÊ Þæíàÿ
    static void otchetUK(String file, ObservableList<Pojo> data)throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("ÓÊ Þæíàÿ");
        int nColumnCount = data.size();                 // Êîëè÷åñòâî ñòðîê â çàïðîñå
        int c=1;
        XSSFRow row;
        Pojo pojo;
        sheet.setColumnWidth(3,800*15);
        sheet.setColumnWidth(4,600*10);
        // Create a new font
        XSSFFont font = workbook.createFont();
        font.setFontHeight(15);
        // Ñòèëè çàãîëîâêà ëèñòà
        XSSFCellStyle styleList = workbook.createCellStyle();
        styleList.setVerticalAlignment(CENTER);
        styleList.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleList.setFont(font);
        styleList.setWrapText(true);
        // Ñòèëè çàãîëîâêà òàáëèöû
        XSSFCellStyle styleZag = workbook.createCellStyle();
        styleZag.setBorderTop(MEDIUM);
        styleZag.setBorderLeft(MEDIUM);
        styleZag.setBorderRight(MEDIUM);
        styleZag.setBorderBottom(MEDIUM);
        styleZag.setVerticalAlignment(CENTER);
        styleZag.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleZag.setWrapText(true);
        // Ñòèëè ÿ÷ååê
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        // Øàïêà
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Îò÷¸ò îá èñïîëüçîâàíèè ãîñòåâûõ êàðò íà ÓÊ 'Þæíàÿ' " +
                "çà ïåðèîä ñ " + Controller.tt2 + " ïî " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        golova.setCellStyle(styleList);
        // Çàãîëîâîê òàáëèöû
        XSSFRow zagolovok = sheet.createRow(1);
        XSSFCell zag0 = zagolovok.createCell(0);zag0.setCellValue("¹ ï/ï");            zag0.setCellStyle(styleZag);
        XSSFCell zag1 = zagolovok.createCell(1);zag1.setCellValue("Ñåðèÿ êàðòû");       zag1.setCellStyle(styleZag);
        XSSFCell zag2 = zagolovok.createCell(2);zag2.setCellValue("Íîìåð êàðòû");       zag2.setCellStyle(styleZag);
        XSSFCell zag3 = zagolovok.createCell(3);zag3.setCellValue("Ïîäðàçäåëåíèå");     zag3.setCellStyle(styleZag);
        XSSFCell zag4 = zagolovok.createCell(4);zag4.setCellValue("Äàòà ñîçäàíèÿ");     zag4.setCellStyle(styleZag);
        // Òåëî òàáëèöû
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
            XSSFCell cel0 = row.createCell(0);cel0.setCellValue(c);                 cel0.setCellStyle(style);
            XSSFCell cel1 = row.createCell(1);cel1.setCellValue(pojo.getSerpas());  cel1.setCellStyle(style);
            XSSFCell cel2 = row.createCell(2);cel2.setCellValue(pojo.getNomer());   cel2.setCellStyle(style);
            XSSFCell cel3 = row.createCell(3);cel3.setCellValue(pojo.getFamil());   cel3.setCellStyle(style);
            XSSFCell cel4 = row.createCell(4);cel4.setCellValue(pojo.getName());    cel4.setCellStyle(style);
            c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Èòîãî: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ôàéë óñïåøíî ñîçäàí!");
        System.out.println("Ðàçìåð pojo = " + nColumnCount);
    }
}