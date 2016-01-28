/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.PassengerService;
import com.smi.travel.datalayer.view.entity.OtherBookingView;
import com.smi.travel.master.controller.SMITravelController;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kanokporn
 */
public class DaytourOperationOtherController extends SMITravelController {

    private static final ModelAndView DaytourOperationOther = new ModelAndView("DaytourOperationOther");
    private BookingOtherService otherBookingService;
    private PassengerService passsengerService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String refNo = request.getParameter("InputRefNo");
        System.out.println(""+refNo);
        List<OtherBooking> listOtherBooking = new LinkedList<OtherBooking>();
        List<OtherBooking> listOtherBookingAll = new LinkedList<OtherBooking>();
        List<OtherBookingView> listOtherBookingAllView = new LinkedList<OtherBookingView>();
        List<Passenger> listPassenger = new LinkedList<Passenger>();
        
        if("search".equalsIgnoreCase(action)){
            listOtherBooking = otherBookingService.getListBookingOtherFromRefno(refNo);
            listPassenger = passsengerService.getPassengerFromRefno(refNo);
            System.out.println("Found Other List Book");
            //Ref No
            if (refNo != null) {
                request.setAttribute("RefNo", refNo);
            }else{
                request.setAttribute("RefNo", null);
            }
            // Book
            if (listOtherBooking != null) {
                 
                for(int i = 0; i < listOtherBooking.size(); i++) {
                    System.out.println(""+listOtherBooking.get(i).getStatus());
                }
                int count = checkReportUni(listOtherBooking);
                String duplicate = "";
                if(count == 0){
                     request.setAttribute("Duplicate","NoDuplicate");
                }else{
                      request.setAttribute("Duplicate","Duplicate");
                }
                request.setAttribute("listOtherBooking", listOtherBooking);
                
                
                //Passenger
                if (listPassenger != null) {

                    for(int i = 0; i < listPassenger.size(); i++) {
                        System.out.println(""+listPassenger.get(i).getCustomer().getFirstName());
                    }
                    request.setAttribute("listPassenger", listPassenger);
                }else{
                    request.setAttribute("listPassenger", null);
                } 
            }else{
                request.setAttribute("listOtherBooking", null);
                request.setAttribute("listPassenger", null);
                request.setAttribute("NODATA", "nodataotherbooking");
            }
   
            
        }else{
            listOtherBooking = otherBookingService.getListBookingOtherFromRefno(refNo);
            listPassenger = passsengerService.getPassengerFromRefno(refNo);
      
            request.setAttribute("listOtherBooking", null);
            request.setAttribute("listPassenger", null);
            request.setAttribute("RefNo", null);
            
        }
        
        //Bookig List
        listOtherBookingAll = otherBookingService.getListBookingAll();
        if(listOtherBookingAll == null){

            request.setAttribute("ListBookingAll", null);
        }else{
            System.out.println("List Booking All");
            for (int i = 0; i < listOtherBookingAll.size(); i++) {
                System.out.println("Ref no : " + listOtherBookingAll.get(i).getMaster().getReferenceNo());
            }
            request.setAttribute("ListBookingAll", listOtherBookingAll);
        }
        
        //Bookig List View 
        listOtherBookingAllView = otherBookingService.getListBookingAllView("");
        if(listOtherBookingAllView == null){

            request.setAttribute("ListBookingAllView", null);
        }else{
            System.out.println("List Booking All View");
            for (int i = 0; i < listOtherBookingAllView.size(); i++) {
                System.out.println("Ref no : " + listOtherBookingAllView.get(i).getRefno());
            }
            request.setAttribute("ListBookingAllView", listOtherBookingAllView);
        }
        return DaytourOperationOther;
    }
    
    private int  checkReportUni(List<OtherBooking> listOtherBooking){
        int count = 0;
        int j = 0 ;
        int i = 0 ;
        for ( i = 0; i < listOtherBooking.size(); i++) {
            for (j = (i+1) ; j < listOtherBooking.size() ; j++) {
                if(listOtherBooking.get(i).getProduct().getCode().equalsIgnoreCase(listOtherBooking.get(j).getProduct().getCode())){
                    count++;
                }
            }
        }
        return count;
    }
    
    public void setOtherBookingService(BookingOtherService otherBookingService) {
        this.otherBookingService = otherBookingService;
    }

    public BookingOtherService getOtherBookingService() {
        return otherBookingService;
    }

    public void setPasssengerService(PassengerService passsengerService) {
        this.passsengerService = passsengerService;
    }
    
    public PassengerService getPasssengerService() {
        return passsengerService;
    }

}
