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
 * ����� ��� ���������� Excel ������ (�������).
 */
class ForExcel {
    // ������ ����
    // ������� � ������ �������
    static void wrightToExcel1(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("�������1");
        int nColumnCount = data.size();             // ���������� ������� � �������
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
        XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("����� ��������");       zag0.setCellStyle(style);
        XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("����� ��������");       zag1.setCellStyle(style);
        XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("�������");              zag2.setCellStyle(style);
        XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("���");                  zag3.setCellStyle(style);
        XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("��������");             zag4.setCellStyle(style);
        XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("��������� �����");      zag5.setCellStyle(style);
        XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("������ ��������");      zag6.setCellStyle(style);
        XSSFCell zag7 = zagolovok.createCell(7); zag7.setCellValue("������� ��������");     zag7.setCellStyle(style);
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
        cell.setCellValue("�����: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ���� ������� ������!");
        System.out.println("������ ���� = " + nColumnCount);
    }
    // ������� �� ������ �������
    static void wrightToExcel2(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("�������2");
        int nColumnCount = data.size();             // ���������� ������� � �������
        //sheet.setColumnWidth(0,500*25);
        //sheet.setColumnWidth(4,600*10);
        sheet.setDefaultColumnWidth(17);
        // ����� �����
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
        // �����
        XSSFRow shapka = sheet.createRow((short)0);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("������� �� ��������");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:G1"));
        // ��������� �������
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0); zag0.setCellValue("��������� �����");   zag0.setCellStyle(style);
            XSSFCell zag1 = zagolovok.createCell(1); zag1.setCellValue("�������");           zag1.setCellStyle(style);
            XSSFCell zag2 = zagolovok.createCell(2); zag2.setCellValue("���");               zag2.setCellStyle(style);
            XSSFCell zag3 = zagolovok.createCell(3); zag3.setCellValue("��������");          zag3.setCellStyle(style);
            XSSFCell zag4 = zagolovok.createCell(4); zag4.setCellValue("�������������");     zag4.setCellStyle(style);
            XSSFCell zag5 = zagolovok.createCell(5); zag5.setCellValue("����� �������");     zag5.setCellStyle(style);
            XSSFCell zag6 = zagolovok.createCell(6); zag6.setCellValue("����� �������");     zag6.setCellStyle(style);
        // ���� �������
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
        cell.setCellValue("����� ��������: " + nColumnCount);
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ���� ������� ������!");
        System.out.println("������ ���� = " + nColumnCount);
    }
    // ����� �� �������
    static void otchetOF(String file, ObservableList<Pojo> data)throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("�������");
        int nColumnCount = data.size();                 // ���������� ����� � �������
        int c=1;
        XSSFRow row;
        Pojo pojo;
        sheet.setColumnWidth(3,800*15);
        sheet.setColumnWidth(4,600*10);
        // ����� �����
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(THIN);
        style.setBorderLeft(THIN);
        style.setBorderRight(THIN);
        style.setBorderTop(THIN);
        style.setVerticalAlignment(CENTER);
        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setWrapText(true);
        // �����
        XSSFRow shapka = sheet.createRow((short)0);
        XSSFCell golova = shapka.createCell((short)0);
        golova.setCellValue("����� �� �� '��������������'");
        sheet.addMergedRegion(CellRangeAddress.valueOf("A1:E1"));
        // ��������� �������
        XSSFRow zagolovok = sheet.createRow(1);
            XSSFCell zag0 = zagolovok.createCell(0);zag0.setCellValue("� �/�");            zag0.setCellStyle(style);
            XSSFCell zag1 = zagolovok.createCell(1);zag1.setCellValue("����� �����");       zag1.setCellStyle(style);
            XSSFCell zag2 = zagolovok.createCell(2);zag2.setCellValue("����� �����");       zag2.setCellStyle(style);
            XSSFCell zag3 = zagolovok.createCell(3);zag3.setCellValue("�������������");     zag3.setCellStyle(style);
            XSSFCell zag4 = zagolovok.createCell(4);zag4.setCellValue("���� ��������");     zag4.setCellStyle(style);
        // ���� �������
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
        System.out.println("Excel ���� ������� ������!");
        System.out.println("������ pojo = " + nColumnCount);
    }



}
