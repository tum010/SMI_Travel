/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.AirticketRefund;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.RefundAirticket;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.RefundTicketDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.service.MStaffService;
import com.smi.travel.datalayer.service.RefundService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.RefundTicket;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author top
 */
public class RefundController extends SMITravelController {
    
    private static final ModelAndView Refund = new ModelAndView("Refund");
    private UtilityService utilservice;
    private MStaffService mStaffService;
    private RefundService refundService;
    private static final String BookingSize = "BookingSize";
    private static final String Master = "Master";
    private static final String Staff = "Staff";
    private static final String Action = "Action";
    UtilityFunction utilty = new UtilityFunction();

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String airbookingid = request.getParameter("airbookingid");
        
        request.setAttribute("actionAdd", action);
        request.setAttribute("airbookingid", airbookingid);
        request.setAttribute("referenceNo", refNo);
        
        if ("new".equalsIgnoreCase(action)) {
        } else if ("edit".equalsIgnoreCase(action)) {
            request.setAttribute(Action, "update");
        } else if ("update".equalsIgnoreCase(action)) {

        }
        // Refund By
        List<CustomerAgentInfo> listRefundBy = utilservice.getListCustomerAgentInfo();
        if(listRefundBy != null){
            request.setAttribute("listRefundBy", listRefundBy);
        }else{
            request.setAttribute("listRefundBy", null);
        }
        
        // Receive by
        List<SystemUser> listReceiveBy = utilservice.getUserList();
        if(listReceiveBy != null){
            request.setAttribute("listReceiveBy", listReceiveBy);
        }else{
            request.setAttribute("listReceiveBy", null);
        }
        
        // Refund by user
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String refundbyid = user.getId();
        String refundby = user.getUsername();
        String refundname = user.getName();
        request.setAttribute("refundbyidDefault", refundbyid);
        request.setAttribute("refundbyDefault", refundby);
        request.setAttribute("refundnameDefault", refundname);
        request.setAttribute("thisdate", utilty.convertDateToString(new Date()));
        
        //Select Ticket No
        List<AirticketPassenger> listTicketNo = new LinkedList<AirticketPassenger>();
        listTicketNo = refundService.selectTicketNo(refNo);
        if(listTicketNo != null){
            request.setAttribute("listTicketNo", listTicketNo);
        }else{
            request.setAttribute("listTicketNo", null);
        }
        
        //Search Refund Ticket
        List<RefundTicket> listRefundTicket = refundService.searchRefundTicket(airbookingid);
        if(listRefundTicket != null){
            request.setAttribute("listRefundTicket", listRefundTicket);
        }else{
            request.setAttribute("listRefundTicket", null);
        }
            
        // Action 
        if("searchRefund".equals(action)){
            String refundid = request.getParameter("refundid");
            searchRefundAction(refundid,request);
        }else if("saveRefund".equals(action)){
            String refundid = request.getParameter("refundById");
            AirticketRefund airticketRefund = new AirticketRefund();
            AirticketBooking airticketBooking = new AirticketBooking();
            RefundAirticket refund = new RefundAirticket();
            refund = getRefundFromPage(request,airbookingid);
            
            if(refundid != null &&  !"".equals(refundid)){
                action = "editRefund";
            }
            List<RefundAirticketDetail> airticketrefundList = setRefundDetail(request,refund,action);
            refund.setRefundAirticketDetails(airticketrefundList);
            
            airticketBooking.setId(airbookingid);
            
            airticketRefund.setId(refundid);
            airticketRefund.setRefundAirticket(refund);
            airticketRefund.setAirticketBooking(airticketBooking);
            
            String resultsave = refundService.saveRefund(airticketRefund);
            if(!"".equals(resultsave)){
                request.setAttribute("result", resultsave);
                List<RefundTicket> refundTicket = refundService.searchRefund(refund);
                List<RefundTicketDetail> refundTicketDetail = new LinkedList<RefundTicketDetail>();
                if(refundTicket != null){
                    request.setAttribute("RefundTicket", refundTicket);
                    request.setAttribute("listRefundTicket", refundTicket);
                    refundTicketDetail = refundTicket.get(0).getRefundTicketDetail();
                    if(refundTicketDetail != null ){
                        request.setAttribute("RefundTicketDetail", refundTicketDetail);
                    }else{
                        request.setAttribute("RefundTicketDetail", refundTicketDetail);
                    }
                }else{
                    request.setAttribute("listRefundTicket", null);
                    request.setAttribute("RefundTicket", null);
                }
            }else{
                request.setAttribute("result", null);
            }
            System.out.println("Result Save : " + resultsave);
            
        }else if("add".equals(action)){
            request.setAttribute("RefundTicket", null);
        }else if("deleteAirTicketRefund".equals(action)){
            String airbookingid_del = request.getParameter("refundById");
            String refundid_del = request.getParameter("refundid");
            String result_delete = refundService.deleteAirticketRefund(airbookingid_del,refundid_del);
            System.out.println("Result Delete Airticket Refund : " + result_delete);
            request.setAttribute("result", result_delete);
                List<RefundTicket> listRefundTicketDel = refundService.searchRefundTicket(airbookingid);
                if(listRefundTicket != null){
                    request.setAttribute("listRefundTicket", listRefundTicketDel);
                }else{
                    request.setAttribute("listRefundTicket", null);
                }
        }
        
        setGeneralResponseAttribute(request, refNo);
        return Refund;
    }
    private void searchRefundAction(String refundid,HttpServletRequest request){
        List<RefundTicket> refundTicket = refundService.listRefundDetail(refundid);
        List<RefundTicketDetail> refundTicketDetail = refundTicket.get(0).getRefundTicketDetail();
        if(refundTicket != null){
            request.setAttribute("RefundTicket", refundTicket);
            request.setAttribute("listRefundTicket", refundTicket);

            if(refundTicketDetail != null ){
                request.setAttribute("RefundTicketDetail", refundTicketDetail);
            }else{
                request.setAttribute("RefundTicketDetail", refundTicketDetail);
            }
        }else{
            request.setAttribute("listRefundTicket", null);
            request.setAttribute("RefundTicket", null);
        }
    }
    
    private RefundAirticket getRefundFromPage(HttpServletRequest request,String airbookingid){
            String refundticketid = request.getParameter("refundticketid");
            String refundcodePage = request.getParameter("refundBy");
            String refundnamePage = request.getParameter("refundByName");
            String receivecodePage = request.getParameter("receiveBy");
            String receivenamePage = request.getParameter("receiveByName");
            String refunddatePage = request.getParameter("refundDate");
            String receivedatePage = request.getParameter("receiveDate");
            String addressPage = request.getParameter("address");
            String cancelDetailPage = request.getParameter("cancelDetail");
            String chargePage = request.getParameter("charge");
            AirticketRefund airticketRefund = new AirticketRefund();
            RefundAirticket refund = new RefundAirticket();
            refund.setId(null);
            if(refundticketid != null && !"".equals(refundticketid)){
                refund.setId(refundticketid);
            }else{
                refund.setId("");
            }
            
            if(refundcodePage != null && !"".equals(refundcodePage)){
                refund.setRefundBy(refundcodePage);
            }else{
                refund.setRefundBy("");
            }
            
            if(refunddatePage != null && !"".equals(refunddatePage)){
                Date due = utilty.convertStringToDate(refunddatePage);
                refund.setRefundDate(due);
            }else{
                refund.setRefundBy("");
            }
            
            if(receivecodePage != null && !"".equals(receivecodePage)){
                refund.setReceiveBy(receivecodePage);
            }else{
                refund.setRefundBy("");
            }
            
            if(receivedatePage != null && !"".equals(receivedatePage)){
                Date due = utilty.convertStringToDate(receivedatePage);
                refund.setReceiveDate(due);
            }else{
                refund.setRefundBy("");
            }
            
            refund.setStatus(0);
        return refund;
    }
    
    private List setRefundDetail(HttpServletRequest request,RefundAirticket refund,String action){
        List<RefundAirticketDetail> airticketrefundList = new LinkedList<RefundAirticketDetail>();
        if("saveRefund".equals(action)){
            String refundRowAdd = request.getParameter("counterTableAdd");
            int rowAdd = Integer.parseInt(refundRowAdd);
            
            for (int i = 1; i <= rowAdd ; i++) {
                String refunddetailidadd = request.getParameter("airticketrefunddetailidadd"+i);
                String ticketnoadd = request.getParameter("SelectTocketNoadd"+i);
                String sectoradd = request.getParameter("inputSectoradd"+i);
                String sectorrefundadd = request.getParameter("inputSectorRefundadd"+i);
                String chargeadd = request.getParameter("inputChargeadd"+i);
                RefundAirticketDetail refundAirticketDetail = new RefundAirticketDetail();
                //refund detail id 
                if(refunddetailidadd != null && !"".equals(refunddetailidadd)){
                    refundAirticketDetail.setId(refunddetailidadd);
                }else{
                    refundAirticketDetail.setId(null);
                }
               
                // ticket passenger id
                AirticketPassenger passenger = new AirticketPassenger();
                if(ticketnoadd != null && !"".equals(ticketnoadd)){               
                    passenger.setId(ticketnoadd);
                    refundAirticketDetail.setAirticketPassenger(passenger);
                }else{
                    refundAirticketDetail.setAirticketPassenger(passenger);
                }
                // sector refund
                if(sectorrefundadd != null && !"".equals(sectorrefundadd)){
                    refundAirticketDetail.setSectorRefund(sectorrefundadd);
                }else{
                    refundAirticketDetail.setSectorRefund(null);
                }
                //pay customer
                if(chargeadd != null && !"".equals(chargeadd)){
                    BigDecimal chargeInt =  new BigDecimal(chargeadd.replaceAll(",", ""));
                    refundAirticketDetail.setPayCustomer(chargeInt);
                }else{
                    refundAirticketDetail.setPayCustomer(new BigDecimal(0.0));
                }
                // Add List Not Null from web page
                if(ticketnoadd != null && !"".equals(ticketnoadd)){
                    refundAirticketDetail.setRefundAirticket(refund);
                    airticketrefundList.add(refundAirticketDetail);
                }
            }
        }else{
            String refundRow = request.getParameter("counterTable");
            int row = Integer.parseInt(refundRow);
            
            for (int i = 1; i <= row ; i++) {
                String refunddetailid = request.getParameter("airticketrefunddetailid"+i);
                String ticketno = request.getParameter("SelectTocketNo"+i);
                String sector = request.getParameter("inputSector"+i);
                String sectorrefund = request.getParameter("inputSectorRefund"+i);
                String charge = request.getParameter("inputCharge"+i);
                RefundAirticketDetail refundAirticketDetail = new RefundAirticketDetail();
            
                //refund detail id 
                if(refunddetailid != null && !"".equals(refunddetailid)){
                    refundAirticketDetail.setId(refunddetailid);
                }
                 // ticket passenger id
                AirticketPassenger passenger = new AirticketPassenger();
                if(ticketno != null && !"".equals(ticketno)){               
                    passenger.setId(ticketno);
                    refundAirticketDetail.setAirticketPassenger(passenger);
                }else{
                    refundAirticketDetail.setAirticketPassenger(passenger);
                }
                // sector refund
                if(sectorrefund != null && !"".equals(sectorrefund)){
                    refundAirticketDetail.setSectorRefund(sectorrefund);
                }else{
                    refundAirticketDetail.setSectorRefund(null);
                }
                //pay customer
                if(charge != null && !"".equals(charge)){
                    BigDecimal chargeInt =  new BigDecimal(charge.replaceAll(",", ""));
                    refundAirticketDetail.setPayCustomer(chargeInt);
                }else{
                    refundAirticketDetail.setPayCustomer(new BigDecimal(0.0));
                }
                // Add List Not Null from web page
                if(ticketno != null && !"".equals(ticketno)){
                    refundAirticketDetail.setRefundAirticket(refund);
                    airticketrefundList.add(refundAirticketDetail);
                }
            }
        }
        
        return airticketrefundList;
    }

    private void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        request.setAttribute(BookingSize, booksize);
        SystemUser staff = new SystemUser();
        List<SystemUser> staffList = mStaffService.searchSystemUser(staff, 2);
        request.setAttribute(Staff, staffList);
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public MStaffService getmStaffService() {
        return mStaffService;
    }

    public void setmStaffService(MStaffService mStaffService) {
        this.mStaffService = mStaffService;
    }

    public RefundService getRefundService() {
        return refundService;
    }

    public void setRefundService(RefundService refundService) {
        this.refundService = refundService;
    }

}
