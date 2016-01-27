package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.PaymentAirticket;
import com.smi.travel.datalayer.entity.PaymentDetailWendy;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.entity.ReceiptCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.InvoiceService;
import com.smi.travel.datalayer.service.ReceiptService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ReceiptController extends SMITravelController {
    private static final ModelAndView Receipt = new ModelAndView("Receipt");
    private static final ModelAndView Receipt_REFRESH = new ModelAndView(new RedirectView("Receipt.smi", true));
    private static final String LINKNAME = "Receipt";
    private static final String PVList = "PVList";
    private static final String CUSTOMERAGENT = "customerAgent";
    private static final String MBILLTYPELIST = "billTypeList";
    private static final String VAT = "vat";
    private static final String MCURRENCYLIST = "currencyList";
    private static final String MSTATUSLIST = "statusList";
    private static final String MCREDITBANKLIST = "creditBankList";
    private static final String PRODUCTROWCOUNT = "productRowCount";
    private static final String CREDITROWCOUNT = "creditRowCount";
    private static final String RECEIPT = "receipt"; // search receive no from Receipt table
    private static final String RECEIPTDETAILLIST = "receiptDetailList"; // search receive no from Receipt Detail table
    private static final String RECEIPTCREDITLIST = "receiptCreditList"; // search receive no from Receipt Credit table
    private static final String SAVERESULT = "saveresult"; // save result
    private static final String RECEIPTDATE = "receiveFromDate";
    private static final String CHQDATE1 = "chqDate1";
    private static final String CHQDATE2 = "chqDate2";
    private static final String DELETERESULT = "deleteresult";
    private static final String SEARCHRECEIPT = "searchReceipt";
    private static final String RECEIVEDATE = "receiveDate";
    private static final String INVIDLIST = "invoiceIdList";
    private UtilityService utilityService;
    private ReceiptService receiptService;
    private InvoiceService invoiceService;
    UtilityFunction util; 
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String action = request.getParameter("action");
        System.out.println("request.getRequestURI() " + request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll("Receipt", "");//request.getParameter("type");
        String callPage = utilty.getAddressUrl(request.getRequestURI());//request.getParameter("type");
        String paymentNo = request.getParameter("paymentNo");
        System.out.println("callPageFrom : "+callPageFrom);
        String receiveId = request.getParameter("receiveId");
        String receiveNo = request.getParameter("receiveNo");
        String inputDate = request.getParameter("inputDate");
        String receiveFromCode = request.getParameter("receiveFromCode");
        String receiveFromName = request.getParameter("receiveFromName");
        String receiveFromAddress = request.getParameter("receiveFromAddress");
        String remark = request.getParameter("remark");
        String receiveFromDate = request.getParameter("receiveFromDate");
        String inputStatus = request.getParameter("inputStatus");
        String arCode = request.getParameter("arCode");
        String withTax = request.getParameter("withTax");
        String cashAmount = request.getParameter("cashAmount");
        String cashMinusAmount = request.getParameter("cashMinusAmount");
        String bankTransfer = request.getParameter("bankTransfer");
        String chqBank1 = request.getParameter("chqBank1");
        String chqNo1 = request.getParameter("chqNo1");
        String chqDate1 = request.getParameter("chqDate1");        
        String chqAmount1 = request.getParameter("chqAmount1"); 
        String chqBank2 = request.getParameter("chqBank2");
        String chqNo2= request.getParameter("chqNo2");
        String chqDate2 = request.getParameter("chqDate2");        
        String chqAmount2 = request.getParameter("chqAmount2"); 
        String InputReceiptType = request.getParameter("InputReceiptType");
        String InputDepartment = request.getParameter("InputDepartment");
        String searchId = request.getParameter("Id");
        String receiveDate = request.getParameter("receiveDate");
        String wildCardSearch = request.getParameter("wildCardSearch");
        String keyCode = request.getParameter("keyCode");
        String invoiceTableNo = request.getParameter("invoiceTableNo1");
        String invoiceTableId = request.getParameter("invoiceTableId1");
        String isref = request.getParameter("isref");
        
        System.out.println(" callPageFrom " + callPageFrom);
        if(!"".equals(callPageFrom)){
           //String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeReceipt", callPageFrom.substring(1));  
           InputDepartment =  callPageFrom.substring(0,1);
           InputReceiptType   =  callPageFrom.substring(1);
        }
        System.out.println("InputDepartment : "+InputDepartment);
        System.out.println("InputReceiptType : "+InputReceiptType);
        if("W".equals(InputDepartment)){
            InputDepartment = "Wendy";
        }else if("I".equals(InputDepartment)){
            InputDepartment = "Inbound";
        }else if("O".equals(InputDepartment)){
            InputDepartment = "Outbound";
        }
        util = new UtilityFunction();
        String result = "";
        request.setAttribute(PRODUCTROWCOUNT, "1");
        request.setAttribute(CREDITROWCOUNT, "1");
        request.setAttribute(SEARCHRECEIPT,"notdummy");
        
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");
        request.setAttribute(RECEIPTDATE,String.valueOf(df.format(new Date())));
        request.setAttribute(RECEIVEDATE,String.valueOf(df.format(new Date())));
        //Role User
        String roleName = user.getRole().getName();
        System.out.println("roleName"+roleName);
        if("Finance Manager".equals(roleName)){
            roleName = "YES";
            request.setAttribute("roleName", roleName);
        }else{
            roleName = "NO";
            request.setAttribute("roleName", roleName);
        }
        
        if("new".equalsIgnoreCase(action)){
            System.out.println(" CLEAR DATA ");
        }else if ("edit".equalsIgnoreCase(action)){
            System.out.println(" ==================== edit =======================");
        }else if ("searchReceiveNo".equalsIgnoreCase(action)) {
            Receipt receipt = new Receipt();
            request.setAttribute(SEARCHRECEIPT,"dummy");
            if(!"".equals(receiveNo)){
                receipt = receiptService.getReceiptfromReceiptNo(receiveNo,InputDepartment,InputReceiptType);
                if(receipt != null) {
                    if(!receipt.getId().isEmpty()){
                        List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
                        List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
                        request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
                        request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
                        List<ReceiptDetail> recInvId = new ArrayList<ReceiptDetail>();
                        if(receiptDetailList != null){
                            for(int i = 0 ;i < receiptDetailList.size();i++){
                                if(receiptDetailList.get(i).getInvoiceDetail() != null){
                                    if(receiptDetailList.get(i).getInvoiceDetail().getInvoice() != null){
                                        
                                        if(i > 0){
                                            boolean check = true;
                                            String invNotemp1 = receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo();
                                            for(int j=0; j<recInvId.size(); j++){
                                                String invNotemp2 = recInvId.get(j).getInvoiceNo();
                                                if(invNotemp1.equalsIgnoreCase(invNotemp2)){
                                                    check = false;
                                                    j = recInvId.size();
                                                }
                                            }
                                            if(check){
                                                ReceiptDetail receiptD = new ReceiptDetail();
                                                receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
                                                receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
                                                receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
                                                recInvId.add(receiptD);
                                            }
//                                            String invNotemp1 = receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo();
//                                            String invNotemp2 = receiptDetailList.get(i-1).getInvoiceDetail().getInvoice().getInvNo();      
//                                            if(!invNotemp1.equalsIgnoreCase(invNotemp2)){
//                                                ReceiptDetail receiptD = new ReceiptDetail();
//                                                receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
//                                                receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
//                                                receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
//                                                recInvId.add(receiptD);
//                                            }
                                        }else{
                                            ReceiptDetail receiptD = new ReceiptDetail();
                                            receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
                                            receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
                                            receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
                                            recInvId.add(receiptD);
                                        }
                                    }
                                }
                            }
                            request.setAttribute(INVIDLIST, recInvId);
                            request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
                        }
                        if(receiptCreditList != null){
                        request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
                        }
                    }
                    request.setAttribute(SEARCHRECEIPT,"notdummy");
                    request.setAttribute(RECEIPT,receipt);
                    request.setAttribute(RECEIPTDATE,receipt.getRecDate());
                    request.setAttribute(RECEIVEDATE,receipt.getReceiveDate());
                }
            }
        }else if ("saveReceipt".equalsIgnoreCase(action)) {
            Receipt receipt = new Receipt();
            receipt.setId(receiveId);
            String counter = request.getParameter("counter");
            String countRowCredit = request.getParameter("countRowCredit");
            
            int rowsProduct = Integer.parseInt(counter);
            int rowsCredit = Integer.parseInt(countRowCredit);
                       
            //save or update payment air ticket fare
            if(receipt.getReceiptDetails() == null){
                receipt.setReceiptDetails(new ArrayList<ReceiptDetail>());
            }
            
            Invoice invoice = new Invoice();
            System.out.println(" invoiceTableNo " + invoiceTableNo);
            if(StringUtils.isNotEmpty(invoiceTableNo)){
                invoice.setInvNo(invoiceTableNo);
            }
            invoice.setInvTo(receiveFromCode);
            invoice.setInvName(receiveFromName);
            invoice.setInvAddress(receiveFromAddress);
            invoice.setArcode(arCode);
            invoice.setInvDate(util.convertStringToDate(receiveFromDate != "" ? receiveFromDate : ""));
            invoice.setDepartment(InputDepartment);
            invoice.setInvType(InputReceiptType);
            invoice.setIsLock(1);
            invoice.setCreateBy(user.getUsername());
            invoice.setCreateDate(new Date());
            if(StringUtils.isNotEmpty(inputStatus)){
                MAccterm mAccpay = new MAccterm();
                mAccpay.setId(inputStatus);
                invoice.setMAccTerm(mAccpay);
            }
            invoice.setStaff(user);
            MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
            mFinanceItemstatus.setId("1"); // 1 = Normal
            invoice.setMFinanceItemstatus(mFinanceItemstatus);
            String receiveProducttemp = "";
            
            System.out.println(" invoiceTableId " + invoiceTableId);
            if(StringUtils.isNotEmpty(invoiceTableId)){
                invoice.setId(invoiceTableId);
            }
            
            System.out.println(" ======================== rowsProduct ======================== " + rowsProduct);
            for (int i = 1; i < rowsProduct; i++) {
                String tableId = request.getParameter("tableId" + i);
                String receiveProduct = request.getParameter("receiveProduct" + i);
                String receiveDes = request.getParameter("receiveDes" + i);
                String receiveCost = request.getParameter("receiveCost" + i);
                String receiveCurCost = request.getParameter("receiveCurCost" + i);
                String receiveVat = request.getParameter("receiveVat" + i);
                String receiveIsVat = request.getParameter("receiveIsVat" + i);
//                String receiveGross = request.getParameter("receiveGross" + i);
                String receiveAmount = request.getParameter("receiveAmount" + i);
                String receiveCurrency = request.getParameter("receiveCurrency" + i);
                String receiveExRate = request.getParameter("receiveExRate" + i);
                String invId = request.getParameter("invId" + i);
                String billDescId = request.getParameter("billDescId" + i);
                String paymentId = request.getParameter("paymentId" + i);
                String airlineCode = request.getParameter("airlineCode" + i);
                String displayDescription = request.getParameter("DescriptionReceiptDetail" + i);
                String paymentTourId = request.getParameter("paymentTourId" + i);
                String gross = request.getParameter("grossInvoice" + i);               
//                System.out.println(" airlineCode " + airlineCode);
                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setId(tableId);
                receiptDetail.setReceipt(receipt);
                if(StringUtils.isNotEmpty(receiveProduct)){
                    MBilltype mBilltype = new MBilltype();
                    mBilltype.setId(receiveProduct);
                    receiveProducttemp = mBilltype.getId();
                    System.out.println(" receiveProducttemp +++++++ " + receiveProducttemp);
                    receiptDetail.setMBilltype(mBilltype);
                }
                receiptDetail.setDescription(receiveDes);
                receiptDetail.setDisplayDescription(displayDescription);
                receiptDetail.setCost(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveCost) ? receiveCost.replaceAll(",","") : 0)));
                receiptDetail.setCurCost(receiveCurCost);
                if("1".equals(receiveIsVat)){
                    receiptDetail.setIsVat(1);
                    MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("vat");
                    receiptDetail.setVat(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveVat) ? receiveVat.replaceAll(",","") : mDefaultData.getValue())));
                }else{
                    receiptDetail.setIsVat(0);
                    receiptDetail.setVat(new BigDecimal(BigInteger.ZERO));
                }

                receiptDetail.setAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveAmount) ? receiveAmount.replaceAll(",","") : 0)));
                receiptDetail.setExRate(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveExRate) ? receiveExRate.replaceAll(",","") : 0)));
                receiptDetail.setCurAmount(receiveCurrency);
                
                if(StringUtils.isNotEmpty(invId)){
                    InvoiceDetail invoiceDetail = new InvoiceDetail();
                    invoiceDetail.setId(invId);
                    receiptDetail.setInvoiceDetail(invoiceDetail);
                }
                if(StringUtils.isEmpty(invId) || "".equalsIgnoreCase(invId)){
                    List<InvoiceDetail>  invoiceDetailList = null;
                    if(StringUtils.isNotEmpty(billDescId)){
                        invoiceDetailList = invoiceService.getInvoiceDetailFromBillableDescId(billDescId);
                    }
//                    if(invoiceDetailList == null){
//                       Invoice invoice = new Invoice();
//                       invoice.setInvNo(receiveNo);
//                       invoice.setInvTo(receiveFromCode);
//                       invoice.setInvName(receiveFromName);
//                       invoice.setInvAddress(receiveFromAddress);
//                       invoice.setArcode(arCode);
//                       invoice.setInvDate(util.convertStringToDate(receiveFromDate != "" ? receiveFromDate : ""));
//                       invoice.setDepartment(InputDepartment);
//                       invoice.setInvType(InputReceiptType);
//                       invoice.setIsLock(1);
//                       invoice.setCreateBy(user.getUsername());
//                       invoice.setCreateDate(new Date());
//                       if(StringUtils.isNotEmpty(inputStatus)){
//                           MAccpay mAccpay = new MAccpay();
//                           mAccpay.setId(inputStatus);
//                           invoice.setMAccpay(mAccpay);
//                       }
//                       invoice.setStaff(user);
//                       MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
//                       mFinanceItemstatus.setId("1"); // 1 = Normal
//                       invoice.setMFinanceItemstatus(mFinanceItemstatus);

                       List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
                       InvoiceDetail invoiceDetail = new InvoiceDetail();
                       String displaydescfromrefno = "";
                       String refNo = "";
                       if(StringUtils.isNotEmpty(billDescId)){
                            BillableDesc bill = new BillableDesc();
                            bill.setId(billDescId);
                            refNo = receiptService.getRefitemidFromBillableDescId(billDescId);
                            invoiceDetail.setBillableDesc(bill);
                       }
                       if(receiptDetail.getMBilltype() != null){
                            invoiceDetail.setMbillType(receiptDetail.getMBilltype());
                       }    
                       invoiceDetail.setInvoice(invoice);
                       invoiceDetail.setDescription(receiptDetail.getDescription());

                       if(!"".equalsIgnoreCase(refNo)){
                            if("1".equalsIgnoreCase(receiveProducttemp)){
                                displaydescfromrefno = receiptService.getDescriptionInvoiceAirTicket(refNo);
                            }else if("2".equalsIgnoreCase(receiveProducttemp) || "8".equalsIgnoreCase(receiveProducttemp)){
                                displaydescfromrefno = receiptService.getDescriptionInvoiceOthers(refNo);
                            }else if("3".equalsIgnoreCase(receiveProducttemp)){
                                displaydescfromrefno = receiptService.getDescriptionInvoiceLand(refNo);
                            }else if("4".equalsIgnoreCase(receiveProducttemp)){
                                displaydescfromrefno = receiptService.getDescriptionInvoiceHotel(refNo);
                            }else if("6".equalsIgnoreCase(receiveProducttemp)){
                                displaydescfromrefno = receiptService.getDescriptionInvoiceDayTour(refNo);
                            }else if("7".equalsIgnoreCase(receiveProducttemp)){
                                displaydescfromrefno = receiptService.getDescriptionInvoiceAirAdditional(refNo);
                            }
                            invoiceDetail.setDisplayDescription(displaydescfromrefno);
                       }
                       invoiceDetail.setCost(receiptDetail.getCost());
                       invoiceDetail.setCostLocal(receiptDetail.getCost());
                       invoiceDetail.setCurCost(receiptDetail.getCurCost());
                       invoiceDetail.setAmount(receiptDetail.getAmount());
                       invoiceDetail.setAmountLocal(receiptDetail.getAmount());
                       invoiceDetail.setCurAmount(receiptDetail.getCurAmount());
                       invoiceDetail.setVat(receiptDetail.getVat());
                       invoiceDetail.setIsVat(receiptDetail.getIsVat());
                       invoiceDetail.setGross(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(gross) ? gross.replaceAll(",","") : 0)));
                       listInvoiceDetail.add(invoiceDetail);
                       
                        if( (receiveProduct!="" && receiveProduct!=null) || 
                            (receiveDes!="" && receiveDes!=null) || 
                            (receiveCost!="" && receiveCost!=null) || 
                            (receiveCurCost!="" && receiveCurCost!=null) || 
                            (receiveAmount!="" && receiveAmount!=null) || 
                            (receiveCurrency!="" && receiveCurrency!=null)){
                            invoice.setInvoiceDetails(listInvoiceDetail);
                        }
                       String invoiceNo = invoiceService.saveInvoiceDetail(invoice);
                       if("fail".equals(invoiceNo)){
                           System.out.println(" SAVE INVOICE FAIL ");
                       }else{
                           System.out.println("invoiceNo " + invoiceNo);
                           
                            if(invoice.getInvoiceDetails() != null){
                                // Check Flag Booking
                                String checkFlag = invoiceService.checkFlagBooking(invoice);
                                System.out.println("Check Flag :" + checkFlag);

                                //Set Booking Status
                                String setBookingStatus = invoiceService.setBookingStatus(invoice);
                                System.out.println("Set Booking Status : " + setBookingStatus);
                            }
                           
                           Invoice inv = invoiceService.getInvoiceFromInvoiceNumber(invoiceNo,"","");
                           List<InvoiceDetail> invDetaill = new ArrayList<InvoiceDetail>(inv.getInvoiceDetails());
                           for(int j=0 ; j < inv.getInvoiceDetails().size() ;j++){
                               InvoiceDetail invD = new InvoiceDetail();
                               invD.setId(invDetaill.get(j).getId());
                               receiptDetail.setInvoiceDetail(invD);
                           }
                       }
//                   }
                }
                
                if(StringUtils.isNotEmpty(paymentId)){
                    PaymentAirticket pay = new PaymentAirticket();
                    pay.setId(paymentId);
                    receiptDetail.setPaymentAirticket(pay);
                    receiptDetail.setRemark(receiveDes);
                    if(StringUtils.isNotEmpty(airlineCode)){
                        receiptDetail.setAirlineCode(airlineCode);
                    }
                    receiptDetail.setCurAmount("THB");
                }
                       
                if(StringUtils.isNotEmpty(paymentTourId)){
                    PaymentDetailWendy pays = new PaymentDetailWendy();
                    pays.setId(paymentTourId);
                    receiptDetail.setPaymentDetailWendy(pays);
                    receiptDetail.setRemark(receiveDes);
                    if(StringUtils.isNotEmpty(airlineCode)){
                        receiptDetail.setAirlineCode(airlineCode);
                    }
                    receiptDetail.setCurAmount("THB");
                }
                
                if( (receiveProduct!="" && receiveProduct!=null) || 
                    (receiveDes!="" && receiveDes!=null) || 
                    (receiveCost!="" && receiveCost!=null) || 
                    (receiveCurCost!="" && receiveCurCost!=null) || 
                    (receiveAmount!="" && receiveAmount!=null) || 
                    (receiveCurrency!="" && receiveCurrency!=null)){
                    receipt.getReceiptDetails().add(receiptDetail);
                }
            }
            
            if(receipt.getReceiptCredits() == null){
                receipt.setReceiptCredits(new ArrayList<ReceiptCredit>());
            }
            for (int i = 0; i < rowsCredit ; i++) {
                String tableId = request.getParameter("tableCreditId" + i);
                String creditBank = request.getParameter("creditBank" + i);
                String creditNo = request.getParameter("creditNo" + i);
                String creditExpired = request.getParameter("creditExpired" + i);
                String creditAmount = request.getParameter("creditAmount" + i);
                ReceiptCredit receiptCredit = new ReceiptCredit();
                receiptCredit.setId(tableId);
                receiptCredit.setReceipt(receipt);
                if(StringUtils.isNotEmpty(creditBank)){
                    MCreditBank mCreditBank = new MCreditBank();
                    mCreditBank.setId(creditBank);
                    receiptCredit.setMCreditBank(mCreditBank);
                }
                receiptCredit.setCreditNo(creditNo);
                receiptCredit.setCreditExpire(util.convertStringToDate(creditExpired != "" ? creditExpired : ""));
                receiptCredit.setCreditAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(creditAmount) ? creditAmount.replaceAll(",","") : 0)));
                if( (creditBank!="" && creditBank!=null) || 
                    (creditNo!="" && creditNo!=null) || 
                    (creditExpired!="" && creditExpired!=null) || 
                    (creditAmount!="" && creditAmount!=null)){
                    
                    receipt.getReceiptCredits().add(receiptCredit);
                }

            }
            
            receipt.setId(receiveId);
            receipt.setRecNo(receiveNo);
            receipt.setRecFrom(receiveFromCode);
            receipt.setRecName(receiveFromName);
            receipt.setRecAddress(receiveFromAddress);
            receipt.setRecDate(util.convertStringToDate(receiveFromDate != "" ? receiveFromDate : ""));
            receipt.setReceiveDate(util.convertStringToDate(receiveDate != "" ? receiveDate : ""));
            receipt.setArCode(arCode);
            receipt.setRemark(remark);
            if(!"".equalsIgnoreCase(isref) && isref != null){
                receipt.setIsRef(Integer.parseInt(isref));
            }
//            MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
            mFinanceItemstatus.setId("1"); // 1 = Normal
            receipt.setMFinanceItemstatus(mFinanceItemstatus);
            
            if(StringUtils.isNotEmpty(inputStatus)){
                MAccpay mAccpay = new MAccpay();
                mAccpay.setId(inputStatus);
                receipt.setMAccpay(mAccpay);
//                MItemstatus mItemstatus = new MItemstatus();
//                mItemstatus.setId(inputStatus);
//                receipt.setMItemStatus(mItemstatus);
            }
            receipt.setWithTax(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(withTax) ? withTax.replaceAll(",","") : 0)));
            receipt.setCashAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(cashAmount) ? cashAmount.replaceAll(",","") : 0)));
            receipt.setCashMinusAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(cashMinusAmount) ? cashMinusAmount.replaceAll(",","") : 0)));
            receipt.setBankTransfer(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(bankTransfer) ? bankTransfer.replaceAll(",","") : 0)));
            receipt.setChqBank1(chqBank1);
            receipt.setChqBank2(chqBank2);
            receipt.setChqNo1(chqNo1);
            receipt.setChqNo2(chqNo2);
            receipt.setChqDate1(util.convertStringToDate(chqDate1 != "" ? chqDate1 : ""));
            receipt.setChqDate2(util.convertStringToDate(chqDate2 != "" ? chqDate2 : ""));
            receipt.setChqAmount1(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(chqAmount1) ? chqAmount1.replaceAll(",","") : 0)));
            receipt.setChqAmount2(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(chqAmount2) ? chqAmount2.replaceAll(",","") : 0)));
            receipt.setDepartment(InputDepartment);
            receipt.setRecType(InputReceiptType);
            receipt.setCreateDate(new Date());
            receipt.setCreateBy(user.getUsername());
            if(receipt.getId() == ""){
                result = receiptService.insertReceipt(receipt);
            }else{
                result = receiptService.updateReceipt(receipt);
            }
            
            System.out.print("result :" + result + " =================== ");
            if (result == "fail") {
                request.setAttribute(SAVERESULT, "save unsuccessful");
            } else if (result == "success"){
                request.setAttribute(SAVERESULT, "save successful");
            } else{
                receipt.setRecNo(result);
                request.setAttribute(SAVERESULT, "save successful");
            }
            request.setAttribute(RECEIPT,receipt);
            request.setAttribute(RECEIPTDATE,receiveFromDate);
            request.setAttribute(RECEIVEDATE,receiveDate);
            request.setAttribute(CHQDATE1,chqDate1);
            request.setAttribute(CHQDATE2,chqDate2);
            
            List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
            List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
            request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
            request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
            if(receiptDetailList != null){
                request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
            }
            if(receiptCreditList != null){
                request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
            }

            List<ReceiptDetail> recInvId = new ArrayList<ReceiptDetail>();
            if(receiptDetailList != null){
                for(int i = 0 ;i < receiptDetailList.size();i++){
//                    ReceiptDetail receiptD = new ReceiptDetail();
                    if(receiptDetailList.get(i).getInvoiceDetail() != null){
                        if(receiptDetailList.get(i).getInvoiceDetail().getInvoice() != null){
                            if(i > 0){
                                boolean check = true;
                                String invNotemp1 = receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo();
                                for(int j=0; j<recInvId.size(); j++){
                                    String invNotemp2 = recInvId.get(j).getInvoiceNo();
                                    if(invNotemp1.equalsIgnoreCase(invNotemp2)){
                                        check = false;
                                        j = recInvId.size();
                                    }
                                }
                                if(check){
                                    ReceiptDetail receiptD = new ReceiptDetail();
                                    receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
                                    receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
                                    receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
                                    recInvId.add(receiptD);
                                }
//                                String invNotemp1 = receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo();
//                                String invNotemp2 = receiptDetailList.get(i-1).getInvoiceDetail().getInvoice().getInvNo();      
//                                if(!invNotemp1.equalsIgnoreCase(invNotemp2)){
//                                    ReceiptDetail receiptD = new ReceiptDetail();
//                                    receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
//                                    receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
//                                    receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
//                                    recInvId.add(receiptD);
//                                }
                            }else{
                                ReceiptDetail receiptD = new ReceiptDetail();
                                receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
                                receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
                                receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
                                recInvId.add(receiptD);
                            }
                        }
                    }
                }
                request.setAttribute(INVIDLIST, recInvId);
            }
                
        }else if("deleteReceiptDetail".equalsIgnoreCase(action)) {
            String receiptDetailIdDelete = request.getParameter("receiptDetailIdDelete");
            System.out.println("receiptDetailIdDelete ::: "+ receiptDetailIdDelete);
            result = receiptService.DeleteReceiptDetail(receiptDetailIdDelete, "");
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }else if("deleteReceiptCredit".equalsIgnoreCase(action)) {
            String receiptCreditIdDelete = request.getParameter("receiptCreditIdDelete");
            System.out.println("receiptCreditIdDelete ::: "+ receiptCreditIdDelete);
            result = receiptService.DeleteReceiptChq(receiptCreditIdDelete, "");
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }else if("disableVoid".equals(action)){
            result = receiptService.UpdateFinanceStatusReceipt(receiveId, 2);
            if (result == "success"){
                request.setAttribute("result", "void");
            }
            Receipt receipt = new Receipt();
            if(receiveNo != null || !"".equals(receiveNo)){
                receipt = receiptService.getReceiptfromReceiptNo(receiveNo,InputDepartment,InputReceiptType);
                if(receipt != null) {
                    if(!receipt.getId().isEmpty()){
                        List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
                        List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
                        request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
                        request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
                        if(receiptDetailList != null){
                        request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
                        }
                        if(receiptCreditList != null){
                        request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
                        }
                    }

                    request.setAttribute(RECEIPT,receipt);
                    request.setAttribute(RECEIPTDATE,receipt.getRecDate());
                    request.setAttribute(RECEIVEDATE,receipt.getReceiveDate());
                }
            }
            
        }else if("enableVoid".equals(action)){
            result = receiptService.UpdateFinanceStatusReceipt(receiveId, 1);
            if (result == "success"){
                request.setAttribute("result", "cancelvoid");
            }
            Receipt receipt = new Receipt();
            if(receiveNo != null || !"".equals(receiveNo)){
                receipt = receiptService.getReceiptfromReceiptNo(receiveNo,InputDepartment,InputReceiptType);
                if(receipt != null) {
                    if(!receipt.getId().isEmpty()){
                        List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
                        List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
                        request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
                        request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
                        if(receiptDetailList != null){
                        request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
                        }
                        if(receiptCreditList != null){
                        request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
                        }
                    }

                    request.setAttribute(RECEIPT,receipt);
                    request.setAttribute(RECEIPTDATE,receipt.getRecDate());
                    request.setAttribute(RECEIVEDATE,receipt.getReceiveDate());
                }
            }
        }else if("wildCardSearch".equalsIgnoreCase(action)){
            Receipt receipt = new Receipt();                
            receipt = receiptService.getReceiptByWildCardSearch(receiveId,receiveNo,wildCardSearch,keyCode,InputDepartment,InputReceiptType);       
            if(receipt != null) {
                if(!receipt.getId().isEmpty()){
                    List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
                    List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
                    request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
                    request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
                    List<ReceiptDetail> recInvId = new ArrayList<ReceiptDetail>();
                    if(receiptDetailList != null){
                        for(int i = 0 ;i < receiptDetailList.size();i++){
                            if(receiptDetailList.get(i).getInvoiceDetail() != null){
                                if(receiptDetailList.get(i).getInvoiceDetail().getInvoice() != null){
                                    ReceiptDetail receiptD = new ReceiptDetail();
                                    receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId());
                                    receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo());
                                    receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType());
                                    recInvId.add(receiptD);
                                }
                            }
                        }
                        request.setAttribute(INVIDLIST, recInvId);
                        request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
                    }
                    if(receiptCreditList != null){
                        request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
                    }
                }
                request.setAttribute(SEARCHRECEIPT,"notdummy");
                request.setAttribute(RECEIPT,receipt);
                request.setAttribute(RECEIPTDATE,receipt.getRecDate());
                request.setAttribute(RECEIVEDATE,receipt.getReceiveDate());
            }
                       
        }else if (!"".equalsIgnoreCase(searchId)) {
            System.out.println(" Id ::: "+ searchId);
            Receipt receipt = new Receipt();
            if(searchId != null || !"".equals(searchId)){
                receipt = receiptService.getReceiptfromReceiptId(searchId);
                if(receipt != null) {
                    if(!receipt.getId().isEmpty()){
                        List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
                        List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
                        request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
                        request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
                        List<ReceiptDetail> recInvId = new ArrayList<ReceiptDetail>();
                        if(receiptDetailList != null){
                            for(int i = 0 ;i < receiptDetailList.size();i++){
                                if(receiptDetailList.get(i).getInvoiceDetail() != null){
                                    if(receiptDetailList.get(i).getInvoiceDetail().getInvoice() != null){
                                        ReceiptDetail receiptD = new ReceiptDetail();
                                        receiptD.setInvoiceId(receiptDetailList.get(i).getInvoiceDetail() != null ? receiptDetailList.get(i).getInvoiceDetail().getInvoice().getId() : "");
                                        receiptD.setInvoiceNo(receiptDetailList.get(i).getInvoiceDetail() != null ? receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvNo() : "");
                                        receiptD.setInvoiceType(receiptDetailList.get(i).getInvoiceDetail() != null ? receiptDetailList.get(i).getInvoiceDetail().getInvoice().getInvType() : "");
                                        recInvId.add(receiptD);
                                    }
                                }
                            }
                            request.setAttribute(INVIDLIST, recInvId);
                            request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
                        }
                        if(receiptCreditList != null){
                        request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
                        }
                    }
//                    request.setAttribute(SEARCHRECEIPT,"notdummy");
                    request.setAttribute(RECEIPT,receipt);
                    request.setAttribute(RECEIPTDATE,receipt.getRecDate());
                    request.setAttribute(RECEIVEDATE,receipt.getReceiveDate());
                }
                
                
//                if(receipt != null) {
//                    if(!receipt.getId().isEmpty()){
//                        List<ReceiptDetail> receiptDetailList = receiptService.getReceiptDetailFromReceiptId(receipt.getId());
//                        List<ReceiptCredit> receiptCreditList = receiptService.getReceiptCreditFromReceiptId(receipt.getId());
//                        request.setAttribute(RECEIPTDETAILLIST,receiptDetailList);
//                        request.setAttribute(RECEIPTCREDITLIST,receiptCreditList);
//                        if(receiptDetailList != null){
//                        request.setAttribute(PRODUCTROWCOUNT, receiptDetailList.size()+1);
//                        }
//                        if(receiptCreditList != null){
//                        request.setAttribute(CREDITROWCOUNT, receiptCreditList.size()+1);
//                        }
//                    }
//                    request.setAttribute(RECEIPT,receipt);
//                    request.setAttribute(RECEIPTDATE,receipt.getRecDate());
//                    request.setAttribute(RECEIVEDATE,receipt.getReceiveDate());
//                }
            }
        }
        
        if((!"".equalsIgnoreCase(receiveNo)) && (receiveNo != null)){
            if("searchReceiveNo".equalsIgnoreCase(action)){
                if((receiveNo.indexOf("%") >= 0)){
                    request.setAttribute("wildCardSearch", receiveNo);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }else if("118".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else if("119".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else{
                if((receiveNo.indexOf("%") == 0)){
                    request.setAttribute("wildCardSearch", receiveNo);
                }else if((!"".equalsIgnoreCase(wildCardSearch)) && (wildCardSearch.indexOf("%") == 0)){
                    request.setAttribute("wildCardSearch", wildCardSearch);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }
        }
        
        setResponseAttribute(request);
        request.setAttribute("page", callPageFrom);
        return Receipt;
    }
    
    public void setResponseAttribute(HttpServletRequest request) {
        List<CustomerAgentInfo> customerAgentInfo = utilityService.getListCustomerAgentInfo();
        request.setAttribute(CUSTOMERAGENT, customerAgentInfo);
        List<MBilltype> mBilltypes = utilityService.getListMBilltype();
        request.setAttribute(MBILLTYPELIST, mBilltypes); //receiveProduct
        MDefaultData mDefaultData = utilityService.getMDefaultDataFromType("vat");
        request.setAttribute(VAT,mDefaultData.getValue()); //vat
        List<MCurrency> mCurrencys = utilityService.getListMCurrency();
        request.setAttribute(MCURRENCYLIST, mCurrencys); //receiveCurrency
        List<MCreditBank> mCreditBanks = utilityService.getListCreditBank();
        request.setAttribute(MCREDITBANKLIST, mCreditBanks); //creditBankList
//        List<MItemstatus> mItemstatuses = utilityService.getListMItemstatus();
//        request.setAttribute(MSTATUSLIST, mItemstatuses); //statusList
        List<MAccpay> mAccpays = utilityService.getListMAccpay();
        request.setAttribute(MSTATUSLIST, mAccpays); //statusList
        
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public ReceiptService getReceiptService() {
        return receiptService;
    }

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
}
