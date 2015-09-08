/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao.impl;

import com.smi.travel.datalayer.view.dao.ARNirvanaDao;
import com.smi.travel.datalayer.view.entity.ARNirvana;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class ARNirvanaImpl implements  ARNirvanaDao{

    @Override
    public List<ARNirvana> SearchArNirvanaFromFilter(String invtype, String department, String billtype, String from, String to, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ExportARFileInterface(List<ARNirvana> APList) {
        return "success";
    }

    @Override
    public String UpdateStatusARInterface(List<ARNirvana> APList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
