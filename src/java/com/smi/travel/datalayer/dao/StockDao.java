/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface StockDao {
    public String InsertStock(Stock ItemLot);
    public String UpdateStock(Stock ItemLot);
    public String DeleteStock(Stock ItemLot);
    public String DeleteStockDetail(String DetailID);
    public StockViewSummary SearchStockFromFilter(String productId,String payStatus,String itemStatus,Date createDate,Date EffecttiveFrom,Date EffectiveTo);
    public List<Product> getListStockProduct();
    public String getStockId(Stock stock);
    public Stock getStock(String stockId);
    public List<StockDetail> checkStockDetail(String stockId);
    public List<Stock> searchStock(String productId,String createDate,String EffecttiveFrom,String EffectiveTo,String expire);
    public StockViewSummary searchStockDetail(String productId,String payStatus,String itemStatus);
    public List<Stock> getStockById(String stockId);
}
