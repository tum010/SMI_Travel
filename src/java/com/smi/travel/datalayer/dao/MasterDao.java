/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import java.util.List;
/**
 *
 * @author Surachai
 */
public interface MasterDao {
    public List<Master> getListBooking();
    public List<Master> getListBookingFromID(String masterid);
    public Master getBookingFromRefno(String refno);
    public int insertBooking(Master master,Passenger passenger);
    public int updateBooking(Master master,Passenger passenger);
    public int getMaxRefno();
    public int LockAndUnLockBooking(String MasterID,int[] Flag);
    public int[] getBookStatusFromRefno(String Refno);

}
