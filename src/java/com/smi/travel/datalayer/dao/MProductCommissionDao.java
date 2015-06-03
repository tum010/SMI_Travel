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
    public Product getListProductCommissionFromID();
    public String insertProductCommission(ProductComission ProCom);
    public String updateProductCommission(ProductComission ProCom);
    public String DeleteProductComission(ProductComission AgentCom);
}
