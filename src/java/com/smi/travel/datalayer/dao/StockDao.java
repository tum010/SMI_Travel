/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.view.entity.StockViewSummary;
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
    public StockViewSummary SearchStockFromFilter(Stock stockData);
    public List<Product> getListStockProduct();
}
