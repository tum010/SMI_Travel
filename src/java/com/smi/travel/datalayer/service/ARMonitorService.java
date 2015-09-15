/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import com.smi.travel.report.GenerateReport;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class ARMonitorService {
    private ARNirvanaDao arNirvanaDao;
    private GenerateReport genreport;
    
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype,String department,String billtype,String from,String to,String status){
        return arNirvanaDao.SearchArNirvanaFromFilter(invtype, department, billtype, from, to, status);
    }
    
    public String ExportARFileInterface(List<ARNirvana> APList){
        return arNirvanaDao.ExportARFileInterface(APList);
    }
    
    public String UpdateStatusARInterface(List<ARNirvana> APList){
        return arNirvanaDao.UpdateStatusARInterface(APList);
    }

    public ARNirvanaDao getArNirvanaDao() {
        return arNirvanaDao;
    }

    public void setArNirvanaDao(ARNirvanaDao arNirvanaDao) {
        this.arNirvanaDao = arNirvanaDao;
    }
    
    public String GetPartFileExport(){
        return genreport.getExportnvnarpath();
    }

    public GenerateReport getGenreport() {
        return genreport;
    }

    public void setGenreport(GenerateReport genreport) {
        this.genreport = genreport;
    }
    
    
    
    
}
