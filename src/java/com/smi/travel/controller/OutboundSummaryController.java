/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.HotelService;
import com.smi.travel.datalayer.service.MCityService;
import com.smi.travel.datalayer.service.MCountryService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Kanokporn
 */
public class OutboundSummaryController extends SMITravelController{
    private static final ModelAndView OutboundSummary = new ModelAndView("OutboundSummary");
    private static final ModelAndView OutboundSummary_REFRESH = new ModelAndView(new RedirectView("OutboundSummary.smi", true));
    private HotelService hotelService;
    private MCityService cityservice;
    private MCountryService countryservice;
    private UtilityService utilityService;
    private static final String CITYLIST = "city_list";
    private static final String COUNTRYLIST = "country_list";
    private static final String HotelList = "HotelList";
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Hotel h = new Hotel();
        List<Hotel> hotelsList = getHotelService().getListHotel(h, 1);
        request.setAttribute(HotelList, hotelsList);
        request.setAttribute(CITYLIST, cityservice.getlistCity());
        request.setAttribute(COUNTRYLIST, countryservice.getListCountry());
        
        List<MItemstatus> listStatus = utilityService.getListMItemstatus();
        if(listStatus != null && listStatus.size() != 0){
            request.setAttribute("listStatus", listStatus);
        }else{
            request.setAttribute("listStatus", listStatus);
        }
        
        // List Pay by
        List<MAccpay> listPayby = utilityService.getListMAccpay();
        if(listPayby != null && listPayby.size() != 0){
            request.setAttribute("listPayby", listPayby);
        }else{
            request.setAttribute("listPayby", listPayby);
        }
        // List Bank
        List<MBank> listBank = utilityService.getListBank();
        if(listBank != null && listBank.size() != 0){
            request.setAttribute("listBank", listBank);
        }else{
            request.setAttribute("listBank", listBank);
        }
        // List Sale 
        List<SystemUser> listSale = utilityService.getUserOutboundList();
        if(listSale != null && listSale.size() != 0){
            request.setAttribute("listSale", listSale);
        }else{
            request.setAttribute("listSale", listSale);
        }
             
        return OutboundSummary;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    public MCityService getCityservice() {
        return cityservice;
    }

    public void setCityservice(MCityService cityservice) {
        this.cityservice = cityservice;
    }

    public MCountryService getCountryservice() {
        return countryservice;
    }

    public void setCountryservice(MCountryService countryservice) {
        this.countryservice = countryservice;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
