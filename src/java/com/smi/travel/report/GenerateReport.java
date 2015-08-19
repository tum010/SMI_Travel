/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.report;

import com.smi.travel.controller.report.ReportController;
import java.util.logging.Level;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author Surachai
 */

public class GenerateReport {
    private String reportpath;
    private String exportpath;
    
    public GenerateReport(){

    }
    
    public void printReport(String reportName,String exportfile,JRDataSource dataSource){
        System.out.println("reportpath "+reportpath);
        System.out.println("exportpath "+exportpath);
        JasperPrint jprint;
            try {
                jprint = (JasperPrint) JasperFillManager.fillReport(reportpath+reportName, null, dataSource);
                // Export pdf file
                JasperExportManager.exportReportToPdfFile(jprint,exportpath+exportfile);
            } catch (JRException ex) {
                java.util.logging.Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public String getReportpath() {
        return reportpath;
    }

    public void setReportpath(String reportpath) {
        this.reportpath = reportpath;
    }

    public String getExportpath() {
        return exportpath;
    }

    public void setExportpath(String exportpath) {
        this.exportpath = exportpath;
    }
    
    
}
