/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;

/**
 *
 * @author Jittima
 */
public interface CheckDuplicateUserDao {
    public CheckDuplicateUser CheckAndUpdateOperationDetail(CheckDuplicateUser checkDuplicateUser,int step);
    public int updateOperationNull(CheckDuplicateUser checkDuplicateUser); 
}
