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
public interface PostSaleVatDao {
    public List<OutputTaxView> SearchOutputTaxViewFromFilter(String from,String to,String department,String status,String type);   
    public String UpdateOutputTaxStatus(List<OutputTaxView> outputTaxViewList);
}
