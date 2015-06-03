/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MProductCommissionDao;
import com.smi.travel.datalayer.dao.ProductDao;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MProductCommissionService {
    private ProductDao productDao;
    private MProductCommissionDao mProductCommissiondao;
    public List<Product> getListMasterProduct(){
        return productDao.getListProduct();
    }
    
    public List<ProductComission> SearchProductComission(String code, String name, int option) {
        return  mProductCommissiondao.SearchProductComission(code, name, option);
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public MProductCommissionDao getmProductCommissiondao() {
        return mProductCommissiondao;
    }

    public void setmProductCommissiondao(MProductCommissionDao mProductCommissiondao) {
        this.mProductCommissiondao = mProductCommissiondao;
    }
    
    
    
    
}
