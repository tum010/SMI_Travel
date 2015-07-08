/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.ProductDao;
import com.smi.travel.datalayer.dao.ProductDetailDao;
import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductDetail;
import com.smi.travel.datalayer.view.dao.ProductPriceDetailDao;
import com.smi.travel.datalayer.view.entity.ProductPriceDetail;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class ProductService {
    private ProductDao productDao;
    private ProductDetailDao productdetailDao;
    private MListItemDao listItemDao;
    private ProductPriceDetailDao productPriceDetailDao;
    
    public String validateProduct(Product Vproduct, String operation) {
        String validate = "";
        Product product = new Product();
        product.setName(Vproduct.getName());
        product.setCode(Vproduct.getCode());
        List<Product> list = productDao.validateProduct(product);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                System.out.println(list.get(0).getId() +" , "+Vproduct.getId());
                if (!(list.get(0).getId().equalsIgnoreCase(Vproduct.getId()))) {
                    validate = "product code already exist";
                }
            } else {
                validate = "product code already exist";
            }

        }
        product.setName(Vproduct.getName());
        product.setCode(null);
        list = productDao.searchProduct(product, 1);
        if (list != null) {
            if ("update".equalsIgnoreCase(operation)) {
                if (!(list.get(0).getId().equalsIgnoreCase(Vproduct.getId()))) {
                    validate = "product name already exist";
                }
            } else {
                validate = "product name already exist";
            }
        }
        
        return validate;
    }
    
    public Product getProductFromcode(String productCode){
        return productDao.getProductFromCode(productCode);
    }
    
    
    
    public List<ProductPriceDetail> getListProductPriceDetail(ProductPriceDetail productPriceDetail,int option){
        return productPriceDetailDao.getListProductPriceDetail(productPriceDetail, option);
    }
    
    public ProductPriceDetail getListProductPriceDetailFromID(String productID){
        return productPriceDetailDao.getListProductPriceDetailFromID(productID);
    }
    
    public List<Product> searchProduct(Product product, int option) {
        return productDao.searchProduct(product, option);
    }
    
    public int insertProduct(Product product) {
        return productDao.insertProduct(product);
    }

    
    public int updateProduct(Product product) {
        return productDao.updateProduct(product);
    }
    
    public int deleteProduct(Product product) {
        return productDao.DeleteProduct(product);
    }
    
    public int insertProductDetail(ProductDetail priceitem) {
        return productdetailDao.insertProductDetail(priceitem);
    }
    
    public int updateProductDetail(ProductDetail priceitem) {
        return productdetailDao.updateProductDetail(priceitem);
    }
    
    public int deleteProductDetail(ProductDetail priceitem) {
        return productdetailDao.DeleteProductDetail(priceitem);
    }
    
    public Product getProductFromID(String ProductID){
        return productDao.getProductFromID(ProductID);
    }
    
    public List<MProductType> getListMasterProductType() {
        return listItemDao.getListMasterProductType();
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public ProductDetailDao getProductdetailDao() {
        return productdetailDao;
    }

    public void setProductdetailDao(ProductDetailDao productdetailDao) {
        this.productdetailDao = productdetailDao;
    }

    public MListItemDao getListItemDao() {
        return listItemDao;
    }

    public void setListItemDao(MListItemDao listItemDao) {
        this.listItemDao = listItemDao;
    }

    public ProductPriceDetailDao getProductPriceDetailDao() {
        return productPriceDetailDao;
    }

    public void setProductPriceDetailDao(ProductPriceDetailDao productPriceDetailDao) {
        this.productPriceDetailDao = productPriceDetailDao;
    }
    
    
    
}
