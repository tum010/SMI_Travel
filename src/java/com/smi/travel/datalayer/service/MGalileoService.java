/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.MGalileoDao;
import com.smi.travel.datalayer.entity.MGalileo;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MGalileoService {
    private MGalileoDao mgalilroDao;
    public List<MGalileo> getGalileoList() {
        return mgalilroDao.getGalileoList();
    }
    
    public int EditGalileo(MGalileo galileo) {
         return mgalilroDao.EditGalileo(galileo);
    }

    public MGalileoDao getMgalilroDao() {
        return mgalilroDao;
    }

    public void setMgalilroDao(MGalileoDao mgalilroDao) {
        this.mgalilroDao = mgalilroDao;
    }
    
    
}
