/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MProductType;
import java.util.List;

/**
 *
 * @author Thitikul
 */
public interface MProductTypeDao {
    public List<MProductType> getListProductType(MProductType productType,int option);
    public List<MProductType> getListProductType();
    public int insertProductType(MProductType productType);
    public int updateProductType(MProductType productType);
    public int DeleteProductType(MProductType productType);
}
