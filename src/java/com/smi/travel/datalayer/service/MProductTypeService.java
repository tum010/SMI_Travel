/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MProductTypeDao;
import com.smi.travel.datalayer.entity.MProductType;
import java.util.List;
/**
 *
 * @author Thitikul
 */
public class MProductTypeService {
    
    private MProductTypeDao mProductTypeDao;

    public List<MProductType> getlistProductType(MProductType productType,int option) {
        return mProductTypeDao.getListProductType(productType,option);
    }
    
    public List<MProductType> getlistProductType() {
        return mProductTypeDao.getListProductType();
    }
    
    public String validateProductType(MProductType vProductType,String operation){
        String validate = "";
        MProductType productType = new MProductType();
        List<MProductType> list = mProductTypeDao.getListProductType(productType,1);
        
        productType.setName(vProductType.getName());
        list = mProductTypeDao.getListProductType(productType,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(vProductType.getId()))){
                    validate = "product type name already exist";
                }
            }else{
                 validate = "product type name already exist";
            }
        }
        return validate;
    }

    public int insertProductType(MProductType productType) {

        return mProductTypeDao.insertProductType(productType);
    }

    public int updateProductType(MProductType productType) {
        return mProductTypeDao.updateProductType(productType);
    }

    public int deleteProductType(MProductType productType) {
        return mProductTypeDao.DeleteProductType(productType);
    }

    public MProductTypeDao getmProductTypeDao() {
        return mProductTypeDao;
    }

    public void setmProductTypeDao(MProductTypeDao mProductTypeDao) {
        this.mProductTypeDao = mProductTypeDao;
    }
}
