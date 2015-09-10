/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller.excel;

import com.smi.travel.datalayer.report.model.TicketFareReport;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Jittima
 */
public class ExportDataToExcelView extends AbstractExcelView {
    private static final String TicketFareReport = "TicketFareReport";
    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String name = (String) model.get("name");
        System.out.println("name : "+name);
        response.setHeader("Content-disposition", "attachment; filename=" + name+".xls");
        
        if(name.equalsIgnoreCase(TicketFareReport)){
             System.out.println("gen report");
             genreport(workbook, (List) model.get(name));
        }
       
    }
    
 
    
    public void genreport(HSSFWorkbook workbook,List TicketFare){
        //create a wordsheet
         HSSFSheet sheet = workbook.createSheet("Revenue Report");
         
         HSSFRow header = sheet.createRow(0);
         header.createCell(0).setCellValue("Docno");
         header.createCell(1).setCellValue("Air");
 
         int rowNum = 1;
         
         //create the row data
          for(int i=0;i<TicketFare.size();i++){
             TicketFareReport data = (TicketFareReport)TicketFare.get(i);
             HSSFRow row = sheet.createRow(rowNum + i);
             row.createCell(0).setCellValue(data.getDocno());
             row.createCell(1).setCellValue(data.getAir());
          }
        
         
    }
    
   

}
