package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.service.MCurrencyService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

public class MCurrencyController extends SMITravelController {

    private static final ModelAndView MCurrency = new ModelAndView("MCurrency");
    private static final String DataList = "Currency_List";
    private static final String DataLap = "currencyLap";
    private static final String TransectionResult = "result";
    private MCurrencyService CurrencyService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String code = request.getParameter("CurrencyCode");
        String name = request.getParameter("CurrencyName");
        String CurrencyID = request.getParameter("CurrencyID");
        int result = 0;
        System.out.println("action  :" + action);
        String resultValidate = "";
        MCurrency currency = new MCurrency();
        currency.setCode((String.valueOf(code)).toUpperCase());
        currency.setDescription((String.valueOf(name)).toUpperCase());
        currency.setId(CurrencyID);

        if ("search".equalsIgnoreCase(action)) {
            List<MCurrency> list = CurrencyService.searchCurrency(currency,2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = CurrencyService.validateCurrency(currency, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = CurrencyService.insertCurrency(currency);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, CurrencyService.searchCurrency(currency,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = CurrencyService.validateCurrency(currency, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = CurrencyService.UpdateCurrency(currency);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, CurrencyService.searchCurrency(currency,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = CurrencyService.DeleteCurrency(currency);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("currencyCode", code);
        request.setAttribute("currencyName", name);
        
        return MCurrency;
    }

    public MCurrencyService getCurrencyService() {
        return CurrencyService;
    }

    public void setCurrencyService(MCurrencyService CurrencyService) {
        this.CurrencyService = CurrencyService;
    }

}
