/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.Hotel;
import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.entity.HotelPassenger;
import com.smi.travel.datalayer.entity.HotelRequest;
import com.smi.travel.datalayer.entity.HotelRoom;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MMeal;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingHotelService;
import com.smi.travel.datalayer.service.HotelService;
import com.smi.travel.datalayer.service.PassengerService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.BillableView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author sumeta
 */
public class HotelDetailController extends SMITravelController {

    private static final ModelAndView HotelDetail = new ModelAndView("HotelDetail");
    private BookingAirticketService bookingAirticketService;
    private BookingHotelService bookingHotelService;
    private UtilityService utilservice;
    private HotelService hotelService;
    private PassengerService passengerService;
    private UtilityFunction util;

    private static final String Bookiing_Size = "BookingSize";
    private static final String HotelBooking = "HotelBooking";
    private static final String HotelRequestsList = "HotelRequestsList";
    private static final String HotelPassengerList = "HotelPassengerList";
    private static final String HotelRooms = "HotelRooms";
    private static final String Master = "Master";
    private static final String MMealsList = "MMealsList";
    private static final String MItemstatusesList = "MItemstatusesList";
    private static final String HotelList = "HotelList";
    private static final String Result = "Result";
    private static final String TransectionResult = "result";
    private static final String PassengerList = "PassengerList";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String MCurrency = "MCurrency";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private static final String EnableSave = "EnableSave";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        SystemUser user = (SystemUser) session.getAttribute("USER");
        System.out.println("HotelDetailController");
        UtilityFunction util = new UtilityFunction();
        String refNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String master = request.getParameter("master");
        String meal = request.getParameter("meal");
        String hotelId = request.getParameter("hotel-id");
        String orderNo = request.getParameter("orderNo");
        String hString = request.getParameter("hotelName");
        String reconfirm = request.getParameter("reconfirm");
        String hotelRef = request.getParameter("hotelRef");
        String isBill = request.getParameter("isBill");
        String remark = request.getParameter("remark");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        String adult = request.getParameter("adult");
        String child = request.getParameter("child");
        String infant = request.getParameter("infant");
        String Deadline = request.getParameter("deadline");
        String Flight = request.getParameter("Flight");
        String currency = request.getParameter("select-currency");
        String currencycost = request.getParameter("select-currencycost");
        

        String hotelRoomRows = request.getParameter("roomCounter");
        String hotelRequestRows = request.getParameter("requestCounter");
        String hotelPassengerRows = request.getParameter("passengerCounter");
        
        int result = 0;
        System.out.println("orderNO : " + orderNo);
        
        request.setAttribute(ISBILLSTATUS,0);

        
        if ("new".equalsIgnoreCase(action)) {
            System.out.println("add");
            setResponseAttribute(request, refNo);
            request.setAttribute(EnableSave,0);
        } else if ("insert".equalsIgnoreCase(action) || "update".equalsIgnoreCase(action)) {
            HotelBooking hotelBooking;
            if ("insert".equalsIgnoreCase(action)) {
                hotelBooking = new HotelBooking();
                status = "OK";
            } else {
                hotelBooking = bookingHotelService.getHotelFromID(id);
            }
            hotelBooking.setMItemstatus(utilservice.getMItemstatusFromName(status));
            hotelBooking.setMaster(utilservice.getMasterdao().getBookingFromRefno(refNo));
            hotelBooking.setMMeal(utilservice.getMMealFromName(meal));
            hotelBooking.setHotel(hotelService.getHotelFromID(hotelId));
            hotelBooking.setDeadline(util.convertStringToDate(Deadline));
            hotelBooking.setFlight(Flight);
            hotelBooking.setIsBill(util.convertStringToInteger(isBill));
            hotelBooking.setCurCost(currencycost);
            hotelBooking.setCurAmount(currency);
            
            System.out.println(" hotelBooking.getIsBill() " + hotelBooking.getIsBill());
            request.setAttribute(ISBILLSTATUS,hotelBooking.getIsBill());
            
            if (StringUtils.isNotEmpty(orderNo)) {
                hotelBooking.setOrderNo(Integer.parseInt(orderNo));
            }
            hotelBooking.setReconfirm(reconfirm);
            hotelBooking.setHotelRef(hotelRef);

            hotelBooking.setRemark(remark);
            if (StringUtils.isNotEmpty(adult)) {
                hotelBooking.setAdult(Integer.parseInt(adult));
            }
            if (StringUtils.isNotEmpty(child)) {
                hotelBooking.setChild(Integer.parseInt(child));
            }
            if (StringUtils.isNotEmpty(infant)) {
                hotelBooking.setInfant(Integer.parseInt(infant));
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dateCheckIn = formatter.parse(checkin);
                Date dateCheckOut = formatter.parse(checkout);
                System.out.println(formatter.format(dateCheckIn));
                hotelBooking.setCheckin(dateCheckIn);
                hotelBooking.setCheckout(dateCheckOut);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("hotelRequestRows : "+hotelRequestRows);
            setHotelRoom(request, hotelRoomRows, hotelBooking);
            setHotelRequest(request, hotelRequestRows, hotelBooking);
            setHotelPassenger(request, hotelPassengerRows, hotelBooking);

            if ("insert".equalsIgnoreCase(action)) {
                System.out.println("instert id = " + id);
                hotelBooking.setIsBill(0);
                result = bookingHotelService.insertHotel(hotelBooking);
                saveHistoryBooking(refNo,user,hotelBooking,"CREATE");
                if (result == 1) {
                    return new ModelAndView(new RedirectView(HotelBooking + ".smi?referenceNo=" + refNo + "&result=1", true));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            } else {
                hotelBooking.setId(id);
                System.out.println("updata where id = " + id);
                result = bookingHotelService.updateHotel(hotelBooking);
                saveHistoryBooking(refNo,user,hotelBooking,"UPDATE");
            }
            request.setAttribute(Result, result);
            
            String billDescId = utilservice.getBillableDescId(id, "4");
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
                BillableView billableView = utilservice.getBillableDescByBookId(id);
                int resultupdate = utilservice.updateBillableDesc(billableView,billDescId);
            }
            return new ModelAndView("redirect:HotelDetail.smi?referenceNo=" + refNo + "&action=edit&id="+ id + "&result=" + result);
        } else if ("edit".equalsIgnoreCase(action)) {
            HotelBooking hotel = bookingHotelService.getHotelFromID(request.getParameter("id"));
            System.out.println("ID : " + request.getParameter("id"));
            request.setAttribute(HotelBooking, hotel);
            List<HotelRequest> hotelRequests = hotel.getHotelRequests();
            for (HotelRequest re : hotelRequests) {
                System.out.println("Description : " + re.getDescription());
            }
            System.out.println(" hotel.getIsBill() " + hotel.getIsBill());
            request.setAttribute(ISBILLSTATUS,hotel.getIsBill());
            
            request.setAttribute(HotelRequestsList, hotelRequests);
            List<HotelRoom> hotelRooms = hotel.getHotelRooms();
            request.setAttribute(HotelRooms, hotelRooms);
            List<HotelPassenger> hotelPassengerList = hotel.getHotelPassengers();
            request.setAttribute(HotelPassengerList, hotelPassengerList);
            setResponseAttribute(request, refNo);
            saveHistoryBooking(refNo,user,hotel,"VIEW");
            
            String billDescId = utilservice.getBillableDescId(id, "4");
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
            }
        } else if ("delete".equalsIgnoreCase(action)) {

        } else if ("deleterRoom".equalsIgnoreCase(action)) {
            System.out.println("Delete Room");
            String hId = request.getParameter("hId");
            String rId = request.getParameter("rId");
            HotelBooking hotelBooking = bookingHotelService.getHotelFromID(hId);
            result = bookingHotelService.deleteRoom(hotelBooking, rId);
            System.out.println(result);
        } else if ("deleteRequest".equalsIgnoreCase(action)) {
            System.out.println("Delete Room");
            String hId = request.getParameter("hId");
            String reId = request.getParameter("reId");
            HotelBooking hotelBooking = bookingHotelService.getHotelFromID(hId);
            result = bookingHotelService.deleteRequest(hotelBooking, reId);
            System.out.println(result);
        } else if ("deletePassenger".equalsIgnoreCase(action)) {
            String hId = request.getParameter("hId");
            String pId = request.getParameter("pId");
            HotelBooking hotelBooking = bookingHotelService.getHotelFromID(hId);
            result = bookingHotelService.deletePassenger(hotelBooking, pId);
            System.out.println(result);

        } else {

        }
        return HotelDetail;
    }

    public HotelRoom getHotelRoom(String hotelRoomId, HotelBooking hotelBooking) {
        if (hotelRoomId == null) {
            return null;
        }
        if (hotelBooking.getHotelRooms().isEmpty()) {
            return null;
        }
        Iterator<HotelRoom> iterator = hotelBooking.getHotelRooms().iterator();
        while (iterator.hasNext()) {
            HotelRoom hotelRoom = iterator.next();
            System.out.println("hotelRoomId = " + hotelRoomId + " ; hotelRoom.getId() = " + hotelRoom.getId());
            if (hotelRoomId.equalsIgnoreCase(hotelRoom.getId())) {
                return hotelRoom;
            }

        }

        return null;
    }

    public void setHotelRoom(HttpServletRequest request, String hotelRoomRows, HotelBooking hotelBooking) {
        System.out.println("setHotelRoom Method");
        util = new UtilityFunction();
        int roomRows = Integer.parseInt(hotelRoomRows);
        if (roomRows == 1) {
            return;
        }
        for (int i = 1; i < roomRows; i++) {
            String id = request.getParameter("row-room-" + i + "-id");
            String qty = request.getParameter("row-room-" + i + "-qty");
            String room = request.getParameter("row-room-" + i + "-room");
            String catagory = request.getParameter("row-room-" + i + "-category");
            String cost = request.getParameter("row-room-" + i + "-cost");
            String price = request.getParameter("row-room-" + i + "-price");
            Integer qtyInt = 0;
            Integer costInt = 0;
            Integer priceInt = 0;

            if (StringUtils.isNotEmpty(qty)) {
                qtyInt = util.convertStringToInteger(qty);
            }

            if (StringUtils.isNotEmpty(cost)) {
                costInt = util.convertStringToInteger(cost);
            }

            if (StringUtils.isNotEmpty(price)) {
                priceInt = util.convertStringToInteger(price);
            }
            HotelRoom hotelRoom = getHotelRoom(id, hotelBooking);
            if (hotelRoom == null) {
                hotelRoom = new HotelRoom();
                System.out.println("*** hotelRoom  new object");
            }
            hotelRoom.setQty(qtyInt);
            hotelRoom.setRoom(room);
            hotelRoom.setCategory(catagory);
            hotelRoom.setCost(costInt);
            hotelRoom.setPrice(priceInt);
            if (StringUtils.isNotEmpty(hotelRoom.getRoom())) {
                System.out.println("## hotelRoom.getId() = " + hotelRoom.getId());
                if (hotelRoom.getId() == null) {
                    hotelBooking.getHotelRooms().add(hotelRoom);
                    hotelRoom.setHotelBooking(hotelBooking);
                }
            } else {
                System.out.println("Room is null, Not update DB this object " + i);

            }

        }
    }

    public HotelRequest getHotelRequest(String hotelRequestId, HotelBooking hotelBooking) {

        if (hotelRequestId == null) {
            return null;
        }
        if (hotelBooking.getHotelRequests().isEmpty()) {
            return null;
        }

        Iterator<HotelRequest> iterator = hotelBooking.getHotelRequests().iterator();
        while (iterator.hasNext()) {
            HotelRequest hotelRequest = iterator.next();
            if (hotelRequestId.equalsIgnoreCase(hotelRequest.getId())) {
                return hotelRequest;
            }

        }

        return null;
    }

    public void setHotelRequest(HttpServletRequest request, String hotelRequestRows, HotelBooking hotelBooking) {
        util = new UtilityFunction();
        int requestRows = Integer.parseInt(hotelRequestRows);
        if (requestRows == 1) {
            return;
        }
        System.out.println("Set hotel request 1213");
        for (int i = 1; i < requestRows; i++) {
            System.out.println("Set hotel request");
            String id = request.getParameter("row-request-" + i + "-id");
            String catagory = request.getParameter("row-request-" + i + "-category");
            String description = request.getParameter("row-request-" + i + "-description");
            String cost = request.getParameter("row-request-" + i + "-cost");
            String price = request.getParameter("row-request-" + i + "-price");
            Integer costInt = 0;
            Integer priceInt = 0;

            if (StringUtils.isNotEmpty(cost)) {
                costInt = util.convertStringToInteger(cost);
            }

            if (StringUtils.isNotEmpty(price)) {
                priceInt = util.convertStringToInteger(price);
            }

            System.out.println("hotelRequest Id=" + id);
            HotelRequest hotelRequest = getHotelRequest(id, hotelBooking);
            if (hotelRequest == null) {
                hotelRequest = new HotelRequest();
            }
            hotelRequest.setCategory(catagory);
            hotelRequest.setDescription(description);
            hotelRequest.setCost(costInt);
            hotelRequest.setPrice(priceInt);

            if (StringUtils.isNotEmpty(hotelRequest.getCategory())) {
                if (hotelRequest.getId() == null) {
                    hotelBooking.getHotelRequests().add(hotelRequest);
                    hotelRequest.setHotelBooking(hotelBooking);
                }
            } else {
                System.out.println("HotelRequest is null, Not update DB this object " + i);

            }

        }
    }

    public HotelPassenger getHotelPassenger(String hotelPassengerId, HotelBooking hotelBooking) {
        if (hotelPassengerId == null) {
            return null;
        }
        if (hotelBooking.getHotelPassengers().isEmpty()) {
            return null;
        }
        Iterator<HotelPassenger> iterator = hotelBooking.getHotelPassengers().iterator();
        while (iterator.hasNext()) {
            HotelPassenger hotelPassenger = iterator.next();

            if (hotelPassengerId.equalsIgnoreCase(hotelPassenger.getId())) {

                return hotelPassenger;
            }

        }
        return null;
    }

    public void setHotelPassenger(HttpServletRequest request, String hotelPassengerRows, HotelBooking hotelBooking) {
        int passengerRows = Integer.parseInt(hotelPassengerRows);
        if (passengerRows == 1) {
            return;
        }

        for (int i = 1; i < passengerRows; i++) {
            String id = request.getParameter("row-passenger-" + i + "-id");
            String name = request.getParameter("row-passenger-" + i + "-name");

            System.out.println("hotelPassenger Id=" + id);
            HotelPassenger hotelPassenger = getHotelPassenger(id, hotelBooking);
            if (hotelPassenger == null) {
                hotelPassenger = new HotelPassenger();
            }

            hotelPassenger.setPassenger(passengerService.getPassengerFromID(name));
            if (!"".equalsIgnoreCase(name)) {
                if (name != null) {
                    if (StringUtils.isNotEmpty(hotelPassenger.getPassenger().getId())) {
                        if (hotelPassenger.getId() == null) {
                            System.out.println("getPassgner = " + hotelPassenger.getId());
                            hotelBooking.getHotelPassengers().add(hotelPassenger);
                            hotelPassenger.setHotelBooking(hotelBooking);
                        }
                    } else {
                        System.out.println("Passenger is null, Not update DB this object " + i);
                    }
                }
            }

        }
    }

    public void setResponseAttribute(HttpServletRequest request, String refNo) {

        List<Passenger> passengerList = passengerService.getPassengerFromRefno(refNo);
        request.setAttribute(PassengerList, passengerList);

        List<MMeal> mMealsList = utilservice.getListMMeal();
        request.setAttribute(MMealsList, mMealsList);

        List<MItemstatus> mItemstatusesList = utilservice.getListMItemstatus();
        request.setAttribute(MItemstatusesList, mItemstatusesList);
        Hotel h = new Hotel();

        List<Hotel> hotelsList = hotelService.getListHotel(h, 1);
        request.setAttribute(HotelList, hotelsList);
        
        List<MCurrency> mCurrency = utilservice.getListMCurrency();
        request.setAttribute(MCurrency, mCurrency);

        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        // Mbookstatus ==> 2 Finish , 5 Finish by Finance
        if(("1").equals(String.valueOf(master.getFlagHotel())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
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

    public BookingHotelService getBookingHotelService() {
        return bookingHotelService;
    }

    public void setBookingHotelService(BookingHotelService bookingHotelService) {
        this.bookingHotelService = bookingHotelService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public void setHotelService(HotelService hotelService) {
        this.hotelService = hotelService;
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
    
    public void saveHistoryBooking(String refNo,SystemUser user,HotelBooking hotelBooking,String action) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        HistoryBooking historyBooking = new HistoryBooking();
        historyBooking.setHistoryDate(new Date());
        historyBooking.setAction(action+" HOTEL BOOKING");
        String detail = "";
        if(!"VIEW".equalsIgnoreCase(action)){
            detail = "HOTEL : " ;
            if(hotelBooking.getHotel() != null){
                detail += hotelBooking.getHotel().getCode() + "\r\n" ;
            }else{
             detail += "\r\n" ;
            }
            detail += "PAX : ADT : " + hotelBooking.getAdult() + " CHD : " + hotelBooking.getChild() + " INF : " + hotelBooking.getInfant() + "\r\n"
            + "CHECK IN-OUT : " ;
            if(hotelBooking.getCheckin() != null){
                detail += String.valueOf(df.format(hotelBooking.getCheckin()))  + " / " ;
            }
            if(hotelBooking.getCheckout() != null){
                detail += String.valueOf(df.format(hotelBooking.getCheckout())) ;
            }
        }
        historyBooking.setDetail(detail);
        historyBooking.setMaster(hotelBooking.getMaster());
        historyBooking.setStaff(user);
        int resultsave = utilservice.insertHistoryBooking(historyBooking);
        
    }
}
