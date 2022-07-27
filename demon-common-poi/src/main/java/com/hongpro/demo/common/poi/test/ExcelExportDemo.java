package com.hongpro.demo.common.poi.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @description:
 * @author: zhangzihong
 * @createTime: 2022/2/17
 */
public class ExcelExportDemo {
    private static final long MAX_ROW = 400000;
    private static final String FILE_PATH = "D:\\source\\temp\\poi-test.xlsx";

    public static void main(String[] args) throws IOException, InterruptedException {
        Date start = new Date();
        readPoi();
        //Thread.sleep(10000L);
        System.out.println("耗时:" + (new Date().getTime() - start.getTime()) + "毫秒");
    }

    public static void readEasyExcel() throws FileNotFoundException {
        writeWithoutHead(new FileInputStream(FILE_PATH));
    }

    public static void readPoi() throws IOException {
        InputStream is = new FileInputStream(new File(FILE_PATH));
        Workbook hssfWorkbook = null;
        hssfWorkbook = new XSSFWorkbook(is);
        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    //System.out.println(hssfRow.getCell(0));
                    //第一列内容
                    //System.out.println(hssfRow.getCell(1));

                }
            }
        }
    }
    public static void exportPoi() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i < MAX_ROW; i++) {
            XSSFRow row = sheet.createRow(i);
            row.createCell(0).setCellValue("XX");
            row.createCell(1).setCellValue(new Date());
            row.createCell(2).setCellValue("你好啊");
            row.createCell(3).setCellValue("你好啊2");
        }
        workbook.setSheetName(0,"xx");
        FileOutputStream out = new FileOutputStream(FILE_PATH);
        workbook.write(out);
        out.close();
    }

    /**
     * 根据excel输入流，读取excel文件
     *
     * @param inputStream exece表格的输入流
     * @return 返回双重list的集合
     **/
    public static List<List<String>> writeWithoutHead(InputStream inputStream) {
        StringExcelListener listener = new StringExcelListener();
        ExcelReader excelReader = EasyExcelFactory.read(inputStream, null, listener).headRowNumber(0).build();
        excelReader.readAll();
        List<List<String>> datas = listener.getDatas();
        excelReader.finish();
        return datas;
    }
}
