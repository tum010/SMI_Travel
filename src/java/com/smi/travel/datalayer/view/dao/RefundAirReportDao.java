/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.RefundAirReport;
import java.util.List;
/**
 *
 * @author chonnasith
 */
public interface RefundAirReportDao {
    public List getRefundAir(String refundId);
    public List getRefundTicketDetail(String refundagent,String refundnameby,String passenger,String receivefrom,String receiveto,String paidfrom,String paidto,String typeprint);
}
