/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;
import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.MDepartment;
import com.smi.travel.datalayer.entity.SystemUser;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Surachai
 */
public class MStaffService {
    private SystemUserDao systemUserDao;
    private MListItemDao ListItemDao;
    
    public List<SystemUser> searchSystemUser(SystemUser user,int option) {
        return systemUserDao.searchSystemUser(user,option);
    }
    
    public String validateSystemUser(SystemUser Vuser,String operation){
        String validate = "";
        SystemUser user = new SystemUser();
        user.setUsername(Vuser.getUsername());
        List<SystemUser> list = systemUserDao.searchSystemUser(user,1);
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
              
                if(!(list.get(0).getId().equalsIgnoreCase(Vuser.getId()))){
                    validate = "username already exist";
                }
            }else{
                 validate = "username already exist";
            }
           
        }
      
        return validate;
    }
    
    public List<MDepartment> getListDepartment() {
        return ListItemDao.getListDepartment();
    }
    
    public List<SystemUser> getListStaff(){
        return systemUserDao.getUserList();
    }
    
    public int insertSystemUser(SystemUser user) {
        Date creteDate = new Date();
        user.setCreateDate(creteDate);
        return systemUserDao.insertSystemUser(user);
    }
    
    public int updateSystemUser(SystemUser user) {
        return systemUserDao.updateSystemUser(user);
    }    

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    public MListItemDao getListItemDao() {
        return ListItemDao;
    }

    public void setListItemDao(MListItemDao ListItemDao) {
        this.ListItemDao = ListItemDao;
    }
    
    
    
    
}
