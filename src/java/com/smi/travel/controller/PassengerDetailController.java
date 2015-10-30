/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.service.PassengerService;
import com.smi.travel.master.controller.SMITravelController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author sumeta
 */
public class PassengerDetailController extends SMITravelController {

    private static final ModelAndView PassengerDetail = new ModelAndView("PassengerDetail");
    private static final ModelAndView PassengerDetail_REFRESH = new ModelAndView(new RedirectView("PassengerDetail.smi", true));
    private BookingAirticketService bookingAirticketService;
    private UtilityService utilservice;
    private PassengerService passengerService;

    private static final String ACTION = "action";
    private static final String Bookiing_Size = "BookingSize";
    private static final String PassengerList = "PassengerList";
    private static final String Master = "Master";
    private static final String initialList = "initialList";
    private static final String CustomerList = "customerList";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ISBILLSTATUS = "IsBillStatus";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        System.out.println("PassengerDetailController");
        
        String refNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        String existCode = request.getParameter("existcode");
        // passenger
        String id = request.getParameter("id");
        String isLeader = request.getParameter("isLeader");
        String billables = request.getParameter("billables");
        // customer
        String customerId = request.getParameter("customerId");
        String MInitialname = request.getParameter("MInitialname");
        String code = request.getParameter("code");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birth = request.getParameter("birthDate");
        Date birthdate = null;
        if (StringUtils.isNotEmpty(birth)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            try {
                birthdate = formatter.parse(birth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String phone = request.getParameter("phone");
        String postalCode = request.getParameter("postalCode");
        String email = request.getParameter("email");
        String firstNameJapan = request.getParameter("firstNameJapan");
        String lastNameJapan = request.getParameter("lastNameJapan");
        String passportNo = request.getParameter("Passport");
        String remark = request.getParameter("remark");
        String masters = request.getParameter("master");
        String nationality = request.getParameter("Nationality");
        String personalId = request.getParameter("personalId");

        System.out.println("refNo = " + refNo + ",Minitialname = " + MInitialname + ", passportNo " + passportNo + " , customerId=" + customerId);
        int result = 0;
        String result2 = null;

        Passenger passenger = new Passenger();
        Customer customerBean = new Customer();
        MInitialname initialname = new MInitialname();
        Master master = new Master();

        initialname.setId(MInitialname);
//        master.setId(masters);
        customerBean.setCode(code);
        customerBean.setMInitialname(initialname);
        customerBean.setFirstName(firstName);
        customerBean.setLastName(lastName);
        customerBean.setBirthDate(birthdate);
        customerBean.setSex(sex);
        customerBean.setAddress(address);
        customerBean.setPostalCode(postalCode);
        customerBean.setTel(tel);
        customerBean.setPhone(phone);
        customerBean.setEmail(email);
        customerBean.setRemark(remark);
        customerBean.setPassportNo(passportNo);
        customerBean.setNationality(nationality);
        customerBean.setPersonalId(personalId);
        customerBean.setFirstNameJapan(firstNameJapan);
        customerBean.setLastNameJapan(lastNameJapan);

        if ("add".equalsIgnoreCase(action)) {
            System.out.println("action add");
            request.setAttribute(ACTION, "insert");
            request.setAttribute("EXISTCODE", existCode);
            this.setResponseAttribute(request, refNo);
        } else if ("insert".equalsIgnoreCase(action)) {
            Customer customer = null;
            if (StringUtils.isNotEmpty(customerBean.getCode())) {
                customer = utilservice.getCustomerdao().getCustomerFromID(customerId);
            } else {
                customer = new Customer();
                BeanUtils.copyProperties(customerBean, customer, new String[]{"id", "masters", "passengers"});
            }
            Passenger newPassenger = new Passenger();
            newPassenger.setCustomer(customer);
            master = utilservice.getbookingFromRefno(refNo);
            newPassenger.setOrderNo(master.getPassengers().size() + 1);
            newPassenger.setMaster(master);

            result2 = passengerService.saveBookingPassenger(newPassenger);
            if(result2 != null){
                result = 1;
            }
            result = 1;
            return new ModelAndView("redirect:Passenger.smi?referenceNo=" + refNo + "&result=" + result + "&action=edit");

        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.println("action edit");
            Passenger passengersList = passengerService.getPassengerFromID(id);
            request.setAttribute(PassengerList, passengersList);
            request.setAttribute(ACTION, "update");
            this.setResponseAttribute(request, refNo);
        } else if ("update".equalsIgnoreCase(action)) {
            System.out.println("action update");
            if (StringUtils.isNotEmpty(code)) {
                //update customer.
                Passenger p = passengerService.getPassengerFromID(id);
                Customer cust = p.getCustomer();
                BeanUtils.copyProperties(customerBean, cust, new String[]{"id", "masters", "passengers"});
                result = passengerService.getCustomerdao().updateCustomer(cust);
                System.out.println("result updateCustomer : "+result);
            } else {
                //Add Customer
                passenger.setId(id);
                result2 = passengerService.saveBookingPassenger(passenger);
                if(result2 != null){
                    result = 1;
                }
            }
           
            return new ModelAndView("redirect:Passenger.smi?referenceNo=" + refNo + "&action=edit&result=" + result);
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("action delete");
            //result = passengerService.deletePassenger(passenger);
            //return new ModelAndView("redirect:Passenger.smi?referenceNo=" + refNo + "&result=" + result);
        } else {
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                System.out.println("******* No refNo !!!!");

            } else {
                System.out.println("******* Code should not come here!!!!");
            }

        }

        return PassengerDetail;
    }

    public void setResponseAttribute(HttpServletRequest request, String refNo) {

        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        System.out.println("Master -" + master.getCreateDate());
        List<MInitialname> mInitial = utilservice.getListMInitialname();
        request.setAttribute(initialList, mInitial);
        List<Customer> customerList = utilservice.getListCustomer();
        request.setAttribute(CustomerList, customerList);
        
        if(("2").equals(String.valueOf(master.getMBookingstatus().getId())) || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }

    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public PassengerService getPassengerService() {
        return passengerService;
    }

    public void setPassengerService(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

}
