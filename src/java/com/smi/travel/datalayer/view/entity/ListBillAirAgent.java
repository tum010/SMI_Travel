/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.view.entity;

import com.smi.travel.datalayer.report.model.BillAirAgent;
import java.util.List;

/**
 *
 * @author Kanokporn
 */
public class ListBillAirAgent {
    private List<BillAirAgentRefund> billAirAgentRefund;
    private List<BillAirAgent> billAirAgent;

    public List<BillAirAgentRefund> getBillAirAgentRefund() {
        return billAirAgentRefund;
    }

    public void setBillAirAgentRefund(List<BillAirAgentRefund> billAirAgentRefund) {
        this.billAirAgentRefund = billAirAgentRefund;
    }

    public List<BillAirAgent> getBillAirAgent() {
        return billAirAgent;
    }

    public void setBillAirAgent(List<BillAirAgent> billAirAgent) {
        this.billAirAgent = billAirAgent;
    }

    
}
