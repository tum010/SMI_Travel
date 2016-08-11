/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MCity;
import com.smi.travel.datalayer.entity.MCountry;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingOtherService;
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
public class OutboundProductSummaryController  extends SMITravelController{
    private static final ModelAndView OutboundProductSummary = new ModelAndView("OutboundProductSummary");
    private static final ModelAndView OutboundProductSummary_REFRESH = new ModelAndView(new RedirectView("OutboundProductSummary.smi", true));
    private UtilityService utilityService;
    private BookingOtherService bookingOtherService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        // List Status 
        List<MItemstatus> listStatus = utilityService.getListMItemstatus();
        if(listStatus != null && listStatus.size() != 0){
            request.setAttribute("listStatus", listStatus);
        }else{
            request.setAttribute("listStatus", listStatus);
        }
        //List Country
        List<MCountry> listCountry = utilityService.getListMCountry();
        if(listCountry != null && listCountry.size() != 0){
            request.setAttribute("listCountry", listCountry);
        }else{
            request.setAttribute("listCountry", listCountry);
        }
        //List City
        List<MCity> listCity = utilityService.getListMCity();
        if(listCity != null && listCity.size() != 0){
            request.setAttribute("listCity", listCity);
        }else{
            request.setAttribute("listCity", listCity);
        }
        //List Product
        List<Product> listProduct = bookingOtherService.getListMasterProduct();
        if(listProduct != null && listProduct.size() != 0){
            request.setAttribute("listProduct", listProduct);
        }else{
            request.setAttribute("listProduct", listProduct);
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
        
        
        return OutboundProductSummary;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public BookingOtherService getBookingOtherService() {
        return bookingOtherService;
    }

    public void setBookingOtherService(BookingOtherService bookingOtherService) {
        this.bookingOtherService = bookingOtherService;
    }
    
}
