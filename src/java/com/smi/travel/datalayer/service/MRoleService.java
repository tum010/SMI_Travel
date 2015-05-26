/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.RoleDao;
import com.smi.travel.datalayer.dao.RoleMappingDao;
import com.smi.travel.datalayer.entity.Function;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MRoleService {

    private RoleDao roleDao;
    private RoleMappingDao rolemappingDao;
    private MListItemDao listItemDao;

    public List<Role> getRole() {
        return roleDao.getRole();
    }

    public int insertRole(Role role) {
        return roleDao.insertRole(role);
    }

    public int updateRole(Role role) {
        return roleDao.updateRole(role);
    }

    public int DeleteRole(Role role) {
        if (CheckUsabilityRole(role)) {
            return 2;
        } else {
            DeleteRolePrivilege(role);
        }
        return roleDao.DeleteRole(role);
    }

    public Role getroleFromID(String roleid) {
        return roleDao.getRoleFromID(roleid);
    }

    public boolean CheckUsabilityRole(Role role) {
        return roleDao.CheckUsabilityRole(role);
    }

    public List<Role> SerchRole(String rolename) {
        return roleDao.SerchRole(rolename);
    }
    
 

    public int insertRolePrivilege(RoleMapping RoleMap, List<Function> funclist) {
        return rolemappingDao.insertRolePrivilege(RoleMap, funclist);
    }

    public int DeleteRolePrivilege(Role role) {
        return rolemappingDao.DeleteRolePrivilege(role);
    }

    public List<Function> getRolePrivilege(String RoleID) {
        return rolemappingDao.getRolePrivilege(RoleID);
    }

    public List<Function> getListFunction() {
        return listItemDao.getListFunction();
    }

    public String ValidateRoleName(Role Vrole,String operation) {
        String validate = "";
        Role role = new Role();
        role.setName(Vrole.getName());
        List<Role> list = roleDao.SerchRole(role.getName());
        if(list != null){
            if("update".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(Vrole.getId()))){
                    validate = "role name already exist";
                }
            }else{
                 validate = "role name already exist";
            }
           
        }
        return validate;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleMappingDao getRolemappingDao() {
        return rolemappingDao;
    }

    public void setRolemappingDao(RoleMappingDao rolemappingDao) {
        this.rolemappingDao = rolemappingDao;
    }

    public MListItemDao getListItemDao() {
        return listItemDao;
    }

    public void setListItemDao(MListItemDao mListItemDao) {
        this.listItemDao = mListItemDao;
    }

}
