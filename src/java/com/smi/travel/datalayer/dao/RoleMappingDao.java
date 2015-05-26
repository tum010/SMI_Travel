/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Function;
import com.smi.travel.datalayer.entity.Role;
import com.smi.travel.datalayer.entity.RoleMapping;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface RoleMappingDao {
    public int insertRolePrivilege(RoleMapping map,List<Function> functionList);
    public List<Function> getRolePrivilege(String RoleID);
    public int DeleteRolePrivilege(Role role);
    
}
