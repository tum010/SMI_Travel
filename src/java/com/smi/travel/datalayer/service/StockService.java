/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.StockDao;

/**
 *
 * @author Kanokporn
 */
public class StockService {
    private StockDao stockDao;

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public StockDao getStockDao() {
        return stockDao;
    }
    
    
}
