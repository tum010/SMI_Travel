/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.AgentDao;
import com.smi.travel.datalayer.dao.AirticketBookingDao;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.dao.PlaceDao;
import com.smi.travel.datalayer.dao.ProductDao;
import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MFlight;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MMeal;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.entity.MTicketType;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.SystemUser;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Surachai
 */
public class UtilityService {

    private MasterDao masterdao;
    private MListItemDao listitemdao;
    private AirticketBookingDao airticketdao;
    private CustomerDao customerdao;
    private AgentDao agentdao;
    private SystemUserDao systemUserdao;
    private PlaceDao placedao;
    private DaytourDao daytourdao;
    
    public int[] getCountItemFromBooking(String refno) {
        int[] Booking_size = new int[7];
        try {
            Master data = masterdao.getBookingFromRefno(refno);
            Booking_size[0] = airticketdao.getNumberOfFlight(refno);
            Booking_size[1] = data.getHotelBookings().size();
            Booking_size[2] = data.getOtherBookings().size();
            Booking_size[3] = data.getLandBookings().size();
            Booking_size[4] = data.getPassengers().size();
            Booking_size[5] = data.getBillables().size();
            Booking_size[6] = data.getDaytourBookings().size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return Booking_size;
    }

    public Master getbookingFromRefno(String refno) {
        return masterdao.getBookingFromRefno(refno);
    }

    public List<MItemstatus> getListMItemstatus() {
        return listitemdao.getListMItemstatus();
    }

    public MItemstatus getMItemstatusFromName(String name) {
        return listitemdao.getMItemstatusFromName(name);
    }

    public List<MCurrency> getListMCurrency() {
        return listitemdao.getListMCurrency();
    }

    public List<MBookingstatus> getListMBookingstatus() {
        return listitemdao.getListMBookingstatus();
    }

    public MBookingstatus getMBookingstatusFromName(String name) {
        return listitemdao.getMBookingstatusFromName(name);
    }

    public List<MInitialname> getListMInitialname() {
        return listitemdao.getListMInitialname();
    }

    public MInitialname getMInitialnameFromId(String id) {
        return listitemdao.getMInitialnameFromId(id);
    }

    public MInitialname getMInitialnameFromName(String name) {
        return listitemdao.getMInitialnameFromName(name);
    }

    public List<MPricecategory> getListMPricecategory() {
        return listitemdao.getListMPricecategory();
    }

    public MPricecategory getMPricecategoryFromCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        return listitemdao.getMPricecategoryFromCode(code);
    }

    public List<MFlight> getListMFlightClass() {
        return listitemdao.getListMFlightClass();
    }

    public List<MTicketType> getListMTicketType() {
        return listitemdao.getListMTicketType();
    }

    public List<MMeal> getListMMeal() {
        return listitemdao.getListMMeal();
    }

    public MMeal getMMealFromName(String name) {
        return listitemdao.getMMealFromName(name);
    }

    public List<Customer> getListCustomer() {
        return customerdao.getListCustomer();
    }

    public List<Agent> getListAgent() {
        return agentdao.getListAgent();
    }
    
    public  List<Daytour> getListDaytour(){
        return daytourdao.getTourListActive();
    }
    
    public List<MAccpay> getListMAccpay() {
        return listitemdao.getListMAccpay();
    }

    public List<MAccterm> getListMAccterm() {
        return listitemdao.getListMAccterm();
    }

    public MBilltype getMBilltypeFromName(String name) {
        return listitemdao.getMBilltypeFromName(name);
    }
    
    public List<SystemUser> getGuildeList() {
        return systemUserdao.getGuildeList();
    }
    
    public List<SystemUser> getDriverList() {
        return systemUserdao.getDriverList();
    }
    
    public List<Place> getPickupList(){
        return placedao.getListPlaceFromStatus("active");
    }

    public MasterDao getMasterdao() {
        return masterdao;
    }

    public void setMasterdao(MasterDao masterdao) {
        this.masterdao = masterdao;
    }

    public MListItemDao getListitemdao() {
        return listitemdao;
    }

    public void setListitemdao(MListItemDao listitemdao) {
        this.listitemdao = listitemdao;
    }

    public AirticketBookingDao getAirticketdao() {
        return airticketdao;
    }

    public void setAirticketdao(AirticketBookingDao airticketdao) {
        this.airticketdao = airticketdao;
    }

    public CustomerDao getCustomerdao() {
        return customerdao;
    }

    public void setCustomerdao(CustomerDao customerdao) {
        this.customerdao = customerdao;
    }

    public AgentDao getAgentdao() {
        return agentdao;
    }

    public void setAgentdao(AgentDao agentdao) {
        this.agentdao = agentdao;
    }

    public SystemUserDao getSystemUserdao() {
        return systemUserdao;
    }

    public void setSystemUserdao(SystemUserDao systemUserdao) {
        this.systemUserdao = systemUserdao;
    }

    public PlaceDao getPlacedao() {
        return placedao;
    }

    public void setPlacedao(PlaceDao placedao) {
        this.placedao = placedao;
    }

    public DaytourDao getDaytourdao() {
        return daytourdao;
    }

    public void setDaytourdao(DaytourDao daytourdao) {
        this.daytourdao = daytourdao;
    }
    
    

}
