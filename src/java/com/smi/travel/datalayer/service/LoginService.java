/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.SystemUser;

/**
 *
 * @author Surachai
 */
public class LoginService {
    private SystemUserDao systemUserDao;
    
    
    public SystemUser getSystemUser(SystemUser user) {
        return systemUserDao.getSystemUser(user);
    }

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

   
    
    
}
