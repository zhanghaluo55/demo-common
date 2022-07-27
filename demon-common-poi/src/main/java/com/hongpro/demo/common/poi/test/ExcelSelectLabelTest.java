package com.hongpro.demo.common.poi.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @description:
 * @author: zhangzihong
 * @createTime: 2022/7/21
 */
public class ExcelSelectLabelTest {

    public static void main(String[] args) throws FileNotFoundException {
        try (OutputStream out = Files.newOutputStream(Paths.get("D:\\source\\temp\\aa.xlsx"), StandardOpenOption.CREATE)) {
            Workbook workbook = new XSSFWorkbook();
            //表头行数据
            List<String> header = Arrays.asList("广告源", "代码位", "代码位名称", "价格类型", "价格", "状态");
            //生成一个表格，设置表格名称
            Sheet sheet = workbook.createSheet("批量导入代码位模板");
            //设置表格列宽度为30个字节
            sheet.setDefaultColumnWidth(15);
            //创建标题的显示样式
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.WHITE1.index);
            headerStyle.setFillPattern(FillPatternType.NO_FILL);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            //创建第一行表头
            Row headrow = sheet.createRow(0);
            String[] heads = header.toArray(new String[0]);
            for (int i = 0; i < heads.length; i++) {
                Cell cell = headrow.createCell(i);
                cell.setCellValue(heads[i]);
                cell.setCellStyle(headerStyle);
            }
            /*
            1.封装下拉框数据
             */
            //下拉框数据
            List<String[]> selecDatatArray = new ArrayList<>();
            String[] settleType = {"固价","竞价"}  ;
            String[] sts = {"开启","关闭"}  ;
            String[] ab = {"a","b"}  ;

            selecDatatArray.add(ab);
            selecDatatArray.add(settleType);
            selecDatatArray.add(sts);
            //下拉框列位置
            List<Integer> colNums = new ArrayList<>();
            colNums.add(0);
            colNums.add(3);
            colNums.add(5);

            /**
             * 2. 设置下拉框数据
             */
            for (int i = 0; i < selecDatatArray.size() ; i++){
                String[] selectData = selecDatatArray.get(i);
                Integer colNum = colNums.get(i);
                //设置下拉框位置  开始行 结束行  开始列 结束列
                DataValidationHelper helper=sheet.getDataValidationHelper();
                //获取需要生成下拉框的格   开始行 结束行  开始列 结束列
                CellRangeAddressList addressList = new CellRangeAddressList(1, 100, colNum, colNum);
                //设置下拉框数据
                DataValidationConstraint constraint = helper.createExplicitListConstraint(selectData);
                DataValidation dataValidation = helper.createValidation(constraint, addressList);
                sheet.addValidationData(dataValidation);
            }
            String excelName = "批量导入代码位模板.xlsx";
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
