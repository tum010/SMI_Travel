/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.AgentDao;
import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.LandBookingDao;
import com.smi.travel.datalayer.dao.MasterDao;
import com.smi.travel.datalayer.dao.PassengerDao;
import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.dao.BookingSummaryDao;
import com.smi.travel.datalayer.view.entity.BookSummary;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class BookingDetailService {

    private AgentDao agentdao;
    private CustomerDao customerdao;
    private PassengerDao passengerdao;
    private MasterDao masterdao;
    private BookingSummaryDao bookingsummarydao;
    private SystemUserDao systemUserDao;
    private LandBookingDao landbookingdao;
    private static final String OUTBOUNDTYPE = "2";

    public Master getBookingDetailFromRefno(String Refno) {
        return masterdao.getBookingFromRefno(Refno);
    }

    public List<Agent> getListAgent() {
        return agentdao.getListAgent();
    }
    
    public Agent getAgentByName(String name){
        return agentdao.getAgentByName(name);
    }

    public List<Customer> getListCustomer() {
        return customerdao.getListCustomer();
    }
    
    public List<PackageTour> getListLandPackage(){
        return landbookingdao.getListLandPackage();
    }

    public int saveBookingDetail(Master master,Passenger passenger, SystemUser user) {
        int result = 0;
        String firstCharName = "";
        String customerCode = "";
        
        if(master.getCustomer() == null){
            System.out.println("customer null");
        }
        if((master.getCustomer() != null) && (master.getCustomer().getFirstName() != null)){
            System.out.println("firstCharName :"+firstCharName);
            firstCharName =   master.getCustomer().getFirstName().substring(0, 1);
        }
        
        if((master.getCustomer() != null) &&(master.getCustomer().getId() == null)){
            customerCode = customerdao.generateCustomerCode(firstCharName);
            System.out.println("customerCode :"+customerCode);
            master.getCustomer().setCode(customerCode);
        }

        
        if (master.getReferenceNo() != null) {
            result = masterdao.updateBooking(master,passenger);
        } else {
            int refno = masterdao.getMaxRefno();
            master.setCreateBy(user.getUsername());
            List<SystemUser> users = systemUserDao.searchSystemUser(user, 1);
            master.setStaff(users.get(0));
            master.setFlagAir(0);
            master.setFlagDaytour(0);
            master.setFlagHotel(0);
            master.setFlagOther(0);
            master.setFlagLand(0);
            if (user.getMDepartment().getId().equalsIgnoreCase(OUTBOUNDTYPE)) {
                master.setBookingType("O");
            } else {
                master.setBookingType("I");
            }
            master.setReferenceNo(String.valueOf(refno + 1));
            
            if(master.getId() != null){
                System.out.println("master update");
                result = masterdao.updateBooking(master,passenger);
                
            }else{
                System.out.println("master insert");
                result = masterdao.insertBooking(master,passenger);
            }
            if(result == 1){
                result = refno +1;
            }
            
        }
        return result;
    }

    public List<BookSummary> getListBookSummary(String refno) {
        return bookingsummarydao.getListBookSummary(refno);
    }

    public String insertFamilyLeader(Passenger passenger) {
        String firstCharName = "";
        if(passenger.getCustomer() == null){
            System.out.println("customer null");
        }
        if((passenger.getCustomer() != null) && (passenger.getCustomer().getFirstName() != null)){
            System.out.println("firstCharName :"+firstCharName);
            firstCharName =   passenger.getCustomer().getFirstName().substring(0, 1);
        }else{
            System.out.println("else : test ");
        }
          
        String customerCode = "";
        
        if((passenger.getCustomer() != null) &&(passenger.getCustomer().getId() == null)){
            
            customerCode = customerdao.generateCustomerCode(firstCharName);
            System.out.println("customerCode :"+customerCode);
        }
        System.out.println("saveFamilyleader");
        return passengerdao.saveFamilyleader(passenger,customerCode);
    }

    public AgentDao getAgentdao() {
        return agentdao;
    }

    public void setAgentdao(AgentDao agentdao) {
        this.agentdao = agentdao;
    }

    public CustomerDao getCustomerdao() {
        return customerdao;
    }

    public void setCustomerdao(CustomerDao customerdao) {
        this.customerdao = customerdao;
    }

    public MasterDao getMasterdao() {
        return masterdao;
    }

    public void setMasterdao(MasterDao masterdao) {
        this.masterdao = masterdao;
    }

    public BookingSummaryDao getBookingsummarydao() {
        return bookingsummarydao;
    }

    public void setBookingsummarydao(BookingSummaryDao bookingsummarydao) {
        this.bookingsummarydao = bookingsummarydao;
    }

    public SystemUserDao getSystemUserDao() {
        return systemUserDao;
    }

    public void setSystemUserDao(SystemUserDao systemUserDao) {
        this.systemUserDao = systemUserDao;
    }

    public PassengerDao getPassengerdao() {
        return passengerdao;
    }

    public void setPassengerdao(PassengerDao passengerdao) {
        this.passengerdao = passengerdao;
    }

    public LandBookingDao getLandbookingdao() {
        return landbookingdao;
    }

    public void setLandbookingdao(LandBookingDao landbookingdao) {
        this.landbookingdao = landbookingdao;
    }
    
    

}
