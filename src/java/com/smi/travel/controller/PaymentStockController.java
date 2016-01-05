/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentStockDetail;
import com.smi.travel.datalayer.entity.PaymentStockItem;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.PaymentStockService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author chonnasith
 */
public class PaymentStockController extends SMITravelController{
    private static final ModelAndView PaymentStock = new ModelAndView("PaymentStock");
    private static final ModelAndView PaymentStock_REFRESH = new ModelAndView(new RedirectView("PaymentStock.smi", true));
    
    private static final String PAYMENTSTOCK = "PaymentStock";
    private static final String STOCKLIST = "StockList";
    private static final String PAYMENTSTOCKDETAILLIST = "PaymentStockDetailList";
    private static final String PAYMENTSTOCKITEMLIST = "PaymentStockItemList";
    private static final String DELETERESULT = "deleteresult";
    
    
    private UtilityService utilityService;
    private PaymentStockService paymentStockService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String action = request.getParameter("action");
        String payNo = request.getParameter("payNo");

        if ("searchPayNo".equalsIgnoreCase(action)){
            System.out.println(" ==================== Search Pay No =======================");
            PaymentStock paymentStock = new PaymentStock();
            PaymentStockDetail paymentStockDetail = new PaymentStockDetail();
            PaymentStockItem paymentStockItem = new PaymentStockItem();
            
            
            if(!"".equals(payNo)){
                paymentStock = paymentStockService.getPaymentStockFromPayNo(payNo);
                if(paymentStock != null) {
                    if(!paymentStock.getId().isEmpty()){
                        List<PaymentStockDetail> paymentStockDetailList = paymentStockService.getListPaymentStockDetailFromPaymentStockId(paymentStock.getId());
                        List<PaymentStockItem> paymentStockItemList = paymentStockService.getListPaymentStockItemFromPaymentStockId(paymentStock.getId());

                        request.setAttribute(PAYMENTSTOCKDETAILLIST,paymentStockDetailList);
                        request.setAttribute(PAYMENTSTOCKITEMLIST,paymentStockItemList);

                        request.setAttribute(PAYMENTSTOCK,paymentStock);
                    }
                }
            }
            
            
        }else if ("deletePaymentStock".equalsIgnoreCase(action)){
            String psdIdDelete = request.getParameter("psdIdDelete");
            System.out.println("psdIdDelete ::: "+ psdIdDelete);
            String result = paymentStockService.deletePaymentStock(psdIdDelete);
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }
        
        setResponseAttribute(request);
        return PaymentStock;
    }

    public void setResponseAttribute(HttpServletRequest request) {
        List<Stock> listStock = paymentStockService.getListStock();
        request.setAttribute(STOCKLIST, listStock);
        
    }

    public PaymentStockService getPaymentStockService() {
        return paymentStockService;
    }

    public void setPaymentStockService(PaymentStockService paymentStockService) {
        this.paymentStockService = paymentStockService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
