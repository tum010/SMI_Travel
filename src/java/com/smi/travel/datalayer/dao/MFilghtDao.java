/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MFlightservice;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface MFilghtDao {
    public List<MFlight> getListFlight(MFlight filght,int option);
    public List<MFlightservice> getListFlightService(String mFlightId);
    public int insertFlight(MFlight filght);
    public int updateFlight(MFlight filght,List<String> id);
    public int DeleteFlight(MFlight filght);
}
