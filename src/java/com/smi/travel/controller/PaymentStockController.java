/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.PaymentStock;
import com.smi.travel.datalayer.entity.PaymentStockDetail;
import com.smi.travel.datalayer.entity.PaymentStockItem;
import com.smi.travel.datalayer.entity.ReceiptDetail;
import com.smi.travel.datalayer.entity.Stock;
import com.smi.travel.datalayer.entity.StockDetail;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.PaymentStockService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private static final String MCURRENCYLIST = "currencyList";
    private static final String SAVERESULT = "saveresult"; // save result
    private static final String CREATEDATE = "createDate"; // save result
    private static final String NOSTOCKTABLE = "noStockTable"; // save result
    private static final String STOCKDETAILLIST = "StockDetailList";
    private UtilityService utilityService;
    private PaymentStockService paymentStockService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String action = request.getParameter("action");
        String payNo = request.getParameter("payNo");
        String payId = request.getParameter("payId");
        String createBy = request.getParameter("createBy");
        String createDate = request.getParameter("createDate");
        String totalSale = request.getParameter("totalSale");
        String totalCost = request.getParameter("totalCost");
        String curCost = request.getParameter("curCost");
        String curSale = request.getParameter("curSale");
        String noStockTable = request.getParameter("noStockTable");
        
        System.out.println("===== createDate =====" + createDate);
        
        request.setAttribute(NOSTOCKTABLE,1);
        String saveresult = "" ;
        
        if ("searchPayNo".equalsIgnoreCase(action)){
            PaymentStock paymentStock = new PaymentStock();
            PaymentStockDetail paymentStockDetail = new PaymentStockDetail();
            PaymentStockItem paymentStockItem = new PaymentStockItem();
            if(!"".equals(payNo)){
                paymentStock = paymentStockService.getPaymentStockFromPayNo(payNo);
                if(paymentStock != null) {
                    if(!paymentStock.getId().isEmpty()){
                        List<PaymentStockDetail> paymentStockDetailList = paymentStockService.getListPaymentStockDetailFromPaymentStockId(paymentStock.getId());
                        List<PaymentStockItem> paymentStockItemList = paymentStockService.getListPaymentStockItemFromPaymentStockId(paymentStock.getId());
                        List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
                        if(paymentStockDetailList!=null){
                            for(int i=0; i<paymentStockDetailList.size();i++){
                                PaymentStockDetail pad = paymentStockDetailList.get(i);
                                if(pad.getStock()!=null){
                                    List<StockDetail> stockDetails = new ArrayList<StockDetail>(pad.getStock().getStockDetails()); 
                                    if(stockDetails != null){
                                        for(int j = 0 ;j<stockDetails.size();j++){
                                            StockDetail sd = new StockDetail();
                                            sd = stockDetails.get(j);
                                            stockDetailList.add(sd);
                                        }
                                    }
                                }
                            }
                        }
                        request.setAttribute(STOCKDETAILLIST,stockDetailList);
                        request.setAttribute(PAYMENTSTOCKDETAILLIST,paymentStockDetailList);
                        
                        if(paymentStockDetailList != null){
                            request.setAttribute(NOSTOCKTABLE,paymentStockDetailList.size()+1);
                        }
                        request.setAttribute(PAYMENTSTOCK,paymentStock);
                        request.setAttribute(CREATEDATE,String.valueOf(paymentStock.getCreateDate()));
                    }
                }
            }
        }else if("deletePaymentStock".equalsIgnoreCase(action)){
            String psdIdDelete = request.getParameter("psdIdDelete");
            System.out.println("psdIdDelete ::: "+ psdIdDelete);
            String result = paymentStockService.deletePaymentStock(psdIdDelete);
            if (result == "success"){
                request.setAttribute(DELETERESULT, "delete successful");
            } else {
                request.setAttribute(DELETERESULT, "delete unsuccessful");
            }
        }
        else if("savePaymentStock".equalsIgnoreCase(action)){
            PaymentStock paymentStock = new PaymentStock();
            paymentStock.setId(payId);
            paymentStock.setPayStockNo(payNo);

            if("".equalsIgnoreCase(payId) ){
                paymentStock.setCreateBy(user.getUsername());
                paymentStock.setCreateDate(new Date());
            }else{
                System.out.println(" createDate " + createDate);
                paymentStock.setCreateBy(createBy);
                paymentStock.setCreateDate(utilty.convertStringToDate(new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(utilty.convertStringToDate(createDate))));
                paymentStock.setUpdateBy(user.getUsername());
                paymentStock.setUpdateDate(new Date());
            }
            
            paymentStock.setSaleAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(totalCost) ? totalCost.replaceAll(",","") : 0)));
            paymentStock.setCostAmount(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(totalSale) ? totalSale.replaceAll(",","") : 0)));
            paymentStock.setCurCost(curCost);
            paymentStock.setCurSale(curSale);

            String countRowStock = request.getParameter("countRowStock");
            String countRowDetail = request.getParameter("countRowDetail");
            countRowStock = noStockTable;
            System.out.println(" countRowStock " + countRowStock + "+++++++ countRowDetail " + countRowDetail);
            
            int rowsStock = Integer.parseInt(countRowStock);
            int rowsDetail = Integer.parseInt(countRowDetail);
            
            if(paymentStock.getPaymentStockDetails() == null){
                paymentStock.setPaymentStockDetails(new ArrayList<PaymentStockDetail>());
            }
            
            for (int i = 1; i < rowsStock ; i++) {
                String paymentStockId = request.getParameter("paymentStockId" + i);
                String stockId = request.getParameter("stockId" + i);
                String paymentStockDetailId = request.getParameter("paymentStockDetailId" + i);
                PaymentStockDetail psd = new PaymentStockDetail();
                psd.setId(paymentStockDetailId); // save Payment Stock Detail Id
                psd.setPaymentStock(paymentStock);
                
                Stock stock = new Stock(); // save Stock Id
                stock.setId(stockId);
                psd.setStock(stock);

                for (int j = 1; j < rowsDetail ; j++) {
                    PaymentStockItem psi = new PaymentStockItem();
                    String psiIdTable = request.getParameter("psiIdTable" + j);
                    String psdIdTable = request.getParameter("psdIdTable" + j);
                    String stockDetailIdTable = request.getParameter("stockDetailIdTable" + j);
                    String cost = request.getParameter("cost" + j);
                    String sale = request.getParameter("sale" + j);
                    String stockIdTable = request.getParameter("stockIdTable" + j);
                    if((!"".equalsIgnoreCase(stockIdTable) && stockIdTable != null ) && (!"".equalsIgnoreCase(stockId) && stockId!=null)){
                        System.out.println(" stockId "+ i + " ____ " + stockId);
                        System.out.println(" stockIdTable "+ j + " ____ " + stockIdTable);
                        if(stockId.equalsIgnoreCase(stockIdTable)){
                            if(  (!"".equalsIgnoreCase(cost) && cost != null ) || (!"".equalsIgnoreCase(sale) && sale!=null)  ){
                                System.out.println(" psiIdTable "+ j + " ____ " + psiIdTable);
                                psi.setId(psiIdTable);
                                psi.setPaymentStockDetail(psd);

                                StockDetail stockDetail = new StockDetail();
                                stockDetail.setId(stockDetailIdTable);
                                psi.setStockDetail(stockDetail);

                                psi.setCost(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(cost) ? cost.replaceAll(",","") : 0)));
                                psi.setSale(new BigDecimal(String.valueOf(StringUtils.isNotEmpty(sale) ? sale.replaceAll(",","") : 0)));
                                psd.getPaymentStockItems().add(psi);
                            }
                        }
                    }
                }
                paymentStock.getPaymentStockDetails().add(psd);
            }
            
            saveresult = paymentStockService.insertOrUpdatePaymentStock(paymentStock);
            System.out.println(" saveresult " + saveresult);
            if("fail".equalsIgnoreCase(saveresult)){
                request.setAttribute(SAVERESULT, "save unsuccessful");
            }else if("success".equalsIgnoreCase(saveresult)){
                request.setAttribute(SAVERESULT, "save successful");
            }else{
                paymentStock.setPayStockNo(saveresult);
                request.setAttribute(SAVERESULT, "save successful");
            }
            request.setAttribute(PAYMENTSTOCK,paymentStock);
            
            List<PaymentStockDetail> paymentStockDetailList = paymentStockService.getListPaymentStockDetailFromPaymentStockId(paymentStock.getId());
//            List<PaymentStockItem> paymentStockItemList = paymentStockService.getListPaymentStockItemFromPaymentStockId(paymentStock.getId());
            List<StockDetail> stockDetailList = new ArrayList<StockDetail>();
                        if(paymentStockDetailList!=null){
                            for(int i=0; i<paymentStockDetailList.size();i++){
                                PaymentStockDetail pad = paymentStockDetailList.get(i);
                                if(pad.getStock()!=null){
                                    List<StockDetail> stockDetails = new ArrayList<StockDetail>(pad.getStock().getStockDetails()); 
                                    if(stockDetails != null){
                                        for(int j = 0 ;j<stockDetails.size();j++){
                                            StockDetail sd = new StockDetail();
                                            sd = stockDetails.get(j);
                                            stockDetailList.add(sd);
                                        }
                                    }
                                }
                            }
                        }
                        request.setAttribute(STOCKDETAILLIST,stockDetailList);
                        request.setAttribute(PAYMENTSTOCKDETAILLIST,paymentStockDetailList);
//            request.setAttribute(PAYMENTSTOCKDETAILLIST,paymentStockDetailList);
//            request.setAttribute(PAYMENTSTOCKITEMLIST,paymentStockItemList);
            request.setAttribute(CREATEDATE,new SimpleDateFormat("yyyy-MM-dd", new Locale("us", "us")).format(paymentStock.getCreateDate()));
            request.setAttribute(NOSTOCKTABLE,noStockTable);
        }
        
        setResponseAttribute(request);
        return PaymentStock;
    }

    public void setResponseAttribute(HttpServletRequest request) {
        List<Stock> listStock = paymentStockService.getListStock();
        request.setAttribute(STOCKLIST, listStock);
        List<MCurrency> mCurrencys = utilityService.getListMCurrency();
        request.setAttribute(MCURRENCYLIST, mCurrencys); //receiveCurrency
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
