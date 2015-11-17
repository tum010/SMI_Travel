/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.view.dao.PostSaleVatDao;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import java.util.List;

/**
 *
 * @author Jittima
 */
public class PostSaleVatService {
    private PostSaleVatDao postSaleVatDao;

    public List<OutputTaxView> SearchOutputTaxViewFromFilter(String from,String to,String department,String status){
        return postSaleVatDao.SearchOutputTaxViewFromFilter(from, to, department, status);
    }
    
    public String UpdateOutputTaxStatus(List<OutputTaxView> outputTaxViews){
        return postSaleVatDao.UpdateOutputTaxStatus(outputTaxViews);
    }
        
    public PostSaleVatDao getPostSaleVatDao() {
        return postSaleVatDao;
    }

    public void setPostSaleVatDao(PostSaleVatDao postSaleVatDao) {
        this.postSaleVatDao = postSaleVatDao;
    }
    
}
