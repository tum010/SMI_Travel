/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.view.entity.OutputTaxView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public interface SaleVatReportDao {
    public List<OutputTaxView> SearchOutputTaxViewFromFilter(String from,String to,String department);   
    public String UpdateOutputTaxStatusCancel(List<OutputTaxView> outputTaxViewList);
    public List getSaleVatReportList(String month,String year,String department);
}
