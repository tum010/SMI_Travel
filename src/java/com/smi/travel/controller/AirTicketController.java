/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.master.controller.SMITravelController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.AirticketDesc;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketFlight;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.BookingPnr;
import com.smi.travel.datalayer.entity.HotelBooking;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MAirticketService;
import com.smi.travel.datalayer.service.BookingHotelService;
import com.smi.travel.datalayer.entity.MAirline;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.util.UtilityFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author sumeta
 */
public class AirTicketController extends SMITravelController {

    private static final ModelAndView AirTicket = new ModelAndView("AirTicket");
    private static final ModelAndView AirTicket_REFRESH = new ModelAndView(new RedirectView("AirTicket.smi", true));
    private BookingAirticketService bookingAirticketService;
    private MStaffService mStaffService;
    private MAirticketService mAirticketService;
    private BookingHotelService bookingHotelService;
    private UtilityService utilservice;
    private UtilityFunction util;

    private static final String airTicketPnrList = "AirTicketPnr";
    private static final String IssueBy = "IssueBy";
    private static final String OwnerBy = "OwnerBy";
    private static final String AirticketBooking = "AirticketBooking";
    private static final String Staff_List = "Staff_List";
    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "master";

    private static final String AirticketDescsList = "AirticketDescsList";
    private static final String ACTION = "action";
    private static final String AddButton = "AddButton";
    private static final String TransactionResult = "result";
    private static final String Result = "Result";
    private static final String[] resultText = {"Unsuccesful save", "Save successful"};
    private static final String LockUnlockBooking = "LockUnlockBooking";


    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String airBookingId = request.getParameter("airBookingId");
        String airDescRows = request.getParameter("counter");
        SystemUser owner = null;
        SystemUser issue = null;
        System.out.println(AirTicketController.class
                .getName() + " action=[" + action + "] refNo[" + refNo + "],[airId=" + airBookingId + "] [airDescrows=" + airDescRows + "]");

        if ("new".equalsIgnoreCase(action)) {
            System.out.println("AirTicketController new not supported yet");
//            request.setAttribute(ACTION, "new");
        } else if ("edit".equalsIgnoreCase(action)) {
            System.out.println("AirTciketController edit ");
            AirticketBooking airBook = bookingAirticketService.getBookDetailAir(refNo);

            int[] booksize = utilservice.getCountItemFromBooking(refNo);
            request.setAttribute(Bookiing_Size, booksize);
            if (airBook != null) {
                System.out.println("AirBook is not null");
                TreeSet<AirticketPnr> sortedPnr = new TreeSet<AirticketPnr>(new AirticketPnrComparator());

                sortedPnr.addAll(airBook.getAirticketPnrs());
                List<AirticketPnr> pnrList = new ArrayList<AirticketPnr>();
                pnrList.addAll(sortedPnr);
                request.setAttribute(airTicketPnrList, pnrList);

                if (pnrList != null) {
                    request.setAttribute(AirticketBooking, airBook);

                    List<AirticketDesc> airticketDescsList = new ArrayList<AirticketDesc>(airBook.getAirticketDescs());
                    request.setAttribute(AirticketDescsList, airticketDescsList);
                }

                owner = airBook.getStaffByOwnerBy();
                request.setAttribute(OwnerBy, owner);

                SystemUser issueBy = airBook.getStaffByIssueBy();
                request.setAttribute(IssueBy, issueBy);
            } else {
                //airBook is null;
                SystemUser user = (SystemUser) session.getAttribute("USER");
                request.setAttribute(OwnerBy, user);
                request.setAttribute(IssueBy, user);
                request.setAttribute(AddButton, "disabled");
            }

            request.setAttribute(ACTION, "update");
        } else if ("update".equalsIgnoreCase(action)) {
            String ownerName = request.getParameter("staff_username");
            String issueName = request.getParameter("issue_username");
            String deadline = request.getParameter("get_deadline");
            String reconfirm = request.getParameter("reconfirm");
            String remark = request.getParameter("remark");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date deadlineDate = null;
            try {
                deadlineDate = formatter.parse(deadline);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("[ownerName=" + ownerName + "], [issueName=" + issueName + "], [deadline=" + deadline + "]");
            AirticketBooking airBook = bookingAirticketService.getBookDetailAir(refNo);

            if (airBook == null) {
                airBook = new AirticketBooking();

                owner = getUpdateSystemUserByName(ownerName, airBook.getStaffByOwnerBy());
                System.out.println("owner " + owner);
                airBook.setStaffByOwnerBy(owner);
//                System.out.println("OwnerName " + ownerName + " Owner name" + owner.getName() + ", [OwnerId=" + owner.getId() + "]");

                issue = getUpdateSystemUserByName(issueName, airBook.getStaffByIssueBy());
                airBook.setStaffByIssueBy(issue);
                airBook.setReConfirm(reconfirm);
                airBook.setRemark(remark);
                airBook.setDeadline(deadlineDate);
                Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
                master.setFlagAir(new Integer("1"));
                airBook.setMaster(master);

                setAirticketDescRows(request, airDescRows, airBook);

                //                master.getAirticketBookings().add(airBook);
                result = bookingAirticketService.insertBookingAirTicket(airBook);

            } else {
                airBook = bookingAirticketService.getBookDetailAir(refNo);
                System.out.println("airBook id=" + airBook.getId());

                issue = getUpdateSystemUserByName(issueName, airBook.getStaffByIssueBy());
                owner = getUpdateSystemUserByName(ownerName, airBook.getStaffByOwnerBy());
                airBook.setStaffByIssueBy(issue);
                airBook.setStaffByOwnerBy(owner);
                airBook.setReConfirm(reconfirm);
                airBook.setDeadline(deadlineDate);
                airBook.setRemark(remark);
                setAirticketDescRows(request, airDescRows, airBook);
                System.out.println("updateBookingAirticket-----");
                result = bookingAirticketService.updateBookingAirTicket(airBook);
            }
            request.setAttribute(Result, result);
            return new ModelAndView("redirect:AirTicket.smi?referenceNo=" + refNo + "&action=edit&result=" + result);
        } else if ("disablePnr".equalsIgnoreCase(action)) {
            String pnrId = request.getParameter("disablePnrId");
            result = bookingAirticketService.cancelBookAirticketPNR(pnrId);
            System.out.println("Mark PNR ID[" + pnrId + "] as disable");
            return new ModelAndView("redirect:AirTicket.smi?referenceNo=" + refNo + "&action=edit&result=" + result);
        } else if ("enablePnr".equalsIgnoreCase(action)) {
            String pnrId = request.getParameter("enablePnrId");
            result = bookingAirticketService.enableBookAirticketPNR(pnrId);
            System.out.println("Mark PNR ID[" + pnrId + "] as enable");
            return new ModelAndView("redirect:AirTicket.smi?referenceNo=" + refNo + "&action=edit&result=" + result);
        } else if ("deleteDesc".equalsIgnoreCase(action)) {
            String bId = request.getParameter("bId");
            String dId = request.getParameter("dId");
            AirticketBooking airticketBooking = bookingAirticketService.getBookDetailAir(bId);
            result = bookingAirticketService.DeleteDesc(airticketBooking, dId);
        } else {
            AirticketBooking airBook = bookingAirticketService.getBookDetailAir(refNo);
            if (airBook == null) {
                System.out.println("AirBook is null");
                request.setAttribute(AddButton, "disabled");
            } else {

                if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                    System.out.println("*****This should not be executed!!!!");
                    request.setAttribute(ACTION, "edit");
                } else {

                    AirticketBooking AirBook = bookingAirticketService.getBookDetailAir(refNo);
                    List<AirticketPnr> pnrList = new ArrayList<AirticketPnr>(AirBook.getAirticketPnrs());
                    request.setAttribute(airTicketPnrList, pnrList);

                    AirticketBooking airticketBooking = pnrList.get(0).getAirticketBooking();
                    request.setAttribute(AirticketBooking, airticketBooking);

                    List<AirticketDesc> airticketDescsList = new ArrayList<AirticketDesc>(AirBook.getAirticketDescs());
                    request.setAttribute(AirticketDescsList, airticketDescsList);

                    SystemUser ownerBy = airticketBooking.getStaffByOwnerBy();
                    System.out.println("ownerBy : " + ownerBy.getName());
                    request.setAttribute(OwnerBy, ownerBy);

                    SystemUser issueBy = airticketBooking.getStaffByIssueBy();
                    request.setAttribute(IssueBy, issueBy);

                }
            }
        }

        setGeneralResponseAttribute(request, refNo);

        return AirTicket;
    }

    private void setAirticketDescRows(HttpServletRequest request, String airDescRows, AirticketBooking airBook) {
        util = new UtilityFunction();
        int descRows = Integer.parseInt(airDescRows);
        if (descRows == 1) {
            return;
        }

        for (int i = 0; i < descRows; i++) {
            String id = request.getParameter("row-" + i + "-id");
            String detail = request.getParameter("row-" + i + "-detail");
            String qty = request.getParameter("row-" + i + "-qty");
            String cost = request.getParameter("row-" + i + "-cost");
            String amount = request.getParameter("row-" + i + "-amount");
            Integer qtyInt = null;
            Integer costInt = null;
            Integer amountInt = null;

            if (StringUtils.isNotEmpty(qty)) {
                qtyInt = util.convertStringToInteger(qty);
            }

            if (StringUtils.isNotEmpty(cost)) {
                costInt = util.convertStringToInteger(cost);
            }

            if (StringUtils.isNotEmpty(amount)) {
                amountInt = util.convertStringToInteger(amount);
            }

            System.out.println("airDesc Id=" + id);
            AirticketDesc airDesc = getAirDesc(id, airBook);
            if (airDesc == null) {
                airDesc = new AirticketDesc();
            }
            airDesc.setDetail(detail);
            airDesc.setQty(qtyInt);
            airDesc.setCost(costInt);
            airDesc.setAmount(amountInt);

            if (StringUtils.isNotEmpty(airDesc.getDetail())) {
                if (airDesc.getId() == null) {
                    airDesc.setIsBill(new Integer(0));
                    airBook.getAirticketDescs().add(airDesc);
                    airDesc.setAirticketBooking(airBook);
                }
            } else {
                System.out.println("Detail is null, Not update DB this object " + i);
            }
        }
        //List<AirticketDesc> airticketDescsList = new ArrayList<AirticketDesc>(airBook.getAirticketDescs());
        //request.setAttribute(AirticketDescsList, airticketDescsList);
    }

    private AirticketDesc getAirDesc(String airDescId, AirticketBooking airBook) {

        if (airDescId == null) {
            return null;
        }

        if (airBook.getAirticketDescs().isEmpty()) {
            return null;
        }
        Iterator<AirticketDesc> iterator = airBook.getAirticketDescs().iterator();
        while (iterator.hasNext()) {
            AirticketDesc airDesc = iterator.next();

            if (airDescId.equalsIgnoreCase(airDesc.getId())) {
                return airDesc;
            }

        }

        return null;
    }

    private void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        SystemUser staff = new SystemUser();
        List<SystemUser> StaffList = mStaffService.searchSystemUser(staff, 0);
        request.setAttribute(Staff_List, StaffList);

        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);

        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(Bookiing_Size, booksize);
        
        // Mbookstatus ==> 2 Finish , 5 Finish by Finance
        if(("1").equals(String.valueOf(master.getFlagAir())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
    }

    private SystemUser getUpdateSystemUserByName(String name, SystemUser existUser) {
        System.out.println("username " + name);
        System.out.println("existUser " + existUser);
        if (name == null) {
            System.out.println("getUpdateSystemUser return name null");
            return null;
        }

        SystemUser newUser = new SystemUser();
        if ((existUser == null)
                || (!name.equalsIgnoreCase(existUser.getName()))) {
            newUser.setUsername(name);
            List<SystemUser> StaffList = mStaffService.searchSystemUser(newUser, 1);
            if (StaffList == null) {
                System.out.println("getUpdateSystemUserByName return null name[" + name + "]");
                return null;
            }
            return StaffList.get(0);
        } else {
            System.out.println("return ExistUser");
            return existUser;

        }
    }

    private String getResultText(int result) {
        return resultText[result];
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public MStaffService getMStaffService() {
        return mStaffService;
    }

    public void setMStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public MAirticketService getMAirticketService() {
        return mAirticketService;
    }

    public void setMAirticketService(MAirticketService mAirticketService) {
        this.mAirticketService = mAirticketService;
    }

    public BookingHotelService getBookingHotelService() {
        return bookingHotelService;
    }

    public void setBookingHotelService(BookingHotelService bookingHotelService) {
        this.bookingHotelService = bookingHotelService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    class AirticketPnrComparator implements Comparator<AirticketPnr> {

        public AirticketPnrComparator() {
        }

        @Override
        public int compare(AirticketPnr p1, AirticketPnr p2) {
            int p1Id = Integer.parseInt(p1.getId());
            int p2Id = Integer.parseInt(p2.getId());
            return Integer.compare(p1Id, p2Id);

        }
    }
}
