/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.MCityService;
import com.smi.travel.datalayer.service.MCountryService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Jittima
 */
public class PaymentSummaryController extends SMITravelController{
    private static final ModelAndView PaymentSummary = new ModelAndView("PaymentSummary");
    private static final ModelAndView PaymentSummary_REFRESH = new ModelAndView(new RedirectView("PaymentSummary.smi", true));
    private static final String INVOICESUPLIST = "invSupList";
    private static final String STAFFLIST = "staffList";
    private static final String CITYLIST = "city_list";
    private static final String COUNTRYLIST = "country_list";
    private static final String PRODUCTLIST = "listProduct";
    private static final String ProductTypeList = "ProductTypeList";
    private BookingOtherService bookingOtherService;
    private MCityService cityservice;
    private MCountryService countryservice;
    private UtilityService utilityService;
    
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<InvoiceSupplier> invoiceSupplierList = utilityService.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        request.setAttribute(CITYLIST, cityservice.getlistCity());
        request.setAttribute(COUNTRYLIST, countryservice.getListCountry());
        List<SystemUser> listStaff = utilityService.getUserList();
       // request.setAttribute(ProductTypeList, utilityService.getB);
        request.setAttribute(STAFFLIST, listStaff);
        List<Product> listProduct = bookingOtherService.getListMasterProduct();
        if(listProduct != null && listProduct.size() != 0){
            request.setAttribute(PRODUCTLIST, listProduct);
        }else{
            request.setAttribute(PRODUCTLIST, listProduct);
        }
         List<MPaytype> listType = new ArrayList<MPaytype>();
        listType = utilityService.getListMPayType();
        if(listType != null){
            request.setAttribute(ProductTypeList, listType);
        }else{
            request.setAttribute(ProductTypeList, null);
        } 
        return PaymentSummary;
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

    public BookingOtherService getBookingOtherService() {
        return bookingOtherService;
    }

    public void setBookingOtherService(BookingOtherService bookingOtherService) {
        this.bookingOtherService = bookingOtherService;
    }
    
    
    
}


