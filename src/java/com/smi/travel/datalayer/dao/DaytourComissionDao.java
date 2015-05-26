/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.DaytourBooking;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface DaytourComissionDao {
    public List<DaytourBooking> getListBookingDaytourComission(String StartDate,String EndDate,String agentID,String guideID);
    public String SaveDaytourComission(List<DaytourBooking> BookList);
    public Double GetGuideComissionFromTour(String Tourcode);
    public Double GetAgentComissionFromTour(String Agentcode,String Tourcode,String Tourdate);
}
