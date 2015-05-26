/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;


import com.smi.travel.datalayer.entity.ProductDetail;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface ProductDetailDao {
    public List<ProductDetail> getListProductDetail(String ProductId);
    public int insertProductDetail(ProductDetail productDetail);
    public int updateProductDetail(ProductDetail productDetail);
    public int DeleteProductDetail(ProductDetail productDetail);
    public ProductDetail getValueFromProduct(String productID);
}
