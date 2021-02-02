package com.atguigu.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestPoi {
    /**
     *
     */
    @Test
    public void TestPoi() throws IOException {
        Workbook workbook = new XSSFWorkbook("D:/atguigu.xlsx");
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                String value = cell.getStringCellValue();
                System.out.print(" " + value + " ");
            }
            System.out.println();
        }
        workbook.close();
    }

    /**
     *
     */
    @Test
    public void TestPoiOut() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        for (int i = 0; i < 3; i++) {
            Row row = sheet.createRow(i);
            for (int c = 0; c < 3; c++) {
                row.createCell(c).setCellValue("列:"+(c+1));
            }
        }
        FileOutputStream outputStream = new FileOutputStream("d:/atguigu1.xlsx");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }
}
