/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Passenger;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface PassengerDao {
    public List<Passenger> getPassengerFromRefno(String refno);
    public Passenger getPassengerFromID(String PassengerID);
    public String saveBookingPassenger(Passenger passenger,String cuscode);
    public String saveFamilyleader(Passenger passenger,String cuscode);    
    public int DeletePassenger(Passenger passenger);    
    public List<Passenger> checkPassenger(Passenger passenger);
    
}
