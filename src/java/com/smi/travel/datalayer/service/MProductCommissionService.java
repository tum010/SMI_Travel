/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.ProductDao;
import com.smi.travel.datalayer.entity.Product;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MProductCommissionService {
    private ProductDao productDao;
    public List<Product> getListMasterProduct(){
        return productDao.getListProduct();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
    
    
}
