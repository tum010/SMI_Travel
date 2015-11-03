/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.OtherBookingDao;
import com.smi.travel.datalayer.dao.ProductDao;
import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class BookingOtherService {
    private ProductDao productDao;
    private OtherBookingDao otherBookDao;
     private SystemUserDao systemUser;
    
    public List<OtherBooking> getListBookingOtherFromRefno(String refno) {
        return otherBookDao.getListBookingOtherFromRefno(refno);
    } 
    
    public List<OtherBooking> getListBookingAll(){
        return otherBookDao.getListBookingAll();
    }
    
    public List<OtherBooking> getListBookingDaytourComission(String StartDate, String EndDate,String agentID,String guideID) {
        return otherBookDao.getListBookingOtherComission(StartDate, EndDate, agentID, guideID);
    }
    
    public String saveOtherBookCommission(List<OtherBooking> BookList){
        return otherBookDao.saveOtherBookCommission(BookList);
    }
    
    public OtherBooking getBookDetailOtherFromID(String OtherBookingID) {
        return otherBookDao.getBookDetailOtherFromID(OtherBookingID);
    }
    
    public List<String> saveBookingOther(OtherBooking other ,SystemUser user ,String createby){
        List<String> result = new ArrayList<String>();
        if(other.getId() != null){
            other.setCreateBy(createby);
            result = otherBookDao.updateBookDetailOther(other);
        }else{
            MItemstatus status = new MItemstatus();
            status.setId("1");
            other.setIsBill(0);
            Date thisdate = new Date();
            other.setStatus(status);
            other.setCreateBy(user.getUsername());
            other.setCreateDate(thisdate);
            
            result = otherBookDao.insertBookDetailOther(other, user);
        }
        return result;
    }
    
    public int cancelBookDetailOther(String OtherID){
        return otherBookDao.cancelBookDetailOther(OtherID);
    }
    
    public int enableBookDetailOther(String OtherID){
        return otherBookDao.enableBookDetailOther(OtherID);
    }
    
    public List<Product> getListMasterProduct(){
        return productDao.getListProduct();
    }
    
    public ProductDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public OtherBookingDao getOtherBookDao() {
        return otherBookDao;
    }

    public void setOtherBookDao(OtherBookingDao otherBookDao) {
        this.otherBookDao = otherBookDao;
    }

    public String saveStockDetailOther(OtherBooking Other, SystemUser user, String addticket, String adTicket, String chTicket, String infTicket, String itemid) {
        return otherBookDao.saveStockDetailOther(Other, user, addticket, adTicket, chTicket, infTicket, itemid);
    }

    public List<OtherTicketView> getListStockDetail(String otherBookingId) {
        return otherBookDao.getListStockDetail(otherBookingId);
    }
    
    public String updateStockTicketStatus(String stockTicketId, String status) {
        return otherBookDao.updateStockTicketStatus(stockTicketId,status);
    }
    
    public int insertSystemUser(SystemUser user){
        return systemUser.insertSystemUser(user);
    }

    public SystemUserDao getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUserDao systemUser) {
        this.systemUser = systemUser;
    }
    
    
}
