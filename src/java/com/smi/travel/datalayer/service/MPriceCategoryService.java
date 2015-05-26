/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MPriceCategoryDao;
import com.smi.travel.datalayer.entity.MPricecategory;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MPriceCategoryService {
    private MPriceCategoryDao mPriCategoryDao;
    
    public List<MPricecategory> searchPrice(MPricecategory price,int option) {
        return mPriCategoryDao.getListPrice(price,option);
    }
    
    public String validatePrice(MPricecategory Vprice,String operation){
        String validate = "";
        MPricecategory price = new MPricecategory();
        price.setCode(Vprice.getCode());
        List<MPricecategory> list = mPriCategoryDao.getListPrice(price,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vprice.getId()))){
                    validate = "price code already exist";
                }
            }else{
                 validate = "price code already exist";
            }
           
        }
        price.setName(Vprice.getName());
        price.setCode(null);
        list = mPriCategoryDao.getListPrice(price,1);
        if(list != null){      
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vprice.getId()))){
                    validate = "price name already exist";
                }
            }else{
                 validate = "price name already exist";
            }
        }
        return validate;
    }

    public int insertPrice(MPricecategory price) {

        return mPriCategoryDao.insertPricecategory(price);
    }

    public int UpdatePrice(MPricecategory price) {
        return mPriCategoryDao.updatePricecategory(price);
    }

    public int DeletePrice(MPricecategory price) {
        return mPriCategoryDao.DeletePricecategory(price);
    }

    public MPriceCategoryDao getmPriCategoryDao() {
        return mPriCategoryDao;
    }

    public void setmPriCategoryDao(MPriceCategoryDao mPriCategoryDao) {
        this.mPriCategoryDao = mPriCategoryDao;
    }
    
    
}
