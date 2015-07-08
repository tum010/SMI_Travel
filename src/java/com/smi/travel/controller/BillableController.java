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
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBank;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.service.AgentService;
import com.smi.travel.datalayer.service.BillableService;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        int result = 0;
        UtilityFunction Util = new UtilityFunction();
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
        Date transferDate = convertStringToDate(transferD);
        String accIdS = request.getParameter("accId");
        MBank accId = this.getBankAccountById(accIdS);
        
        request.setAttribute("thisdate", Util.convertDateToString(new Date()));
        Integer checkedPayable = null;
        if ("on".equalsIgnoreCase(payable)) {
            checkedPayable = new Integer(0);
        } else {
            checkedPayable = new Integer(1);
        }
        
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
                    Date billDate = convertStringToDate(billdate);
                    System.out.println("remark insert: " + remark);
                    System.out.println("billDate insert: " + billDate);
                    updateRemarkByBillDescId(billForm, billDescId, remark, billDate);

                } else {
                    getBillDescListForm(request, billForm, i);
                }

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
                    billableDesc.add(newBill);
                }
                System.out.println("billableDesc size :" + billable.getBillableDescs().size());

            }

            if (billable != null) {
                request.setAttribute(BillableDesc, billableDesc);
                // request.setAttribute(BillableDesc,billable.getBillableDescs());
            } else {
                request.setAttribute(BillableDesc, billableDesc);
            }

            setDefaultBill(master,billable);
            request.setAttribute(BillableList, billable);

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
                    Date billDate = convertStringToDate(billdate);
                    
                    System.out.println("remark insert: " + remark);
                    System.out.println("billDate insert: " + billdate);
                    updateRemarkByBillDescId(billable, billDescId, remark, billDate);

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

            System.out.println("action update");
            return new ModelAndView("redirect:Billable.smi?referenceNo=" + refNo + "&result=" + result + "&action=edit");
        } else if ("delete".equalsIgnoreCase(action)) {
            System.out.println("action delete");
        } else {
            System.out.println("no action");
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                System.out.println("******* No refNo !!!!");
            } else {
                System.out.println("******* have refNo ************");

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
        
        if(("2").equals(String.valueOf(master.getMBookingstatus().getId())) || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
            request.setAttribute(LockUnlockBooking,1);
        }else{
            request.setAttribute(LockUnlockBooking,0);
        }
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
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
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

        String billtypeS = request.getParameter("billtype-" + index).trim();
        MBilltype billtype = utilservice.getMBilltypeFromName(billtypeS);
        AirticketAirline airB = null;
        String costS = request.getParameter("cost-" + index).trim();
        int cost = Integer.parseInt(costS);
        String priceS = request.getParameter("price-" + index).trim();
        String remark = request.getParameter("remark-" + index);
        String detail = request.getParameter("detail-"+index);
        String billdate = request.getParameter("billDate-"+index);
        Date billDate =    convertStringToDate(billdate);
        System.out.println("remark[" + index + "] : " + remark);
        int price = Integer.parseInt(priceS);
        BillableDesc bd = new BillableDesc();
        bd.setBillable(billable);
        bd.setMBilltype(billtype);
        bd.setCost(cost);
        bd.setPrice(price);
        bd.setRemark(remark);
        bd.setDetail(detail);
        bd.setBillDate(billDate);
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

    private void updateRemarkByBillDescId(Billable billable, String billDescId, String remark,Date billDate) {
        Iterator<BillableDesc> iterator = billable.getBillableDescs().iterator();
        while (iterator.hasNext()) {
            BillableDesc bd = iterator.next();
            if (billDescId.equalsIgnoreCase(bd.getId())) {
                System.out.println("updateRemarkByBillDescId remark : " + remark);
                System.out.println("updateRemarkByBillDescId billDate : " + billDate);
                bd.setRemark(remark);
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
}
