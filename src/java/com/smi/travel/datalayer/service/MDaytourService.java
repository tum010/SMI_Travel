/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.DaytourDao;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourPrice;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class MDaytourService {
    private DaytourDao daytourdao;
    
    public List<Daytour> searchTourList(Daytour tour, int option){
        return daytourdao.searchTourList(tour, option);
    }
    
    public String validateDaytour(Daytour VDaytour,String operation){
        String validate = "";
        Daytour daytour = new Daytour();
        daytour.setCode(VDaytour.getCode());
        List<Daytour> list = daytourdao.searchTourList(daytour,1);
        if(list != null){
            if("save".equalsIgnoreCase(operation)){
                if(!(list.get(0).getId().equalsIgnoreCase(VDaytour.getId()))){
                    validate = "tour code already exist";
                }
            }else{
                 validate = "tour code already exist";
            }
        }
//        daytour.setName(VDaytour.getName());
//        daytour.setCode(null);
//        list = daytourdao.searchTourList(daytour,1);
//        if(list != null){      
//            if("update".equalsIgnoreCase(operation)){
//                if(!(list.get(0).getId().equalsIgnoreCase(VDaytour.getId()))){
//                    validate = "tour name already exist";
//                }
//            }else{
//                 validate = "tour name already exist";
//            }
//        }
        return validate;
    }
    
    public String SaveTour(Daytour tour,String DelPriceID,String DelExpenseID, List<DaytourPrice> tourPrice){
        if(tour.getId() == null){
            return daytourdao.InsertTour(tour, tourPrice);
        }else{
            return daytourdao.UpdateTour(tour,DelPriceID,DelExpenseID, tourPrice);
        } 
    }
    
    public String getStafftour() {
        return daytourdao.getStafftour();
    }
    
    public Daytour getTourFromID(String TourID) {
        return daytourdao.getTourFromID(TourID);
    }
    
    public String DeleteTour(Daytour tour ){
        return daytourdao.DeleteTour(tour);
    }
    
    public String DeleteTourPrice(String TourPriceID){
        return daytourdao.DeleteTourPrice(TourPriceID);
    }
    public String DeleteTourExpense(String TourExpenseID){
        return daytourdao.DeleteTourExpense(TourExpenseID);
    }

    public DaytourDao getDaytourdao() {
        return daytourdao;
    }

    public void setDaytourdao(DaytourDao daytourdao) {
        this.daytourdao = daytourdao;
    }
    
    
    
}
