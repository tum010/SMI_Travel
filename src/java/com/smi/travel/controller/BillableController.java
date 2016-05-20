/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.AirticketAirline;
import com.smi.travel.datalayer.entity.AirticketBooking;
import com.smi.travel.datalayer.entity.AirticketPnr;
import com.smi.travel.datalayer.entity.Billable;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.LandBooking;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MExchangeRate;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.BillableService;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.MExchangeRateService;
import com.smi.travel.datalayer.service.ReceiptService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.ReceiptDetailView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
public class BillableController extends SMITravelController {

    private static final ModelAndView Billable = new ModelAndView("Billable");
    private static final ModelAndView Billable_REFRESH = new ModelAndView(new RedirectView("Billable.smi", true));

    private BookingAirticketService bookingAirticketService;
    private BillableService billableService;
    private UtilityService utilservice;
    private AgentService agentService;
    private ReceiptService receiptService;
    private MExchangeRateService mExchangeRateService;
    private static final String ACTION = "action";
    private static final String Bookiing_Size = "BookingSize";
    private static final String BillableList = "BillableList";
    private static final String BillableDesc = "BillableDesc";
    private static final String AgentList = "AgentList";
    private static final String Master = "Master";
    private static final String MAccpayList = "MAccpayList";
    private static final String MAcctermList = "MAcctermList";
    private static final String initialList = "initialList";
    private static final String CustomerAgent = "customerAgent";
    private static final String TransectionResult = "result";
    private static final String MBankList = "MBankList";
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ReceiptDetailList = "ReceiptDetailList";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private static final String DELETERESULT = "deleteresult";
    private static final String invno = "invno";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        UtilityFunction util = new UtilityFunction();
        String refNo = request.getParameter("referenceNo");
        String action = request.getParameter("action");

        String passengerId = request.getParameter("passengerId");
        String payable = request.getParameter("payable");
        String billto = request.getParameter("billto");
//        String billdate = request.getParameter("billdate");
        String billname = request.getParameter("billname");
        String address = request.getParameter("address");
        String acctermS = request.getParameter("accterm");
        String accpayS = request.getParameter("accpay");
        String Description = request.getParameter("description");
        MAccpay accpay = this.getAccpayById(accpayS);
        MAccterm accterm = this.getAcctermById(acctermS);
//        Date billDate = convertStringToDate(billdate);
        String transferD = request.getParameter("transferD");
        Date transferDate = util.convertStringToDate(transferD);
        String accIdS = request.getParameter("accId");
        MBank accId = this.getBankAccountById(accIdS);
        String deleteresult = request.getParameter("deleteresult");
        request.setAttribute("thisdate", util.convertDateToString(new Date()));
        Integer checkedPayable = null;
        if ("on".equalsIgnoreCase(payable)) {
            checkedPayable = new Integer(0);
        } else {
            checkedPayable = new Integer(1);
        }
        
        request.setAttribute(DELETERESULT, deleteresult); 
        SystemUser user = (SystemUser) session.getAttribute("USER");
        Master master = utilservice.getbookingFromRefno(refNo);
        Billable billForm = new Billable();
        billForm.setMaster(master);
        billForm.setPassenger(this.getPassengerById(master, passengerId));
        billForm.setBillTo(billto);
        billForm.setBillName(billname);
        billForm.setBillAddress(address);
        billForm.setMAccpay(accpay);
        billForm.setMAccterm(accterm);
//        billForm.setBillDate(billDate);
        billForm.setIsPayYourself(checkedPayable);
        billForm.setRemark(Description);
        billForm.setTransferDate(transferDate);
        billForm.setBankAccount(accId);
        System.out.println("Billable Controller payable[" + payable + "]");
        
        //Print Button
        String printTicketOrder = billableService.printTicketOrder(refNo);
        request.setAttribute("printTicketOrder", printTicketOrder);
        
        if ("add".equalsIgnoreCase(action)) {
            System.out.println("action add");
            request.setAttribute(ACTION, "insert");
            request.setAttribute(TransectionResult, "save successful");
        } else if ("insert".equalsIgnoreCase(action)) {
            System.out.println("action insert");

            String billDescCount = request.getParameter("billDescCount").trim();
            System.out.println("billDescCount : " + billDescCount);
            if ("".equalsIgnoreCase(billDescCount)) {
                billDescCount = "0";
            }
            Integer billDescCountInt = Integer.valueOf(billDescCount);
            System.out.println("billDescCountInt insert: " + billDescCountInt);
            for (int i = 1; i < billDescCountInt; i++) {
                String billDescId = request.getParameter("billDescId-" + i);
                if (StringUtils.isNotEmpty(billDescId)) {
                    String remark = request.getParameter("remark-" + i);
                    String billdate = request.getParameter("billDate-"+i);
                    String refId = request.getParameter("billRefId-"+i);
                    String exratetemp = request.getParameter("exrate-"+ i);
                    BigDecimal exrate = null;
                    if(!"".equalsIgnoreCase(exratetemp)){
                        System.out.println("+++ insert exratetemp ++++ " + exratetemp);
                        exrate = new BigDecimal(exratetemp);
                    }
                    Date billDate = util.convertStringToDate(billdate);
                    System.out.println("remark insert: "+ i + remark);
                    System.out.println("billDate insert: " + i + billDate);
                    System.out.println("exrate insert: " + i + exrate);
                    updateRemarkByBillDescId(billForm, billDescId, remark, billDate,exrate);

                } else {
                    getBillDescListForm(request, billForm, i);
                }

            }
            if(billForm.getId() != null){
                billForm.setMaster(master);
                saveHistoryBooking(refNo,user,billForm,"UPDATE");
            }else{
                billForm.setMaster(master);
                saveHistoryBooking(refNo,user,billForm,"CREATE");
            }
            result = billableService.saveBillable(billForm);
            request.setAttribute(BillableList, billForm);

            setResponseAttribute(request, refNo);
            return new ModelAndView("redirect:Billable.smi?referenceNo=" + refNo + "&result=" + result + "&action=edit");
        } else if ("edit".equalsIgnoreCase(action)) {
            if ((request.getParameter("result") != null) && (!"".equalsIgnoreCase(request.getParameter("result")))) {
                if (request.getParameter("result").equalsIgnoreCase("1")) {
                    request.setAttribute(TransectionResult, "save successful");
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }

            }

            System.out.println("action edit");

            Billable billable = billableService.getBillableBooking(refNo);
            if (billable != null) {
                // billable
                request.setAttribute(ACTION, "update");
            } else {
                // Billable is null.
                Passenger leader = null;
              
                Iterator<Passenger> iterator = master.getPassengers().iterator();
                while (iterator.hasNext()) {
                    Passenger p = iterator.next();
                    if (p.getIsLeader() == 1) {
                        leader = p;
                        break;
                    }
                }
                billable = new Billable();
                billable.setMaster(master);
                billable.setPassenger(leader);
                request.setAttribute(ACTION, "insert");
            }
            
            List<BillableDesc> billableDesc = billable.getBillableDescs();          
            
            //billableDesc = SortBillDescList(billableDesc);
            List<BillableDesc> billableDescNopay = billableService.getListBillableNopay(refNo);
            if (billableDescNopay != null) {
                System.out.println("billableDesc size :" + billable.getBillableDescs().size());

                System.out.println("billableDescNopay size :" + billableDescNopay.size());
                for (BillableDesc newBill : billableDescNopay) {
                    //  billable.getBillableDescs().add(newBill);
                    newBill.setCurCost("null".equalsIgnoreCase(String.valueOf(newBill.getCurCost())) ? "" : newBill.getCurCost());
                    newBill.setCurrency("null".equalsIgnoreCase(String.valueOf(newBill.getCurrency())) ? "" : newBill.getCurrency());
                    billableDesc.add(newBill);
                }
                System.out.println("billableDesc size :" + billable.getBillableDescs().size());

            }

            if (billable != null) {
                request.setAttribute(BillableDesc, billableDesc);
                // List Receipt Detail
                List<ReceiptDetailView> receiptView = receiptService.getReceiptDetailViewFromBillableId(billable.getId());
                request.setAttribute(ReceiptDetailList, receiptView);
                // request.setAttribute(BillableDesc,billable.getBillableDescs());
            } else {
                request.setAttribute(BillableDesc, billableDesc);
            }
            
            if(billableDesc != null){
                setExchangeRate(billableDesc);
            }
      
            setDefaultBill(master,billable);
            request.setAttribute(BillableList, billable);
            saveHistoryBooking(refNo,user,billable,"VIEW");
            setResponseAttribute(request, refNo);

        } else if ("update".equalsIgnoreCase(action)) {

            Billable billable = billableService.getBillableBooking(refNo);
            System.out.println("billable.getId() :" + billable.getId());
            billForm.setId(billable.getId());
            String billDescCount = request.getParameter("billDescCount").trim();
            if (billDescCount.equalsIgnoreCase("")) {
                billDescCount = "0";
            }
            Integer billDescCountInt = Integer.valueOf(billDescCount);
            System.out.println("billDescCountInt update: " + billDescCountInt);
            for (int i = 1; i < billDescCountInt; i++) {
                String billDescId = request.getParameter("billDescId-" + i);
                if (StringUtils.isNotEmpty(billDescId)) {
                    String remark = request.getParameter("remark-" + i);
                    String billdate = request.getParameter("billDate-"+ i);
                    String exratetemp = request.getParameter("exrate-"+ i);
                    Date billDate = util.convertStringToDate(billdate);
                    BigDecimal exrate = null;
                    if(!"".equalsIgnoreCase(exratetemp)){
                        System.out.println("+++ exratetemp ++++ " + exratetemp);
                        exrate = new BigDecimal(exratetemp);
                    }
                    System.out.println("remark update: " + i + remark);
                    System.out.println("billDate update: " + i + billDate);
                    System.out.println("exrate update: " + i + exrate);
                    updateRemarkByBillDescId(billable, billDescId, remark, billDate,exrate);

                } else {
                    billForm.setId(billable.getId());
                    getBillDescListForm(request, billForm, i);
                }

            }
            System.out.println("billableDesc size : " + billable.getBillableDescs().size());
            Iterator<BillableDesc> iterator = billable.getBillableDescs().iterator();
            while (iterator.hasNext()) {
                BillableDesc bILLDESC = iterator.next();
                billForm.getBillableDescs().add(bILLDESC);
            }
            result = billableService.saveBillable(billForm);
            saveHistoryBooking(refNo,user,billForm,"UPDATE");
            System.out.println("action update");
            return new ModelAndView("redirect:Billable.smi?referenceNo=" + refNo + "&result=" + result + "&action=edit");
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("action delete");
        } else if("deleteBillable".equalsIgnoreCase(action)) {
            System.out.println("===================================== deleteBillable ");
            String resultdelete = "";
            String billDescIdDelete = request.getParameter("billDescIdDelete");
            String billDescRowDelete = request.getParameter("billDescRowDelete");
            System.out.println(" billdescIdDelete " + billDescIdDelete);
            System.out.println(" billDescRowDelete " + billDescRowDelete);
            resultdelete = billableService.DeleteBillableDesc(billDescIdDelete);
            
            System.out.println("resultdelete " + resultdelete);
            if (resultdelete == "fail"){ 
                request.setAttribute(DELETERESULT, "unsuccessful");
                return new ModelAndView("redirect:Billable.smi?referenceNo=" + refNo + "&deleteresult=unsuccessful&action=edit");
            } else if (resultdelete == "success") { 
                request.setAttribute(DELETERESULT, "successful");
                return new ModelAndView("redirect:Billable.smi?referenceNo=" + refNo + "&deleteresult=successful&action=edit");
            }else{
                request.setAttribute(invno, String.valueOf(resultdelete));
                request.setAttribute(DELETERESULT, "isuseininv");
                return new ModelAndView("redirect:Billable.smi?referenceNo=" + refNo + "&deleteresult=isuseininv&invno="+resultdelete+"&action=edit");
            }
        } else {
            System.out.println("no action");
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                System.out.println("******* No refNo !!!!");
            } else {
                System.out.println("******* have refNo ************");
                setResponseAttribute(request, refNo);
            }
        }

        return Billable;

    }

    public void setDefaultBill(Master master, Billable billable) {
        if ((master.getBillables().isEmpty())) {
            if (master.getAgent().getCode().equalsIgnoreCase("WLK")) {
                billable.setBillTo(master.getCustomer().getCode());
                billable.setBillName(master.getCustomer().getFirstName() + " " + master.getCustomer().getLastName());
                MAccpay cash = new MAccpay();
                cash.setId("1");
                billable.setMAccpay(cash);
            } else {
                billable.setBillTo(master.getAgent().getCode());
                billable.setBillName(master.getAgent().getName());
                billable.setMAccpay(master.getAgent().getMAccpay());
                billable.setMAccterm(master.getAgent().getMAccterm());               
            }
        }
    }

    public void setResponseAttribute(HttpServletRequest request, String refNo) {
        System.out.println(" +++++++++++++++++++++++ setResponseAttribute +++++++++++++++++++++++++++++++++++++ ");
        int[] booksize = utilservice.getCountItemFromBooking(refNo);
        List<CustomerAgentInfo> customerAgentInfo = billableService.getListCustomerAgentInfo();
        request.setAttribute(CustomerAgent, customerAgentInfo);
        request.setAttribute(Bookiing_Size, booksize);
        List<MAccterm> mAcctermList = utilservice.getListMAccterm();
        request.setAttribute(MAcctermList, mAcctermList);
               
        List<MAccpay> mAccpayList = utilservice.getListMAccpay();
        request.setAttribute(MAccpayList, mAccpayList);
              
        List<MInitialname> mInitial = utilservice.getListMInitialname();
        request.setAttribute(initialList, mInitial);

        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        
        List<MBank> mBankList = utilservice.getListBank();
        request.setAttribute(MBankList, mBankList);
//        if(master != null){
        if(("2").equals(String.valueOf(master.getMBookingstatus().getId())) || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
//        }
    }

    private Integer convertStringToInteger(String input) {
        if (StringUtils.isNotEmpty(input)) {
            return Integer.valueOf(input);
        } else {
            return null;
        }
    }

    private Date convertStringToDate(String input) {
        if (input == null) {
            return null;
        }
        if("".equalsIgnoreCase(input)){
            return null;
        }
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(input);
        } catch (ParseException e) {
           // e.printStackTrace();
            return null;

        }
        return date;
    }

    private Passenger getPassengerById(Master master, String passengerId) {

        if (StringUtils.isEmpty(passengerId)) {
            return null;
        }

        Iterator<Passenger> iterator = master.getPassengers().iterator();
        while (iterator.hasNext()) {
            Passenger p = iterator.next();
            if (passengerId.equalsIgnoreCase(p.getId())) {
                return p;
            }
        }
        return null;
    }

    private MAccterm getAcctermById(String acctermS) {
        Integer accterm = convertStringToInteger(acctermS);
        if (accterm == null) {
            return null;
        }

        Iterator<MAccterm> iterator = utilservice.getListMAccterm().iterator();
        while (iterator.hasNext()) {
            MAccterm a = iterator.next();
            if (acctermS.equalsIgnoreCase(a.getId())) {
                return a;
            }
        }
        return null;
    }

    private MAccpay getAccpayById(String accpayS) {
        Integer accpay = convertStringToInteger(accpayS);
        if (accpay == null) {
            return null;
        }

        Iterator<MAccpay> iterator = utilservice.getListMAccpay().iterator();
        while (iterator.hasNext()) {
            MAccpay a = iterator.next();
            if (accpayS.equalsIgnoreCase(a.getId())) {
                return a;
            }
        }
        return null;
    }

    private void getBillDescListForm(HttpServletRequest request, Billable billable, int index) {
        UtilityFunction util = new UtilityFunction();
        String billtypeS = request.getParameter("billtype-" + index).trim();
        MBilltype billtype = utilservice.getMBilltypeFromName(billtypeS);
        AirticketAirline airB = null;
        String costS = request.getParameter("cost-" + index).trim();
//        int cost = Integer.parseInt(costS);
        BigDecimal cost = new BigDecimal(costS);
        String priceS = request.getParameter("price-" + index).trim();
        String remark = request.getParameter("remark-" + index);
        String detail = request.getParameter("detail-"+index);
        String billdate = request.getParameter("billDate-"+index);
        String currency = request.getParameter("currency-"+index);
        String currencycost = request.getParameter("currencycost-"+index);
        String exratetemp = request.getParameter("exrate-"+index);
        String refId = request.getParameter("billRefId-"+index);
        
        Date billDate = util.convertStringToDate(billdate);
        System.out.println("remark[" + index + "] : " + remark);
//        int price = Integer.parseInt(priceS);
        BigDecimal price = new BigDecimal(priceS);
        BillableDesc bd = new BillableDesc();
        bd.setBillable(billable);
        bd.setMBilltype(billtype);
        bd.setCost(cost);
        bd.setPrice(price);
        bd.setRemark(remark);
        bd.setDetail(detail);
        bd.setBillDate(billDate);
        bd.setRefItemId(refId);
        System.out.println(" currency " + currency);
        bd.setCurrency("null".equalsIgnoreCase(String.valueOf(currency)) ? "" : currency);
        bd.setCurCost("null".equalsIgnoreCase(String.valueOf(currencycost)) ? "" : currencycost);
        
        BigDecimal exrate = new BigDecimal(BigInteger.ZERO);
        if(!"".equalsIgnoreCase(exratetemp) && !"0.0000".equalsIgnoreCase(String.valueOf(exratetemp))){
            System.out.println("+++ getBillDescListForm exratetemp ++++ " + exratetemp);
            exrate = new BigDecimal(exratetemp);
            bd.setExRate(exrate);
        }
        
        
        billable.getBillableDescs().add(bd);
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public BillableService getBillableService() {
        return billableService;
    }

    public void setBillableService(BillableService billableService) {
        this.billableService = billableService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public AgentService getAgentService() {
        return agentService;
    }

    public void setAgentService(AgentService agentService) {
        this.agentService = agentService;
    }

    private void updateRemarkByBillDescId(Billable billable, String billDescId, String remark,Date billDate,BigDecimal exrate) {
        Iterator<BillableDesc> iterator = billable.getBillableDescs().iterator();
        while (iterator.hasNext()) {
            BillableDesc bd = iterator.next();
            if (billDescId.equalsIgnoreCase(bd.getId())) {
                System.out.println("updateRemarkByBillDescId remark : " + remark);
                System.out.println("updateRemarkByBillDescId billDate : " + billDate);
                System.out.println("updateRemarkByBillDescId exrate : " + exrate);
                bd.setRemark(remark);
                if(!"".equalsIgnoreCase(String.valueOf(exrate)) && exrate != null){
                    Double d = exrate.doubleValue();
                    BigDecimal bds = new BigDecimal(d).setScale(4, RoundingMode.HALF_EVEN);
                    d = bds.doubleValue();
                    bd.setExRate(exrate);
                    System.out.println(" bd.getExRate() " + bd.getExRate());
                
                }else{
                    bd.setExRate(null);
                }
                bd.setBillDate(billDate);
                return;
            }
        }
    }

    public List<BillableDesc> SortBillDescList(List<BillableDesc> data) {
        List<BillableDesc> sortBill = new ArrayList<BillableDesc>();
        List Dataindex = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            System.out.println("data id : " + data.get(i).getId());
            if (data.get(i).getId() == null) {
                return data;
            }
        }
        for (int i = 0; i < data.size(); i++) {

            Dataindex.add(Integer.parseInt(data.get(i).getId()));
        }

        Collections.sort(Dataindex);
        for (int i = 0; i < Dataindex.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (Dataindex.get(i).equals(Integer.parseInt(data.get(j).getId()))) {
                    sortBill.add(data.get(j));
                }
            }
        }

        return sortBill;
    }
    
    private MBank getBankAccountById(String accIdS) {
        Integer accid = convertStringToInteger(accIdS);
        if (accid == null) {
            return null;
        }

        Iterator<MBank> iterator = utilservice.getListBank().iterator();
        while (iterator.hasNext()) {
            MBank a = iterator.next();
            if (accIdS.equalsIgnoreCase(a.getId())) {
                return a;
            }
        }
        return null;
    }

    public void saveHistoryBooking(String refNo,SystemUser user,Billable bill,String action) {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("dd-MM-yyyy");        
        HistoryBooking historyBooking = new HistoryBooking();
        historyBooking.setHistoryDate(new Date());
        historyBooking.setAction(action+" BILLABLE BOOKING");
        String detail = "";
        if(!"VIEW".equalsIgnoreCase(action)){
            if(bill != null){
            detail = "BILL : " + bill.getBillTo() + " : " + bill.getBillName()+ "\r\n"
                    + "ADDRESS : " + bill.getBillAddress() + "\r\n";
                    if(bill.getMAccterm() != null){
                        detail += "TERM PAY : " + bill.getMAccterm().getName() +"\r\n";
                    }else{
                        detail += "TERM PAY  : "+"\r\n";
                    }
                    if(bill.getMAccpay() != null){
                        detail += "PAY BY : " + bill.getMAccpay().getName() + "\r\n";
                    }else{
                        detail += "PAY BY :  "+"\r\n";
                    }
                    detail +=  "DESCRIPTION : " + bill.getRemark();
            }
        }
        historyBooking.setDetail(detail);
        if(bill != null){
            historyBooking.setMaster(bill.getMaster());
        }
        historyBooking.setStaff(user);
        int resultsave = utilservice.insertHistoryBooking(historyBooking);
    }
    
    
    public ReceiptService getReceiptService() {
        return receiptService;
    }

    public void setReceiptService(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public MExchangeRateService getmExchangeRateService() {
        return mExchangeRateService;
    }

    public void setmExchangeRateService(MExchangeRateService mExchangeRateService) {
        this.mExchangeRateService = mExchangeRateService;
    }

    private void setExchangeRate(List<BillableDesc> billableDesc) {
        System.out.println("===== setExchangeRate =====");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String todayDate = sdf.format(date);
        System.out.println("===== todayDate ===== : "+todayDate);
        System.out.println("===== billableDesc.size() ===== : "+billableDesc.size());
        for(int i=0; i<billableDesc.size(); i++){
            BillableDesc billableDescTemp = billableDesc.get(i);
            System.out.println("===== Is Bill ===== : "+billableDescTemp.getIsBill());
            System.out.println("===== Currency ===== : "+billableDescTemp.getCurrency());
            if(billableDescTemp.getIsBill() == 0 && !"".equalsIgnoreCase(billableDescTemp.getCurrency())){
                List<MExchangeRate> mExchangeRateList = mExchangeRateService.searchExchangeRate(todayDate, todayDate, billableDescTemp.getCurrency());
                billableDescTemp.setExRate(mExchangeRateList != null ? mExchangeRateList.get(0).getExrate() : BigDecimal.ZERO);
            
            }       
        }

    }
}
