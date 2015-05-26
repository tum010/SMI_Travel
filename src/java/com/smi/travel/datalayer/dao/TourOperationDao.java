/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.entity.TourOperationDriver;
import com.smi.travel.datalayer.entity.TourOperationExpense;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TourOperationDao {
    public List<DaytourBooking> getTourJob();
    public List<DaytourBooking>  getTourDetail(String TourID,String TourDate);
    public TourOperationDesc getTouroperation(String TourID,String TourDate);
    public String insertTourOperation(TourOperationDesc TourInfo,List<DaytourBooking> BookList,List<TourOperationDriver> DriverList);
    public String updateTourOperation(TourOperationDesc TourInfo,List<DaytourBooking> BookList,List<TourOperationDriver> DriverList);
    public String deleteBookDriver(String driverId);
    public String deleteBookExpen(String expenId);
    public List<DaytourBooking> SortBookOrder(List<DaytourBooking> data);
    public String[] calculatePassengerDaytour(List<DaytourBookingPrice> DriverList);
    public List<TourOperationDriver> SortDriver(List<TourOperationDriver> data);
    public List<TourOperationExpense> SortExpense(List<TourOperationExpense> data);
}
