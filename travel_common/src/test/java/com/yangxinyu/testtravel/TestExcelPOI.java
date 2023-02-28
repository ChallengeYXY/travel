/*
package com.yangxinyu.testtravel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestExcelPOI {
    @Test
    public void test01() throws IOException {
        //新建一个xlsx格式的office的工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook("/Users/yxy/Desktop/me.xlsx");
        //从工作簿中拿工作表（可以按表名，可以按顺序）
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
        //遍历每一行
        for (Row cells : sheet) {
            //遍历每一列(每一格)
            for (Cell cell : cells) {
                String stringCellValue = cell.getStringCellValue();
                System.out.print(stringCellValue);
            }
            System.out.println();
        }
        xssfWorkbook.close();
    }

    @Test
    public void test02() throws IOException {
        //创建一个xlsx文件
        FileOutputStream fis = new FileOutputStream("/Users/yxy/Desktop/meme.xlsx");
        //创建工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //创建工作表表
        XSSFSheet firstSheet = xssfWorkbook.createSheet("firstSheet");
        //创建行，0表示第一行
        XSSFRow row = firstSheet.createRow(0);
        //创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年龄");

        XSSFRow row1 = firstSheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");

        XSSFRow row2 = firstSheet.createRow(2);
        row2.createCell(0).setCellValue("2");
        row2.createCell(1).setCellValue("小王");
        row2.createCell(2).setCellValue("20");


        //将完整的xlsx文件格式，以及数据文件写入文件
        xssfWorkbook.write(fis);

        xssfWorkbook.close();
        fis.close();
    }
}
*/
