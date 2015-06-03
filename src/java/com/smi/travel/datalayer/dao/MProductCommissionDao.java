/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MProductCommissionDao {
    public List<ProductComission> SearchProductComission(String code,String name, int option);
    public List<ProductComission> getListProductCommissionFromID(String ProductID);
    public String insertProductCommission(Product ProCom);
    public String updateProductCommission(Product ProCom);
    public String DeleteProductComission(ProductComission ProCom);
    public String DeleteAllProductComission(Product ProCom);
}
