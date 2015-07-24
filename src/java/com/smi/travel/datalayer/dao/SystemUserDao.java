/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.SystemUser;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface SystemUserDao {
    public SystemUser getSystemUser(SystemUser user);
    public int insertSystemUser(SystemUser user);
    public int updateSystemUser(SystemUser user);
    public List<SystemUser> searchSystemUser(SystemUser user,int option);
    public List<SystemUser> getGuildeList();
    public List<SystemUser> getDriverList();
    public List<SystemUser> getUserList();
}
