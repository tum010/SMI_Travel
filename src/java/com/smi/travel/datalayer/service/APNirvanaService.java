/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import com.smi.travel.datalayer.view.entity.NirvanaInterface;
import com.smi.travel.report.GenerateReport;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class APNirvanaService {
    private APNirvanaDao apNirvanaDao;
    private GenerateReport genreport;
    
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType,String producttype,String status,String from,String to, String accno) {
        return apNirvanaDao.SearchApNirvanaFromFilter(paymentType, producttype, status, from, to, accno);
    }
    
    public String ExportAPFileInterface(List<APNirvana> APList,String pathfile) {
        return apNirvanaDao.ExportAPFileInterface(APList, pathfile);
    }
    
    public String UpdateStatusAPInterface(List<NirvanaInterface> nirvanaInterfaceList) {
        return apNirvanaDao.UpdateStatusAPInterface(nirvanaInterfaceList);
    }

    public APNirvanaDao getApNirvanaDao() {
        return apNirvanaDao;
    }

    public void setApNirvanaDao(APNirvanaDao apNirvanaDao) {
        this.apNirvanaDao = apNirvanaDao;
    }
    
    public String GetPartFileExport(){
        return genreport.getExportnvnappath();
    }

    public GenerateReport getGenreport() {
        return genreport;
    }

    public void setGenreport(GenerateReport genreport) {
        this.genreport = genreport;
    }

    public String MappingAPNirvana(List<APNirvana> apNirvanaData) {
        return apNirvanaDao.MappingAPNirvana(apNirvanaData);
    }
    
    
   
}
