package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCreditBank;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.Receipt;
import com.smi.travel.datalayer.entity.ReceiptCredit;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.service.ReceiptService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
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
    private static final String PVList = "PVList";
    private static final String CUSTOMERAGENT = "customerAgent";
    private static final String MBILLTYPELIST = "billTypeList";
    private static final String VAT = "vat";
    private static final String MCURRENCYLIST = "currencyList";
    private static final String MSTATUSLIST = "statusList";
    private static final String MCREDITBANKLIST = "creditBankList";
    private static final String PRODUCTROWCOUNT = "productRowCount";
    private static final String RECEIPT = "receipt"; // search receive no from Receipt table
    private static final String SELECTEDRECEIPT = "SelectedReceive"; // search receive no from Receipt table aaa
    private static final String SAVERESULT = "saveresult"; // save result
    
    
    private UtilityService utilityService;
    private ReceiptService receiptService;
    UtilityFunction util;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String callPageFrom = request.getParameter("type");
        String paymentNo = request.getParameter("paymentNo");
        
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
        
        if(callPageFrom != null){
           String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", type[0]);  
        }
        
        util = new UtilityFunction();
        String result = "";
        
        if("new".equalsIgnoreCase(action)){

        }else if ("edit".equalsIgnoreCase(action)){
            
        }else if ("searchReceiveNo".equalsIgnoreCase(action)) {
            Receipt receipt = new Receipt();
            receipt = receiptService.getReceiptfromReceiptNo(receiveNo);
            request.setAttribute(RECEIPT,receipt);
        }else if ("saveReceipt".equalsIgnoreCase(action)) {
            Receipt receipt = new Receipt();
            receipt.setId(receiveId);
            String counter = request.getParameter("counter");
            String countRowCredit = request.getParameter("countRowCredit");
            
            int rowsProduct = Integer.parseInt(counter);
            int rowsCredit = Integer.parseInt(countRowCredit);
            
            System.out.println("rowsProduct " + rowsProduct);
                //save or update payment air ticket fare
            if(receipt.getReceiptDetails() == null){
                receipt.setReceiptDetails(new ArrayList<ReceiptDetail>());
            }
            for (int i = 0; i < rowsProduct ; i++) {
                String tableId = request.getParameter("tableId" + i);
                String receiveProduct = request.getParameter("receiveProduct" + i);
                String receiveDes = request.getParameter("receiveDes" + i);
                String receiveCost = request.getParameter("receiveCost" + i);
                String receiveCurCost = request.getParameter("receiveCurCost" + i);
                String receiveVat = request.getParameter("receiveVat" + i);
//                String receiveGross = request.getParameter("receiveGross" + i);
                String receiveAmount = request.getParameter("receiveAmount" + i);
                String receiveCurrency = request.getParameter("receiveCurrency" + i);
                
                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setId(tableId);
                receiptDetail.setReceipt(receipt);
                if(StringUtils.isNotEmpty(receiveProduct)){
                    MBilltype mBilltype = new MBilltype();
                    mBilltype.setId(receiveProduct);
                    receiptDetail.setMBilltype(mBilltype);
                }
                receiptDetail.setDescription(receiveDes);
                receiptDetail.setDisplayDescription(receiveDes);
                receiptDetail.setCost(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveCost) ? receiveCost.replaceAll(",","") : 0)));
                receiptDetail.setCurCost(receiveCurCost);
//                receiptDetail.setIsVat(i);
                receiptDetail.setVat(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveVat) ? receiveVat.replaceAll(",","") : 0)));
                receiptDetail.setAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(receiveAmount) ? receiveAmount.replaceAll(",","") : 0)));
                receiptDetail.setCurAmount(receiveCurrency);
                receipt.getReceiptDetails().add(receiptDetail);
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
                receiptCredit.setCreditAmount(Long.valueOf(String.valueOf(StringUtils.isNotEmpty(creditAmount) ? creditAmount.replaceAll(",","") : 0)));
                receipt.getReceiptCredits().add(receiptCredit);
            }
            
            receipt.setId(receiveId);
            receipt.setRecNo(receiveNo);
            receipt.setRecFrom(receiveFromCode);
            receipt.setRecName(receiveFromName);
            receipt.setRecAddress(receiveFromAddress);
            receipt.setRecDate(util.convertStringToDate(receiveFromDate != "" ? receiveFromDate : ""));
            receipt.setArCode(arCode);
            receipt.setRemark(remark);
            
            MFinanceItemstatus mFinanceItemstatus = new MFinanceItemstatus();
            mFinanceItemstatus.setId("1"); // 1 = Normal
            receipt.setMFinanceItemstatus(mFinanceItemstatus);
            
            if(StringUtils.isNotEmpty(inputStatus)){
                MItemstatus mItemstatus = new MItemstatus();
                mItemstatus.setId(inputStatus);
                receipt.setMItemStatus(mItemstatus);
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
        }
        
        setResponseAttribute(request);
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
        List<MItemstatus> mItemstatuses = utilityService.getListMItemstatus();
        request.setAttribute(MSTATUSLIST, mItemstatuses); //statusList

        
        
        request.setAttribute(PRODUCTROWCOUNT, "0");
        
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
}
