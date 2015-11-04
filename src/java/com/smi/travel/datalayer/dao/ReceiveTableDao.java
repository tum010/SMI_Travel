/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AdvanceReceive;
import com.smi.travel.datalayer.entity.AdvanceReceiveCredit;
import com.smi.travel.datalayer.entity.AdvanceReceivePeriod;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public interface ReceiveTableDao {

    public List<AdvanceReceive> searchAdvanceReceive(String inputDate, String selectStatus, String option);
    public String deleteAdvanceReceive(AdvanceReceive advanceReceive);
    public String insertAdvanceReceive(AdvanceReceive advanceReceive);
    public String updateAdvanceReceive(AdvanceReceive advanceReceive);
    public String deleteAdvanceReceiveCredit(AdvanceReceiveCredit advanceReceiveCredit, String option);
    public String saveReceivePeriod(String periodId, String fromDate, String toDate, String detail);
    public String checkReceivePeriod(String periodId, String fromDate, String toDate);
    public AdvanceReceivePeriod getReceivePeriod(String receiveDate);
   
}
