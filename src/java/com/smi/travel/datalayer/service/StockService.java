/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.StockDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class StockService {
    private StockDao stockDao;
    
    public List<Product> getListStockProduct(){
        return  stockDao.getListStockProduct();
    }
    
    public String InsertStock(Stock ItemLot){
        return  stockDao.InsertStock(ItemLot);
    }
    
    public String UpdateStock(Stock ItemLot){
        return  stockDao.UpdateStock(ItemLot);
    }
    public String DeleteStock(Stock ItemLot){
        return  stockDao.DeleteStock(ItemLot);
    }
    public String DeleteStockDetail(String DetailID){
        return  stockDao.DeleteStockDetail(DetailID);
    }
    
    public StockViewSummary SearchStockFromFilter(String productId,String payStatus,String itemStatus,Date createDate,Date EffecttiveFrom,Date EffectiveTo){
        return stockDao.SearchStockFromFilter(productId, payStatus, itemStatus, createDate, EffecttiveFrom, EffectiveTo);
    }

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public StockDao getStockDao() {
        return stockDao;
    }
    
    
}
