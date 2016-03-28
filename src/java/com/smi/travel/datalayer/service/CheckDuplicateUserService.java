/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.CheckDuplicateUserDao;
import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;

/**
 *
 * @author Jittima
 */
public class CheckDuplicateUserService {
    private CheckDuplicateUserDao checkDuplicateUserDao;
    
    public CheckDuplicateUser CheckAndUpdateOperationDetail(CheckDuplicateUser checkDuplicateUser, int step){
        return checkDuplicateUserDao.CheckAndUpdateOperationDetail(checkDuplicateUser,step);
    }
    
    public int updateOperationNull(CheckDuplicateUser checkDuplicateUser){
        return checkDuplicateUserDao.updateOperationNull(checkDuplicateUser);
    }

    public CheckDuplicateUserDao getCheckDuplicateUserDao() {
        return checkDuplicateUserDao;
    }

    public void setCheckDuplicateUserDao(CheckDuplicateUserDao checkDuplicateUserDao) {
        this.checkDuplicateUserDao = checkDuplicateUserDao;
    }
}
