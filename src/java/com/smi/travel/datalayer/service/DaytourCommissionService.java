/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.DaytourComissionDao;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.DaytourBookingPrice;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class DaytourCommissionService {
    private DaytourComissionDao daytourComissiondao;
    
    public List<DaytourBooking> getListBookingDaytourComission(String StartDate,String EndDate,String agentID,String guideID){
        return daytourComissiondao.getListBookingDaytourComission(StartDate, EndDate,agentID,guideID);
    }
    
    
    public String SaveDaytourComission(List<DaytourBooking> BookList){
        return daytourComissiondao.SaveDaytourComission(BookList);
    }
    
    public void CalculateDaytourPrice(List<DaytourBooking> dBookingList){
        int price =0;
        if(dBookingList != null){
            for(int i=0;i<dBookingList.size();i++){
                price = 0;
                 DaytourBooking Book = dBookingList.get(i);
                 List<DaytourBookingPrice> priceList = new ArrayList<DaytourBookingPrice>(Book.getDaytourBookingPrices());
                 for(int j =0;j<priceList.size();j++){
                     DaytourBookingPrice myprice = priceList.get(j);
                     System.out.println(myprice.getQty() +","+myprice.getPrice());
                     price += (myprice.getQty()==null? 0:myprice.getQty()) * (myprice.getPrice()==null? 0:myprice.getPrice());
                 }
                 System.out.println("price : "+price);
                 dBookingList.get(i).setAdult(price);
            }           
        }

        
    }
    

    public DaytourComissionDao getDaytourComissiondao() {
        return daytourComissiondao;
    }

    public void setDaytourComissiondao(DaytourComissionDao daytourComissiondao) {
        this.daytourComissiondao = daytourComissiondao;
    }
    
    
}
