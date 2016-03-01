 package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.LandItinerary;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.OtherBooking;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingLandService;
import com.smi.travel.datalayer.service.ProductService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.BillableView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class LandDetailController extends SMITravelController {

    private static final ModelAndView LandDetail = new ModelAndView("LandDetail");
    private static final ModelAndView Land = new ModelAndView("Land");
    private BookingAirticketService bookingAirticketService;
    private static final String Booking_Size = "BookingSize";
    private static final String PRODUCTLIST = "product_list";
    private static final String AGENTLIST = "agent_list";
    private static final String ITINERARYLIST = "itinerary_list";
    private static final String PACKAGELIST = "package_list";
    private static final String TransectionResult = "result";
    private static final String CurrencyList = "CurrencyList";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private static final String EnableSave = "EnableSave";
    private UtilityService utilservice;
    private AgentService agentservice;
    private ProductService productservice;
    private BookingLandService landservice;
    private UtilityFunction util;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String refno = request.getParameter("referenceNo");
        System.out.println("LandDetailController : " + refno);
        String landID = request.getParameter("landid");
        String action = request.getParameter("action");
        String agentId = request.getParameter("agent_id");
        String agentCode = request.getParameter("agent_code");
        String agentName = request.getParameter("agent_name");
        String Category = request.getParameter("Category");
        String okby = request.getParameter("okby");
        String Description = request.getParameter("Description");
        String inb_Cost = request.getParameter("inb_Cost");
        String inb_Price = request.getParameter("inb_Price");
        String inb_QTY = request.getParameter("inb_QTY");
        String inbCHCost = request.getParameter("inb_CH_Cost");
        String inbCHQty = request.getParameter("inb_CH_Qty");
        String inbCHPrice = request.getParameter("inb_CH_Price");
        String inbINCost = request.getParameter("inb_IN_Cost");
        String inbINQty = request.getParameter("inb_IN_Qty");
        String inbINPrice = request.getParameter("inb_IN_Price");
        String currency = request.getParameter("currency");
        String currencycost = request.getParameter("currencycost");
        String departDate = request.getParameter("departdate");
        String arriveDate = request.getParameter("arrivedate");
        String hotel = request.getParameter("hotel");
        
        String packageId = request.getParameter("Product_id");
        String productCode = request.getParameter("Product_code");
        String productName = request.getParameter("Product_name");
        String ADCost = request.getParameter("AD_Cost");
        String ADQty = request.getParameter("AD_Qty");
        String ADPrice = request.getParameter("AD_Price");
        String CHCost = request.getParameter("CH_Cost");
        String CHQty = request.getParameter("CH_Qty");
        String CHPrice = request.getParameter("CH_Price");
        String INCost = request.getParameter("IN_Cost");
        String INQty = request.getParameter("IN_Qty");
        String INPrice = request.getParameter("IN_Price");
        String remark = request.getParameter("remark");
        String status = request.getParameter("status");
        String isbill = request.getParameter("isbill");
        String Itenarary = request.getParameter("Itenarary");
        String DelItenarary = request.getParameter("DelItenarary");
        String itinerarycount = request.getParameter("itinerarycount");
        
        request.setAttribute(ISBILLSTATUS,0);
        SystemUser user = (SystemUser) session.getAttribute("USER");
        util = new UtilityFunction();
        int[] booksize = utilservice.getCountItemFromBooking(refno);
        Master master = utilservice.getbookingFromRefno(refno);
        if ("add".equalsIgnoreCase(action)) {
            ADQty = String.valueOf(master.getAdult());
            CHQty = String.valueOf(master.getChild());
            INQty = String.valueOf(master.getInfant());
            currency = master.getCurrency();
            currencycost = master.getCurrency();
            inb_QTY = String.valueOf(master.getAdult());
            inbCHQty = String.valueOf(master.getChild());
            inbINQty = String.valueOf(master.getInfant());
            
        }
        

        
        request.setAttribute("Master", master);
        request.setAttribute(Booking_Size, booksize);
        request.setAttribute(PRODUCTLIST, landservice.getListLandPackage());
        request.setAttribute(AGENTLIST, agentservice.getListAgent(new Agent(), 1));
        request.setAttribute(PACKAGELIST, landservice.getListMpackage());
        String BookType = master.getBookingType();
        request.setAttribute("itinerarycount", "0");
        if ("I".equalsIgnoreCase(BookType)) {
            request.setAttribute("BOOKING_TYPE", "i");
        } else {
            request.setAttribute("BOOKING_TYPE", "o");
        }
        if("add".equalsIgnoreCase(action)){
            request.setAttribute("isEdit", "0");
            request.setAttribute(EnableSave,0);
        }
        if ("save".equalsIgnoreCase(action)) {
            LandBooking land = new LandBooking();
            if (!"".equalsIgnoreCase(agentId)) {
                Agent agent = new Agent();
                agent.setId(agentId);
                agent.setCode(agentCode);
                agent.setName(agentName);
                land.setAgent(agent);
            }
            land.setMaster(utilservice.getbookingFromRefno(refno));
            land.setOkBy(okby);
            land.setDescription(Description);
            land.setCategory(Category);
            if(!"".equalsIgnoreCase(currency)){
                land.setCurAmount(currency);
            }
            if(!"".equalsIgnoreCase(currencycost)){
                land.setCurCost(currencycost);
            }
            if(!"".equalsIgnoreCase(status)){
                MItemstatus ItemStatus = new MItemstatus();
                ItemStatus.setId(status);
                land.setMItemstatus(ItemStatus);
            }
            
            if(!("").equalsIgnoreCase(isbill)){
                land.setIsBill(Integer.parseInt(isbill));
            }
            if ("I".equalsIgnoreCase(BookType)) {
                land.setInboundCost(util.convertStringTolong(inb_Cost));
                land.setInboundPrice(util.convertStringTolong(inb_Price));
                land.setInboundQty(util.convertStringToInteger(inb_QTY));
                
                land.setInboundChCost(util.convertStringTolong(inbCHCost));
                land.setInboundChPrice(util.convertStringTolong(inbCHPrice));
                land.setInboundChQty(util.convertStringToInteger(inbCHQty));
                
                land.setInboundInCost(util.convertStringTolong(inbINCost));
                land.setInboundInPrice(util.convertStringTolong(inbINPrice));
                land.setInboundInQty(util.convertStringToInteger(inbINQty));
                land.setInboundHotel(hotel);
            } else {
                if (!"".equalsIgnoreCase(packageId)) {
                    PackageTour packagetour = new PackageTour();
                    packagetour.setId(packageId);
                    land.setPackageTour(packagetour);
                  
                }
                    land.setOutboundAdCost(util.convertStringTolong(ADCost));
                    land.setOutboundAdPrice(util.convertStringTolong(ADPrice));
                    land.setOutboundAdQty(util.convertStringToInteger(ADQty));
                    land.setOutboundChCost(util.convertStringTolong(CHCost));
                    land.setOutboundChPrice(util.convertStringTolong(CHPrice));
                    land.setOutboundChQty(util.convertStringToInteger(CHQty));
                    land.setOutboundInCost(util.convertStringTolong(INCost));
                    land.setOutboundInPrice(util.convertStringTolong(INPrice));
                    land.setOutboundInQty(util.convertStringToInteger(INQty));
                    land.setOutboundArrive(util.convertStringToDate(arriveDate));
                    land.setOutboundDepart(util.convertStringToDate(departDate));
                    land.setRemark(remark);
                    
 
            }
            request.setAttribute(ISBILLSTATUS,land.getIsBill());
            if (!"".equalsIgnoreCase(landID)) {
                land.setId(landID);
            }
            int result = landservice.saveBookDetailLand(land, Itenarary, DelItenarary, BookType);
            if(land.getId() != null){
                land.setMaster(master);
                saveHistoryBooking(refno,user,land,"UPDATE");
            }else{
                land.setMaster(master);
                saveHistoryBooking(refno,user,land,"CREATE");
            }
            
            String billDescId = utilservice.getBillableDescId(landID, "3");
            System.out.println(" ======== billDescId ======== " + billDescId);
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
                BillableView billableView = utilservice.getBillableDescByBookId(landID,"3");
                int resultupdate = utilservice.updateBillableDesc(billableView,billDescId);
            }
            
            if (result == 1) {
                ModelAndView LAND = new ModelAndView(new RedirectView("Land.smi?referenceNo=" + refno + "&result=1", true));
                return LAND;

            } else {
                request.setAttribute(TransectionResult, "save unsuccessful");
            }
            

            
        } else if ("edit".equalsIgnoreCase(action)) {
            request.setAttribute("isEdit", "1");
            landID = request.getParameter("landid");
            LandBooking land = landservice.getBookDetailLandFromID(landID);
            saveHistoryBooking(refno,user,land,"VIEW");
            request.setAttribute(ISBILLSTATUS,land.getIsBill());
            List<LandItinerary> ItineraryList = landservice.getListItinerary(landID);
            request.setAttribute(ITINERARYLIST, ItineraryList);
            if (ItineraryList == null) {
                request.setAttribute("itinerarycount", "0");
            } else {
                request.setAttribute("itinerarycount", ItineraryList.size());
            }

            if (land.getAgent() != null) {
                Agent agent = land.getAgent();
                agentId = agent.getId();
                agentCode = agent.getCode();
                agentName = agent.getName();
            }
            Category = land.getCategory();
            okby = land.getOkBy();
            Description = land.getDescription();
            isbill = String.valueOf(land.getIsBill());
            currency = String.valueOf(land.getCurAmount());
            currencycost = String.valueOf(land.getCurCost());
            if (land.getMItemstatus() != null) {
                status = String.valueOf(land.getMItemstatus().getId());
                System.out.println("status : " + status);
            }
            if ("I".equalsIgnoreCase(BookType)) {
                inb_Cost = String.valueOf(land.getInboundCost());
                inb_Price = String.valueOf(land.getInboundPrice());
                inb_QTY = String.valueOf(land.getInboundQty());
                
                inbCHCost   = String.valueOf(land.getInboundChCost());
                inbCHPrice   = String.valueOf(land.getInboundChPrice());
                inbCHQty  = String.valueOf(land.getInboundChQty());
                
                inbINCost = String.valueOf(land.getInboundInCost());
                inbINPrice = String.valueOf(land.getInboundInPrice());
                inbINQty = String.valueOf(land.getInboundInQty());
            } else {
                if (land.getPackageTour() != null) {
                    PackageTour pack = land.getPackageTour();
                    packageId = pack.getId();
                    productCode = pack.getCode();
                    productName = pack.getName();
                }

                ADCost = String.valueOf(land.getOutboundAdCost());
                ADQty = String.valueOf(land.getOutboundAdQty());
                ADPrice = String.valueOf(land.getOutboundAdPrice());

                CHCost = String.valueOf(land.getOutboundChCost());
                CHQty = String.valueOf(land.getOutboundChQty());
                CHPrice = String.valueOf(land.getOutboundChPrice());

                INCost = String.valueOf(land.getOutboundInCost());
                INQty = String.valueOf(land.getOutboundInQty());
                INPrice = String.valueOf(land.getOutboundInPrice());
    
                arriveDate = util.convertDateToString(land.getOutboundArrive());
                departDate = util.convertDateToString(land.getOutboundDepart());
                System.out.println("arriveDate : "+arriveDate);
                System.out.println("departDate : "+departDate);
                remark = land.getRemark();

            }
            
            String billDescId = utilservice.getBillableDescId(landID, "3");
            if("".equalsIgnoreCase(billDescId)){
                request.setAttribute(EnableSave,1);
            }else{
                request.setAttribute(EnableSave,0);
            }

        }
        if(isbill != null && !"".equalsIgnoreCase(isbill)){
            request.setAttribute(ISBILLSTATUS,Integer.parseInt(isbill));
        }
        
        request.setAttribute("isbill", isbill);
        request.setAttribute("landid", landID);
        request.setAttribute("agent_id", agentId);
        request.setAttribute("agent_code", agentCode);
        request.setAttribute("agent_name", agentName);
        request.setAttribute("Category", Category);
        request.setAttribute("okby", okby);
        request.setAttribute("Description", Description);
        request.setAttribute("currency", currency);
        request.setAttribute("currencycost", currencycost);
        
        request.setAttribute("status", status);
        List<MCurrency> mCurrency = utilservice.getListMCurrency();
        request.setAttribute(CurrencyList, mCurrency);
        if ("I".equalsIgnoreCase(BookType)) {
            request.setAttribute("inb_Cost", inb_Cost);
            request.setAttribute("inb_Price", inb_Price);
            request.setAttribute("inb_QTY", inb_QTY);
            request.setAttribute("inb_CH_Cost", inbCHCost);
            request.setAttribute("inb_CH_Qty", inbCHQty);
            request.setAttribute("inb_CH_Price", inbCHPrice);
            request.setAttribute("inb_IN_Cost", inbINCost);
            request.setAttribute("inb_IN_Qty", inbINQty);
            request.setAttribute("inb_IN_Price", inbINPrice);
        } else {
            request.setAttribute("Product_id", packageId);
            request.setAttribute("Product_code", productCode);
            request.setAttribute("Product_name", productName);
            request.setAttribute("AD_Cost", ADCost);
            request.setAttribute("AD_Qty", ADQty);
            request.setAttribute("AD_Price", ADPrice);
            request.setAttribute("CH_Cost", CHCost);
            request.setAttribute("CH_Qty", CHQty);
            request.setAttribute("CH_Price", CHPrice);
            request.setAttribute("IN_Cost", INCost);
            request.setAttribute("IN_Qty", INQty);
            request.setAttribute("IN_Price", INPrice);
            request.setAttribute("arrivedate", arriveDate);
            request.setAttribute("departdate", departDate); 
            request.setAttribute("remark", remark);
        }
        if(("1").equals(String.valueOf(master.getFlagLand())) 
            || ("2").equals(String.valueOf(master.getMBookingstatus().getId()))
            || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
        return LandDetail;
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

    public AgentService getAgentservice() {
        return agentservice;
    }

    public void setAgentservice(AgentService agentservice) {
        this.agentservice = agentservice;
    }

    public ProductService getProductservice() {
        return productservice;
    }

    public void setProductservice(ProductService productservice) {
        this.productservice = productservice;
    }

    public BookingLandService getLandservice() {
        return landservice;
    }

    public void setLandservice(BookingLandService landservice) {
        this.landservice = landservice;
    }
    
    public List<LandItinerary> SortLandItineraryList(List<LandItinerary> data) {
        List<LandItinerary> sortItinerary = new ArrayList<LandItinerary>();
        List Dataindex = new ArrayList();
        if(data == null){
            return data;
        }else if(data.size() == 0){
            return data;
        }
        for (int i = 0; i < data.size(); i++) {
            System.out.println("data id : " + data.get(i).getOrderNo());
            if (data.get(i).getOrderNo() == 0) {
                System.out.println("data id : null ");
                return data;
            }
        }
        for (int i = 0; i < data.size(); i++) {
            Dataindex.add(data.get(i).getOrderNo());
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(data.get(j).getOrderNo())) {
                    System.out.println("order no : " + data.get(j).getOrderNo()+","+data.get(j).getId());
                    if(!CheckDupilate(sortItinerary,data.get(j))){
                        sortItinerary.add(data.get(j));
                    }
                    
                    
                    
                }
                
            }
        }

        return sortItinerary;
    }
    
    public boolean CheckDupilate(List<LandItinerary> data , LandItinerary newdata){
        for(int i=0;i<data.size();i++){
            LandItinerary compare = data.get(i);
            if(compare.getId().equalsIgnoreCase(newdata.getId())){
                System.out.println("id : "+compare.getId() +" is dup");
                return true;
            } 
        }
        return false;
    }
    
    public void saveHistoryBooking(String refNo,SystemUser user,LandBooking land,String action) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");        
        HistoryBooking historyBooking = new HistoryBooking();
        historyBooking.setHistoryDate(new Date());
        historyBooking.setAction(action+" LAND BOOKING");
        String detail = "";
        if(!"VIEW".equalsIgnoreCase(action)){
            if(land.getAgent() != null){
                detail += "AGENT : " + land.getAgent().getCode() + " : " + land.getAgent().getName() + "\r\n";
            }else{
                detail += "AGENT : " + "\r\n";
            }
                detail += "DETAIL : " + land.getOkBy() + "\r\n"
                    + "CATEGORY : " + land.getCategory() +"\r\n"
                    + "DESCRIPTION : " + land.getDescription();
        }
        historyBooking.setDetail(detail);
        historyBooking.setMaster(land.getMaster());
        historyBooking.setStaff(user);
        int resultsave = utilservice.insertHistoryBooking(historyBooking);
    }
}
