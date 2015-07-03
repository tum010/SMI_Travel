/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourPrice;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface DaytourDao {
    public List<Daytour> searchTourList(Daytour tour, int option);
    public List<Daytour> getTourListActive();
    public Daytour getTourFromID(String TourID);
    public String InsertTour(Daytour tour, List<DaytourPrice> tourPrice);
    public String UpdateTour(Daytour tour,String DelPriceID,String DelExpenseID, List<DaytourPrice> tourPrice);
    public String DeleteTour(Daytour tour);
    public String DeleteTourPrice(String TourPriceID);
    public String DeleteTourExpense(String TourExpenseID);
    public Boolean CheckUsabilityTour(Daytour tour);
    public List<DaytourPrice> getDaytourPrice(String TourID);
    public List<DaytourPrice> sortPriceList(List<DaytourPrice> priceList);
    public String saveStafftour(String stafftourname);
    public String getStafftour();
    
}
