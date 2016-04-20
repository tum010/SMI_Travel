/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.Customer;
import com.smi.travel.datalayer.entity.HistoryBooking;
import com.smi.travel.datalayer.entity.MBookingstatus;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MInitialname;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PackageTour;
import com.smi.travel.datalayer.entity.Passenger;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.BookingAirticketService;
import com.smi.travel.datalayer.service.BookingDetailService;
import com.smi.travel.datalayer.service.CheckDuplicateUserService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
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

/**
 *
 * @author sumeta
 */
public class BookDetailController extends SMITravelController {

    private static final ModelAndView BookDetail = new ModelAndView("BookDetail");
    private static final ModelAndView BookDetail_REFRESH = new ModelAndView(new RedirectView("BookDetail.smi", true));
    private BookingAirticketService bookingAirticketService;
    private BookingDetailService bookingDetailService;
    private static final String Bookiing_Size = "BookingSize";
    private static final String Master = "Master";
    private static final String FamilyLeaderDetail = "FamilyLeaderDetail";
    private static final String Agent = "Agent";
    private static final String SelectedAgent = "SelectedAgent";
    private static final String ACTION = "action";
    private static final String Detail = "BookDetail";
//    private static final String BookSummaryList = "BookSummaryList";
    private static final String TransactionResult = "result";
    private static final String CurrencyList = "CurrencyList";
    private static final String BookingstatuseList = "BookingstatuseList";
    private static final String initialList = "initialList";
    private static final String CustomerList = "customerList";
    private static final String PackageList = "packagelist";
    private static final String PACKAGEID = "packageID";
    private static final String[] resultText = {"Save unsuccessful", "Save successful"};
    private static final String LockUnlockBooking = "LockUnlockBooking";
    private static final String ISBILLSTATUS = "IsBillStatus";
    private static final String CHECKDUPLICATEUSER = "checkDuplicateUser";
    private UtilityService utilservice;
    private MInitialname mInitialname;
    private CheckDuplicateUserService checkDuplicateUserService;
    UtilityFunction util;
    Date opDate;
    String opUser;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        int result = 0;
        String action = request.getParameter("action");
        String refNo = request.getParameter("referenceNo");
        String leaderId = request.getParameter("leaderId");
        String leaderCode = request.getParameter("FamilyLeaderCodeVal");
        String initial = request.getParameter("initialname");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String tel = request.getParameter("tel");
        String packagecode = request.getParameter("packagecode");
        System.out.println("BookDetailController action=[" + action + "] refno[" + refNo + "]");
        SystemUser user = (SystemUser) session.getAttribute("USER");
        List<Agent> agent = bookingDetailService.getListAgentForBookingDetail();
        request.setAttribute(Agent, agent);
        
        String operationTableId = request.getParameter("operationTableIdBooking");
        String operationUser = request.getParameter("operationUserBooking");
        String checkDuplicateUser = "";
        String clearDuplicateUser = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        CheckDuplicateUser checkDuplicateUserSession = (CheckDuplicateUser) session.getAttribute("checkDuplicateUser");
        if(checkDuplicateUserSession != null){
            operationTableId = checkDuplicateUserSession.getTableId();
            operationUser = checkDuplicateUserSession.getOperationUser();
            
        }                  
        
        //Duplicate User
        if("operationUpdate".equalsIgnoreCase(action)){
            System.out.println("operationTableId : "+operationTableId);
            checkDuplicateUser = checkDuplicateUser(request,response,session,operationTableId,3);
            action = "edit";
        }
        //**
        
        
//        for(int i=0;i<agent.size();i++){
//            Agent agentRe = new Agent();
//            agentRe = agent.get(i);
//            String agentAdd = (agentRe.getAddress()).replaceAll("(\r\n|\n)", " ");
//            agentRe.setAddress(agentAdd);
//        }

        if ("new".equalsIgnoreCase(action)) {
            request.setAttribute(ACTION, "init");
            request.setAttribute(SelectedAgent, getAgentWLK());
            checkDuplicateUserSession.setTableId("");
            session.setAttribute("checkDuplicateUser", checkDuplicateUserSession);
            //request.setAttribute(SelectedAgent, bookingDetailService.getAgentByName("WLK"));
            request.setAttribute(Agent, agent);
        } else if ("addfamily".equalsIgnoreCase(action)) {
            System.out.println("addfamily : sction");
            /*
            initial = request.getParameter("input-initial-name");
            firstname = request.getParameter("input-first-name");
            lastname = request.getParameter("input-last-name");
            address = request.getParameter("input-address");
            tel = request.getParameter("address");
            Customer customer = new Customer();
            customer.setMInitialname(utilservice.getMInitialnameFromName(initial));
            customer.setFirstName(firstname);
            customer.setLastName(lastname);
            customer.setAddress(address);
            customer.setTel(tel);
            String resultS = utilservice.getCustomerdao().insertCustomer(customer);
            if (!"".equals(resultS)) {

            }*/
        } else if ("edit".equalsIgnoreCase(action)) {
            String resultS = request.getParameter("result");

            Master master = bookingDetailService.getBookingDetailFromRefno(refNo);
            
            
            //Duplicate User
//            if(master.getId() != null){
//                if(!"".equalsIgnoreCase(master.getId())){
//                    CheckDuplicateUser cdu = new CheckDuplicateUser();
//                    cdu.setOperationTable("Master");
//                    cdu.setTableId(master.getId());
//                    cdu.setOperationUser(user.getUsername());
//                    checkDuplicateUserService.updateOperationNull(cdu);
//                }
//            }
            checkDuplicateUser = checkDuplicateUser(request,response,session,master.getId(),1);
            
            Agent selectedAgent = null;
//            if(master != null){
                selectedAgent = master.getAgent();
                request.setAttribute(Detail, master);
//            }
            request.setAttribute(ACTION, "update");
            request.setAttribute(SelectedAgent, selectedAgent);
            request.setAttribute(Agent, agent);

            if (StringUtils.isNotEmpty(resultS)) {
                request.setAttribute(TransactionResult, getResultText(Integer.parseInt(resultS)));
            }
            HistoryBooking historyBooking = new HistoryBooking();
            historyBooking.setHistoryDate(new Date());
            historyBooking.setAction("VIEW BOOKING");
            String detail = "";
            historyBooking.setDetail(detail);
            historyBooking.setMaster(master);
            historyBooking.setStaff(user);
            int resultsave = bookingDetailService.insertHistoryBooking(historyBooking);
            System.out.println(" resultsave " + resultsave);
        } else if ("update".equalsIgnoreCase(action)) {
            
            
            
            //Get data from form
            String agentId = request.getParameter("agent_id");
            String agentName = request.getParameter("agent_user");
            String agentRef = request.getParameter("agent_ref");
            String currency = request.getParameter("currency");
            String status = request.getParameter("status");
            System.out.print("action update refNo[" + refNo + "] agentId = " + agentId + " , name=" + agentName + "[currency=" + currency + "] ");
            System.out.println();

            Master dbMaster = bookingDetailService.getBookingDetailFromRefno(refNo);
            
            
             //Duplicate User
            if(!"".equalsIgnoreCase(dbMaster.getId()) && dbMaster.getId() != null){
                checkDuplicateUser = checkDuplicateUser(request,response,session,dbMaster.getId(),2);
                if("fail".equalsIgnoreCase(checkDuplicateUser)){
                    return new ModelAndView("redirect:BookDetail.smi?referenceNo=" + dbMaster.getReferenceNo() + "&action=edit");
                }
            }
            
            dbMaster.setAgentRef(agentRef);
            Agent selectedAgent = null;
            selectedAgent = bookingDetailService.getAgentdao().getAgentFromID(agentId);
            dbMaster.setAgent(selectedAgent);

            Passenger passenger = (Passenger) dbMaster.getPassengers().iterator().next();
            Customer selectedLeader;
            if (StringUtils.isNotEmpty(leaderCode)) {
                selectedLeader = bookingDetailService.getCustomerdao().getCustomerFromID(leaderId);
            } else {
                // found issue.
                selectedLeader = new Customer();

                //Create new passenger.
            }
            MInitialname initialName = utilservice.getMInitialnameFromName(initial);
            selectedLeader.setMInitialname(initialName);
            selectedLeader.setFirstName(firstname);
            selectedLeader.setLastName(lastname);
            selectedLeader.setAddress(address);
            selectedLeader.setTel(tel);
            dbMaster.setCustomer(selectedLeader);

            setMasterNumberPassenger(request, dbMaster);
            List<SystemUser> users = bookingDetailService.getSystemUserDao().searchSystemUser(user, 1);
            dbMaster.setStaff(users.get(0));
            dbMaster.setRevisedBy(users.get(0).getUsername());
            dbMaster.setRevisedDate(new Date());
            dbMaster.setCurrency(currency);
            MBookingstatus bookingstatus = utilservice.getMBookingstatusFromName(status);
            dbMaster.setMBookingstatus(bookingstatus);
            //set package id
            if((packagecode != null)&&(!"".equalsIgnoreCase(packagecode))){
                PackageTour pack = new PackageTour();
                pack.setId(packagecode);
                dbMaster.setPackageTour(pack);                
            }
            HistoryBooking historyBooking = new HistoryBooking();
            historyBooking.setHistoryDate(new Date());
            historyBooking.setAction("UPDATE BOOKING");
            String detail = "Refno : " + dbMaster.getReferenceNo() + "\r\n"
                            + "FL : " + utilty.getCustomerName(dbMaster.getCustomer()) + "\r\n"
                            + "Agent : " ;
                            if(dbMaster.getAgent()!=null){
                                detail += dbMaster.getAgent().getCode() + " : " + dbMaster.getAgent().getName();
                            }
            historyBooking.setDetail(detail);
            historyBooking.setMaster(dbMaster);
            historyBooking.setStaff(users.get(0));
            
            try {
                result = bookingDetailService.saveBookingDetail(dbMaster, passenger, null,historyBooking);
                if (result == 1) {
                    System.out.println("BookingDetailController update refNo " + dbMaster.getReferenceNo());
                    return new ModelAndView("redirect:BookDetail.smi?referenceNo=" + refNo + "&action=edit" + "&result=" + result);
                } else {
                    request.setAttribute(TransactionResult, "BookingDetail cannot be saved!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute(TransactionResult, "BookingDetail cannot be saved! Please see log.");
            }
            
            if(!"1".equalsIgnoreCase(String.valueOf(result))){
                //Duplicate User
                checkDuplicateUser = checkDuplicateUser(request,response,session,dbMaster.getId(),1);
            }
            
            request.setAttribute(Detail, dbMaster);
            request.setAttribute(ACTION, "update");
            request.setAttribute(SelectedAgent, selectedAgent);
            request.setAttribute(Agent, agent);
            //request.setAttribute(PACKAGEID, dbMaster.getPackageTour().getId());
        } else {
            if ("".equalsIgnoreCase(refNo) || (refNo == null)) {
                String agentId = request.getParameter("agent_id");
                String agentRef = request.getParameter("agent_ref");
                String currency = request.getParameter("currency");
                String status = request.getParameter("status");

                System.out.println(" refNo null or initMaster action[" + action + "] [currency=" + currency + "]");

                Master newMaster = new Master();
                setMasterNumberPassenger(request, newMaster);
                Agent selectedAgent = bookingDetailService.getAgentdao().getAgentFromID(agentId);
                newMaster.setAgent(selectedAgent);
                newMaster.setAgentRef(agentRef);
                newMaster.setCurrency(currency);
                Customer selectedLeader;
                if (StringUtils.isNotEmpty(leaderCode)) {
                    selectedLeader = bookingDetailService.getCustomerdao().getCustomerFromID(leaderId);
                } else {
                    // found issue.
                    selectedLeader = new Customer();
                }
                MInitialname initialName = utilservice.getMInitialnameFromName(initial);
                selectedLeader.setMInitialname(initialName);
                selectedLeader.setFirstName(firstname);
                selectedLeader.setLastName(lastname);
                selectedLeader.setAddress(address);
                selectedLeader.setTel(tel);
                newMaster.setCustomer(selectedLeader);
                Passenger passenger = new Passenger();
                passenger.setIsLeader(1);
                passenger.setCustomer(selectedLeader);
                passenger.setMaster(newMaster);
                passenger.setOrderNo(1);
                newMaster.getPassengers().add(passenger);
                MBookingstatus bookingstatus = utilservice.getMBookingstatusFromName(status);
                newMaster.setMBookingstatus(bookingstatus);
                //set package id
                if((packagecode != null)&&(!"".equalsIgnoreCase(packagecode))){
                    PackageTour pack = new PackageTour();
                    pack.setId(packagecode);
                    newMaster.setPackageTour(pack);                
                }
                HistoryBooking historyBooking = new HistoryBooking();
                historyBooking.setHistoryDate(new Date());
                historyBooking.setAction("CREATE NEW BOOKING");
                String detail = "Refno : " + newMaster.getReferenceNo() + "\r\n"
                                + "FL : " + utilty.getCustomerName(newMaster.getCustomer()) + "\r\n" 
                                + "Agent : " ;
                                if(newMaster.getAgent()!=null){
                                    detail += newMaster.getAgent().getCode() + " : " + newMaster.getAgent().getName();
                                }
                historyBooking.setDetail(detail);
                historyBooking.setMaster(newMaster);
                historyBooking.setStaff(user);
                newMaster.getHistoryBookings().add(historyBooking);
                result = bookingDetailService.saveBookingDetail(newMaster, passenger, user , historyBooking);
                if (result > 1) {
                    System.out.println("BookingDetailController refNo " + newMaster.getReferenceNo());
                    bookingDetailService.getPassengerdao().saveFamilyleader(passenger, selectedLeader.getCode());
                    // Set Success status.
                    result = 1;
                    return new ModelAndView("redirect:BookDetail.smi?referenceNo=" + newMaster.getReferenceNo() + "&action=edit" + "&result=" + result);
                } else {
                    request.setAttribute(TransactionResult, "BookingDetail cannot be saved!");
                }
                request.setAttribute(Detail, newMaster);
                request.setAttribute("referenceNo", newMaster.getReferenceNo());
                request.setAttribute(SelectedAgent, selectedAgent);
                request.setAttribute(Agent, agent);
                request.setAttribute(ACTION, "update");
             //   request.setAttribute(PACKAGEID, newMaster.getPackageTour().getId());
            }
        }

        setGeneralResponseAttribute(request, refNo);

        return BookDetail;
    }

    private Agent getAgentWLK() {
        List<Agent> agent = bookingDetailService.getListAgent();
        for (Agent agent1 : agent) {
            String wlk = agent1.getCode();
            if("WLK".equals(wlk)){
                return agent1;
            }
        }
        return null;
    }

    private String getResultText(int result) {
        return resultText[result];
    }

    public void setGeneralResponseAttribute(HttpServletRequest request, String refNo) {
        if (StringUtils.isNotEmpty(refNo)) {
            int[] booksize = utilservice.getCountItemFromBooking(refNo);
            request.setAttribute(Bookiing_Size, booksize);
        }
        Master master = utilservice.getMasterdao().getBookingFromRefno(refNo);
        request.setAttribute(Master, master);
        if(master != null){
            if(("2").equals(String.valueOf(master.getMBookingstatus().getId())) || ("5").equals(String.valueOf(master.getMBookingstatus().getId()))){
                request.setAttribute(LockUnlockBooking,1);
            }else{
                request.setAttribute(LockUnlockBooking,0);
            }
        }else{
             request.setAttribute(LockUnlockBooking,0);
        }
        List<MCurrency> mCurrency = utilservice.getListMCurrency();
        request.setAttribute(CurrencyList, mCurrency);
        List<MBookingstatus> mBookingstatuses = utilservice.getListMBookingstatus();
        request.setAttribute(BookingstatuseList, mBookingstatuses);
        List<MInitialname> mInitial = utilservice.getListMInitialname();
        request.setAttribute(initialList, mInitial);
        List<Customer> customerList = utilservice.getListCustomer();
        request.setAttribute(CustomerList, customerList);
        List<PackageTour> PackList = bookingDetailService.getListLandPackage();
        request.setAttribute(PackageList, PackList);
    }

    private void setMasterNumberPassenger(HttpServletRequest request, Master master) {

        String adult = request.getParameter("adult").trim();
        String child = request.getParameter("child").trim();
        String infant = request.getParameter("infant").trim();
        String pax = request.getParameter("pax");

        Integer childInt = null;
        Integer adultInt = null;
        Integer infantInt = null;
        Integer paxInt = 0;

        if (StringUtils.isNotEmpty(child)) {
            childInt = Integer.valueOf(child);
        }

        if (StringUtils.isNotEmpty(adult)) {
            adultInt = Integer.valueOf(adult);
        }

        if (StringUtils.isNotEmpty(infant)) {
            infantInt = Integer.valueOf(infant);
        }

        if (StringUtils.isNotEmpty(pax)) {
            paxInt = Integer.valueOf(pax);
        }

//        System.out.println("adult[" + adult + "] child[" + child + "] infant[" + infant + "]");
        master.setAdult(adultInt);
        master.setChild(childInt);
        master.setInfant(infantInt);
        master.setIsPackage(paxInt);
    }

    public BookingAirticketService getBookingAirticketService() {
        return bookingAirticketService;
    }

    public void setBookingAirticketService(BookingAirticketService bookingAirticketService) {
        this.bookingAirticketService = bookingAirticketService;
    }

    public BookingDetailService getBookingDetailService() {
        return bookingDetailService;
    }

    public void setBookingDetailService(BookingDetailService bookingDetailService) {
        this.bookingDetailService = bookingDetailService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public MInitialname getmInitialname() {
        return mInitialname;
    }

    public void setmInitialname(MInitialname mInitialname) {
        this.mInitialname = mInitialname;
    }

    public CheckDuplicateUserService getCheckDuplicateUserService() {
        return checkDuplicateUserService;
    }

    public void setCheckDuplicateUserService(CheckDuplicateUserService checkDuplicateUserService) {
        this.checkDuplicateUserService = checkDuplicateUserService;
    }

   private String checkDuplicateUser(HttpServletRequest request, HttpServletResponse response,HttpSession session, String matertId, int step) {
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String result = "fail";
        SystemUser user = (SystemUser) session.getAttribute("USER");
        CheckDuplicateUser chuSession = new CheckDuplicateUser();
        chuSession.setOperationTable("Master");
        chuSession.setTableId(matertId);
        if(step == 1){
            chuSession.setOperationDate(String.valueOf(df.format(new Date())));
            chuSession.setOperationUser(user.getUsername());
        }else if(step == 2){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            CheckDuplicateUser checkDuplicateUserSession = (CheckDuplicateUser) session.getAttribute("checkDuplicateUser"); 
            String operationDate = checkDuplicateUserSession.getOperationDate();
            String operationUser = checkDuplicateUserSession.getOperationUser();
            System.out.println("operationDate : "+operationDate);
            System.out.println("new Date : "+new Date());
            chuSession.setOperationDate((df.format(util.convertStringToDateTime(operationDate))));
            chuSession.setOperationUser(operationUser);
            System.out.println("chuSession.getOperationDate() : "+chuSession.getOperationDate());
        }else if(step == 3){
            chuSession.setOperationDate(String.valueOf(df.format(new Date())));
            chuSession.setOperationUser(user.getUsername());
        }    
//        session.setAttribute(CHECKDUPLICATEUSER, chuSession);
        CheckDuplicateUser cdu = checkDuplicateUserService.CheckAndUpdateOperationDetail(chuSession, step);
        System.out.println("=====cdu===== :"+cdu.getOperationUser());
        request.setAttribute(CHECKDUPLICATEUSER, cdu);
        if(cdu.getIsDuplicateUser() == 0){
            result = "success";
            opDate = util.convertStringToDateTime(cdu.getOperationDate());
            opUser = cdu.getOperationUser();
//            invoice.setOperationDate(util.convertStringToDateTime(cdu.getOperationDate()));
//            invoice.setOperationUser(cdu.getOperationUser());
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
//        CheckDuplicateUser checkDuplicateUser = new CheckDuplicateUser();
//        checkDuplicateUser.setOperationUser("PJ");
//        checkDuplicateUser.setOperationTable("Master");
//        checkDuplicateUser.setTableId("1080");
//        checkDuplicateUser.setOperationDate(String.valueOf(sdf.format(new Date())));
        session.setAttribute("checkDuplicateUser", chuSession);
        return result;
    }
    
    private String clearDuplicateUser(HttpServletRequest request, HttpServletResponse response,HttpSession session, String matetId){
        String result = "fail";
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        SystemUser  user = (SystemUser) session.getAttribute("USER");
        CheckDuplicateUser chuSession = new CheckDuplicateUser();
        session.setAttribute(CHECKDUPLICATEUSER, chuSession);
        CheckDuplicateUser chu = new CheckDuplicateUser();
        chu.setOperationTable("Master");
        chu.setTableId(matetId);
        chu.setOperationDate(String.valueOf(df.format(new Date())));
        chu.setOperationUser(user.getUsername());     
        int update = checkDuplicateUserService.updateOperationNull(chu);
        if(update == 1){
            result = "success";
        }
        return result;
    }
}
