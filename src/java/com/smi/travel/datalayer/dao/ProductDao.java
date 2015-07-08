/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.view.entity.ProductPriceDetail;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface ProductDao {
    public List<Product> getListProduct();
    public List<Product> searchProduct(Product product,int option);
    public List<Product> validateProduct(Product product);
    public Product getProductFromID(String ProductID);
    public Product getProductFromCode(String ProductCode);
    public int insertProduct(Product product);
    public int updateProduct(Product product);
    public int DeleteProduct(Product product);
    public List<ProductPriceDetail> getListProductPriceDetail();
    
}
