package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAirlineAgent;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.PaymentAirCredit;
import com.smi.travel.datalayer.entity.PaymentAirDebit;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentAirticketFare;
import com.smi.travel.datalayer.entity.PaymentAirticketRefund;
import com.smi.travel.datalayer.entity.RefundAirticketDetail;
import com.smi.travel.datalayer.entity.RefundAirticketDetailView;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.entity.TicketFareAirline;
import com.smi.travel.datalayer.service.PaymentAirTicketService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.datalayer.view.entity.TicketFareView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class PaymentAirlineController extends SMITravelController {
    private static final ModelAndView PaymentAirline = new ModelAndView("PaymentAirline");
    private static final ModelAndView PaymentAirline_REFRESH = new ModelAndView(new RedirectView("PaymentAirline.smi", true));
    private static final String AIRLINELIST = "airlineList";
    private static final String PAYBYLIST = "payByList";
    private static final String INVOICESUPLIST = "invoiceSupList";
    private static final String PAYMENTAIRTICKET = "paymentAirticket"; 
    private static final String TICKETFARELIST = "ticketFareList"; // search table ticketfare
    private static final String SELECTEDINVOICE = "SelectedInvoice"; // select invoice sup code , name , apcode
    private static final String TICKETFROM = "TicketForm";
    private static final String TYPEAIRLINE = "TypeAirline";
    private static final String DATEFROM = "dateFrom";
    private static final String DATETO = "dateTo";
    private static final String SAVERESULT = "saveresult";
    private static final String PAYDATE = "payDate";
    private static final String DUEDATE = "dueDate";
    private static final String EXPORTDATE = "exportDate";
    private static final String PAYNO = "payNo";
    private static final String VAT = "vat";
    private static final String TICKETFARECOUNT ="ticketFareCount";
    private static final String FLAGSEARCG ="flagSearch";
    private static final String DELETERESULT = "deleteresult";
    private static final String ADDREFUNDLIST = "addRefundList"; 
    private static final String SETCALCULATETICKET = "setCalculateTicket"; 
    private static final String SETCALCULATEREFUND = "setCalculateRefund";
    private static final String OPTIONSAVE = "optionSave";
    private static final String SEARCHPAYMENTNOFLAG = "searchPaymentNoFlag";
    private static final String CREDITROWCOUNT = "creditRowCount";
    private static final String CREDITLIST = "creditList";
    private static final String SETCALCULATECREDIT = "setCalculateCredit";
    private static final String withholdingtax = "withholdingtax";
    private static final String DEBITROWCOUNT = "debitRowCount";
    private static final String DEBITLIST = "debitList";
    private static final String SETCALCULATEDEBIT = "setCalculateDebit";
    private static final String TYPEAIRLINEOTHER = "TypeAirlineOther";
    private static final String TICKETTYPE = "TicketType";
    private UtilityService utilityService; 
    private PaymentAirTicketService paymentAirTicketService;
    UtilityFunction util;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String paymentId = request.getParameter("paymentId");
        String paymentNo = request.getParameter("paymentNo");
        String invoiceSupCode = request.getParameter("invoiceSupCode");
        String apCode = request.getParameter("apCode");
        String detail = request.getParameter("detail");
        String payBy = request.getParameter("payBy");
        String agentAmount = request.getParameter("agentAmount");
//        String creditNote = request.getParameter("creditNote");
//        String creditAmount = request.getParameter("creditAmount");
        String commissionVat = request.getParameter("commissionVat");
//        String debitNote = request.getParameter("debitNote");
        String cash = request.getParameter("cash"); 
        String withholdingTax = request.getParameter("withholdingTax");
        String chqNo = request.getParameter("chqNo");
        String amount = request.getParameter("amount");
        String totalPayment = request.getParameter("totalPayment");
//        String debitAmount = request.getParameter("debitAmount");
        String ticketFrom = request.getParameter("ticketFrom");
        String typeAirline = request.getParameter("typeAirline");
        String totalCommissionTicketFare = request.getParameter("totalCommissionTicketFare");
        String totalAmountTicketFare = request.getParameter("totalAmountTicketFare");
        String totalAmountRefund = request.getParameter("totalAmountRefund");
        String totalAmountRefundVat = request.getParameter("totalAmountRefundVat");
        String optionSave = request.getParameter("optionSave");
        // Add PayTo radio
        String payto = request.getParameter("payto");
        String typeAirlineOther = request.getParameter("typeAirlineOther");
        String ticketType = request.getParameter("ticketType");
        String isExport = request.getParameter("isExport");
        String wildCardSearch = request.getParameter("wildCardSearch");
        String keyCode = request.getParameter("keyCode");

        String dateFrom = util.covertStringDateToFormatYMD(request.getParameter("dateFrom"));
        String dateTo = util.covertStringDateToFormatYMD(request.getParameter("dateTo"));
        String paymentDate = util.covertStringDateToFormatYMD(request.getParameter("paymentDate")); 
        String duePaymentDate = util.covertStringDateToFormatYMD(request.getParameter("duePaymentDate"));
        String exportDate = util.covertStringDateToFormatYMD(request.getParameter("exportDate"));
        
        String saveandnewresult = request.getParameter("saveandnewresult");
        if("savesuccessful".equalsIgnoreCase(saveandnewresult)){
            request.setAttribute(SAVERESULT, "save successful");  
        }
        System.out.println("  exportDate " + exportDate);
        System.out.println("  isExport " + isExport);
        request.setAttribute(CREDITROWCOUNT, "1");
        request.setAttribute(DEBITROWCOUNT, "1");
        request.setAttribute(TICKETFARECOUNT,"0");
        request.setAttribute(FLAGSEARCG,"0");
        request.setAttribute(SETCALCULATETICKET,0);
        request.setAttribute(SETCALCULATEREFUND,0);
        request.setAttribute(SEARCHPAYMENTNOFLAG,"notdummy");
        PaymentAirticket paymentAirticket = new PaymentAirticket();
        InvoiceSupplier invoiceSupplier = new InvoiceSupplier();
        MAccpay mAccpay = new MAccpay();
        List<TicketFareAirline> ticketFareAirlines = new ArrayList<TicketFareAirline>();
        List<TicketFareView> ticketFareViews = new ArrayList<TicketFareView>();
        List<RefundAirticketDetailView> refundAirticketDetailViews = new ArrayList<RefundAirticketDetailView>();

        util = new UtilityFunction();
        String result = "";
        SystemUser user = (SystemUser) session.getAttribute("USER");
        
        if (StringUtils.isNotEmpty(paymentId)){
            paymentAirticket.setId(paymentId);
        }
        
        MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("withholding tax");
        MDefaultData mDefaultDatavat = utilityService.getMDefaultDataFromType("vat");
        paymentAirticket.setVat(new BigDecimal(String.valueOf(mDefaultDatavat.getValue())));
        paymentAirticket.setWht(new BigDecimal(String.valueOf(mDefaultData.getValue())));   
        
        if ("search".equalsIgnoreCase(action)){
            if(paymentNo == null){
                System.out.print("Payment No is null");
            }else{
                paymentAirticket = paymentAirTicketService.getPaymentAirTicketFromPayno(paymentNo);
                if(paymentAirticket != null){
                    if (StringUtils.isNotEmpty(paymentAirticket.getInvoiceSup())){
                        invoiceSupplier = utilityService.getDataInvoiceSuppiler(paymentAirticket.getInvoiceSup());
                        request.setAttribute(SELECTEDINVOICE, invoiceSupplier);
                    }
                    if(paymentAirticket.getMAccpay() != null){
                        mAccpay.setId(paymentAirticket.getMAccpay().getId());
                        paymentAirticket.setMAccpay(mAccpay);
                    }
                    request.setAttribute(PAYDATE, paymentAirticket.getPayDate());
                    request.setAttribute(DUEDATE, paymentAirticket.getDueDate());
                    request.setAttribute(PAYMENTAIRTICKET,paymentAirticket);
                    request.setAttribute(EXPORTDATE, paymentAirticket.getExportDate());
                    
                    ticketFareViews = paymentAirTicketService.getTicketFareViewsByPaymentAirId(paymentAirticket.getId());
                    if(ticketFareViews != null){
                        request.setAttribute(FLAGSEARCG,"1");
                        request.setAttribute(TICKETFARELIST,ticketFareViews);
                        request.setAttribute(SETCALCULATETICKET,1);
                    }
                    refundAirticketDetailViews = paymentAirTicketService.getRefundDetailByPaymentAirId(paymentAirticket.getId());
                    if(refundAirticketDetailViews != null){
                        request.setAttribute(SETCALCULATEREFUND,1);
                    }
                    request.setAttribute(ADDREFUNDLIST,refundAirticketDetailViews);
                    
                    // Credit
                    List<PaymentAirCredit> payPaymentAirCredits = paymentAirTicketService.getPaymentAirCreditByPaymentAirId(paymentAirticket.getId());
                    if(payPaymentAirCredits != null){
                        request.setAttribute(SETCALCULATECREDIT,1);
                        request.setAttribute(CREDITROWCOUNT,payPaymentAirCredits.size()+1);
                    }  
                    request.setAttribute(CREDITLIST,payPaymentAirCredits);
                    
                    /// Debit
                    List<PaymentAirDebit> payPaymentAirDebits = paymentAirTicketService.getPaymentAirDebitByPaymentAirId(paymentAirticket.getId());
                    if(payPaymentAirDebits != null){
                        request.setAttribute(SETCALCULATEDEBIT,1);
                        request.setAttribute(DEBITROWCOUNT,payPaymentAirDebits.size()+1);
                    }  
                    request.setAttribute(DEBITLIST,payPaymentAirDebits);
                    
                    request.setAttribute(SEARCHPAYMENTNOFLAG,"notdummy");
                }else{
                    request.setAttribute(SEARCHPAYMENTNOFLAG,"dummy");
                }
            }
            request.setAttribute(PAYNO,paymentNo);
            
        }else if("searchTicketFare".equalsIgnoreCase(action)){
            if (StringUtils.isNotEmpty(paymentId)){
                paymentAirticket.setId(paymentId);
            }
            paymentAirticket.setPayNo(paymentNo);
            if (StringUtils.isNotEmpty(paymentDate)){
                paymentAirticket.setPayDate(util.convertStringToDate(paymentDate));
                request.setAttribute(PAYDATE, paymentDate);
            }
            if (StringUtils.isNotEmpty(duePaymentDate)){
                paymentAirticket.setDueDate(util.convertStringToDate(duePaymentDate));
                request.setAttribute(DUEDATE, duePaymentDate);
            }
            paymentAirticket.setInvoiceSup(invoiceSupCode);
            paymentAirticket.setApCode(apCode);
            paymentAirticket.setDetail(detail);
//            paymentAirticket.setCreditNote(creditNote);
//            paymentAirticket.setDebitNote(debitNote);
            paymentAirticket.setChqNo(chqNo); 
            paymentAirticket.setCreateBy(user.getUsername());
            paymentAirticket.setCreateDate(new Date());
            paymentAirticket.setIsExport("".equalsIgnoreCase(isExport)? 0 : Integer.parseInt(isExport));
            
            if(!"null".equalsIgnoreCase(exportDate) && !exportDate.isEmpty()){
                paymentAirticket.setExportDate(util.convertStringToDate(exportDate));
                request.setAttribute(EXPORTDATE,exportDate);
            }
            
            if(StringUtils.isNotEmpty(payBy)){
                mAccpay.setId(payBy);
                paymentAirticket.setMAccpay(mAccpay);
            }
            
            if(StringUtils.isNotEmpty(agentAmount)){
                paymentAirticket.setAgentAmount(new BigDecimal(String.valueOf(agentAmount.replaceAll(",","")))); 
            }else{
                paymentAirticket.setAgentAmount(new BigDecimal(0)); 
            }
            
//            if(StringUtils.isNotEmpty(creditAmount)){
//                paymentAirticket.setCreditAmount(new BigDecimal(String.valueOf(creditAmount.replaceAll(",","")))); 
//            }else{
//                paymentAirticket.setCreditAmount(new BigDecimal(0)); 
//            }
            
//            if(StringUtils.isNotEmpty(debitAmount)){
//                paymentAirticket.setDebitAmount(new BigDecimal(String.valueOf(debitAmount.replaceAll(",","")))); 
//            }else{
//                paymentAirticket.setDebitAmount(new BigDecimal(0)); 
//            }            
            
            if(StringUtils.isNotEmpty(cash)){
                paymentAirticket.setCash(new BigDecimal(String.valueOf(cash.replaceAll(",","")))); 
            }else{
                paymentAirticket.setCash(new BigDecimal(0)); 
            }            
            if(StringUtils.isNotEmpty(withholdingTax)){
                paymentAirticket.setWitholdingTax(new BigDecimal(String.valueOf(withholdingTax.replaceAll(",","")))); 
            }else{
                paymentAirticket.setWitholdingTax(new BigDecimal(0)); 
            }
       
            if(StringUtils.isNotEmpty(amount)){
                paymentAirticket.setChqAmount(new BigDecimal(String.valueOf(amount.replaceAll(",","")))); 
            }else{
                paymentAirticket.setChqAmount(new BigDecimal(0)); 
            }
            
            if(StringUtils.isNotEmpty(totalPayment)){
                paymentAirticket.setTotalAmount(new BigDecimal(String.valueOf(totalPayment.replaceAll(",","")))); 
            }else{
                paymentAirticket.setTotalAmount(new BigDecimal(0)); 
            }            
            paymentAirticket.setPayTo(payto);
            if(StringUtils.isNotEmpty(invoiceSupCode)){
                invoiceSupplier = utilityService.getDataInvoiceSuppiler(invoiceSupCode);
                request.setAttribute(SELECTEDINVOICE, invoiceSupplier);
            }
            
            if("OTHER".equalsIgnoreCase(typeAirline)){
                typeAirline = "";
            }
            
            ticketFareViews = paymentAirTicketService.getListTicketFare(dateFrom,dateTo,ticketFrom,typeAirline,invoiceSupCode,typeAirlineOther,ticketType);
            if(ticketFareViews != null){
                request.setAttribute(SETCALCULATETICKET,1);
                request.setAttribute(TICKETFARECOUNT,ticketFareViews.size()+1);
            }
            request.setAttribute(TICKETFARELIST,ticketFareViews);
            
            
            refundAirticketDetailViews = paymentAirTicketService.getRefundDetailByPaymentAirId(paymentId);
            if(refundAirticketDetailViews != null){
                 request.setAttribute(SETCALCULATEREFUND,1);
            }
            
//            List<TicketFareView> ticketFareViewTemp = new ArrayList<TicketFareView>();
//            ticketFareViewTemp = paymentAirTicketService.getTicketFareViewsByPaymentAirId(paymentId);
//            if(ticketFareViewTemp != null){
//                paymentAirTicketService.DeletePaymentAirFare(paymentId,null,2);
//            }
            
            request.setAttribute(ADDREFUNDLIST,refundAirticketDetailViews);
            
            List<PaymentAirCredit> payPaymentAirCredits = paymentAirTicketService.getPaymentAirCreditByPaymentAirId(paymentId);
            if(payPaymentAirCredits != null){
                request.setAttribute(SETCALCULATECREDIT,1);
                request.setAttribute(CREDITROWCOUNT,payPaymentAirCredits.size()+1);
            }  
            request.setAttribute(CREDITLIST,payPaymentAirCredits);
            
            
            List<PaymentAirDebit> payPaymentAirDebits = paymentAirTicketService.getPaymentAirDebitByPaymentAirId(paymentId);
            if(payPaymentAirDebits != null){
                request.setAttribute(SETCALCULATEDEBIT,1);
                request.setAttribute(DEBITROWCOUNT,payPaymentAirDebits.size()+1);
            }  
            request.setAttribute(DEBITLIST,payPaymentAirDebits);
            
            request.setAttribute(PAYNO,paymentNo);
        }else if("save".equalsIgnoreCase(action)){
           
            String counter = request.getParameter("counter");
            String rowRefundCount = request.getParameter("rowRefundCount");
            String countRowCredit = request.getParameter("countRowCredit");
            String countRowDebit= request.getParameter("countRowDebit");
            int Rows = Integer.parseInt(counter);
            int RowRefund = Integer.parseInt(rowRefundCount);
            int RowCredit = Integer.parseInt(countRowCredit);
            int RowDebit = Integer.parseInt(countRowDebit);
            
            System.out.println(" RowDebit ++++++++++++++++++++++++++++++ " + RowDebit);
            if (StringUtils.isNotEmpty(paymentId)){ //Update
                paymentAirticket.setId(paymentId);
                paymentAirticket.setIsExport("".equalsIgnoreCase(isExport)? 0 : Integer.parseInt(isExport));
            
                if(!"null".equalsIgnoreCase(String.valueOf(exportDate)) && !"".equalsIgnoreCase(String.valueOf(exportDate))){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try
                    {
                        Date date = df.parse(exportDate);

                        System.out.println("date : "+df.format(date));
                        paymentAirticket.setExportDate(date);
                    }
                    catch (ParseException ex)
                    {
                        System.out.println("Exception "+ex);
                    }
                    
                    request.setAttribute(EXPORTDATE,exportDate);
                }
                //save or update payment air ticket fare
                if(paymentAirticket.getPaymentAirticketFares() == null){
                    paymentAirticket.setPaymentAirticketFares(new ArrayList<PaymentAirticketFare>());
                }
                for (int i = 1; i < Rows  ; i++) {
                    String paymentAirFareId = request.getParameter("tableId" + i);
                        System.out.println("tableId "+i+"::::"+ paymentAirFareId);
                        PaymentAirticketFare paymentAirticketFare = new PaymentAirticketFare();
                        TicketFareAirline ticketFareAirline = new TicketFareAirline();
                        //payment air id
                        paymentAirticketFare.setPaymentAirticket(paymentAirticket);
                        //ticket fare id
                        ticketFareAirline.setId(paymentAirFareId);
                        paymentAirticketFare.setTicketFareAirline(ticketFareAirline);
                        paymentAirticket.getPaymentAirticketFares().add(paymentAirticketFare);
                        request.setAttribute(SETCALCULATETICKET,1);
                }
                //delete PaymentAirRefund 
                List<RefundAirticketDetailView> refundAirticketDetailViewTemp = new ArrayList<RefundAirticketDetailView>();
                refundAirticketDetailViewTemp = paymentAirTicketService.getRefundDetailByPaymentAirId(paymentId);
                if(refundAirticketDetailViewTemp != null){
                    paymentAirTicketService.DeletePaymentAirRefund(paymentId,null,2);
                }
                //save or update payment air ticket refund
                if(paymentAirticket.getPaymentAirticketRefunds() == null){
                    paymentAirticket.setPaymentAirticketRefunds(new ArrayList<PaymentAirticketRefund>());
                }
                for (int i = 1; i < RowRefund  ; i++) {
                    String refundDetailId = request.getParameter("tableRefundId" + i);
                    System.out.println("tableRefundId "+i+"::::"+ refundDetailId);
                        System.out.println("tableRefundId check"+i+"::::"+ refundDetailId);
                        PaymentAirticketRefund paymentAirticketRefund = new PaymentAirticketRefund();
                        RefundAirticketDetail refundAirticketDetail = new RefundAirticketDetail();
                        //payment air id
                        paymentAirticketRefund.setPaymentAirticket(paymentAirticket);
                        //ticket fare id
                        refundAirticketDetail.setId(refundDetailId);
                        paymentAirticketRefund.setRefundAirticketDetail(refundAirticketDetail);
                        paymentAirticket.getPaymentAirticketRefunds().add(paymentAirticketRefund);
                        request.setAttribute(SETCALCULATEREFUND,1);
                }
                //save or update payment air credit
                if(paymentAirticket.getPaymentAirCredits() == null){
                    paymentAirticket.setPaymentAirCredits(new ArrayList<PaymentAirCredit>());
                }
                System.out.println(" RowCredit " + RowCredit);
                for (int i = 1; i < RowCredit  ; i++) {
                    String creditId = request.getParameter("creditId" + i);
                    String creditNote = request.getParameter("creditNote" + i);
                    String creditAmount = request.getParameter("creditAmount" + i);
                    System.out.println("tableCreditId "+i+"::::"+ creditId);
                    System.out.println("creditNote "+i+"::::"+ creditNote);
                    System.out.println("creditAmount "+i+"::::"+ creditAmount);
                    PaymentAirCredit paymentAirCredit = new PaymentAirCredit();
                    //payment air id
                    paymentAirCredit.setPaymentAirticket(paymentAirticket);
                    //ticket credit id
                    paymentAirCredit.setId(creditId);
                    paymentAirCredit.setCreditNote(creditNote);
                    if(StringUtils.isNotEmpty(creditAmount)){
                        paymentAirCredit.setCreditAmount(new BigDecimal(String.valueOf(creditAmount.replaceAll(",","")))); 
                    }else{
                        paymentAirCredit.setCreditAmount(new BigDecimal(0)); 
                    }

                    if((!"".equalsIgnoreCase(creditNote) && creditNote != null) || (!"".equalsIgnoreCase(creditAmount) && creditAmount != null)){
                        paymentAirticket.getPaymentAirCredits().add(paymentAirCredit);
                    }
                }
                //save or update payment air debit
                if(paymentAirticket.getPaymentAirDebits() == null){
                    paymentAirticket.setPaymentAirDebits(new ArrayList<PaymentAirDebit>());
                }
                for (int i = 1; i < RowDebit ; i++) {
                    String debitId = request.getParameter("debitId" + i);
                    String debitNote = request.getParameter("debitNote" + i);
                    String debitAmount = request.getParameter("debitAmount" + i);
                    System.out.println("debitId "+i+"::::"+ debitId);
                    System.out.println("debitNote "+i+"::::"+ debitNote);
                    System.out.println("debitAmount "+i+"::::"+ debitAmount);
                    PaymentAirDebit paymentAirDebit = new PaymentAirDebit();
                    //payment air id
                    paymentAirDebit.setPaymentAirticket(paymentAirticket);
                    //ticket debit id
                    paymentAirDebit.setId(debitId);
                    paymentAirDebit.setDebitNote(debitNote);
                    if(StringUtils.isNotEmpty(debitAmount)){
                        paymentAirDebit.setDebitAmount(new BigDecimal(String.valueOf(debitAmount.replaceAll(",","")))); 
                    }else{
                        paymentAirDebit.setDebitAmount(new BigDecimal(0)); 
                    }

                    if((!"".equalsIgnoreCase(debitNote) && debitNote != null)  || (!"".equalsIgnoreCase(debitAmount) && debitAmount != null) ){
                        paymentAirticket.getPaymentAirDebits().add(paymentAirDebit);
                    }
                }
            }else{
                //save payment air ticket fare
                if(paymentAirticket.getPaymentAirticketFares() == null){
                    paymentAirticket.setPaymentAirticketFares(new ArrayList<PaymentAirticketFare>());
                }
                for (int i = 1; i < Rows  ; i++) {
                    String paymentAirFareId = request.getParameter("tableId" + i);
                    System.out.println("tableId "+i+"::::"+ paymentAirFareId);
                    if(paymentAirFareId != null){
                        
                        PaymentAirticketFare paymentAirticketFare = new PaymentAirticketFare();
                        TicketFareAirline ticketFareAirline = new TicketFareAirline();
                        paymentAirticketFare.setPaymentAirticket(paymentAirticket);
                        ticketFareAirline.setId(paymentAirFareId);
                        ticketFareAirline.setAgentId(null);
                        ticketFareAirline.setMAirlineAgent(null);
//                        ticketFareAirline.setMPaymentDoctype(null);
                        ticketFareAirline.setMaster(null);
                        paymentAirticketFare.setTicketFareAirline(ticketFareAirline);
                        paymentAirticket.getPaymentAirticketFares().add(paymentAirticketFare);
                        request.setAttribute(SETCALCULATETICKET,1);
                    }
                }
                //save payment air ticket refund
                if(paymentAirticket.getPaymentAirticketRefunds() == null){
                    paymentAirticket.setPaymentAirticketRefunds(new ArrayList<PaymentAirticketRefund>());
                }
                for (int i = 1; i < RowRefund  ; i++) {
                    String refundDetailId = request.getParameter("tableRefundId" + i);
                    System.out.println("tableRefundId "+i+"::::"+ refundDetailId);
                    if(refundDetailId != null){
                        System.out.println("tableRefundId check"+i+"::::"+ refundDetailId);
                        PaymentAirticketRefund paymentAirticketRefund = new PaymentAirticketRefund();
                        RefundAirticketDetail refundAirticketDetail = new RefundAirticketDetail();
                        //payment air id
                        paymentAirticketRefund.setPaymentAirticket(paymentAirticket);
                        //ticket fare id
                        refundAirticketDetail.setId(refundDetailId);
                        paymentAirticketRefund.setRefundAirticketDetail(refundAirticketDetail);
                        paymentAirticket.getPaymentAirticketRefunds().add(paymentAirticketRefund);
                        request.setAttribute(SETCALCULATEREFUND,1);
                    }
                }
                //save payment air credit
                if(paymentAirticket.getPaymentAirCredits() == null){
                    paymentAirticket.setPaymentAirCredits(new ArrayList<PaymentAirCredit>());
                }
                for (int i = 1; i < RowCredit  ; i++) {
                    String creditId = request.getParameter("creditId" + i);
                    String creditNote = request.getParameter("creditNote" + i);
                    String creditAmount = request.getParameter("creditAmount" + i);
                    System.out.println("tableCreditId "+i+"::::"+ creditId);
                    System.out.println("creditNote "+i+"::::"+ creditNote);
                    System.out.println("creditAmount "+i+"::::"+ creditAmount);
//                    if(creditId != null){
                    PaymentAirCredit paymentAirCredit = new PaymentAirCredit();
                    //payment air id
                    paymentAirCredit.setPaymentAirticket(paymentAirticket);
                    //ticket credit id
                    paymentAirCredit.setId(creditId);
                    paymentAirCredit.setCreditNote(creditNote);
                    if(StringUtils.isNotEmpty(creditAmount)){
                        paymentAirCredit.setCreditAmount(new BigDecimal(String.valueOf(creditAmount.replaceAll(",","")))); 
                    }else{
                        paymentAirCredit.setCreditAmount(new BigDecimal(0)); 
                    }
                    if(!"".equalsIgnoreCase(creditNote) || !"".equalsIgnoreCase(creditAmount)){
                        paymentAirticket.getPaymentAirCredits().add(paymentAirCredit);
                    }
                }
                
                 //save or update payment air debit
                if(paymentAirticket.getPaymentAirDebits() == null){
                    paymentAirticket.setPaymentAirDebits(new ArrayList<PaymentAirDebit>());
                }
                for (int i = 1; i < RowDebit ; i++) {
                    String debitId = request.getParameter("debitId" + i);
                    String debitNote = request.getParameter("debitNote" + i);
                    String debitAmount = request.getParameter("debitAmount" + i);
                    System.out.println("debitId "+i+"::::"+ debitId);
                    System.out.println("debitNote "+i+"::::"+ debitNote);
                    System.out.println("debitAmount "+i+"::::"+ debitAmount);
                    PaymentAirDebit paymentAirDebit = new PaymentAirDebit();
                    //payment air id
                    paymentAirDebit.setPaymentAirticket(paymentAirticket);
                    //ticket debit id
                    paymentAirDebit.setId(debitId);
                    paymentAirDebit.setDebitNote(debitNote);
                    if(StringUtils.isNotEmpty(debitAmount)){
                        paymentAirDebit.setDebitAmount(new BigDecimal(String.valueOf(debitAmount.replaceAll(",","")))); 
                    }else{
                        paymentAirDebit.setDebitAmount(new BigDecimal(0)); 
                    }

                    if(!"".equalsIgnoreCase(debitNote) || !"".equalsIgnoreCase(debitAmount)){
                        paymentAirticket.getPaymentAirDebits().add(paymentAirDebit);
                    }
                }
            }

            paymentAirticket.setPayNo(paymentNo);
            if (StringUtils.isNotEmpty(paymentDate)){
                paymentAirticket.setPayDate(util.convertStringToDate(paymentDate));
                request.setAttribute(PAYDATE, paymentDate);
            }
            if (StringUtils.isNotEmpty(duePaymentDate)){
                paymentAirticket.setDueDate(util.convertStringToDate(duePaymentDate));
                request.setAttribute(DUEDATE, duePaymentDate);
            }
            paymentAirticket.setInvoiceSup(invoiceSupCode);
            paymentAirticket.setApCode(apCode);
            paymentAirticket.setDetail(detail);
//            paymentAirticket.setCreditNote(creditNote);
//            paymentAirticket.setDebitNote(debitNote);
            paymentAirticket.setChqNo(chqNo); 
            paymentAirticket.setCreateBy(user.getUsername());
            paymentAirticket.setCreateDate(new Date());
            paymentAirticket.setIsExport("".equalsIgnoreCase(isExport)? 0 : Integer.parseInt(isExport));
            
            if(!"null".equalsIgnoreCase(String.valueOf(exportDate)) && !"".equalsIgnoreCase(String.valueOf(exportDate))){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    try
                    {
                        Date date = df.parse(exportDate);

                        System.out.println("date : "+df.format(date));
                        paymentAirticket.setExportDate(date);
                    }
                    catch (ParseException ex)
                    {
                        System.out.println("Exception "+ex);
                    }
                    
                    request.setAttribute(EXPORTDATE,exportDate);
                }
            
            if(StringUtils.isNotEmpty(payBy)){
                mAccpay.setId(payBy);
                paymentAirticket.setMAccpay(mAccpay);
            }
            
            if(StringUtils.isNotEmpty(agentAmount)){
                paymentAirticket.setAgentAmount(new BigDecimal(String.valueOf(agentAmount.replaceAll(",","")))); 
            }else{
                paymentAirticket.setAgentAmount(new BigDecimal(0)); 
            }

//            if(StringUtils.isNotEmpty(creditAmount)){
//                paymentAirticket.setCreditAmount(new BigDecimal(String.valueOf(creditAmount.replaceAll(",","")))); 
//            }else{
//                paymentAirticket.setCreditAmount(new BigDecimal(0)); 
//            }
            
//            if(StringUtils.isNotEmpty(debitAmount)){
//                paymentAirticket.setDebitAmount(new BigDecimal(String.valueOf(debitAmount.replaceAll(",","")))); 
//            }else{
//                paymentAirticket.setDebitAmount(new BigDecimal(0)); 
//            }            
            
            if(StringUtils.isNotEmpty(cash)){
                paymentAirticket.setCash(new BigDecimal(String.valueOf(cash.replaceAll(",","")))); 
            }else{
                paymentAirticket.setCash(new BigDecimal(0)); 
            }          
            
            if(StringUtils.isNotEmpty(withholdingTax)){
                paymentAirticket.setWitholdingTax(new BigDecimal(String.valueOf(withholdingTax.replaceAll(",","")))); 
            }else{
                paymentAirticket.setWitholdingTax(new BigDecimal(0)); 
            }
       
            if(StringUtils.isNotEmpty(amount)){
                paymentAirticket.setChqAmount(new BigDecimal(String.valueOf(amount.replaceAll(",","")))); 
            }else{
                paymentAirticket.setChqAmount(new BigDecimal(0)); 
            }
            
            if(StringUtils.isNotEmpty(totalPayment)){
                paymentAirticket.setTotalAmount(new BigDecimal(String.valueOf(totalPayment.replaceAll(",","")))); 
            }else{
                paymentAirticket.setTotalAmount(new BigDecimal(0)); 
            }            
    
            if(StringUtils.isNotEmpty(invoiceSupCode)){
                invoiceSupplier = utilityService.getDataInvoiceSuppiler(invoiceSupCode);
                request.setAttribute(SELECTEDINVOICE, invoiceSupplier);
            }
            paymentAirticket.setPayTo(payto);
            System.out.println(" paymentAirticket.getExportDate() +++ " + paymentAirticket.getExportDate());
            result = paymentAirTicketService.validateSavePaymentAir(paymentAirticket);
            
            System.out.println("result :::" +result);
            if (result == "success"){
                request.setAttribute(PAYNO,paymentAirticket.getPayNo());
                request.setAttribute(SAVERESULT, "save successful");
            } else if (result == null || result == "fail") {
                request.setAttribute(PAYNO,paymentAirticket.getPayNo());
                request.setAttribute(SAVERESULT, "save unsuccessful");
            } else {
                request.setAttribute(PAYNO,result);
                request.setAttribute(SAVERESULT, "save successful");
            }
            if("0".equals(optionSave)){
                if(paymentAirticket.getId() != null){
                    ticketFareViews = paymentAirTicketService.getTicketFareViewsByPaymentAirId(paymentAirticket.getId());
                    if(ticketFareViews != null){
                        request.setAttribute(SETCALCULATETICKET,1);
                    }
                    request.setAttribute(TICKETFARELIST,ticketFareViews);

                    refundAirticketDetailViews = paymentAirTicketService.getRefundDetailByPaymentAirId(paymentAirticket.getId());
                    if(refundAirticketDetailViews != null){
                        request.setAttribute(SETCALCULATEREFUND,1);
                    }  
                    request.setAttribute(ADDREFUNDLIST,refundAirticketDetailViews);
                    
                    List<PaymentAirCredit> payPaymentAirCredits = paymentAirTicketService.getPaymentAirCreditByPaymentAirId(paymentAirticket.getId());
                    if(payPaymentAirCredits != null){
                        request.setAttribute(SETCALCULATECREDIT,1);
                        request.setAttribute(CREDITROWCOUNT,payPaymentAirCredits.size()+1);
                    }  
                    request.setAttribute(CREDITLIST,payPaymentAirCredits);
                    
                    List<PaymentAirDebit> payPaymentAirDebits = paymentAirTicketService.getPaymentAirDebitByPaymentAirId(paymentAirticket.getId());
                    if(payPaymentAirDebits != null){
                        request.setAttribute(SETCALCULATEDEBIT,1);
                        request.setAttribute(DEBITROWCOUNT,payPaymentAirDebits.size()+1);
                    }  
                    request.setAttribute(DEBITLIST,payPaymentAirDebits);
                    
                }else{
                    request.setAttribute(SETCALCULATETICKET,0);
                    request.setAttribute(SETCALCULATEREFUND,0);
                    request.setAttribute(SETCALCULATECREDIT,0);
                    request.setAttribute(SETCALCULATEDEBIT,0);
                }
            }
            if("1".equals(optionSave)){
                return new ModelAndView(new RedirectView("PaymentAirline.smi?saveandnewresult=savesuccessful", true));
            }
            request.setAttribute(OPTIONSAVE,optionSave); 
        }else if("deleteTicket".equalsIgnoreCase(action)) {
            String deleteTicketId = request.getParameter("deleteTicketId");
            result = paymentAirTicketService.DeletePaymentAirFare(paymentId,deleteTicketId,1);
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }else if("deleteRefund".equalsIgnoreCase(action)) {
            String delRefundId = request.getParameter("delRefundId");
            String delRefundNo = request.getParameter("delRefundNo");
            result = paymentAirTicketService.DeletePaymentAirRefund(paymentId,delRefundId,1);
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }else if("deleteCredit".equalsIgnoreCase(action)) {
            String creditIdDelete = request.getParameter("creditIdDelete");
            result = paymentAirTicketService.DeletePaymentAirCredit("",creditIdDelete);
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }else if("deleteDebit".equalsIgnoreCase(action)) {
            String debitIdDelete = request.getParameter("debitIdDelete");
            result = paymentAirTicketService.DeletePaymentAirDebit("",debitIdDelete);
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }else if("wildCardSearch".equalsIgnoreCase(action)){
            paymentAirticket = paymentAirTicketService.getPaymentAirTicketByWildCardSearch(paymentId,paymentNo,wildCardSearch,keyCode);
            if(paymentAirticket != null){
                if (StringUtils.isNotEmpty(paymentAirticket.getInvoiceSup())){
                    invoiceSupplier = utilityService.getDataInvoiceSuppiler(paymentAirticket.getInvoiceSup());
                    request.setAttribute(SELECTEDINVOICE, invoiceSupplier);
                }
                if(paymentAirticket.getMAccpay() != null){
                    mAccpay.setId(paymentAirticket.getMAccpay().getId());
                    paymentAirticket.setMAccpay(mAccpay);
                }
                request.setAttribute(PAYDATE, paymentAirticket.getPayDate());
                request.setAttribute(DUEDATE, paymentAirticket.getDueDate());
                request.setAttribute(PAYMENTAIRTICKET,paymentAirticket);
                request.setAttribute(EXPORTDATE, paymentAirticket.getExportDate());

                ticketFareViews = paymentAirTicketService.getTicketFareViewsByPaymentAirId(paymentAirticket.getId());
                if(ticketFareViews != null){
                    request.setAttribute(FLAGSEARCG,"1");
                    request.setAttribute(TICKETFARELIST,ticketFareViews);
                    request.setAttribute(SETCALCULATETICKET,1);
                }
                refundAirticketDetailViews = paymentAirTicketService.getRefundDetailByPaymentAirId(paymentAirticket.getId());
                if(refundAirticketDetailViews != null){
                    request.setAttribute(SETCALCULATEREFUND,1);
                }
                request.setAttribute(ADDREFUNDLIST,refundAirticketDetailViews);

                // Credit
                List<PaymentAirCredit> payPaymentAirCredits = paymentAirTicketService.getPaymentAirCreditByPaymentAirId(paymentAirticket.getId());
                if(payPaymentAirCredits != null){
                    request.setAttribute(SETCALCULATECREDIT,1);
                    request.setAttribute(CREDITROWCOUNT,payPaymentAirCredits.size()+1);
                }  
                request.setAttribute(CREDITLIST,payPaymentAirCredits);

                /// Debit
                List<PaymentAirDebit> payPaymentAirDebits = paymentAirTicketService.getPaymentAirDebitByPaymentAirId(paymentAirticket.getId());
                if(payPaymentAirDebits != null){
                    request.setAttribute(SETCALCULATEDEBIT,1);
                    request.setAttribute(DEBITROWCOUNT,payPaymentAirDebits.size()+1);
                }  
                request.setAttribute(DEBITLIST,payPaymentAirDebits);

                request.setAttribute(SEARCHPAYMENTNOFLAG,"notdummy");
            }else{
                request.setAttribute(SEARCHPAYMENTNOFLAG,"dummy");
            }
            request.setAttribute(PAYNO,paymentAirticket.getPayNo());
            
                       
        }
        
        if((!"".equalsIgnoreCase(paymentNo)) && (paymentNo != null)){
            if("search".equalsIgnoreCase(action)){
                if((paymentNo.indexOf("%") >= 0)){
                    request.setAttribute("wildCardSearch", paymentNo);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }else if("118".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else if("119".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else{
                if((paymentNo.indexOf("%") == 0)){
                    request.setAttribute("wildCardSearch", paymentNo);
                }else if((!"".equalsIgnoreCase(wildCardSearch)) && (wildCardSearch.indexOf("%") == 0)){
                    request.setAttribute("wildCardSearch", wildCardSearch);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }
        }
        
//      request.setAttribute(PAYNO,paymentNo);
        request.setAttribute(DATEFROM,dateFrom);
        request.setAttribute(DATETO,dateTo);
        request.setAttribute(TICKETFROM,ticketFrom);
        request.setAttribute(TYPEAIRLINE,typeAirline);
        request.setAttribute(TYPEAIRLINEOTHER,typeAirlineOther);
        request.setAttribute(TICKETTYPE,ticketType);
        request.setAttribute(PAYMENTAIRTICKET,paymentAirticket);
        setResponseAttribute(request);
        return PaymentAirline;
    }
    
    public void setResponseAttribute(HttpServletRequest request) {
        List<MAirlineAgent> mAirlineAgentsList = utilityService.getListMAirLineAgent();
        request.setAttribute(AIRLINELIST,mAirlineAgentsList);
        List<MAccpay> mAccpayList = utilityService.getListMAccpay();
        request.setAttribute(PAYBYLIST,mAccpayList);
        List<InvoiceSupplier> invoiceSupplierList = utilityService.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST,invoiceSupplierList);
        MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("vat");
        request.setAttribute(VAT,mDefaultData.getValue());
        MDefaultData mDefault = utilityService.getMDefaultDataFromType("withholding tax");
        request.setAttribute(withholdingtax,mDefault.getValue());
    }
    
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public PaymentAirTicketService getPaymentAirTicketService() {
        return paymentAirTicketService;
    }

    public void setPaymentAirTicketService(PaymentAirTicketService paymentAirTicketService) {
        this.paymentAirTicketService = paymentAirTicketService;
    }
}
