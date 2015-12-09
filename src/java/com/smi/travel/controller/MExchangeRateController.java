/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MExchangeRate;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MExchangeRateService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kanokporn
 */
public class MExchangeRateController  extends SMITravelController{
    private static final ModelAndView MExchangeRate = new ModelAndView("MExchangeRate");
    private MExchangeRateService mExchangeRateService;
    private UtilityService  utilityService;
    UtilityFunction utilty = new UtilityFunction();
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
         //List Currency
        List<MCurrency> listCurrency = new ArrayList<MCurrency>();
        listCurrency = utilityService.getListMCurrency();
        if(listCurrency != null){
            request.setAttribute("listCurrency", listCurrency);
        }else{
            request.setAttribute("listCurrency", null);
        }
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String action = request.getParameter("action");
        String actionSearch = request.getParameter("actionSearch");
        if(!"".equals(actionSearch) && actionSearch != null){
            action = actionSearch;
        }
        String ExchangeId = request.getParameter("ExchangeID");
        String EdxchangeDate = request.getParameter("ExchangeDate");
        String EdxchangeRate = request.getParameter("ExchangeRate");
        String Currency = request.getParameter("Currency");
        
        MExchangeRate mExchangeRate = new MExchangeRate();
        if(ExchangeId != null && !"".equals(ExchangeId)){
            mExchangeRate.setId(ExchangeId);
        }else{
            mExchangeRate.setId(null);
        }
        // Exchange Date
        if(EdxchangeDate != null && !"".equals(EdxchangeDate)){
            Date exdate = utilty.convertStringToDate(EdxchangeDate);
            mExchangeRate.setExdate(exdate);
        }else{
            mExchangeRate.setExdate(null);
        }
        // Exchange Rate
        if(EdxchangeRate != null && !"".equals(EdxchangeRate)){
            BigDecimal CurrencyInt =  new BigDecimal(EdxchangeRate.replaceAll(",", ""));
            mExchangeRate.setExrate(CurrencyInt);
        }else{
            mExchangeRate.setExrate(null);
        }
        // Currency
        if(Currency != null && !"".equals(Currency)){
            mExchangeRate.setCurrency(Currency);
        }else{
            mExchangeRate.setCurrency(null);
        }
        mExchangeRate.setCreateby(user.getName());
        mExchangeRate.setCreatedate(new Date());
        
        // Search
        String Currency_Search = request.getParameter("CurrencyS");
        String FromDate = request.getParameter("FromDate");
        String ToDate = request.getParameter("ToDate");
        
        if ("search".equalsIgnoreCase(action)) {
            List<MExchangeRate> listMExchange = mExchangeRateService.searchExchangeRate(FromDate, ToDate, Currency_Search);
            if( listMExchange != null && listMExchange.size() != 0 ){
                request.setAttribute("ExchangeList", listMExchange);
            }else{
                request.setAttribute("ExchangeList", listMExchange);
            }
            request.setAttribute("fromdate", FromDate);
            request.setAttribute("todate", ToDate);
            request.setAttribute("currency_exchange", Currency_Search);
        }else if ("add".equalsIgnoreCase(action)) {
            String result = "";
            String result_find = mExchangeRateService.findExchangeDuplicate(EdxchangeDate, Currency,ExchangeId);
            if("OK".equals(result_find)){
                result = mExchangeRateService.insertExchange(mExchangeRate);             
            }else{
                result = "duplicate";               
            }
            List<MExchangeRate> listMExchange = mExchangeRateService.searchExchangeRateById(EdxchangeDate,"");
            if( listMExchange != null && listMExchange.size() != 0 ){
                request.setAttribute("ExchangeList", listMExchange);
            }else{
                request.setAttribute("ExchangeList", listMExchange);
            }
            System.out.println("Result Add Exchange : " + result);
            request.setAttribute("result", result);
            request.setAttribute("fromdate", EdxchangeDate);
            request.setAttribute("todate", EdxchangeDate);
        }else if ("delete".equalsIgnoreCase(action)) {
            String result = mExchangeRateService.deleteExchange(mExchangeRate);
            System.out.println("Result Delete Exchange : " + result);
            request.setAttribute("result", result);
            
            List<MExchangeRate> listMExchange = mExchangeRateService.searchExchangeRateById(EdxchangeDate,"");
            if( listMExchange != null && listMExchange.size() != 0 ){
                request.setAttribute("ExchangeList", listMExchange);
            }else{
                request.setAttribute("ExchangeList", listMExchange);
            }
        }
        System.out.println("Date Current : " + new Date());
        
        return MExchangeRate;
    }

    public MExchangeRateService getmExchangeRateService() {
        return mExchangeRateService;
    }

    public void setmExchangeRateService(MExchangeRateService mExchangeRateService) {
        this.mExchangeRateService = mExchangeRateService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
