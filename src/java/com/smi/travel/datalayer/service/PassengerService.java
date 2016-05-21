/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.CustomerDao;
import com.smi.travel.datalayer.dao.PassengerDao;
import com.smi.travel.datalayer.entity.Passenger;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class PassengerService {
    private PassengerDao passengerdao;
    private CustomerDao customerdao;

    public List<Passenger> getPassengerFromRefno(String refno){
        return passengerdao.getPassengerFromRefno(refno);
    }
    
    public Passenger getPassengerFromID(String PassengerID){
        return passengerdao.getPassengerFromID(PassengerID);
    }
    
    public String saveBookingPassenger(Passenger passenger){
        String firstCharName = "";
//        if((passenger.getCustomer() != null) && (passenger.getCustomer().getCode() != null) && (!"".equalsIgnoreCase(passenger.getCustomer().getCode()))){
//            List<Passenger> passengerList = passengerdao.checkPassenger(passenger);
//            if(!passengerList.isEmpty()){
//                return "duplicate";
//            }
//        }       
        
        if((passenger.getCustomer() != null) && (passenger.getCustomer().getFirstName() != null)){
            firstCharName =   passenger.getCustomer().getFirstName().substring(0, 1);
        }
          
        String customerCode = "";

        if((passenger.getCustomer() != null) &&(passenger.getCustomer().getId() == null)){
            customerCode = customerdao.generateCustomerCode(firstCharName);
        }
        return passengerdao.saveBookingPassenger(passenger,customerCode);
    }
    
    public int DeletePassenger(Passenger passenger) {
        return passengerdao.DeletePassenger(passenger);
    }
    
    public PassengerDao getPassengerdao() {
        return passengerdao;
    }

    public void setPassengerdao(PassengerDao passengerdao) {
        this.passengerdao = passengerdao;
    }

    public CustomerDao getCustomerdao() {
        return customerdao;
    }

    public void setCustomerdao(CustomerDao customerdao) {
        this.customerdao = customerdao;
    }
    
    
        
        
    
}
