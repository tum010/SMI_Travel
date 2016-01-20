/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.SaleVatReportDao;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class SaleVatReportService {
    private SaleVatReportDao saleVatReportDao;
    
    public List<OutputTaxView> SearchOutputTaxViewFromFilter(String from,String to,String department,String status){
        return saleVatReportDao.SearchOutputTaxViewFromFilter(from, to, department,status);
    }  
    
    public String UpdateOutputTaxStatusCancel(List<OutputTaxView> outputTaxViewList){
        return saleVatReportDao.UpdateOutputTaxStatusCancel(outputTaxViewList);
    }  
    
    public SaleVatReportDao getSaleVatReportDao() {
        return saleVatReportDao;
    }

    public void setSaleVatReportDao(SaleVatReportDao saleVatReportDao) {
        this.saleVatReportDao = saleVatReportDao;
    }
}
