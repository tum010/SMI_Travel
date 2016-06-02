package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.service.PassengerService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.BillableView;
import com.smi.travel.datalayer.view.entity.OtherTicketView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class OtherDetailController extends SMITravelController {

    private static final ModelAndView OtherDetail = new ModelAndView("OtherDetail");
    private static final String Booking_Size = "BookingSize";
    private static final String PRODUCTLIST = "product_list";
    private static final String AGENTLIST ="agent_list";
    private static final String TransectionResult = "result";
    private static final String BOOKINGTYPE = "BOOKING_TYPE";
    private static final String CurrencyList = "currency_list";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private static final String EnableSave = "EnableSave";
    private static final String PASSENGERID = "passengerId";
    private UtilityService utilservice;
    private BookingOtherService OtherService;
    private PassengerService passsengerService;
    private UtilityFunction util;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String callPageFrom = request.getParameter("callPageFrom");
        String callpageSubmit  = request.getParameter("callpageSubmit");
        String itemid = request.getParameter("itemid");
        String productName = request.getParameter("product_name");
        String productCode = request.getParameter("product_code");
        String productId = request.getParameter("product_id");
        String agentId = request.getParameter("agent_id");
        String agentCode = request.getParameter("agent_code");
        String agentName = request.getParameter("agent_name");
        String adCost = request.getParameter("ad_cost");
        String adQty = request.getParameter("ad_qty");
        String adPrice = request.getParameter("ad_price");
        String chCost = request.getParameter("ch_cost");
        String chQty = request.getParameter("ch_qty");
        String chPrice = request.getParameter("ch_price");
        String inCost = request.getParameter("in_cost");
        String inQty = request.getParameter("in_qty");
        String inPrice = request.getParameter("in_price");
        String otherdate = request.getParameter("otherdate");
        String otherdateTo = request.getParameter("otherdateTo");
        String othertime = request.getParameter("othertime");
        String canceldate = request.getParameter("canceldate");
        String remark = request.getParameter("remark");
        String refno = request.getParameter("referenceNo");
        String status = request.getParameter("status");
        String isbill = request.getParameter("isbill");
        String currency = request.getParameter("currency");
        String currencycost = request.getParameter("currencycost");
        String createby = request.getParameter("createby");
        String createdate = request.getParameter("createdate");
        String ticketstatus = request.getParameter("ticketstatus");
        String addticket = request.getParameter("addticket");
        String adTicket = request.getParameter("adTicket");
        String chTicket = request.getParameter("chTicket");
        String infTicket = request.getParameter("infTicket");       
        String counter = request.getParameter("counter");
        String memo = request.getParameter("memo");
        String resultsave = request.getParameter("resultsave");
                         
        SystemUser user = (SystemUser) session.getAttribute("USER");
        
        request.setAttribute(ISBILLSTATUS,0);
        
        util = new UtilityFunction();
        if (refno != null) {
            int[] booksize = utilservice.getCountItemFromBooking(refno);
            request.setAttribute(Booking_Size, booksize);
        }
    
        Master master = utilservice.getbookingFromRefno(refno);
        if((callPageFrom!=null) && (callPageFrom.equalsIgnoreCase("FromDayTour"))){
            request.setAttribute("callpage", callPageFrom);       
        }               
                
        if((callPageFrom!=null) && (callPageFrom.equalsIgnoreCase("FromOther"))){
            request.setAttribute("callpage", callPageFrom);
        }
        
        List<Passenger> passengerList = passsengerService.getPassengerFromRefno(refno);
        if(passengerList != null){
            String passengerId = "";
            for(Passenger passenger : passengerList){
                Customer customer = passenger.getCustomer();
                passengerId += (!"".equalsIgnoreCase(passengerId) ? "," + customer.getId() : customer.getId());
                request.setAttribute(PASSENGERID, passengerId);       
            }
        }
        
        if(action.equalsIgnoreCase("new")){
            adQty = String.valueOf(master.getAdult());
            chQty = String.valueOf(master.getChild());
            inQty = String.valueOf(master.getInfant());
            request.setAttribute(EnableSave,0);
        }
        String bookTypeNo = "";
        request.setAttribute("Master", master);
        System.out.println("agentId : "+agentId);
        String BookType = master.getBookingType();
        if("I".equalsIgnoreCase(BookType)){
            request.setAttribute(BOOKINGTYPE,"i");
            bookTypeNo = "8";
        }else{
            request.setAttribute(BOOKINGTYPE,"o");
            bookTypeNo = "2";
        }

        request.setAttribute("currency", master.getCurrency());
        request.setAttribute("currencycost", master.getCurrency());
           
        if ("save".equalsIgnoreCase(action)) {
            OtherBooking Other = new OtherBooking();
            if(!"".equalsIgnoreCase(itemid)){
                Other.setId(itemid);
            }
            Product product = new Product();
            product.setId(productId);
            product.setCode(productCode);
            product.setName(productName);
            System.out.println("productId :"+productId);
            System.out.println("agentId :"+agentId);
            Other.setProduct(product);
            Other.setCurAmount(currency);
            Other.setCurCost(currencycost);
            
            System.out.println(" Other.getIsBill() " + Other.getIsBill());
            request.setAttribute(ISBILLSTATUS,Other.getIsBill());
            
            Agent agent = new Agent();
            if((agentId != null)&&(!"".equalsIgnoreCase(agentId))){
                System.out.println("setup agentId :"+agentId);
                agent.setId(agentId);
                agent.setCode(agentCode);
                agent.setName(agentName);
                Other.setAgent(agent);
            }
            if((status != null)&&(!"".equalsIgnoreCase(status))){
                MItemstatus Mstatus = new MItemstatus();
                System.out.println("setup staus :"+agentId);
                Mstatus.setId(status);
                Other.setStatus(Mstatus);
            }
            Other.setAdCost(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(adCost) ? adCost.replaceAll(",","") : 0)));
            Other.setAdPrice(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(adPrice) ? adPrice.replaceAll(",","") : 0)));
            Other.setAdQty(util.convertStringToInteger(adQty));
            Other.setChCost(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(chCost) ? chCost.replaceAll(",","") : 0)));
            Other.setChPrice(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(chPrice) ? chPrice.replaceAll(",","") : 0)));
            Other.setChQty(util.convertStringToInteger(chQty));
            Other.setInCost(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(inCost) ? inCost.replaceAll(",","") : 0)));
            Other.setInPrice(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(inPrice) ? inPrice.replaceAll(",","") : 0)));
            Other.setInQty(util.convertStringToInteger(inQty));
            Other.setCreateDate(util.convertStringToDate(createdate));
            if(!("").equalsIgnoreCase(isbill)){
                Other.setIsBill(Integer.parseInt(isbill));
            }
            
            if(remark != null){
                Other.setRemark(remark.trim());
            }
            if(memo != null){
                Other.setMemo(memo.trim());
            }
            Other.setMaster(utilservice.getbookingFromRefno(refno));
            if(!"".equalsIgnoreCase(otherdate)){
                Other.setOtherDate(util.convertStringToDate(otherdate));
            }
            if(!"".equalsIgnoreCase(othertime)){
                Other.setOtherTime(util.convertStringToTime(othertime));
            }
            if(!"".equalsIgnoreCase(canceldate)){
                Other.setCancelDate(util.convertStringToTime(canceldate));
            }
            if(!"".equalsIgnoreCase(otherdateTo)){
                Other.setOtherDateTo(util.convertStringToDate(otherdateTo));
            }
            if(Other.getId() != null){
                saveHistoryBooking(refno,user,Other,"UPDATE");
            }else{
                saveHistoryBooking(refno,user,Other,"CREATE");
            }  
//            int result = OtherService.saveBookingOther(Other,user);
            List<String> result = OtherService.saveBookingOther(Other,user,createby);
            
            String billDescId = utilservice.getBillableDescId(itemid, bookTypeNo);
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
                BillableView billableView = utilservice.getBillableDescByBookId(itemid,bookTypeNo);
                int resultupdate = utilservice.updateBillableDesc(billableView,billDescId);
            }

            if(("1".equalsIgnoreCase(result.get(0))) && (callpageSubmit==null || !callpageSubmit.equalsIgnoreCase("FromDayTour"))){
                String stock = OtherService.saveStockDetailOther(Other, user, addticket, adTicket, chTicket, infTicket, itemid);
                if("notStock".equalsIgnoreCase(stock)){
//                    ModelAndView OTHER = new ModelAndView(new RedirectView("Other.smi?referenceNo="+refno+"&result=1", true));
                    ModelAndView OTHER = new ModelAndView(new RedirectView("OtherDetail.smi?referenceNo="+refno+"&resultsave=1&itemid="+result.get(1)+"&action=edit&callPageFrom=FromOther", true));
                    return OTHER;
                }else if("fail".equalsIgnoreCase(stock)){
                    request.setAttribute("resultText", "unsuccess");
                }else {
                    if((!"".equalsIgnoreCase(ticketstatus)) && (ticketstatus != null)){
                        int row = Integer.parseInt(counter);
                        for(int i=1;i<row;i++){
                            String selectAll = request.getParameter("selectAll" + i);
                            if("1".equalsIgnoreCase(selectAll)){
                                String stockticketid = request.getParameter("stockticketid" + i);
                                String resultTicket = OtherService.updateStockTicketStatus(stockticketid,ticketstatus);
                                request.setAttribute("resultTicket", resultTicket);
                            }
                        }                                             
                    } else {
                       request.setAttribute("resultText", "success"); 
                    }
                    String[] ticketData = stock.split("\\|\\|", 3);//Adult||Child||
                    if(("".equalsIgnoreCase(itemid)) || (!"".equalsIgnoreCase(addticket))){
                        request.setAttribute("adultCancel", ticketData[0]);
                        request.setAttribute("childCancel", ticketData[1]);
                        request.setAttribute("infantCancel", ticketData[2]);
                    }else{
                        request.setAttribute("adultCancel", "0");
                        request.setAttribute("childCancel", "0");
                        request.setAttribute("infantCancel", "0");
                    }
                    
                    getTicket(request, Other.getId());
                    itemid = result.get(1);
                    createby = Other.getCreateBy();
                    status = Other.getStatus().getId();
                    createdate = util.convertDateToString(Other.getCreateDate());
                    isbill = String.valueOf(Other.getIsBill());
                    
                    Other.setRemarkTicket("Require Ticket-Adult:" + ticketData[0] + " Child:" + ticketData[1] + " Infant:" + ticketData[2]);
                    List<String> resultRemarkTicket = OtherService.saveBookingOther(Other,user,createby);
//                    if(Other.getId() != null){
//                        saveHistoryBooking(refno,user,Other,"UPDATE");
//                    }else{
//                        saveHistoryBooking(refno,user,Other,"CREATE");
//                    }
                    
                }
            }else if(("1".equalsIgnoreCase(result.get(0))) && (callpageSubmit!=null) && (callpageSubmit.equalsIgnoreCase("FromDayTour"))){
                String stock = OtherService.saveStockDetailOther(Other, user, addticket, adTicket, chTicket, infTicket, itemid);
                if("notStock".equalsIgnoreCase(stock)){
                    ModelAndView DAYTOUR = new ModelAndView(new RedirectView("Daytour.smi?referenceNo="+refno+"&result=success", true));
                    return DAYTOUR;
                }else if("fail".equalsIgnoreCase(stock)){
                    request.setAttribute("resultText", "unsuccess");
                }else{
                   if((!"".equalsIgnoreCase(ticketstatus)) && (ticketstatus != null)){
                        int row = Integer.parseInt(counter);
                        for(int i=1;i<row;i++){
                            String selectAll = request.getParameter("selectAll" + i);
                            if("1".equalsIgnoreCase(selectAll)){
                                String stockticketid = request.getParameter("stockticketid" + i);
                                String resultTicket = OtherService.updateStockTicketStatus(stockticketid,ticketstatus);
                                request.setAttribute("resultTicket", resultTicket);
                            }
                        }                       
                    } else {
                       request.setAttribute("resultText", "success"); 
                    }
                    String[] ticketData = stock.split("\\|\\|", 3);//Adult||Child||Infant
                    if(("".equalsIgnoreCase(itemid)) || (!"".equalsIgnoreCase(addticket))){
                        request.setAttribute("adultCancel", ticketData[0]);
                        request.setAttribute("childCancel", ticketData[1]);
                        request.setAttribute("infantCancel", ticketData[2]);
                    }else{
                        request.setAttribute("adultCancel", "0");
                        request.setAttribute("childCancel", "0");
                        request.setAttribute("infantCancel", "0");
                    }
                    
                    getTicket(request, Other.getId());
                    itemid = result.get(1);
                    createby = Other.getCreateBy();
                    status = Other.getStatus().getId();
                    createdate = util.convertDateToString(Other.getCreateDate());
                    isbill = String.valueOf(Other.getIsBill());
                    
                    Other.setRemarkTicket("Require Ticket-Adult:" + ticketData[0] + " Child:" + ticketData[1] + " Infant:" + ticketData[2]);
                    List<String> resultRemarkTicket = OtherService.saveBookingOther(Other,user,createby);
//                    if(Other.getId() != null){
//                        saveHistoryBooking(refno,user,Other,"UPDATE");
//                    }else{
//                        saveHistoryBooking(refno,user,Other,"CREATE");
//                    }                    
                    request.setAttribute("adultCancel", ticketData[0]);
                    request.setAttribute("childCancel", ticketData[1]);
                    request.setAttribute("infantCancel", ticketData[2]);
                }    
            }else{
                request.setAttribute(TransectionResult, "save unsuccessful");
            }
            
        }
        if ("edit".equalsIgnoreCase(action)) {
            OtherBooking Other = OtherService.getBookDetailOtherFromID(request.getParameter("itemid"));
            System.out.println(" Other.getIsBill() " + Other.getIsBill());
            request.setAttribute(ISBILLSTATUS,Other.getIsBill());
            saveHistoryBooking(refno,user,Other,"VIEW");
            itemid = request.getParameter("itemid");
            Product pro = Other.getProduct();
            if (pro != null) {
                productId = pro.getId();
                productName = pro.getName();
                productCode = pro.getCode();
            }
            Agent agent = Other.getAgent();
            if (agent != null){
                agentId = agent.getId();
                agentName = agent.getName();
                agentCode = agent.getCode();
            }
            
            adCost = String.valueOf(Other.getAdCost());
            adQty = String.valueOf(Other.getAdQty());
            adPrice = String.valueOf(Other.getAdPrice());
            chCost = String.valueOf(Other.getChCost());
            chQty = String.valueOf(Other.getChQty());
            chPrice = String.valueOf(Other.getChPrice());
            inCost = String.valueOf(Other.getInCost());
            inQty = String.valueOf(Other.getInQty());
            inPrice = String.valueOf(Other.getInPrice());
            isbill = String.valueOf(Other.getIsBill());
            if(Other.getOtherDate() == null){
                otherdate = "";
            }else{
                otherdate = util.convertDateToString(Other.getOtherDate()); 
            }
            if(Other.getOtherTime() == null){
                othertime = "";
            }else{
                othertime = util.convertTimeToString(Other.getOtherTime());             
            }
            if(Other.getCancelDate() == null){
                canceldate = "";
            }else{
                canceldate = util.convertDateToString(Other.getCancelDate());
            }
            if(Other.getOtherDateTo() == null){
                otherdateTo = "";
            }else{
                otherdateTo = util.convertDateToString(Other.getOtherDateTo()); 
            }
            
            if(Other.getStatus() != null){
                status = Other.getStatus().getId();
            }
            remark = Other.getRemark();
            memo = Other.getMemo();
            currency = Other.getCurAmount();
            currencycost = Other.getCurCost();
            createby = Other.getCreateBy();
            status = Other.getStatus().getId();
            createdate = String.valueOf(Other.getCreateDate());
            isbill = String.valueOf(Other.getIsBill());
            request.setAttribute("currency", currency);
            request.setAttribute("currencycost", currencycost);
            getTicket(request, Other.getId());

            String billDescId = utilservice.getBillableDescId(itemid, bookTypeNo);
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
            }
        }
        
        if("1".equalsIgnoreCase(isbill)){
            request.setAttribute(ISBILLSTATUS,1);   
        }else{
            request.setAttribute(ISBILLSTATUS,0);   
        }
        
        if("1".equalsIgnoreCase(resultsave)){
            request.setAttribute("resultsave", "success");
        }else if("0".equalsIgnoreCase(resultsave)){
            request.setAttribute("resultsave", "unsuccess");
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        otherdate = (otherdate != null && !"".equalsIgnoreCase(otherdate) ? sdf.format(util.convertStringToDate(otherdate)) : "");
        otherdateTo = (otherdateTo != null && !"".equalsIgnoreCase(otherdateTo) ? sdf.format(util.convertStringToDate(otherdateTo)) : "");
        canceldate = (canceldate != null && !"".equalsIgnoreCase(canceldate) ? sdf.format(util.convertStringToDate(canceldate)) : "");
            
        request.setAttribute("isbill", isbill);
        request.setAttribute("status", status);
        request.setAttribute("itemid", itemid);
        request.setAttribute("product_id", productId);
        request.setAttribute("product_name", productName);
        request.setAttribute("agent_code", agentCode);
        request.setAttribute("product_code", productCode);
        request.setAttribute("agent_name", agentName);
        request.setAttribute("product_id", productId);
        request.setAttribute("agent_id", agentId);
        request.setAttribute("ad_cost", adCost);
        request.setAttribute("ad_qty", adQty);
        request.setAttribute("ad_price", adPrice);
        request.setAttribute("ch_cost", chCost);
        request.setAttribute("ch_qty", chQty);
        request.setAttribute("ch_price", chPrice);
        request.setAttribute("in_cost", inCost);
        request.setAttribute("in_qty", inQty);
        request.setAttribute("in_price", inPrice);
        request.setAttribute("in_price", inPrice);
        request.setAttribute("otherdate", otherdate);
        request.setAttribute("otherdateTo", otherdateTo);
        request.setAttribute("othertime", othertime);
        request.setAttribute("cancelDate", canceldate);
        request.setAttribute("remark", remark);
        request.setAttribute("memo", memo);
        request.setAttribute("createby", createby);
        request.setAttribute("createdate", createdate);       
        request.setAttribute(PRODUCTLIST, OtherService.getListMasterProductWithBookType(BookType));
        request.setAttribute(AGENTLIST, utilservice.getListAgent());
        List<MCurrency> mCurrency = utilservice.getListMCurrency();
        request.setAttribute(CurrencyList, mCurrency);
        System.out.println("OtherController");
        if(("1").equals(String.valueOf(master.getFlagOther())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);  
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
        return OtherDetail;
    }
    
    private void getTicket(HttpServletRequest request, String otherBookingId) {
        List<OtherTicketView> ticketList = OtherService.getListStockDetail(otherBookingId);
//      List<StockDetail> stockDetailList = OtherService.getListStockDetail(stockId);
//      String a = stockDetailList.get(0).getTypeId().getName();
        request.setAttribute("ticketList",ticketList);
        request.setAttribute("ticketListSize",ticketList.size());
        return;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public BookingOtherService getOtherService() {
        return OtherService;
    }

    public void setOtherService(BookingOtherService OtherService) {
        this.OtherService = OtherService;
    }
    
    public void saveHistoryBooking(String refNo,SystemUser user,OtherBooking other,String action) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");
        HistoryBooking historyBooking = new HistoryBooking();
        historyBooking.setHistoryDate(new Date());
        historyBooking.setAction(action+" OTHER BOOKING");
        String detail = "";
        if(!"VIEW".equalsIgnoreCase(action)){
            detail = "PRODUCT : " ; 
            if(other.getProduct() != null){
                System.out.println(" other.getProduct() " + other.getProduct().getCode());
                detail +=  other.getProduct().getCode() + " : " + other.getProduct().getName() + "\r\n" ;
            }else{
                detail += "\r\n" ;
            }
            detail+= "AGENT : " ;
            if(other.getAgent() != null){
                detail +=  other.getAgent().getCode() + " : " + other.getAgent().getName() + "\r\n" ;
            }else{
                detail += "\r\n" ;
            }
            detail+= "DATE : " ;
            if(other.getOtherDate()!=null){
                detail += String.valueOf(df.format(other.getOtherDate())) + " : "  ;
            }else{
                detail += "\r\n" ;
            }
            if(other.getOtherTime()!=null){
                detail += String.valueOf(df.format(other.getOtherTime())) + "\r\n" ;
            }else{
                detail += "\r\n" ;
            }
                detail += "REMARK : " + other.getRemark();
        }
        historyBooking.setDetail(detail);
        historyBooking.setMaster(other.getMaster());
        historyBooking.setStaff(user);
        int resultsave = utilservice.insertHistoryBooking(historyBooking);
    }

    public PassengerService getPasssengerService() {
        return passsengerService;
    }

    public void setPasssengerService(PassengerService passsengerService) {
        this.passsengerService = passsengerService;
    }
}
