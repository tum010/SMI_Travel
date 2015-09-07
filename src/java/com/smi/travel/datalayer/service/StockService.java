/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.StockDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class StockService {
    private StockDao stockDao;
    
    public String getStockId(Stock stock){
        return stockDao.getStockId(stock);
    }
    
    public List<Product> getListStockProduct(){
        return  stockDao.getListStockProduct();
    }
    
    public String saveStock(Stock ItemLot){
        if(ItemLot.getId() == null){
            return stockDao.InsertStock(ItemLot);
        }else{
            return stockDao.UpdateStock(ItemLot);
        }
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
    
    public Stock getStock(String stockId){
        return stockDao.getStock(stockId);
    }
    
    public List<StockDetail> checkStockDetail(String stockId){
        return stockDao.checkStockDetail(stockId);
    }
    
    public List<Stock> searchStock(String productId,String createDate,String EffecttiveFrom,String EffectiveTo,String expire){
        return stockDao.searchStock(productId, createDate, EffecttiveFrom, EffectiveTo,expire);
    }
    
    public StockViewSummary searchStockDetail(String productId,String payStatus,String itemStatus){
        return stockDao.searchStockDetail(productId, payStatus,itemStatus);
    }
    
    public List<Stock> getStockById(String stockid){
        return stockDao.getStockById(stockid);
    }
}
