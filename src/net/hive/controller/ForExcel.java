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
 * Класс для управления Excel файлом (файлами).
 */
class ForExcel {
    private static XSSFWorkbook workbook = new XSSFWorkbook();
    private XSSFCellStyle styleList;
    private XSSFCellStyle styleZag;
    private XSSFCellStyle style;
    private ForExcel(){
        // Create a new font
        XSSFFont font = workbook.createFont();
        font.setFontHeight(15);
        // Стили заголовка листа
        styleList = workbook.createCellStyle();
        styleList.setVerticalAlignment(CENTER);
        styleList.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleList.setFont(font);
        styleList.setWrapText(true);
        // Стили заголовка таблицы
        styleZag = workbook.createCellStyle();
        styleZag.setBorderTop(MEDIUM);
        styleZag.setBorderLeft(MEDIUM);
        styleZag.setBorderRight(MEDIUM);
        styleZag.setBorderBottom(MEDIUM);
        styleZag.setVerticalAlignment(CENTER);
        styleZag.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleZag.setWrapText(true);
        // Стили ячеек
        style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
    }
    // Экспорт в первой вкладке
    static void wrightToExcel1(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        ForExcel styleEx = new ForExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Выдача ропусков");
        int nColumnCount = data.size();             // Количество колонок в запросе
        sheet.setDefaultColumnWidth(17);

        // Шапка листа
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Выдача пропусков сотрудникам и гостям" +
                " за период с " + Controller.tt2 + " по " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:H1"));
        golova.setCellStyle(styleEx.styleList);
        int c=1;
        XSSFRow row;
        Pojo pojo;
        XSSFRow zagolovok = sheet.createRow(1);

        XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("Серия паспорта");       zag0.setCellStyle(styleEx.styleZag);
        XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("Номер паспорта");       zag1.setCellStyle(styleEx.styleZag);
        XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("Фамилия");              zag2.setCellStyle(styleEx.styleZag);
        XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("Имя");                  zag3.setCellStyle(styleEx.styleZag);
        XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("Отчество");             zag4.setCellStyle(styleEx.styleZag);
        XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("Табельный номер");      zag5.setCellStyle(styleEx.styleZag);
        XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("Выдача пропуска");      zag6.setCellStyle(styleEx.styleZag);
        XSSFCell zag7 = zagolovok.createCell(7); zag7.setCellValue("Изъятие пропуска");     zag7.setCellStyle(styleEx.styleZag);
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
            XSSFCell cel0 = row.createCell(0);  cel0.setCellValue(pojo.getSerpas());    cel0.setCellStyle(styleEx.style);
            XSSFCell cel1 = row.createCell(1);  cel1.setCellValue(pojo.getNompas());    cel1.setCellStyle(styleEx.style);
            XSSFCell cel2 = row.createCell(2);  cel2.setCellValue(pojo.getFamil());     cel2.setCellStyle(styleEx.style);
            XSSFCell cel3 = row.createCell(3);  cel3.setCellValue(pojo.getName());      cel3.setCellStyle(styleEx.style);
            XSSFCell cel4 = row.createCell(4);  cel4.setCellValue(pojo.getOtch());      cel4.setCellStyle(styleEx.style);
            XSSFCell cel5 = row.createCell(5);  cel5.setCellValue(pojo.getCartser());   cel5.setCellStyle(styleEx.style);
            XSSFCell cel6 = row.createCell(6);  cel6.setCellValue(pojo.getVhod());      cel6.setCellStyle(styleEx.style);
            XSSFCell cel7 = row.createCell(7);  cel7.setCellValue(pojo.getVihod());     cel7.setCellStyle(styleEx.style);
            c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Итого: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
        System.out.println("Размер даты = " + nColumnCount);

    }
    // Экспорт во второй вкладке
    static void wrightToExcel2(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        ForExcel styleEx = new ForExcel();
        XSSFSheet sheet = workbook.createSheet("Вкладка2");
        int nColumnCount = data.size();             // Количество колонок в запросе
        sheet.setDefaultColumnWidth(17);
        // Шапка
        XSSFCellStyle styleHead = workbook.createCellStyle();
        styleHead.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        int c=1;
        XSSFRow row;
        Pojo pojo;
        // Шапка
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Выборка по проходам" +
                " за период с " + Controller.tt2 + " по " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
        golova.setCellStyle(styleEx.styleList);
        // Заголовок таблицы
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("Табельный номер");   zag0.setCellStyle(styleEx.styleZag);
            XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("Фамилия");           zag1.setCellStyle(styleEx.styleZag);
            XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("Имя");               zag2.setCellStyle(styleEx.styleZag);
            XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("Отчество");          zag3.setCellStyle(styleEx.styleZag);
            XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("Подразделение");     zag4.setCellStyle(styleEx.styleZag);
            XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("Точка прохода");     zag5.setCellStyle(styleEx.styleZag);
            XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("Время прохода");     zag6.setCellStyle(styleEx.styleZag);
        // Тело таблицы
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
                XSSFCell cel0 = row.createCell(0);  cel0.setCellValue(pojo.getSerpas());    cel0.setCellStyle(styleEx.style);
                XSSFCell cel1 = row.createCell(1);  cel1.setCellValue(pojo.getNompas());    cel1.setCellStyle(styleEx.style);
                XSSFCell cel2 = row.createCell(2);  cel2.setCellValue(pojo.getFamil());     cel2.setCellStyle(styleEx.style);
                XSSFCell cel3 = row.createCell(3);  cel3.setCellValue(pojo.getName());      cel3.setCellStyle(styleEx.style);
                XSSFCell cel4 = row.createCell(4);  cel4.setCellValue(pojo.getCartser());   cel4.setCellStyle(styleEx.style);
                XSSFCell cel5 = row.createCell(5);  cel5.setCellValue(pojo.getOtch());      cel5.setCellStyle(styleEx.style);
                XSSFCell cel6 = row.createCell(6);  cel6.setCellValue(pojo.getVhod());      cel6.setCellStyle(styleEx.style);
                c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Итого проходов: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
        System.out.println("Размер даты = " + nColumnCount);
    }
    // Отчёт по Фабрике
    static void otchetOF(String file, ObservableList<Pojo> data)throws IOException, NullPointerException {
        ForExcel styleEx = new ForExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Фабрика");
        int nColumnCount = data.size();                 // Количество строк в запросе
        int c=1;
        XSSFRow row;
        Pojo pojo;
        sheet.setColumnWidth(3,800*15);
        sheet.setColumnWidth(4,600*10);

        // Шапка
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Отчёт об использовании гостевых карт на ОФ 'Междуреченская'" +
                " за период с " + Controller.tt2 + " по " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        golova.setCellStyle(styleEx.styleList);
        // Заголовок таблицы
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0);zag0.setCellValue("№ п/п");            zag0.setCellStyle(styleEx.styleZag);
            XSSFCell zag1 = zagolovok.createCell(1);zag1.setCellValue("Серия карты");       zag1.setCellStyle(styleEx.styleZag);
            XSSFCell zag2 = zagolovok.createCell(2);zag2.setCellValue("Номер карты");       zag2.setCellStyle(styleEx.styleZag);
            XSSFCell zag3 = zagolovok.createCell(3);zag3.setCellValue("Подразделение");     zag3.setCellStyle(styleEx.styleZag);
            XSSFCell zag4 = zagolovok.createCell(4);zag4.setCellValue("Дата создания");     zag4.setCellStyle(styleEx.styleZag);
        // Тело таблицы
        for (Pojo aData : data) {
            pojo = aData;
            row = sheet.createRow(c + 1);
            XSSFCell cel0 = row.createCell(0);
            cel0.setCellValue(c);
            cel0.setCellStyle(styleEx.style);
            XSSFCell cel1 = row.createCell(1);
            cel1.setCellValue(pojo.getSerpas());
            cel1.setCellStyle(styleEx.style);
            XSSFCell cel2 = row.createCell(2);
            cel2.setCellValue(pojo.getNomer());
            cel2.setCellStyle(styleEx.style);
            XSSFCell cel3 = row.createCell(3);
            cel3.setCellValue(pojo.getFamil());
            cel3.setCellStyle(styleEx.style);
            XSSFCell cel4 = row.createCell(4);
            cel4.setCellValue(pojo.getName());
            cel4.setCellStyle(styleEx.style);
            c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Итого: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
        System.out.println("Размер pojo = " + nColumnCount);
    }
    // Отчёт по УК Южная
    static void otchetUK(String file, ObservableList<Pojo> data)throws IOException, NullPointerException {
        ForExcel styleEx = new ForExcel();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("УК Южная");
        int nColumnCount = data.size();                 // Количество строк в запросе
        int c=1;
        XSSFRow row;
        Pojo pojo;
        sheet.setColumnWidth(3,800*15);
        sheet.setColumnWidth(4,600*10);
        // Шапка
        XSSFRow shapka = sheet.createRow((short)0);
        shapka.setHeight((short) 1000);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Отчёт об использовании гостевых карт на УК 'Южная' " +
                "за период с " + Controller.tt2 + " по " + Controller.tt21);
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        golova.setCellStyle(styleEx.styleList);
        // Заголовок таблицы
        XSSFRow zagolovok = sheet.createRow(1);
        XSSFCell zag0 = zagolovok.createCell(0);zag0.setCellValue("№ п/п");            zag0.setCellStyle(styleEx.styleZag);
        XSSFCell zag1 = zagolovok.createCell(1);zag1.setCellValue("Серия карты");       zag1.setCellStyle(styleEx.styleZag);
        XSSFCell zag2 = zagolovok.createCell(2);zag2.setCellValue("Номер карты");       zag2.setCellStyle(styleEx.styleZag);
        XSSFCell zag3 = zagolovok.createCell(3);zag3.setCellValue("Подразделение");     zag3.setCellStyle(styleEx.styleZag);
        XSSFCell zag4 = zagolovok.createCell(4);zag4.setCellValue("Дата создания");     zag4.setCellStyle(styleEx.styleZag);
        // Тело таблицы
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
            XSSFCell cel0 = row.createCell(0);cel0.setCellValue(c);                 cel0.setCellStyle(styleEx.style);
            XSSFCell cel1 = row.createCell(1);cel1.setCellValue(pojo.getSerpas());  cel1.setCellStyle(styleEx.style);
            XSSFCell cel2 = row.createCell(2);cel2.setCellValue(pojo.getNomer());   cel2.setCellStyle(styleEx.style);
            XSSFCell cel3 = row.createCell(3);cel3.setCellValue(pojo.getFamil());   cel3.setCellStyle(styleEx.style);
            XSSFCell cel4 = row.createCell(4);cel4.setCellValue(pojo.getName());    cel4.setCellStyle(styleEx.style);
            c++;
        }
        XSSFRow lastRow = sheet.createRow(nColumnCount + 2);
        XSSFCell cell = lastRow.createCell(0);
        cell.setCellValue("Итого: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
        System.out.println("Размер pojo = " + nColumnCount);
    }
}