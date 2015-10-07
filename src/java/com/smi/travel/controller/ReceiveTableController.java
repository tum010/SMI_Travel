package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.AdvanceReceive;
import com.smi.travel.datalayer.entity.AdvanceReceiveCredit;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.ReceiveTableService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class ReceiveTableController extends SMITravelController {
    private static final ModelAndView ReceiveTable = new ModelAndView("ReceiveTable");
    private static final ModelAndView ReceiveTable_REFRESH = new ModelAndView(new RedirectView("ReceiveTable.smi", true));
    private static final String CUSTOMERAGENTINFOLIST = "customerAgentInfoList";
    private static final String MACCPAYLIST = "mAccpayList";
    private static final String MCREDITBANKLIST = "mCreditBankList";
    private static final String ADVANCERECEIVELIST = "advanceReceiveList";
    private static final String ADVANCERECEIVE = "advanceReceive";
    private static final String ADVANCERECEIVECREDITLIST = "advanceReceiveCreditList";
    private static final String RESULT = "result";
    private ReceiveTableService receiveTableService;
    private UtilityService utilservice;
    private UtilityFunction util;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        List<CustomerAgentInfo> customerAgentInfoList = getUtilservice().getListCustomerAgentInfo();
        request.setAttribute(CUSTOMERAGENTINFOLIST, customerAgentInfoList);
        List<MAccpay> mAccpayList = getUtilservice().getListMAccpay();
        request.setAttribute(MACCPAYLIST, mAccpayList);
        List<MCreditBank> mCreditBankList = getUtilservice().getListCreditBank();
        request.setAttribute(MCREDITBANKLIST, mCreditBankList);
        
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String username = user.getUsername();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        
        String action = request.getParameter("action");
        String inputDate = request.getParameter("InputDate");
        String selectStatus = request.getParameter("SelectStatus");
        String deleteReceiveTableId = request.getParameter("deleteReceiveTableId");
        
        String receiveDate = request.getParameter("receiveDate");
        String vatType = request.getParameter("vatType");
        String receiveId = request.getParameter("receiveId");
        String receiveCode = request.getParameter("receiveCode");
        String receiveName = request.getParameter("receiveName");
        String receiveArCode = request.getParameter("receiveArCode");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String receiveAmount = request.getParameter("receiveAmount");
        String cashAmount = request.getParameter("cashAmount");
        String bankAmount = request.getParameter("bankAmount");
        String chqAmount = request.getParameter("chqAmount");
        String chqBank = request.getParameter("chqBank");
        String chqDate = request.getParameter("chqDate");
        String chqNo = request.getParameter("chqNo");
        String createBy = request.getParameter("createBy");
        String createDate = request.getParameter("createDate");
        String countCredit = request.getParameter("countCredit");
        
        if("search".equalsIgnoreCase(action)){
            List<AdvanceReceive> advanceReceiveList = receiveTableService.searchAdvanceReceive(inputDate,selectStatus,"search");
            request.setAttribute(ADVANCERECEIVELIST, advanceReceiveList);
            request.setAttribute("inputDate", inputDate);
            request.setAttribute("selectStatus", selectStatus);
        }else if("save".equalsIgnoreCase(action)){
            AdvanceReceive advanceReceive = new AdvanceReceive();
            if("".equalsIgnoreCase(receiveId)){
                advanceReceive.setCreateBy(username);
                createDate = dateFormat.format(cal.getTime());              
                advanceReceive.setCreateDate(utilty.convertStringToDate(createDate));
            }else{
                advanceReceive.setId(receiveId);
                advanceReceive.setCreateBy(createBy);
                advanceReceive.setCreateDate(utilty.convertStringToDate(createDate));
            }           
            
            advanceReceive.setRecTo(receiveCode);
            advanceReceive.setRecName(receiveName);
            advanceReceive.setRecDate(utilty.convertStringToDate(receiveDate));
            advanceReceive.setArCode(receiveArCode);
            advanceReceive.setDescription(description);
            advanceReceive.setVatType(vatType);
            advanceReceive.setRecAmount(new BigDecimal(receiveAmount.replaceAll(",", "")));           
            advanceReceive.setChqBank(chqBank.replaceAll(",", ""));
            advanceReceive.setChqDate(utilty.convertStringToDate(chqDate));
            advanceReceive.setChqNo(chqNo.replaceAll(",", ""));
            
            MAccpay mAccpay = new MAccpay();
            mAccpay.setId(status);
            advanceReceive.setMAccpay(mAccpay);
            
            if(!"".equalsIgnoreCase(cashAmount)){
                advanceReceive.setCashAmount(new BigDecimal(cashAmount.replaceAll(",", "")));
            }
            if(!"".equalsIgnoreCase(bankAmount)){
                advanceReceive.setBankAmount(new BigDecimal(bankAmount.replaceAll(",", "")));
            }
            if(!"".equalsIgnoreCase(chqAmount)){
                advanceReceive.setChqAmount(new BigDecimal(chqAmount.replaceAll(",", "")));
            }
            
            if(Integer.parseInt(countCredit) > 1){
                setAdvanceReceiptCredit(request,Integer.parseInt(countCredit),advanceReceive);
            }
            
            String result = receiveTableService.saveReceiveTable(advanceReceive);
            request.setAttribute(RESULT, result);
            if("success".equalsIgnoreCase(result)){
                List<AdvanceReceive> advanceReceiveList = receiveTableService.searchAdvanceReceive(receiveDate,vatType,"success");
                request.setAttribute(ADVANCERECEIVELIST, advanceReceiveList);
            }
            
        }else if("edit".equalsIgnoreCase(action)){
            List<AdvanceReceive> advanceReceiveList = receiveTableService.searchAdvanceReceive(inputDate,selectStatus,receiveId);
            request.setAttribute(ADVANCERECEIVE, advanceReceiveList.get(0));
            if(advanceReceiveList.get(0).getAdvanceReceiveCredits() != null){
                List<AdvanceReceiveCredit> advanceReceiveCreditList = new ArrayList<AdvanceReceiveCredit>();
                advanceReceiveCreditList = advanceReceiveList.get(0).getAdvanceReceiveCredits();
                request.setAttribute(ADVANCERECEIVECREDITLIST, advanceReceiveCreditList);
            }
                       
        }else if("deleteAdvanceReceive".equalsIgnoreCase(action)){
            AdvanceReceive advanceReceive = new AdvanceReceive();
            advanceReceive.setId(deleteReceiveTableId);
            String deleteResult = receiveTableService.deleteAdvanceReceive(advanceReceive);
        }
        
        return ReceiveTable;
    }
    
    private void setAdvanceReceiptCredit(HttpServletRequest request, int row, AdvanceReceive advanceReceive) {
        UtilityFunction utilty = new UtilityFunction();
        if(advanceReceive.getAdvanceReceiveCredits() == null){
            advanceReceive.setAdvanceReceiveCredits(new ArrayList<AdvanceReceiveCredit>());
        }
        for(int i=1;i<=row;i++){
            String advanceReceiveCreditId = request.getParameter("advanceReceiveCreditId" + i);
            String advanceReceiveId = request.getParameter("advanceReceiveId" + i);
            String creditCard = request.getParameter("creditCard" + i);
            String creditNo = request.getParameter("creditNo" + i);
            String creditExpire = request.getParameter("creditExpire" + i);
            String creditAmount = request.getParameter("creditAmount" + i);
            
            AdvanceReceiveCredit advanceReceiveCredit = new AdvanceReceiveCredit();
            MCreditBank mCreditBank = new MCreditBank();
            
            if((!"".equalsIgnoreCase(advanceReceiveCreditId) && advanceReceiveCreditId!=null) || (!"".equalsIgnoreCase(advanceReceiveId) && advanceReceiveId!=null) ||
                    (!"".equalsIgnoreCase(creditCard) && creditCard!=null) || (!"".equalsIgnoreCase(creditNo) && creditNo!=null) ||
                    (!"".equalsIgnoreCase(creditExpire) && creditExpire!=null) || (!"".equalsIgnoreCase(creditAmount) && creditAmount!=null)){
                
                if((!"".equalsIgnoreCase(advanceReceiveCreditId) && advanceReceiveCreditId!=null)){
                    advanceReceiveCredit.setId(advanceReceiveCreditId);
                }
//                if((!"".equalsIgnoreCase(advanceReceiveId) && advanceReceiveId!=null)){
//                    advanceReceiveCredit.setAdvanceReceive(advanceReceive);
//                }
                if((!"".equalsIgnoreCase(creditCard) && creditCard!=null)){
                    mCreditBank.setId(creditCard);
                    advanceReceiveCredit.setMCreditBank(mCreditBank);
                }
                if((!"".equalsIgnoreCase(creditNo) && creditNo!=null)){
                    advanceReceiveCredit.setCreditNo(creditNo);
                }
                if((!"".equalsIgnoreCase(creditExpire) && creditExpire!=null)){
                    advanceReceiveCredit.setCreditExpire(utilty.convertStringToDate(creditExpire));
                }
                if((!"".equalsIgnoreCase(creditAmount) && creditAmount!=null)){
                    advanceReceiveCredit.setCreditAmount(new BigDecimal(creditAmount.replaceAll(",", "")));
                }
                advanceReceiveCredit.setAdvanceReceive(advanceReceive);
                advanceReceive.getAdvanceReceiveCredits().add(advanceReceiveCredit);
            }
        }
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public ReceiveTableService getReceiveTableService() {
        return receiveTableService;
    }

    public void setReceiveTableService(ReceiveTableService receiveTableService) {
        this.receiveTableService = receiveTableService;
    }    
}
