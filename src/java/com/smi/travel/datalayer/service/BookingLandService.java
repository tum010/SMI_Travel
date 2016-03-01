/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.datalayer.service;

import com.smi.travel.datalayer.dao.LandBookingDao;
import com.smi.travel.datalayer.dao.LandItineraryDao;
import com.smi.travel.datalayer.dao.MListItemDao;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.LandItinerary;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.util.UtilityFunction;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Surachai
 */
public class BookingLandService {

    private LandBookingDao landbookingdao;
    private LandItineraryDao landItinerarydao;
    private MListItemDao listItemdao;
    private UtilityFunction util;

    public List<LandBooking> getListBookingLandFromRefno(String refno) {
        return landbookingdao.getListBookingLandFromRefno(refno);
    }

    public List<PackageTour> getListMpackage() {
        return listItemdao.getListMPackage();
    }

    public LandBooking getBookDetailLandFromID(String LandID) {
        return landbookingdao.getBookDetailLandFromID(LandID);
    }

    public List<Product> getListLandProduct() {
        return landbookingdao.getListLandProduct();
    }
    
    public List<PackageTour> getListLandPackage(){
        return landbookingdao.getListLandPackage();
    }

    public int saveBookDetailLand(LandBooking land, String Itenarary, String DelItenarary, String BookType) {
        int result = 0;
        List<LandItinerary> data = MappingListLandItinerary(Itenarary, BookType);
       

        if (land.getId() == null) {
               MItemstatus status = new MItemstatus();
               land.setMItemstatus(status);
               status.setId("1");
               land.setIsBill(0);
               result = landbookingdao.insertBookDetailLand(land,data);
        } else {
               result = landbookingdao.updateBookDetailLand(land,data,DelItenarary);
        }

        return result;
    }

    public List<LandItinerary> MappingListLandItinerary(String Itenarary, String BookType) {
        String replacedata = ",,0,,,&";
        replacedata = " , , , , &";
        
        List<LandItinerary> ItineraryList = new LinkedList<LandItinerary>();
        Itenarary = Itenarary.replaceAll(replacedata, "");
        util = new UtilityFunction();
        if (!"".equalsIgnoreCase(Itenarary)) {
            String[] path = Itenarary.split("&");

            for (int i = 0; i < path.length; i++) {
                System.out.println("path :" + path[i]);
                String[] data = path[i].split(",");
                LandItinerary itinarary = new LandItinerary();
                if (!" ".equalsIgnoreCase(data[0])) {
                    itinarary.setId(data[0]);
                }
                if (!" ".equalsIgnoreCase(data[1])) {
                     System.out.println("data[1] :"+data[1]);
                    itinarary.setOrderNo(Integer.parseInt(data[1]));
                }
                if (!" ".equalsIgnoreCase(data[2])) {
                   
                    itinarary.setDayDate(util.convertStringToDate(data[2]));
                }
                if (!" ".equalsIgnoreCase(data[3])) {
                    itinarary.setDayTime(util.convertStringToTime(data[3].trim()));
                }
                
                if(data.length == 5){
                    itinarary.setDescription(data[4]);
                }else{
                    itinarary.setDescription("");
                }
                ItineraryList.add(itinarary);
            }
        }

        return ItineraryList;
    }
    
    public int cancelBookDetailLand(String LandID){
        return landbookingdao.cancelBookDetailLand(LandID);
    }
    
    public int enableBookDetailLand(String LandID){
        return landbookingdao.enableBookDetailLand(LandID);
    }

    public List<LandItinerary> getListItinerary(String LandID) {
        return landItinerarydao.getListItinerary(LandID);
    }

    public int InsertItinerary(LandItinerary land) {
        return landItinerarydao.InsertItinerary(land);
    }

    public int DeleteItinerary(LandItinerary land) {
        return landItinerarydao.DeleteItinerary(land);
    }

    public int UpdateItinerary(LandItinerary land) {
        return landItinerarydao.UpdateItinerary(land);
    }
    
    public String DeleteLandCity(String landCityID){
        return landbookingdao.DeleteLandCity(landCityID);
    }

    public LandBookingDao getLandbookingdao() {
        return landbookingdao;
    }

    public void setLandbookingdao(LandBookingDao landbookingdao) {
        this.landbookingdao = landbookingdao;
    }

    public LandItineraryDao getLandItinerarydao() {
        return landItinerarydao;
    }

    public void setLandItinerarydao(LandItineraryDao landItinerarydao) {
        this.landItinerarydao = landItinerarydao;
    }

    public MListItemDao getListItemdao() {
        return listItemdao;
    }

    public void setListItemdao(MListItemDao listItemdao) {
        this.listItemdao = listItemdao;
    }

}
