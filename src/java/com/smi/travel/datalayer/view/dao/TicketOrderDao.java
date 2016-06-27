/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.view.dao;

import com.smi.travel.datalayer.report.model.TicketOrder;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TicketOrderDao {
    public TicketOrder getTicketOrder(String refno,String pnrID);
    public TicketOrder getTicketOrderAddition(String refno);
}
