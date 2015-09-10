/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelView extends AbstractExcelView {

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setHeader("Content-disposition", "attachment; filename=" + "exported.xls");

        
         Map<String,String> revenueData = (Map<String,String>) model.get("revenueData");
         //create a wordsheet
         HSSFSheet sheet = workbook.createSheet("Revenue Report");
 
         HSSFRow header = sheet.createRow(0);
         header.createCell(0).setCellValue("Month");
         header.createCell(1).setCellValue("Revenue");
 
         int rowNum = 1;
         for (Map.Entry<String, String> entry : revenueData.entrySet()) {
         //create the row data
         HSSFRow row = sheet.createRow(rowNum++);
         row.createCell(0).setCellValue(entry.getKey());
         row.createCell(1).setCellValue(entry.getValue());
         }
    }
    
    public static void main(String[] args) throws IOException {
        String excelFileName = "D:/somchayng/Test.xls";//name of excel file

        String sheetName = "Sheet1";//name of sheet

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName) ;

        //iterating r number of rows
        for (int r=0;r < 5; r++ )
        {
                HSSFRow row = sheet.createRow(r);

                //iterating c number of columns
                for (int c=0;c < 5; c++ )
                {
                        HSSFCell cell = row.createCell(c);

                        cell.setCellValue("Cell "+r+" "+c);
                }
        }

        FileOutputStream fileOut = new FileOutputStream(excelFileName);

        //write this workbook to an Outputstream.
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();

    }
}

