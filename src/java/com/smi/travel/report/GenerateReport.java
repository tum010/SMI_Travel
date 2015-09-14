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
    private String exportnvncsvpath;
    
    public GenerateReport(){

    }
    
    public String printReport(String reportName,String exportfile,JRDataSource dataSource){
        System.out.println("reportpath "+reportpath);
        System.out.println("exportpath "+exportpath);
        System.out.println("exportnvncsvpath "+exportnvncsvpath);
        JasperPrint jprint;
        String result = "";
            try {
                jprint = (JasperPrint) JasperFillManager.fillReport(reportpath+reportName, null, dataSource);
                // Export pdf file
                JasperExportManager.exportReportToPdfFile(jprint,exportpath+exportfile);
                result = "success";
            } catch (JRException ex){
                result = "fail";
                java.util.logging.Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
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

    public String getExportnvncsvpath() {
        return exportnvncsvpath;
    }

    public void setExportnvncsvpath(String exportnvncsvpath) {
        this.exportnvncsvpath = exportnvncsvpath;
    }
    
    
}
