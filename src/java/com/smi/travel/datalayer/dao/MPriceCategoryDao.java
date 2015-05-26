/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MPricecategory;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MPriceCategoryDao {
    public List<MPricecategory> getListPrice(MPricecategory Price,int option);
    public int insertPricecategory(MPricecategory Price);
    public int updatePricecategory(MPricecategory Price);
    public int DeletePricecategory(MPricecategory Price);
}
