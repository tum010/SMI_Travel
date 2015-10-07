/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AdvanceReceive;
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
   
}
