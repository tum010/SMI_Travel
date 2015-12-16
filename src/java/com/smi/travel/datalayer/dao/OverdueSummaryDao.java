/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.dao;

import java.util.List;

/**
 *
 * @author Kanokporn
 */
public interface OverdueSummaryDao {
    public List listOverdueSummary(String clientcode,String clientname,String staffcode,String staffname,String vattype,String from,String to,String depart,String group,String view,String printby);
}
