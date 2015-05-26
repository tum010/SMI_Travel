/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.TourOperationDao;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.TourOperationDesc;
import com.smi.travel.datalayer.entity.TourOperationDriver;
import com.smi.travel.datalayer.entity.TourOperationExpense;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class DaytourOperationService {
    private TourOperationDao tourOperationdao;
    
    public List<DaytourBooking> getTourJob(){
        return tourOperationdao.getTourJob();
    }
    
    public List<DaytourBooking>  getTourDetail(String TourID,String TourDate){
        return tourOperationdao.getTourDetail(TourID, TourDate);
    }
    
    public TourOperationDesc getTouroperation(String TourID,String TourDate) {
        return tourOperationdao.getTouroperation(TourID, TourDate);
    }
    
    public TourOperationDesc getTouroperation(){
        return null;
    }
    
    public String saveTourOperation(TourOperationDesc TourInfo, List<DaytourBooking> BookList,List<TourOperationDriver> DriverList) {
        if(TourInfo.getId() ==null){
          return  tourOperationdao.insertTourOperation(TourInfo, BookList,DriverList);
        }else{
          return  tourOperationdao.updateTourOperation(TourInfo, BookList,DriverList);
        }    
    }
    
    public String deleteBookDriver(String driverId) {
          return  tourOperationdao.deleteBookDriver(driverId);
    }
    public String deleteBookExpen(String expenId) {
          return  tourOperationdao.deleteBookExpen(expenId);
    }
    
    public List<DaytourBooking> SortBookOrder(List<DaytourBooking> data){
        return tourOperationdao.SortBookOrder(data);
    }
    
    public List<TourOperationDriver> SortDriver(List<TourOperationDriver> data) {
        return tourOperationdao.SortDriver(data);
    }
    
    public List<TourOperationExpense> SortExpense(List<TourOperationExpense> data) {
        return tourOperationdao.SortExpense(data);
    }
    
    public String[] calculatePassengerDaytour(List<DaytourBookingPrice> PriceList){
        return tourOperationdao.calculatePassengerDaytour(PriceList);
    }

    public TourOperationDao getTourOperationdao() {
        return tourOperationdao;
    }
    


    public void setTourOperationdao(TourOperationDao tourOperationdao) {
        this.tourOperationdao = tourOperationdao;
    }
    
    
}
