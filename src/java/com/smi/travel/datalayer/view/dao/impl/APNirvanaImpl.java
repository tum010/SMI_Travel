/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.APNirvanaDao;
import com.smi.travel.datalayer.view.entity.APNirvana;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class APNirvanaImpl implements APNirvanaDao{

    @Override
    public String ExportAPFileInterface(List<APNirvana> APList) {
        return "success";
    }

    @Override
    public String UpdateStatusAPInterface(List<APNirvana> APList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<APNirvana> SearchApNirvanaFromFilter(String paymentType, String producttype, String status, String from, String to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
