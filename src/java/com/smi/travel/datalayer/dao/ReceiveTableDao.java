/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AdvanceReceive;
import com.smi.travel.datalayer.entity.AdvanceReceiveCredit;
import com.smi.travel.datalayer.entity.AdvanceReceivePeriod;
import com.smi.travel.datalayer.view.entity.AdvanceReceivePeriodView;
import java.util.List;

/**
 *
 * @author chonnasith
 */
public interface ReceiveTableDao {

    public List<AdvanceReceive> searchAdvanceReceive(String inputDate, String selectStatus, String department, String option);
    public String deleteAdvanceReceive(AdvanceReceive advanceReceive);
    public String insertAdvanceReceive(AdvanceReceive advanceReceive);
    public String updateAdvanceReceive(AdvanceReceive advanceReceive);
    public String deleteAdvanceReceiveCredit(AdvanceReceiveCredit advanceReceiveCredit, String option);
    public String saveReceivePeriod(String periodId, String fromDate, String toDate, String detail, String department, String vatType, AdvanceReceivePeriodView advanceReceivePeriodView);
    public String checkReceivePeriod(String periodId, String fromDate, String toDate, String department, String vatType);
    public AdvanceReceivePeriodView getAdvanceReceivePeriodView(String from, String to, String department, String vatType);
    public AdvanceReceivePeriod getReceivePeriod(String receiveDate, String department, String vatType);
    public String updateReceivePeriod(String periodId, String fromDate, String toDate, String vatType, String periodDetail);
    public List getCollectionReport(String receiveDate, String vatType, String department, String printBy);
    public List<AdvanceReceivePeriod> getReceivePeriodList(String department);
    public AdvanceReceiveCredit testStoredProcedure(String agentName);
    public String deleteReceivePeriod(String periodId);
    public String compareReceiptSummary(AdvanceReceivePeriod advanceReceivePeriod, AdvanceReceivePeriodView advanceReceivePeriodView);
   
}
