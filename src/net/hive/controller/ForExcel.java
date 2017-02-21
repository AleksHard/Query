package net.hive.controller;
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
    static void wrightToExcel(String file) throws IOException, NullPointerException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("������");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("����������");
        try(FileOutputStream out = new FileOutputStream((new File(file)))){
            workbook.write(out);
        }   catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Excel ���� ������� ������!");
    }
}
