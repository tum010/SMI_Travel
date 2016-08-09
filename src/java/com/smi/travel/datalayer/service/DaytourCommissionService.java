/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.DaytourComissionDao;
import com.smi.travel.datalayer.dao.SystemUserDao;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.view.entity.DaytourBookingViewMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class DaytourCommissionService {
    private DaytourComissionDao daytourComissiondao;
    private SystemUserDao systemUser;
    
    public List<DaytourBookingViewMin> getListBookingDaytourComission(String StartDate,String EndDate,String agentID,String guideID){
        return daytourComissiondao.getListBookingDaytourComission(StartDate, EndDate,agentID,guideID);
    }
    
    
    public String SaveDaytourComission(List<DaytourBooking> BookList){
        return daytourComissiondao.SaveDaytourComission(BookList);
    }
    
    public void CalculateDaytourPrice(List<DaytourBooking> dBookingList){
        BigDecimal price = new BigDecimal(0);
        if(dBookingList != null){
            for(int i=0;i<dBookingList.size();i++){
                price = new BigDecimal(0);
                 DaytourBooking Book = dBookingList.get(i);
                 List<DaytourBookingPrice> priceList = new ArrayList<DaytourBookingPrice>(Book.getDaytourBookingPrices());
                 for(int j =0;j<priceList.size();j++){
                     DaytourBookingPrice myprice = priceList.get(j);
//                     System.out.println(myprice.getQty() +","+myprice.getPrice());
//                     price += (myprice.getQty()==null? 0:myprice.getQty()) * (myprice.getPrice()==null? 0:myprice.getPrice());
                    BigDecimal pricetemp = myprice.getPrice() == null ? new BigDecimal(0) : myprice.getPrice();
                    BigDecimal qtytemp = myprice.getQty() == null ? new BigDecimal(0) : new BigDecimal(myprice.getQty());
                    BigDecimal p = pricetemp.multiply(qtytemp);
                    price = price.add(p);
                 }
                 dBookingList.get(i).setAdult(price.intValueExact());
            }           
        }
    }
    

    public DaytourComissionDao getDaytourComissiondao() {
        return daytourComissiondao;
    }

    public void setDaytourComissiondao(DaytourComissionDao daytourComissiondao) {
        this.daytourComissiondao = daytourComissiondao;
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
