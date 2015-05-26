/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface RoleDao {
    public List<Role> getRole();
    public Role getRoleFromID(String roleid);
    public int insertRole(Role role);
    public int updateRole(Role role);
    public int DeleteRole(Role role);
    public boolean CheckUsabilityRole(Role role);
     public List<Role> SerchRole(String rolename);
    
}
