package net.hive.controller;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * Created by kharlashkin on 20.02.2017.
 * ����� ��� ���������� Excel ������ (�������).
 */
class ForExcel {
    // ������ ����
    // ������� � ������ �������
    static void wrightToExcel1(String file, ObservableList<Pojo> data) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("������1");
        int nColumnCount = data.size();             // ���������� ������� � �������
        // CellStyle style = sheet.getWorkbook().createCellStyle();
        XSSFRow row;
        Pojo pojo;
        XSSFRow zagolovok = sheet.createRow(0);
        zagolovok.createCell(0).setCellValue("����� ��������");
        zagolovok.createCell(1).setCellValue("����� ��������");
        zagolovok.createCell(2).setCellValue("�������");
        zagolovok.createCell(3).setCellValue("���");
        zagolovok.createCell(4).setCellValue("��������");
        zagolovok.createCell(5).setCellValue("��������� �����");
        zagolovok.createCell(6).setCellValue("������ ��������");
        zagolovok.createCell(7).setCellValue("������� ��������");
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(i+1);
            XSSFCell cel0 = row.createCell(0);
            cel0.setCellValue(pojo.getSerpas());
            XSSFCell cel1 = row.createCell(1);
            cel1.setCellValue(pojo.getNompas());
            XSSFCell cel2 = row.createCell(2);
            cel2.setCellValue(pojo.getFamil());
            XSSFCell cel3 = row.createCell(3);
            cel3.setCellValue(pojo.getName());
            XSSFCell cel4 = row.createCell(4);
            cel4.setCellValue(pojo.getOtch());
            XSSFCell cel5 = row.createCell(5);
            cel5.setCellValue(pojo.getCartser());
            XSSFCell cel6 = row.createCell(6);
            cel6.setCellValue(pojo.getVhod());
            XSSFCell cel7 = row.createCell(7);
            cel7.setCellValue(pojo.getVihod());
        }
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
        XSSFSheet sheet = workbook.createSheet("������");
        int nColumnCount = data.size();             // ���������� ������� � �������

        XSSFRow row;
        Pojo pojo;
        XSSFRow zagolovok = sheet.createRow(0);
        zagolovok.createCell(0).setCellValue("��������� �����");
        zagolovok.createCell(1).setCellValue("�������");
        zagolovok.createCell(2).setCellValue("���");
        zagolovok.createCell(3).setCellValue("��������");
        zagolovok.createCell(4).setCellValue("�������������");
        zagolovok.createCell(5).setCellValue("����� �������");
        zagolovok.createCell(6).setCellValue("����� �������");
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(i+1);
                XSSFCell cel0 = row.createCell(0);
                cel0.setCellValue(pojo.getSerpas());
                XSSFCell cel1 = row.createCell(1);
                cel1.setCellValue(pojo.getNompas());
                XSSFCell cel2 = row.createCell(2);
                cel2.setCellValue(pojo.getFamil());
                XSSFCell cel3 = row.createCell(3);
                cel3.setCellValue(pojo.getName());
                XSSFCell cel4 = row.createCell(4);
                cel4.setCellValue(pojo.getCartser());
                XSSFCell cel5 = row.createCell(5);
                cel5.setCellValue(pojo.getOtch());
                XSSFCell cel6 = row.createCell(6);
                cel6.setCellValue(pojo.getVhod());
        }
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
        XSSFSheet sheet = workbook.createSheet("������");
        int nColumnCount = data.size();                 // ���������� ����� � �������
        int c=1;
        XSSFRow row;
        Pojo pojo;
        XSSFRow zagolovok = sheet.createRow(1);
        zagolovok.createCell(0).setCellValue("� �/�");
        zagolovok.createCell(1).setCellValue("����� �����");
        zagolovok.createCell(2).setCellValue("����� �����");
        zagolovok.createCell(3).setCellValue("�������������");
        zagolovok.createCell(4).setCellValue("���� ��������");
        for(int i = 0; i < nColumnCount; i++){
            pojo = data.get(i);
            row = sheet.createRow(c+1);
            XSSFCell cel0 = row.createCell(0);
            cel0.setCellValue(c);
            XSSFCell cel1 = row.createCell(1);
            cel1.setCellValue(pojo.getSerpas());
            XSSFCell cel2 = row.createCell(2);
            cel2.setCellValue(pojo.getNomer());
            XSSFCell cel3 = row.createCell(3);
            cel3.setCellValue(pojo.getFamil());
            XSSFCell cel4 = row.createCell(4);
            cel4.setCellValue(pojo.getName());
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
