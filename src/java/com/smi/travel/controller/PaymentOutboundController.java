package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MItemstatus;
import com.smi.travel.datalayer.entity.MPaytype;
import com.smi.travel.datalayer.entity.Master;
import com.smi.travel.datalayer.entity.PaymentOutbound;
import com.smi.travel.datalayer.entity.PaymentOutboundDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.PaymentOutboundService;
import com.smi.travel.datalayer.service.PaymentTourHotelService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
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
public class PaymentOutboundController extends SMITravelController {
    private static final ModelAndView PaymentOutbound = new ModelAndView("PaymentOutbound");
    private static final ModelAndView PaymentOutbound_REFRESH = new ModelAndView(new RedirectView("PaymentOutbound.smi", true));
    private static final String STATUS = "statusList";
    private static final String PAYTYPE = "payTypeList";
    private static final String CURRENCY = "currencyList";
    private static final String MVAT = "mVat";
    private static final String INVOICESUPLIST = "invSupList";
    private static final String PAYMENTOUTBOUND = "paymentOutbound";   
    private static final String PAYMENTOUTBOUNDDETAIL = "paymentOutboundDetail";   
    private UtilityService utilservice;
    private PaymentTourHotelService paymentTourHotelService;
    private PaymentOutboundService paymentOutboundService;        
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<MItemstatus> mItemstatusList = utilservice.getListMItemstatus();
        request.setAttribute(STATUS, mItemstatusList);
        List<MPaytype> mPayTypeList = utilservice.getListMPayType();
        request.setAttribute(PAYTYPE, mPayTypeList);
        List<MCurrency> mCurrencyList = utilservice.getListMCurrency();
        request.setAttribute(CURRENCY, mCurrencyList);
        MDefaultData mDefaultData = utilservice.getMDefaultDataFromType("vat");
        BigDecimal mVat = new BigDecimal(mDefaultData.getValue());
        request.setAttribute(MVAT, mVat);
        List<InvoiceSupplier> invoiceSupplierList = utilservice.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        
        String action = request.getParameter("action");
        String payId = request.getParameter("payId");
        String payNo = request.getParameter("payNo");
        String payDate = request.getParameter("payDate");
        String account = request.getParameter("account");
        String invSupCode = request.getParameter("invSupCode");
        String invSupApCode = request.getParameter("invSupApCode");
        String detail = request.getParameter("detail");
        String createBy = request.getParameter("createBy");
        String createDate = request.getParameter("createDate");
        String status = request.getParameter("status");
        String updateDate = request.getParameter("updateDate");
        String countPaymentDetail = request.getParameter("countPaymentDetail");
        
        UtilityFunction utilfunction = new UtilityFunction();
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SystemUser user = (SystemUser) session.getAttribute("USER");
        
        if("save".equalsIgnoreCase(action)){
            PaymentOutbound paymentOutbound = new PaymentOutbound();
            paymentOutbound.setId(payId);
            paymentOutbound.setPayNo(!"".equalsIgnoreCase(payId) ? payNo : "");
            paymentOutbound.setPayDate(utilfunction.convertStringToDate(payDate));
            paymentOutbound.setAccount(Integer.parseInt(account));
            paymentOutbound.setInvoiceSup(invSupCode);
            paymentOutbound.setApCode(invSupApCode);
            paymentOutbound.setDetail(detail);
            
            MItemstatus mItemstatus = new MItemstatus();
            mItemstatus.setId(status);
            paymentOutbound.setMItemstatus(mItemstatus);
            
            if((!"".equalsIgnoreCase(payId)) && (payId != null)){
                paymentOutbound.setCreateBy(createBy);
                paymentOutbound.setCreateDate(utilfunction.convertStringToDate(createDate));
            }else{
                paymentOutbound.setCreateBy(user.getUsername());
                createDate = sdf.format(date);
                paymentOutbound.setCreateDate(utilfunction.convertStringToDate(createDate));
            }
            
            if(Integer.parseInt(countPaymentDetail) > 0){
                setPaymentOutboundDetail(request, Integer.parseInt(countPaymentDetail), paymentOutbound);
            }
            
            String result = paymentOutboundService.savePaymentOutbound(paymentOutbound);
            
            
        }
                
        return PaymentOutbound;
    }
    
    private void setPaymentOutboundDetail(HttpServletRequest request, int row, PaymentOutbound paymentOutbound) {
        UtilityFunction utilfunction = new UtilityFunction();
        if(paymentOutbound.getPaymentOutboundDetails()== null){
            paymentOutbound.setPaymentOutboundDetails(new ArrayList<PaymentOutbound>());
        }
        for(int i=0; i<=row; i++){
            String detailId = request.getParameter("detailId"+i);
            String payId = request.getParameter("payId"+i);
            String refNo = request.getParameter("refNo"+i);
            String bookDetailId = request.getParameter("bookDetailId"+i);
            String type = request.getParameter("type"+i);
            String description = request.getParameter("description"+i);
            String invoice = request.getParameter("invoice"+i);
            String cost = request.getParameter("cost"+i);
            String gross = request.getParameter("gross"+i);
            String vat = request.getParameter("vat"+i);
            String isVat = request.getParameter("isVat"+i);
            String amount = request.getParameter("amount"+i);
            String cur = request.getParameter("cur"+i);
            String comm = request.getParameter("comm"+i);
            String value = request.getParameter("value"+i);
            String accCode = request.getParameter("accCode"+i);
            String bookDetailType = request.getParameter("bookDetailType"+i);       
            String payStock = request.getParameter("payStock"+i);
            String exportDate = request.getParameter("exportDate"+i);
            String isExport = request.getParameter("isExport"+i);
            
            if((!"".equalsIgnoreCase(detailId) && detailId != null) || ((!"".equalsIgnoreCase(payId) && payId != null)) || ((!"".equalsIgnoreCase(refNo) && refNo != null)) || 
                    (!"".equalsIgnoreCase(type) && type != null) || (!"".equalsIgnoreCase(description) && description != null) || (!"".equalsIgnoreCase(invoice) && invoice != null) || 
                    (!"".equalsIgnoreCase(cost) && cost != null) || (!"".equalsIgnoreCase(gross) && gross != null) || (!"".equalsIgnoreCase(isVat) && isVat != null) || 
                    (!"".equalsIgnoreCase(amount) && amount != null) || (!"".equalsIgnoreCase(cur) && cur != null) || (!"".equalsIgnoreCase(comm) && comm != null) || 
                    (!"".equalsIgnoreCase(value) && value != null) || (!"".equalsIgnoreCase(payStock) && payStock != null)){
                
                PaymentOutboundDetail paymentOutboundDetail = new PaymentOutboundDetail();
                paymentOutboundDetail.setId(!"".equalsIgnoreCase(detailId) && detailId != null ? detailId : "");
                paymentOutboundDetail.setBookDetailId(!"".equalsIgnoreCase(bookDetailId) && bookDetailId != null ? Integer.parseInt(bookDetailId) : null);
                paymentOutboundDetail.setDescription(!"".equalsIgnoreCase(description) && description != null ? description : "");
                paymentOutboundDetail.setInvoiceCreditor(!"".equalsIgnoreCase(invoice) && invoice != null? invoice : "");
                paymentOutboundDetail.setCost(!"".equalsIgnoreCase(cost) && cost != null ? new BigDecimal(cost.replaceAll(",", "")) : null);
                paymentOutboundDetail.setGross(!"".equalsIgnoreCase(gross) && gross != null ? new BigDecimal(gross.replaceAll(",", "")) : null);
                paymentOutboundDetail.setVat(!"".equalsIgnoreCase(vat) && vat != null ? new BigDecimal(vat.replaceAll(",", "")) : null);
                paymentOutboundDetail.setIsVat("1".equalsIgnoreCase(isVat) && isVat != null ? 1 : 0);
                paymentOutboundDetail.setAmount(!"".equalsIgnoreCase(amount) && amount != null ? new BigDecimal(amount.replaceAll(",", "")) : null);
                paymentOutboundDetail.setCurrency(!"".equalsIgnoreCase(cur) && cur != null ? cur : "");
                paymentOutboundDetail.setRecCom(!"".equalsIgnoreCase(value) && value != null? new BigDecimal(value.replaceAll(",", "")) : null);
                paymentOutboundDetail.setBookDetailType(!"".equalsIgnoreCase(bookDetailType) && bookDetailType != null ? bookDetailType : "");
                paymentOutboundDetail.setIsExport(!"".equalsIgnoreCase(payId) && payId != null? Integer.parseInt(isExport) : 0);
                paymentOutboundDetail.setExportDate(!"".equalsIgnoreCase(exportDate) && exportDate != null ? utilfunction.convertStringToDateTime(exportDate) : null);
                paymentOutboundDetail.setPayStockId(null);
                
                Master master = paymentTourHotelService.getMasterFromRefno(refNo);
                paymentOutboundDetail.setMaster(master != null ? master : null);
                
                MPaytype mpayType = paymentTourHotelService.getMPayTypeFromPayTypeId(type);
                paymentOutboundDetail.setMPaytype(mpayType != null ? mpayType : null);
                
                paymentOutboundDetail.setPaymentOutbound(paymentOutbound);
                paymentOutbound.getPaymentOutboundDetails().add(paymentOutboundDetail);
            }
        }
        
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public PaymentTourHotelService getPaymentTourHotelService() {
        return paymentTourHotelService;
    }

    public void setPaymentTourHotelService(PaymentTourHotelService paymentTourHotelService) {
        this.paymentTourHotelService = paymentTourHotelService;
    }

    public PaymentOutboundService getPaymentOutboundService() {
        return paymentOutboundService;
    }

    public void setPaymentOutboundService(PaymentOutboundService paymentOutboundService) {
        this.paymentOutboundService = paymentOutboundService;
    }    
}
