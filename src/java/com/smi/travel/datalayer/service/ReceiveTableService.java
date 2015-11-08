/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.ReceiveTableDao;
import com.smi.travel.datalayer.entity.AdvanceReceive;
import com.smi.travel.datalayer.entity.AdvanceReceiveCredit;
import com.smi.travel.datalayer.entity.AdvanceReceivePeriod;
import com.smi.travel.datalayer.view.entity.AdvanceReceivePeriodView;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public class ReceiveTableService {
    
    private ReceiveTableDao receiveTableDao;

    public ReceiveTableDao getReceiveTableDao() {
        return receiveTableDao;
    }

    public void setReceiveTableDao(ReceiveTableDao receiveTableDao) {
        this.receiveTableDao = receiveTableDao;
    }

    public List<AdvanceReceive> searchAdvanceReceive(String inputDate, String selectStatus, String option) {
        return this.receiveTableDao.searchAdvanceReceive(inputDate,selectStatus,option);
    }

    public String deleteAdvanceReceive(AdvanceReceive advanceReceive) {
        return this.receiveTableDao.deleteAdvanceReceive(advanceReceive);
    }

    public String saveReceiveTable(AdvanceReceive advanceReceive) {
        if(("".equalsIgnoreCase(advanceReceive.getId())) || (advanceReceive.getId() == null)){
            return this.receiveTableDao.insertAdvanceReceive(advanceReceive);
        }else{
            return this.receiveTableDao.updateAdvanceReceive(advanceReceive);
        }                
    }

    public String deleteAdvanceReceiveCredit(AdvanceReceiveCredit advanceReceiveCredit, String option) {
        return this.receiveTableDao.deleteAdvanceReceiveCredit(advanceReceiveCredit,option);
    }

    public AdvanceReceivePeriod getReceivePeriod(String receiveDate) {
        return this.receiveTableDao.getReceivePeriod(receiveDate);
    }

    public AdvanceReceivePeriodView getAdvanceReceivePeriodView(String from, String to) {
        return this.receiveTableDao.getAdvanceReceivePeriodView(from,to);
    }
    
}
