package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingOtherService;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private UtilityService utilservice;
    private BookingOtherService OtherService;
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
        String othertime = request.getParameter("othertime");
        String canceldate = request.getParameter("canceldate");
        String remark = request.getParameter("remark");
        String refno = request.getParameter("referenceNo");
        String status = request.getParameter("status");
        String isbill = request.getParameter("isbill");
        String currency = request.getParameter("currency");
                        
        SystemUser user = (SystemUser) session.getAttribute("USER");

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
        
        if(action.equalsIgnoreCase("new")){
            adQty = String.valueOf(master.getAdult());
            chQty = String.valueOf(master.getChild());
            inQty = String.valueOf(master.getInfant());
        }
        
        request.setAttribute("Master", master);
        System.out.println("agentId : "+agentId);
        String BookType = master.getBookingType();
        if("I".equalsIgnoreCase(BookType)){
            request.setAttribute(BOOKINGTYPE,"i");
        }else{
            request.setAttribute(BOOKINGTYPE,"o");
        }
        request.setAttribute("currency", master.getCurrency());
        
        if ("save".equalsIgnoreCase(action)) {
            OtherBooking Other = new OtherBooking();
            if(!"".equalsIgnoreCase(itemid)){
                Other.setId(itemid);
            }
            Product product = new Product();
            product.setId(productId);
            System.out.println("productId :"+productId);
            System.out.println("agentId :"+agentId);
            Other.setProduct(product);
            Other.setCurrency(currency);
            
            Agent agent = new Agent();
            if((agentId != null)&&(!"".equalsIgnoreCase(agentId))){
                System.out.println("setup agentId :"+agentId);
                agent.setId(agentId);
                Other.setAgent(agent);
            }
            if((status != null)&&(!"".equalsIgnoreCase(status))){
                MItemstatus Mstatus = new MItemstatus();
                System.out.println("setup staus :"+agentId);
                Mstatus.setId(status);
                Other.setStatus(Mstatus);
            }
            Other.setAdCost(util.convertStringTolong(adCost));
            Other.setAdPrice(util.convertStringTolong(adPrice));
            Other.setAdQty(util.convertStringToInteger(adQty));
            Other.setChCost(util.convertStringTolong(chCost));
            Other.setChPrice(util.convertStringTolong(chPrice));
            Other.setChQty(util.convertStringToInteger(chQty));
            Other.setInCost(util.convertStringTolong(inCost));
            Other.setInPrice(util.convertStringTolong(inPrice));
            Other.setInQty(util.convertStringToInteger(inQty));
            if(!("").equalsIgnoreCase(isbill)){
                Other.setIsBill(Integer.parseInt(isbill));
            }
            
            if(remark != null)
            Other.setRemark(remark.trim());
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

            int result = OtherService.saveBookingOther(Other,user);
            if((result==1) && (callpageSubmit==null || !callpageSubmit.equalsIgnoreCase("FromDayTour"))){
                ModelAndView OTHER = new ModelAndView(new RedirectView("Other.smi?referenceNo="+refno+"&result=1", true));
                return OTHER;
            }else if((result==1) && (callpageSubmit!=null) && (callpageSubmit.equalsIgnoreCase("FromDayTour"))){
                ModelAndView DAYTOUR = new ModelAndView(new RedirectView("Daytour.smi?referenceNo="+refno+"&result=success", true));
                return DAYTOUR;
            }else{
                request.setAttribute(TransectionResult, "save unsuccessful");
            }
            
        }
        if ("edit".equalsIgnoreCase(action)) {
            OtherBooking Other = OtherService.getBookDetailOtherFromID(request.getParameter("itemid"));
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
            
            if(Other.getStatus() != null){
                status = Other.getStatus().getId();
            }
            remark = Other.getRemark();
            currency = Other.getCurrency();
            request.setAttribute("currency", currency);
        }

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
        request.setAttribute("othertime", othertime);
        request.setAttribute("cancelDate", canceldate);
        request.setAttribute("remark", remark);
        request.setAttribute(PRODUCTLIST, OtherService.getListMasterProduct());
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

}
