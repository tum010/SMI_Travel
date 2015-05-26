/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.DaytourBookingDao;
import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.OtherBooking;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class BookingDaytourService {
    private DaytourDao daytourdao;
    private DaytourBookingDao daytourBookingdao;
    private OtherBookingDao otherBookingdao;

    public List<Daytour> getTourList(){
        return daytourdao.getTourListActive();
    }
    
    public DaytourBooking getBookDetailDaytourFromID(String DaytourBookingID){
        return daytourBookingdao.getBookDetailDaytourFromID(DaytourBookingID);
    }
    
    public List<DaytourBooking> getListBookingDaytourFromRefno(String refno){
        return daytourBookingdao.getListBookingDaytourFromRefno(refno);
    }
    
    public String saveBookingDaytour(DaytourBooking daytourBook){
        if(daytourBook.getId() == null){
            //setup bill and item status
            MItemstatus status = new MItemstatus();
            status.setId("1");
            daytourBook.setIsBill(0);
            daytourBook.setMItemstatus(status);
            return daytourBookingdao.InsertBookingDaytour(daytourBook);
        }else{
            return daytourBookingdao.UpdateBookingDaytour(daytourBook);
        }
    }
    
    public int cancelBookDetailDaytour(String DaytourID) {
        return daytourBookingdao.cancelBookDetailDaytour(DaytourID);
    }
    
    public int enableBookDetailDaytour(String DaytourID) {
        return daytourBookingdao.enableBookDetailDaytour(DaytourID);
    }
    
    public String DeleteBookingDaytourPrice(String TourPriceID) {
        return daytourBookingdao.DeleteBookingDaytourPrice(TourPriceID);
    }
    
    public String DeleteBookingDaytourPriceNotMatch(String TourCode,String DaytourBook) {
        return daytourBookingdao.DeleteBookingDaytourPriceNotMatch(TourCode, DaytourBook);
    }

    public List<OtherBooking> getCouponList(String refno){
        return otherBookingdao.getListBookingOtherFromRefno(refno);
    }
    
    public DaytourDao getDaytourdao() {
        return daytourdao;
    }

    public void setDaytourdao(DaytourDao daytourdao) {
        this.daytourdao = daytourdao;
    }

    public DaytourBookingDao getDaytourBookingdao() {
        return daytourBookingdao;
    }

    public void setDaytourBookingdao(DaytourBookingDao daytourBookingdao) {
        this.daytourBookingdao = daytourBookingdao;
    }

    public OtherBookingDao getOtherBookingdao() {
        return otherBookingdao;
    }

    public void setOtherBookingdao(OtherBookingDao otherBookingdao) {
        this.otherBookingdao = otherBookingdao;
    }
    
    
    
}
