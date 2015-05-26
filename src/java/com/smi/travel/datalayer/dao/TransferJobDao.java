/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.datalayer.dao;

import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourBooking;
import com.smi.travel.datalayer.entity.Place;
import com.smi.travel.datalayer.entity.TransferJob;
import java.util.List;

/**
 *
 * @author Surachai
 */
public interface TransferJobDao {
    public List<DaytourBooking> filterTourFromDate(String TourDate);
    public List<Place> filterPlaceFromDateAndTour(String TourDate,String TourID);
    public List<String> filterPlaceOtherFromDateAndTour(String TourDate,String TourID);
    public List<TransferJob> searchTransferJob(String StartDate , String EndDate,String Hotel);
    public String saveTransferjob(TransferJob Job);
    public List<DaytourBooking> getTransferjobData(String TourId,String TourDate,String Place,String Other);
    public List<DaytourBooking> sortTransferjobDataFromTime(List<DaytourBooking> daytourList);
}
