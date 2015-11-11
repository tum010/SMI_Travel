package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketFlightView;
import com.smi.travel.datalayer.entity.AirticketPassenger;
import com.smi.travel.datalayer.entity.BookingFlight;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MPaymentDoctype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.entity.TicketFareInvoice;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.ReceiptService;
import com.smi.travel.datalayer.service.TicketFareAirlineService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceDetailView;
import com.smi.travel.datalayer.view.entity.ReceiptDetailView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class AddTicketFareController extends SMITravelController {
    private static final ModelAndView AddTicketFare = new ModelAndView("AddTicketFare");
    private static final ModelAndView AddTicketFare_REFRESH = new ModelAndView(new RedirectView("AddTicketFare.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String TICKETFARE = "ticketFare";
    private static final String Agent = "Agent";
    private static final String SAVERESULT = "saveresult";
    private static final String SELECTEDAGENT = "SelectedAgent";
    private static final String ISSUEDATE = "issueDate";
    private static final String OVERDATE = "overDate";
    private static final String LITTERDATE = "litterDate";
    private static final String DECPAYDATE = "decPayDate";
    private static final String ADDPAYDATE = "addPayDate";
    private static final String AGENTPAYDATE = "agentPayDate";
    private static final String AGENTRECEIVEDATE = "agentReceiveDate";
    private static final String TICKETROUTING = "TicketRouting";
    private static final String TICKETBUY = "TicketBuy";
    private static final String TICKETTYPE = "TicketType";
    private static final String TICKETLIST = "ticketList";
    private static final String DEPARTMENT = "department";
    private static final String FLIGHTDETAIL = "Flight_Detail";
    private static final String PVTYPELIST = "pvTypeList";
    private static final String OPTIONSAVE = "optionSave";
    private static final String FLIGHTDETAILFLAG = "flightDetailFlag";
    private static final String TICKETFAREFLAG = "ticketFareFlag";
    private static final String REFNO = "refNo";
    private static final String FLIGHTDETAILFROMAIR = "Flight_Detail_Airticket";
    private static final String INVOICEDETAILLIST = "invoiceDetailList";
    private static final String RECEIPTDETAILLIST = "receiptDetailList";
    private static final String INVOICEDATE = "invoiceDate";
    private static final String INVOICENO = "invoiceNo";
    private static final String INVOICECREDIT = "invoiceCredit";
    private static final String INVOICECREDITVALUE = "invoiceCreditValue";
    private static final String DUEDATE = "dueDate";
    private static final String TICKETCOMMDATE = "ticketCommDate";
    private static final String AGENTCOMMDATE = "agentCommDate";
    private static final String withholdingtax = "withholdingtax";
    private static final String INVNO = "invNo";
    private static final String VAT = "VAT";
    private static final String REFUNDDETAILLIST = "refundDetailList";
    private static final String SELECTINVID = "selectInvIdTemp";
    private static final String INVDETAILID = "invDetailTableId";
    private UtilityService utilityService;
    private TicketFareAirlineService ticketFareAirlineService;
    private AgentService agentService;
    private ReceiptService receiptService;
    UtilityFunction util;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String ticketNo = request.getParameter("ticketNo");
        String ticketType = request.getParameter("ticketType");
        String ticketBuy = request.getParameter("ticketBuy");
        String ticketRouting = request.getParameter("ticketRouting");
        String ticketAirline = request.getParameter("ticketAirline");
        String passenger = request.getParameter("passenger");
        String issueDate = request.getParameter("issueDate");
//        String ticketDate = request.getParameter("ticketDate");
        String ticketFare = request.getParameter("ticketFare");
        String ticketTax = request.getParameter("ticketTax");
        String ticketIns = request.getParameter("ticketIns");
        String ticketCommission = request.getParameter("ticketCommission");
        String agentCommission = request.getParameter("agentCommission");
        String salePrice = request.getParameter("salePrice");
        String diffVat = request.getParameter("diffVat");
        String agentId = request.getParameter("agent_id");
        String remark = request.getParameter("remark");
        String overCommission = request.getParameter("overCommission");
        String litterCommission = request.getParameter("litterCommission");
        String decPay = request.getParameter("decPay");
        String addPay = request.getParameter("addPay");
        String agentComPay = request.getParameter("agentComPay");
        String agentComReceive = request.getParameter("agentComReceive");
        String overDate = request.getParameter("overDate");
        String litterDate = request.getParameter("litterDate");
        String decPayDate = request.getParameter("decPayDate");
        String addPayDate = request.getParameter("addPayDate");
        String agentPayDate = request.getParameter("agentPayDate");
        String agentReceiveDate = request.getParameter("agentReceiveDate");
        String ticketId = request.getParameter("ticketId");
        String department = request.getParameter("department");
        String pvType = request.getParameter("pvType");
        String pvCode = request.getParameter("pvCode");
        String masterId = request.getParameter("masterId");
        String optionSave = request.getParameter("optionSave");
        String refno = request.getParameter("refno");
        String invoiceAmount = request.getParameter("invoiceAmount");
        String airlineCharge = request.getParameter("airlineCharge");
        String owner = request.getParameter("owner");
        String routing = request.getParameter("routing");
        String dueDate = request.getParameter("dueDate");
        String countRowInvoice = request.getParameter("countRow");
        String agentCommDate = request.getParameter("agentCommDate");
        String ticketCommDate = request.getParameter("ticketCommDate");
        String invno = request.getParameter("invno");
        String airlinecode = request.getParameter("airlinecode");
        String isWaitPay = request.getParameter("isWaitPay");
        String whtax = request.getParameter("whtax");
        String vat = request.getParameter("vat");
        String isTempTicket = request.getParameter("isTempTicket");
        String invoiceId = request.getParameter("selectInvId");
        String invoiceDetailTableId = request.getParameter("invoiceDetailTableId");
        
        String result = "";
        setResponseAttribute(request,refno,invno);
        Agent agents = new Agent();
        
        util = new UtilityFunction();
        System.out.print("action :" + action + "//");
        
        TicketFareAirline ticketFareAirline = new TicketFareAirline();
        MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("withholding tax");
        MDefaultData mDefaultDatavat = utilityService.getMDefaultDataFromType("vat");
        ticketFareAirline.setVat(new BigDecimal(String.valueOf(mDefaultDatavat.getValue())));
        ticketFareAirline.setWht(new BigDecimal(String.valueOf(mDefaultData.getValue())));        
        
        AirticketPassenger airticketPassenger = new AirticketPassenger();
        MAirlineAgent mAirlineAgent = new MAirlineAgent();
        MPaymentDoctype mPaymentDoctype = new MPaymentDoctype();
        
        BigDecimal invAmount = new BigDecimal(BigInteger.ZERO);
        String invNo = "" ;
        String invDate = "" ;
        String invcredit = "" ;
        String invcreditvalue = "";
        String staffowner = "";
        String ticketflightrouting = "";
        String invTo = "" ;
        String invName = "" ;
        if ("save".equalsIgnoreCase(action)){
            System.out.println("ticketId : "+ ticketId);
            System.out.println("masterId : "+ masterId);
            mAirlineAgent.setId(ticketAirline);
            if(StringUtils.isNotEmpty(ticketAirline)){
                ticketFareAirline.setMAirlineAgent(mAirlineAgent);
            }
            mPaymentDoctype.setId(pvType);
            if(StringUtils.isNotEmpty(pvType)){
                ticketFareAirline.setMPaymentDoctype(mPaymentDoctype);
            }

            if(StringUtils.isNotEmpty(pvCode)){
                ticketFareAirline.setPvCode(pvCode);
            }

            if(StringUtils.isNotEmpty(ticketId)){
                ticketFareAirline.setId(ticketId);
            }else{
                ticketFareAirline.setId("");
            }
            
            if(ticketFareAirline.getTicketFareInvoices() == null){
                ticketFareAirline.setTicketFareInvoices(new ArrayList<TicketFareInvoice>());
            }
         
            Master master = new Master();
            if(StringUtils.isNotEmpty(masterId)){
                master.setId(masterId);
                ticketFareAirline.setMaster(master);
            }

            ticketFareAirline.setTicketNo(ticketNo);
            ticketFareAirline.setTicketType(ticketType);
            request.setAttribute(TICKETTYPE, ticketType);
            ticketFareAirline.setTicketBuy(ticketBuy);
            request.setAttribute(TICKETBUY, ticketBuy);
            ticketFareAirline.setTicketRouting(ticketRouting);
            request.setAttribute(TICKETROUTING, ticketRouting);

            ticketFareAirline.setPassenger(passenger);
            if(StringUtils.isNotEmpty(issueDate)){
                ticketFareAirline.setIssueDate(util.convertStringToDate(issueDate));
                request.setAttribute(ISSUEDATE, issueDate);
            }
            
            if(StringUtils.isNotEmpty(ticketCommDate)){
                ticketFareAirline.setTicketCommissionDate(util.convertStringToDate(ticketCommDate));
                request.setAttribute(TICKETCOMMDATE, ticketCommDate);
            }
            
            if(StringUtils.isNotEmpty(agentCommDate)){
                ticketFareAirline.setAgentCommissionDate(util.convertStringToDate(agentCommDate));
                request.setAttribute(AGENTCOMMDATE, agentCommDate);
            }

            if(StringUtils.isNotEmpty(ticketFare)){
                ticketFareAirline.setTicketFare(new BigDecimal(String.valueOf(ticketFare.replaceAll(",","")))); 
            }else{
                ticketFareAirline.setTicketFare(new BigDecimal(0)); 
            }

            if(StringUtils.isNotEmpty(ticketTax)){
                ticketFareAirline.setTicketTax(new BigDecimal(String.valueOf(ticketTax.replaceAll(",",""))));
            }else{
                ticketFareAirline.setTicketTax(new BigDecimal(0)); 
            }

            if(StringUtils.isNotEmpty(ticketIns)){
                ticketFareAirline.setTicketIns(new BigDecimal(String.valueOf(ticketIns.replaceAll(",",""))));
            }else{
                ticketFareAirline.setTicketIns(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(ticketCommission)){
                ticketFareAirline.setTicketCommission(new BigDecimal(String.valueOf(ticketCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setTicketCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(agentCommission)){
                ticketFareAirline.setAgentCommission(new BigDecimal(String.valueOf(agentCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAgentCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(salePrice)){
                ticketFareAirline.setSalePrice(new BigDecimal(String.valueOf(salePrice.replaceAll(",",""))));
            }else{
                ticketFareAirline.setSalePrice(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(diffVat)){
                ticketFareAirline.setDiffVat(new BigDecimal(String.valueOf(diffVat.replaceAll(",",""))));
            }else{
                ticketFareAirline.setDiffVat(new BigDecimal(0)); 
            }
            if (StringUtils.isNotEmpty(agentId)){
                ticketFareAirline.setAgentId(util.convertStringToInteger(agentId));
                agents = agentService.getAgentFromID(String.valueOf(agentId));
                request.setAttribute(SELECTEDAGENT, agents);
            }

            ticketFareAirline.setRemark(remark);
            if(StringUtils.isNotEmpty(overCommission)){
                ticketFareAirline.setOverCommission(new BigDecimal(String.valueOf(overCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setOverCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(litterCommission)){
                ticketFareAirline.setLitterCommission(new BigDecimal(String.valueOf(litterCommission.replaceAll(",",""))));
            }else{
                ticketFareAirline.setLitterCommission(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(decPay)){
                ticketFareAirline.setDecPay(new BigDecimal(String.valueOf(decPay.replaceAll(",",""))));
            }else{
                ticketFareAirline.setDecPay(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(addPay)){
                ticketFareAirline.setAddPay(new BigDecimal(String.valueOf(addPay.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAddPay(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(agentComPay)){
                ticketFareAirline.setAgentComPay(new BigDecimal(String.valueOf(agentComPay.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAgentComPay(new BigDecimal(0)); 
            }
            if(StringUtils.isNotEmpty(agentComReceive)){
                ticketFareAirline.setAgentComReceive(new BigDecimal(String.valueOf(agentComReceive.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAgentComReceive(new BigDecimal(0)); 
            }
            if (StringUtils.isNotEmpty(overDate)){
                ticketFareAirline.setOverDate(util.convertStringToDate(overDate));
                request.setAttribute(OVERDATE, overDate);
            }
            if (StringUtils.isNotEmpty(litterDate)){
                ticketFareAirline.setLitterDate(util.convertStringToDate(litterDate));
                request.setAttribute(LITTERDATE, litterDate);
            }
            if (StringUtils.isNotEmpty(decPayDate)){
                ticketFareAirline.setDecPayDate(util.convertStringToDate(decPayDate));
                request.setAttribute(DECPAYDATE, decPayDate);
            }
            if (StringUtils.isNotEmpty(addPayDate)){
                ticketFareAirline.setAddPayDate(util.convertStringToDate(addPayDate));
                request.setAttribute(ADDPAYDATE, addPayDate);
            }
            if (StringUtils.isNotEmpty(agentPayDate)){
                ticketFareAirline.setAgentPayDate(util.convertStringToDate(agentPayDate));
                request.setAttribute(AGENTPAYDATE, agentPayDate);
            }
            if (StringUtils.isNotEmpty(agentReceiveDate)){
                ticketFareAirline.setAgentReceiveDate(util.convertStringToDate(agentReceiveDate));
                request.setAttribute(AGENTRECEIVEDATE, agentReceiveDate);
            }
            if (StringUtils.isNotEmpty(department)){
                ticketFareAirline.setDepartment(department);
                request.setAttribute(DEPARTMENT, department);
            }
            if (StringUtils.isNotEmpty(dueDate)){
                ticketFareAirline.setDueDate(util.convertStringToDate(dueDate));
                request.setAttribute(DUEDATE, dueDate);
            }
            
            if(StringUtils.isNotEmpty(airlineCharge)){
                ticketFareAirline.setAirlineCharge(new BigDecimal(String.valueOf(airlineCharge.replaceAll(",",""))));
            }else{
                ticketFareAirline.setAirlineCharge(new BigDecimal(0)); 
            }
            
            if(StringUtils.isNotEmpty(invoiceAmount)){
                ticketFareAirline.setInvAmount(new BigDecimal(String.valueOf(invoiceAmount.replaceAll(",",""))));
            }else{
                ticketFareAirline.setInvAmount(new BigDecimal(0)); 
            }
            ticketFareAirline.setOwner(owner);
            ticketFareAirline.setRoutingDetail(routing);
            
            if("1".equalsIgnoreCase(isWaitPay)){
                ticketFareAirline.setIsWaitPay(1);
            }else{
                ticketFareAirline.setIsWaitPay(0);
            }
            if("1".equalsIgnoreCase(isTempTicket)){
                ticketFareAirline.setIsTempTicket(1);
            }else if("0".equalsIgnoreCase(isTempTicket)){
                ticketFareAirline.setIsTempTicket(0);
            }
            
            //Save Ticket Fare Invoice
//            int count = Integer.parseInt(countRowInvoice);
//            for (int i = 0; i < count ; i++) {
//                String invoiceId = request.getParameter("invoiceId" + i);
//                System.out.println(" invoiceId " + invoiceId);
//                if(invoiceId != null && "".equalsIgnoreCase(ticketId)){
//                    TicketFareInvoice ticketFareInvoice = new TicketFareInvoice();
//                    Invoice invoice = new Invoice();
//                    invoice.setId(invoiceId);
//                    ticketFareInvoice.setInvoice(invoice);
//                    ticketFareInvoice.setTicketFareAirline(ticketFareAirline);
//                    ticketFareAirline.getTicketFareInvoices().add(ticketFareInvoice);
//                }
//            }
            
            
            
            if(invoiceId != null && !"".equalsIgnoreCase(ticketId)){
                TicketFareInvoice ticketFareInvoice = new TicketFareInvoice();
                Invoice invoice = new Invoice();
                invoice.setId(invoiceId);
                ticketFareInvoice.setId(invoiceDetailTableId);
                ticketFareInvoice.setInvoice(invoice);
                ticketFareInvoice.setTicketFareAirline(ticketFareAirline);
                ticketFareAirline.getTicketFareInvoices().add(ticketFareInvoice);
                request.setAttribute(SELECTINVID,invoiceId);
                request.setAttribute(INVDETAILID,invoiceDetailTableId);
            }
            
            if("".equals(ticketNo) || ticketNo == null ){
                request.setAttribute(SAVERESULT, "save unsuccessful");
            }else{
                result = ticketFareAirlineService.validateSaveTicket(ticketFareAirline);
            
                System.out.print("result :" + result + " =================== ");
                if (result == "fail") {
                    request.setAttribute(SAVERESULT, "save unsuccessful");
                } else if (result == "success"){
                    request.setAttribute(SAVERESULT, "save successful");
                } else{
                    ticketFareAirline.setPvCode(result);
                    request.setAttribute(SAVERESULT, "save successful");
                }
            }
                        
            List<InvoiceDetailView> invoiceDetailViewList = new ArrayList<InvoiceDetailView>();
            invoiceDetailViewList = ticketFareAirlineService.getInvoiceDetailFromTicketNo(ticketNo);
            List<ReceiptDetailView> receiptDetailViewListTemp = new ArrayList<ReceiptDetailView>();
            List<ReceiptDetailView> receiptDetailViewList = new ArrayList<ReceiptDetailView>();
            if(((invoiceDetailViewList != null))&&(!"null".equals(invoiceDetailViewList.get(0).getInvoiceId()))){
                request.setAttribute(INVOICEDETAILLIST, invoiceDetailViewList);
                for (int i = 0; i < invoiceDetailViewList.size() ; i++) {
                    if(invoiceId != null && !"null".equalsIgnoreCase(String.valueOf(invoiceDetailViewList.get(i).getInvoiceId()))){
                        if((String.valueOf(invoiceId)).equalsIgnoreCase(invoiceDetailViewList.get(i).getInvoiceId())){
                            invNo = invoiceDetailViewList.get(i).getInvNo();
                            invDate = String.valueOf(invoiceDetailViewList.get(i).getInvDate());
                            invcredit =  invoiceDetailViewList.get(i).getCredit();
                            invTo = String.valueOf(invoiceDetailViewList.get(i).getInvTo());

                            request.setAttribute(SELECTINVID,invoiceId);
                        }
                    }else{
                        invNo = invoiceDetailViewList.get(0).getInvNo();
                        invDate = String.valueOf(invoiceDetailViewList.get(0).getInvDate());
                        invcredit =  invoiceDetailViewList.get(0).getCredit();
                        invTo = String.valueOf(invoiceDetailViewList.get(0).getInvTo());
                        
                        request.setAttribute(SELECTINVID,invoiceDetailViewList.get(0).getInvoiceId());
                    }
                    
                    if (StringUtils.isNotEmpty(invTo)){
                        agents = agentService.getAgentFromID(String.valueOf(invTo));
                        request.setAttribute(SELECTEDAGENT, agents);
                    }
                    receiptDetailViewListTemp = receiptService.getReceiptDetailViewFromInvDetailId(invoiceDetailViewList.get(i).getId());
                    if(receiptDetailViewListTemp != null){
                        for (int j = 0; j < receiptDetailViewListTemp.size() ; j++) {
                            ReceiptDetailView receiptDetailView = new ReceiptDetailView();
                            receiptDetailView.setReceiptNo(receiptDetailViewListTemp.get(j).getReceiptNo());
                            receiptDetailView.setReceiptDate(receiptDetailViewListTemp.get(j).getReceiptDate());
                            receiptDetailView.setReceiveDate(receiptDetailViewListTemp.get(j).getReceiveDate());
                            receiptDetailView.setMbillTypeStatus(receiptDetailViewListTemp.get(j).getMbillTypeStatus());
                            receiptDetailView.setInvoiceNo(receiptDetailViewListTemp.get(j).getInvoiceNo());
                            receiptDetailViewList.add(receiptDetailView);
                        }
                    }
                }
            }
            List<RefundAirticketDetail> refundAirticketDetailList = ticketFareAirlineService.getRefundAirticketDetailFromTicketNo(ticketNo);
            request.setAttribute(REFUNDDETAILLIST, refundAirticketDetailList);
            
            request.setAttribute(INVOICENO, invNo);
            request.setAttribute(INVOICEDATE, invDate);
            request.setAttribute(INVOICECREDIT, invcredit);
            request.setAttribute(RECEIPTDETAILLIST, receiptDetailViewList);
            
            request.setAttribute(OPTIONSAVE,optionSave); 
            request.setAttribute(TICKETFARE,ticketFareAirline);
                
            List<BookingFlight> bookingFlights = new ArrayList<BookingFlight>();
            List<AirticketFlightView> airticketFlightView = new ArrayList<AirticketFlightView>();
            bookingFlights = ticketFareAirlineService.getListFlightFromTicketNo(ticketNo);
            request.setAttribute(FLIGHTDETAIL, bookingFlights);

            if(bookingFlights == null){
                airticketFlightView = ticketFareAirlineService.getListAirticketFlightFromTicketNo(ticketNo);
                request.setAttribute(FLIGHTDETAILFROMAIR, airticketFlightView);
                if(airticketFlightView != null){
                    request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
                }
            }else{
                request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
            }

        } else if ("edit".equalsIgnoreCase(action)){
            System.out.print("ticketId : " +ticketId);
            ticketFareAirline = ticketFareAirlineService.getTicketFareFromId(ticketId);
//            List<TicketFareInvoice> ticketFareInvoiceList = ticketFareAirline.getTicketFareInvoices();
//            if(ticketFareInvoiceList != null){
//                request.setAttribute(INVDETAILID,ticketFareInvoiceList.get(0).getId());
//            }
            List<TicketFareInvoice> ticketFareInvoiceList = new ArrayList<TicketFareInvoice>();
            if(ticketFareAirline != null){
                ticketFareInvoiceList = ticketFareAirline.getTicketFareInvoices();
            }

            if((ticketFareInvoiceList != null)&&(!ticketFareInvoiceList.isEmpty()) ){
                System.out.println(" ==== ticketFareInvoiceList.get(0).getId() ==== " + ticketFareInvoiceList.get(0).getId());
                request.setAttribute(INVDETAILID,ticketFareInvoiceList.get(0).getId());
            }
            
            
            List<BookingFlight> bookingFlights = new ArrayList<BookingFlight>();
            List<AirticketFlightView> airticketFlightView = new ArrayList<AirticketFlightView>();
            bookingFlights = ticketFareAirlineService.getListFlightFromTicketNo(ticketFareAirline.getTicketNo());
            request.setAttribute(FLIGHTDETAIL, bookingFlights);
            if(bookingFlights == null){
                airticketFlightView = ticketFareAirlineService.getListAirticketFlightFromTicketNo(ticketFareAirline.getTicketNo());
                request.setAttribute(FLIGHTDETAILFROMAIR, airticketFlightView);
                if(airticketFlightView != null){
                    request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
                }
            }else{
                request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
            }
            
            List<InvoiceDetailView> invoiceDetailViewList = new ArrayList<InvoiceDetailView>();
            invoiceDetailViewList = ticketFareAirlineService.getInvoiceDetailFromTicketNo(ticketFareAirline.getTicketNo());
            
            List<ReceiptDetailView> receiptDetailViewListTemp = new ArrayList<ReceiptDetailView>();
            List<ReceiptDetailView> receiptDetailViewList = new ArrayList<ReceiptDetailView>();    
            if(((invoiceDetailViewList != null))&&(!"null".equals(invoiceDetailViewList.get(0).getInvoiceId()))){
                request.setAttribute(INVOICEDETAILLIST, invoiceDetailViewList);
                for (int i = 0; i < invoiceDetailViewList.size() ; i++){
                    if(((ticketFareInvoiceList != null)&&(!ticketFareInvoiceList.isEmpty())) && !"null".equalsIgnoreCase(String.valueOf(invoiceDetailViewList.get(i).getInvoiceId()))){
                        if((String.valueOf(ticketFareInvoiceList.get(0).getInvoice().getId())).equalsIgnoreCase(invoiceDetailViewList.get(i).getInvoiceId())){
                            invNo = invoiceDetailViewList.get(i).getInvNo();
                            invDate = String.valueOf(invoiceDetailViewList.get(i).getInvDate());
                            invcredit =  invoiceDetailViewList.get(i).getCredit();
                            invTo = String.valueOf(invoiceDetailViewList.get(i).getInvTo());
                            request.setAttribute(SELECTINVID,invoiceDetailViewList.get(i).getInvoiceId());
                        }
                    }else{
                        invNo = invoiceDetailViewList.get(0).getInvNo();
                        invDate = String.valueOf(invoiceDetailViewList.get(0).getInvDate());
                        invcredit =  invoiceDetailViewList.get(0).getCredit();
                        invTo = String.valueOf(invoiceDetailViewList.get(0).getInvTo());
                        
                        request.setAttribute(SELECTINVID,invoiceDetailViewList.get(0).getInvoiceId());
                    }

                    
                    receiptDetailViewListTemp = receiptService.getReceiptDetailViewFromInvDetailId(invoiceDetailViewList.get(i).getId());
                    if(receiptDetailViewListTemp != null){
                        for (int j = 0; j < receiptDetailViewListTemp.size() ; j++) {
                            ReceiptDetailView receiptDetailView = new ReceiptDetailView();
                            receiptDetailView.setReceiptNo(receiptDetailViewListTemp.get(j).getReceiptNo());
                            receiptDetailView.setReceiptDate(receiptDetailViewListTemp.get(j).getReceiptDate());
                            receiptDetailView.setReceiveDate(receiptDetailViewListTemp.get(j).getReceiveDate());
                            receiptDetailView.setMbillTypeStatus(receiptDetailViewListTemp.get(j).getMbillTypeStatus());
                            receiptDetailView.setInvoiceNo(receiptDetailViewListTemp.get(j).getInvoiceNo());
                            receiptDetailViewList.add(receiptDetailView);
                        }
                    }
                }
            }
            
            List<RefundAirticketDetail> refundAirticketDetailList = ticketFareAirlineService.getRefundAirticketDetailFromTicketNo(ticketFareAirline.getTicketNo());
            request.setAttribute(REFUNDDETAILLIST, refundAirticketDetailList);
                
            request.setAttribute(INVOICENO, invNo);
            request.setAttribute(INVOICEDATE, invDate);
            request.setAttribute(INVOICECREDIT, invcredit);
            request.setAttribute(RECEIPTDETAILLIST, receiptDetailViewList);
            
            request.setAttribute(TICKETFARE,ticketFareAirline);
            request.setAttribute(TICKETTYPE, ticketFareAirline.getTicketType());
            request.setAttribute(TICKETBUY, ticketFareAirline.getTicketBuy());
            request.setAttribute(TICKETROUTING, ticketFareAirline.getTicketRouting());
            request.setAttribute(ISSUEDATE, ticketFareAirline.getIssueDate());
            request.setAttribute(TICKETCOMMDATE, ticketFareAirline.getTicketCommissionDate());
            request.setAttribute(AGENTCOMMDATE, ticketFareAirline.getAgentCommissionDate());
            agents = agentService.getAgentFromID(String.valueOf(ticketFareAirline.getAgentId()));
            request.setAttribute(SELECTEDAGENT, agents);
            if (StringUtils.isNotEmpty(invTo)){
                agents = agentService.getAgentFromID(String.valueOf(invTo));
                request.setAttribute(SELECTEDAGENT, agents);
            }
            request.setAttribute(OVERDATE, ticketFareAirline.getOverDate());
            request.setAttribute(LITTERDATE, ticketFareAirline.getLitterDate());
            request.setAttribute(DECPAYDATE, ticketFareAirline.getDecPayDate());
            request.setAttribute(ADDPAYDATE, ticketFareAirline.getAddPayDate());
            request.setAttribute(AGENTPAYDATE, ticketFareAirline.getAgentPayDate());
            request.setAttribute(AGENTRECEIVEDATE, ticketFareAirline.getAgentReceiveDate());
            request.setAttribute(DEPARTMENT, ticketFareAirline.getDepartment());
        }else if ("search".equalsIgnoreCase(action)) {
            System.out.print("ticketNo : " +ticketNo);
            if("".equals(ticketNo) || ticketNo == null ){
                System.out.print("ticketNo is null");
            }else{
                ticketFareAirline = ticketFareAirlineService.getTicketFareFromTicketNo(ticketNo,ticketId);
                List<TicketFareInvoice> ticketFareInvoiceList = new ArrayList<TicketFareInvoice>();
                if(ticketFareAirline != null){
                    ticketFareInvoiceList = ticketFareAirline.getTicketFareInvoices();
                }
                        
                if((ticketFareInvoiceList != null)&&(!ticketFareInvoiceList.isEmpty())){
                    System.out.println(" ==== ticketFareInvoiceList.get(0).getId() ==== " + ticketFareInvoiceList.get(0).getId());
                    request.setAttribute(INVDETAILID,ticketFareInvoiceList.get(0).getId());
                }
                List<BookingFlight> bookingFlights = new ArrayList<BookingFlight>();
                List<AirticketFlightView> airticketFlightView = new ArrayList<AirticketFlightView>();
                bookingFlights = ticketFareAirlineService.getListFlightFromTicketNo(ticketNo);
                request.setAttribute(FLIGHTDETAIL, bookingFlights);
                
                List<InvoiceDetailView> invoiceDetailViewList = new ArrayList<InvoiceDetailView>();
                invoiceDetailViewList = ticketFareAirlineService.getInvoiceDetailFromTicketNo(ticketNo);

                List<ReceiptDetailView> receiptDetailViewListTemp = new ArrayList<ReceiptDetailView>();
                List<ReceiptDetailView> receiptDetailViewList = new ArrayList<ReceiptDetailView>();
                if(((invoiceDetailViewList != null))&&(!"null".equals(invoiceDetailViewList.get(0).getInvoiceId()))){
                    request.setAttribute(INVOICEDETAILLIST, invoiceDetailViewList);
                    for (int i = 0; i < invoiceDetailViewList.size() ; i++) {
                        if(((ticketFareInvoiceList != null)&&(!ticketFareInvoiceList.isEmpty())) && !"null".equalsIgnoreCase(String.valueOf(invoiceDetailViewList.get(i).getInvoiceId()))){
                            System.out.println(" ticketFareInvoiceList.size() " + ticketFareInvoiceList.size());
                            System.out.println(" ticketFareInvoiceList.get(0).getInvoice().getId() " + ticketFareInvoiceList.get(0).getInvoice().getId());
                            System.out.println(" invoiceDetailViewList.get(i).getInvoiceId() " + invoiceDetailViewList.get(i).getInvoiceId());
                            if((String.valueOf(ticketFareInvoiceList.get(0).getInvoice().getId())).equalsIgnoreCase(invoiceDetailViewList.get(i).getInvoiceId())){
                                BigDecimal invAmounttemp = invoiceDetailViewList.get(i).getInvAmount();
                                invAmount = invAmounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                                invNo = invoiceDetailViewList.get(i).getInvNo();
                                invDate = String.valueOf(invoiceDetailViewList.get(i).getInvDate());
                                invcredit =  invoiceDetailViewList.get(i).getCredit();
                                invcreditvalue = invoiceDetailViewList.get(i).getCreditValue();
                                staffowner =  invoiceDetailViewList.get(i).getOwner();
                                ticketflightrouting = invoiceDetailViewList.get(i).getRouting();
                                invTo = String.valueOf(invoiceDetailViewList.get(i).getInvTo());
                                request.setAttribute(SELECTINVID,invoiceDetailViewList.get(i).getInvoiceId());
                            }
                        }else{
                            BigDecimal invAmounttemp = invoiceDetailViewList.get(0).getInvAmount();
                            invAmount = invAmounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            invNo = invoiceDetailViewList.get(0).getInvNo();
                            invDate = String.valueOf(invoiceDetailViewList.get(0).getInvDate());
                            invcredit =  invoiceDetailViewList.get(0).getCredit();
                            invcreditvalue = invoiceDetailViewList.get(0).getCreditValue();
                            staffowner =  invoiceDetailViewList.get(0).getOwner();
                            ticketflightrouting = invoiceDetailViewList.get(0).getRouting();
                            invTo = String.valueOf(invoiceDetailViewList.get(0).getInvTo());
                            request.setAttribute(SELECTINVID,invoiceDetailViewList.get(0).getInvoiceId());
                        }
                        
                        if (StringUtils.isNotEmpty(invTo)){
                            agents = agentService.getAgentFromID(String.valueOf(invTo));
                            request.setAttribute(SELECTEDAGENT, agents);
                        }
                        
                        receiptDetailViewListTemp = receiptService.getReceiptDetailViewFromInvDetailId(invoiceDetailViewList.get(i).getId());
                        if(receiptDetailViewListTemp != null){
                            for (int j = 0; j < receiptDetailViewListTemp.size() ; j++) {
                                ReceiptDetailView receiptDetailView = new ReceiptDetailView();
                                receiptDetailView.setReceiptNo(receiptDetailViewListTemp.get(j).getReceiptNo());
                                receiptDetailView.setReceiptDate(receiptDetailViewListTemp.get(j).getReceiptDate());
                                receiptDetailView.setReceiveDate(receiptDetailViewListTemp.get(j).getReceiveDate());
                                receiptDetailView.setMbillTypeStatus(receiptDetailViewListTemp.get(j).getMbillTypeStatus());
                                receiptDetailView.setInvoiceNo(receiptDetailViewListTemp.get(j).getInvoiceNo());
                                receiptDetailViewList.add(receiptDetailView);
                            }
                        }
                    }
                }else{
                    if(invoiceDetailViewList != null){
                        for(int i = 0; i < invoiceDetailViewList.size() ; i++) {
                            BigDecimal invAmounttemp = invoiceDetailViewList.get(0).getInvAmount();
                            invAmount = invAmounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            staffowner =  invoiceDetailViewList.get(0).getOwner();
                            ticketflightrouting = invoiceDetailViewList.get(0).getRouting();
                        }
                    }
                }
                System.out.println(" invAmount " + invAmount);
                request.setAttribute(INVOICENO, invNo);
                request.setAttribute(INVOICEDATE, invDate);
                request.setAttribute(INVOICECREDIT, invcredit);
                request.setAttribute(INVOICECREDITVALUE, invcreditvalue);
                request.setAttribute(RECEIPTDETAILLIST, receiptDetailViewList);
                
                List<RefundAirticketDetail> refundAirticketDetailList = ticketFareAirlineService.getRefundAirticketDetailFromTicketNo(ticketNo);
                request.setAttribute(REFUNDDETAILLIST, refundAirticketDetailList);
                
                if(bookingFlights == null){
                    airticketFlightView = ticketFareAirlineService.getListAirticketFlightFromTicketNo(ticketNo);
                    request.setAttribute(FLIGHTDETAILFROMAIR, airticketFlightView);
                    if(airticketFlightView != null){
                        request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
                    }
                }else{
                    request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
                }
                if(ticketFareAirline == null){
                    String airticketPass = ticketFareAirlineService.getTicketFareBookingFromTicketNo(ticketNo);
                    TicketFareAirline ticketFareAirlines = new TicketFareAirline();
                    request.setAttribute(TICKETFAREFLAG,"dummy");
                    if(StringUtils.isNotEmpty(airticketPass)){
                        String[] parts = airticketPass.split(",");
                        String TicketFare = parts[0].trim();
                        String TicketTax = parts[1].trim();
                        String IssueDate = parts[2].trim(); 
                        String TicketRouting = parts[3].trim(); 
                        String Airline = parts[4].trim(); 
                        String TicketBy = parts[5].trim(); 
                        String Passenger = parts[6].trim(); 
                        String Department = parts[7].trim(); 
                        String MasterId = parts[8].trim(); 
                        ticketFareAirlines.setTicketNo(ticketNo);
                        if(StringUtils.isNotEmpty(TicketFare)){
                            ticketFareAirlines.setTicketFare(new BigDecimal(TicketFare));
                        }else{
                            ticketFareAirlines.setTicketFare(new BigDecimal(0));
                        }
                        if(StringUtils.isNotEmpty(TicketTax)){
                            ticketFareAirlines.setTicketTax(new BigDecimal(TicketTax));
                        }else{
                            ticketFareAirlines.setTicketTax(new BigDecimal(0));
                        }

                        if(Department.equalsIgnoreCase("I")){
                            Department = "wendy";
                        }else if(Department.equalsIgnoreCase("O")){
                            Department = "outbound";
                        }
                        mAirlineAgent.setId(Airline);
                        if(StringUtils.isNotEmpty(Airline)){
                            ticketFareAirlines.setMAirlineAgent(mAirlineAgent);
                        }
                        Master master = new Master();
                        if(StringUtils.isNotEmpty(MasterId)){
                            master.setId(MasterId);
                            ticketFareAirlines.setMaster(master);
                        }
                        ticketFareAirlines.setPassenger(Passenger);
                        ticketFareAirlines.setOwner(staffowner);
                        ticketFareAirlines.setRoutingDetail(ticketflightrouting);
                        ticketFareAirlines.setInvAmount(invAmount);
                        ticketFareAirlines.setIsTempTicket(0);
                        request.setAttribute(TICKETBUY, TicketBy);
                        request.setAttribute(TICKETROUTING, TicketRouting);
                        request.setAttribute(ISSUEDATE, IssueDate);
                        request.setAttribute(DEPARTMENT, Department);
                        request.setAttribute(TICKETFAREFLAG,"notdummy");
                    }
                    
                    ticketFareAirlines.setTicketNo(ticketNo);
                    request.setAttribute(TICKETFARE,ticketFareAirlines);

                }else{
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketFare()))){
                        ticketFareAirline.setTicketFare(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketTax()))){
                        ticketFareAirline.setTicketTax(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketIns()))){
                        ticketFareAirline.setTicketIns(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getTicketCommission()))){
                        ticketFareAirline.setTicketCommission(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAgentCommission()))){
                        ticketFareAirline.setAgentCommission(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getSalePrice()))){
                        ticketFareAirline.setSalePrice(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getDiffVat()))){
                        ticketFareAirline.setDiffVat(new BigDecimal(0)); 
                    }             
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getOverCommission()))){
                        ticketFareAirline.setOverCommission(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAddPay()))){
                        ticketFareAirline.setAddPay(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAgentComPay()))){
                        ticketFareAirline.setAgentComPay(new BigDecimal(0)); 
                    }                    
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getLitterCommission()))){
                        ticketFareAirline.setLitterCommission(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getDecPay()))){
                        ticketFareAirline.setDecPay(new BigDecimal(0)); 
                    }
                    if(StringUtils.isEmpty(String.valueOf(ticketFareAirline.getAgentComReceive()))){
                        ticketFareAirline.setAgentComReceive(new BigDecimal(0)); 
                    }
                    ticketFareAirline.setIsTempTicket(1);
                    
                    request.setAttribute(TICKETFARE,ticketFareAirline);
                    request.setAttribute(TICKETTYPE, ticketFareAirline.getTicketType());
                    request.setAttribute(TICKETBUY, ticketFareAirline.getTicketBuy());
                    request.setAttribute(TICKETROUTING, ticketFareAirline.getTicketRouting());
                    request.setAttribute(ISSUEDATE, ticketFareAirline.getIssueDate());
                    request.setAttribute(TICKETCOMMDATE, ticketFareAirline.getTicketCommissionDate());
                    request.setAttribute(AGENTCOMMDATE, ticketFareAirline.getAgentCommissionDate());
                    agents = agentService.getAgentFromID(String.valueOf(ticketFareAirline.getAgentId()));
                    request.setAttribute(SELECTEDAGENT, agents);
                    request.setAttribute(OVERDATE, ticketFareAirline.getOverDate());
                    request.setAttribute(LITTERDATE, ticketFareAirline.getLitterDate());
                    request.setAttribute(DECPAYDATE, ticketFareAirline.getDecPayDate());
                    request.setAttribute(ADDPAYDATE, ticketFareAirline.getAddPayDate());
                    request.setAttribute(AGENTPAYDATE, ticketFareAirline.getAgentPayDate());
                    request.setAttribute(AGENTRECEIVEDATE, ticketFareAirline.getAgentReceiveDate());
                    request.setAttribute(DEPARTMENT, ticketFareAirline.getDepartment());
                    request.setAttribute(TICKETFAREFLAG,"notdummy");
                    request.setAttribute(DUEDATE, dueDate);
                }
                

            }
        }else if ("searchTicketnoNew".equalsIgnoreCase(action)) {
            System.out.print("ticketNo : " +ticketNo);
            if("".equals(ticketNo) || ticketNo == null ){
                System.out.print("ticketNo is null");
            }else{
                List<BookingFlight> bookingFlights = new ArrayList<BookingFlight>();
                List<AirticketFlightView> airticketFlightView = new ArrayList<AirticketFlightView>();
                bookingFlights = ticketFareAirlineService.getListFlightFromTicketNo(ticketNo);
                request.setAttribute(FLIGHTDETAIL, bookingFlights);
                
                List<InvoiceDetailView> invoiceDetailViewList = new ArrayList<InvoiceDetailView>();
                invoiceDetailViewList = ticketFareAirlineService.getInvoiceDetailFromTicketNo(ticketNo);

                List<ReceiptDetailView> receiptDetailViewListTemp = new ArrayList<ReceiptDetailView>();
                List<ReceiptDetailView> receiptDetailViewList = new ArrayList<ReceiptDetailView>();
                if(((invoiceDetailViewList != null))&&(!"null".equals(invoiceDetailViewList.get(0).getInvoiceId()))){
                    request.setAttribute(INVOICEDETAILLIST, invoiceDetailViewList);
                    for (int i = 0; i < invoiceDetailViewList.size() ; i++) {
                        BigDecimal invAmounttemp = invoiceDetailViewList.get(0).getInvAmount();
                        invAmount = invAmounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                        invNo = invoiceDetailViewList.get(0).getInvNo();
                        invDate = String.valueOf(invoiceDetailViewList.get(0).getInvDate());
                        invcredit =  invoiceDetailViewList.get(0).getCredit();
                        invcreditvalue = invoiceDetailViewList.get(0).getCreditValue();
                        staffowner =  invoiceDetailViewList.get(0).getOwner();
                        ticketflightrouting = invoiceDetailViewList.get(0).getRouting();
                        request.setAttribute(SELECTINVID,invoiceDetailViewList.get(0).getInvoiceId());
                        receiptDetailViewListTemp = receiptService.getReceiptDetailViewFromInvDetailId(invoiceDetailViewList.get(i).getId());
                        if(receiptDetailViewListTemp != null){
                            for (int j = 0; j < receiptDetailViewListTemp.size() ; j++) {
                                ReceiptDetailView receiptDetailView = new ReceiptDetailView();
                                receiptDetailView.setReceiptNo(receiptDetailViewListTemp.get(j).getReceiptNo());
                                receiptDetailView.setReceiptDate(receiptDetailViewListTemp.get(j).getReceiptDate());
                                receiptDetailView.setReceiveDate(receiptDetailViewListTemp.get(j).getReceiveDate());
                                receiptDetailView.setMbillTypeStatus(receiptDetailViewListTemp.get(j).getMbillTypeStatus());
                                receiptDetailView.setInvoiceNo(receiptDetailViewListTemp.get(j).getInvoiceNo());
                                receiptDetailViewList.add(receiptDetailView);
                            }
                        }
                    }
                }else{
                    if(invoiceDetailViewList != null){
                        for(int i = 0; i < invoiceDetailViewList.size() ; i++) {
                            BigDecimal invAmounttemp = invoiceDetailViewList.get(0).getInvAmount();
                            invAmount = invAmounttemp.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            staffowner =  invoiceDetailViewList.get(0).getOwner();
                            ticketflightrouting = invoiceDetailViewList.get(0).getRouting();
                        }
                    }
                }
                System.out.println(" invAmount " + invAmount);
                request.setAttribute(INVOICENO, invNo);
                request.setAttribute(INVOICEDATE, invDate);
                request.setAttribute(INVOICECREDIT, invcredit);
                request.setAttribute(INVOICECREDITVALUE, invcreditvalue);
                request.setAttribute(RECEIPTDETAILLIST, receiptDetailViewList);
                
                List<RefundAirticketDetail> refundAirticketDetailList = ticketFareAirlineService.getRefundAirticketDetailFromTicketNo(ticketNo);
                request.setAttribute(REFUNDDETAILLIST, refundAirticketDetailList);
                
                if(bookingFlights == null){
                    airticketFlightView = ticketFareAirlineService.getListAirticketFlightFromTicketNo(ticketNo);
                    request.setAttribute(FLIGHTDETAILFROMAIR, airticketFlightView);
                    if(airticketFlightView != null){
                        request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
                    }
                }else{
                    request.setAttribute(FLIGHTDETAILFLAG,"notdummy");
                }
                
                String airticketPass = ticketFareAirlineService.getTicketFareBookingFromTicketNo(ticketNo);
                TicketFareAirline ticketFareAirlines = new TicketFareAirline();
                request.setAttribute(TICKETFAREFLAG,"dummy");
                if(StringUtils.isNotEmpty(airticketPass)){
                    String[] parts = airticketPass.split(",");
                    String TicketFare = parts[0].trim();
                    String TicketTax = parts[1].trim();
                    String IssueDate = parts[2].trim(); 
                    String TicketRouting = parts[3].trim(); 
                    String Airline = parts[4].trim(); 
                    String TicketBy = parts[5].trim(); 
                    String Passenger = parts[6].trim(); 
                    String Department = parts[7].trim(); 
                    String MasterId = parts[8].trim(); 
                    ticketFareAirlines.setTicketNo(ticketNo);

                    if(Department.equalsIgnoreCase("I")){
                        Department = "wendy";
                    }else if(Department.equalsIgnoreCase("O")){
                        Department = "outbound";
                    }
                    mAirlineAgent.setId(Airline);
                    if(StringUtils.isNotEmpty(Airline)){
                        ticketFareAirlines.setMAirlineAgent(mAirlineAgent);
                    }
                    Master master = new Master();
                    if(StringUtils.isNotEmpty(MasterId)){
                        master.setId(MasterId);
                        ticketFareAirlines.setMaster(master);
                    }
                    ticketFareAirlines.setPassenger(Passenger);
                    ticketFareAirlines.setOwner(staffowner);
                    ticketFareAirlines.setRoutingDetail(ticketflightrouting);
                    ticketFareAirlines.setInvAmount(invAmount);
                    ticketFareAirlines.setIsTempTicket(0);
                    request.setAttribute(TICKETBUY, TicketBy);
                    request.setAttribute(TICKETROUTING, TicketRouting);
                    request.setAttribute(ISSUEDATE, IssueDate);
                    request.setAttribute(DEPARTMENT, Department);
                    request.setAttribute(TICKETFAREFLAG,"notdummy");
                }

                ticketFareAirlines.setTicketNo(ticketNo);
                request.setAttribute(TICKETFARE,ticketFareAirlines);
            }
        }
        return AddTicketFare;
    }
    
    
    public void setResponseAttribute(HttpServletRequest request,String refno,String invno) {
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        List<Agent> agent = utilityService.getListAgent();
        request.setAttribute(Agent, agent);
        List<MPaymentDoctype> mPaymentDoctypeList = utilityService.getListMpaymentDocType("airticket");
        request.setAttribute(PVTYPELIST, mPaymentDoctypeList);
        
        request.setAttribute(FLIGHTDETAILFLAG,"dummy");
        request.setAttribute(REFNO,refno);
        request.setAttribute(INVNO,invno);
        MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("withholding tax");
        request.setAttribute(withholdingtax,mDefaultData.getValue());
        
        MDefaultData mDefaultDatavat = utilityService.getMDefaultDataFromType("vat");
        request.setAttribute(VAT,mDefaultDatavat.getValue());
    
    }
   
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public TicketFareAirlineService getTicketFareAirlineService() {
        return ticketFareAirlineService;
    }

    public void setTicketFareAirlineService(TicketFareAirlineService ticketFareAirlineService) {
        this.ticketFareAirlineService = ticketFareAirlineService;
    }

    public AgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(AgentService agentService) {
        this.agentService = agentService;
    }

    public ReceiptService getReceiptService() {
        return receiptService;
    }

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

}
