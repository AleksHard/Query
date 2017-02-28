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

/**
 * Created by kharlashkin on 20.02.2017.
 * Класс для управления Excel файлом (файлами).
 */
class ForExcel {
    // Создаём файл
    // Экспорт в первой вкладке
    static void wrightToExcel1(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Вкладка1");
        int nColumnCount = data.size();             // Количество колонок в запросе
        // CellStyle style = sheet.getWorkbook().createCellStyle();

        sheet.setDefaultColumnWidth(17);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setBorderTop(THIN);
        style.setVerticalAlignment(CENTER);
        //style.setAlignment(HorizontalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        int c=1;
        XSSFRow row;
        Pojo pojo;
        XSSFRow zagolovok = sheet.createRow(1);
        XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("Серия паспорта");       zag0.setCellStyle(style);
        XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("Номер паспорта");       zag1.setCellStyle(style);
        XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("Фамилия");              zag2.setCellStyle(style);
        XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("Имя");                  zag3.setCellStyle(style);
        XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("Отчество");             zag4.setCellStyle(style);
        XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("Табельный номер");      zag5.setCellStyle(style);
        XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("Выдача пропуска");      zag6.setCellStyle(style);
        XSSFCell zag7 = zagolovok.createCell(7); zag7.setCellValue("Изъятие пропуска");     zag7.setCellStyle(style);
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
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Вкладка2");
        int nColumnCount = data.size();             // Количество колонок в запросе
        //sheet.setColumnWidth(0,500*25);
        //sheet.setColumnWidth(4,600*10);
        sheet.setDefaultColumnWidth(17);
        // Стили ячеек
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setBorderTop(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        XSSFCellStyle styleHead = workbook.createCellStyle();
        styleHead.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        styleHead.fo
        int c=1;
        XSSFRow row;
        Pojo pojo;
        // Шапка
        XSSFRow shapka = sheet.createRow((short)0);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Выборка по проходам");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
        // Заголовок таблицы
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("Табельный номер");   zag0.setCellStyle(style);
            XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("Фамилия");           zag1.setCellStyle(style);
            XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("Имя");               zag2.setCellStyle(style);
            XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("Отчество");          zag3.setCellStyle(style);
            XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("Подразделение");     zag4.setCellStyle(style);
            XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("Точка прохода");     zag5.setCellStyle(style);
            XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("Время прохода");     zag6.setCellStyle(style);
        // Тело таблицы
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
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Фабрика");
        int nColumnCount = data.size();                 // Количество строк в запросе
        int c=1;
        XSSFRow row;
        Pojo pojo;
        sheet.setColumnWidth(3,800*15);
        sheet.setColumnWidth(4,600*10);
        // Стили ячеек
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setBorderTop(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        // Шапка
        XSSFRow shapka = sheet.createRow((short)0);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("Отчёт по ОФ 'Междуреченская'");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        // Заголовок таблицы
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0);zag0.setCellValue("№ п/п");            zag0.setCellStyle(style);
            XSSFCell zag1 = zagolovok.createCell(1);zag1.setCellValue("Серия карты");       zag1.setCellStyle(style);
            XSSFCell zag2 = zagolovok.createCell(2);zag2.setCellValue("Номер карты");       zag2.setCellStyle(style);
            XSSFCell zag3 = zagolovok.createCell(3);zag3.setCellValue("Подразделение");     zag3.setCellStyle(style);
            XSSFCell zag4 = zagolovok.createCell(4);zag4.setCellValue("Дата создания");     zag4.setCellStyle(style);
        // Тело таблицы
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
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан!");
        System.out.println("Размер pojo = " + nColumnCount);
    }



}
