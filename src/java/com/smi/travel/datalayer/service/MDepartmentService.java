/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;
import com.smi.travel.datalayer.dao.DepartmentDao;
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
public class MDepartmentService {
    private DepartmentDao departmentDao;
    
    public List<MDepartment> getDepartment() {
        return departmentDao.getDepartment();
    }

    public MDepartment getDepartmentById(String id) {
        return departmentDao.getDepartmentFromId(id);
    }
    public DepartmentDao getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }
    
    
}
