/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MTicketType;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface AirticketPnrDao {
    public AirticketPnr getAirticketDetail(String refno);
    public int insertAirticketPnr(AirticketPnr airPnr);
    public int UpdateAirticketPnr(AirticketPnr airPnr);
    public int importExistingAirticketPnr(AirticketPnr airPnr);
    public int cancelBookAirticketPNR(String PNRID);
    public int enableBookAirticketPNR(String PNRID);
    public int cancelBookAirticketFlight(String FlightID);
    public int enableBookAirticketFlight(String FlightID);
    public int deletePassenger(String airPassenger);
    public MFlight MappingFlightClass(String Flight);
    public MTicketType MappintTicketLife(String TicketLife);
    public String MappingTicketType(String TicketType);
    public List<String> getListPnrFromRefno(String Refno);
}
