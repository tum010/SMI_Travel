/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MProductTypeService;
import com.smi.travel.datalayer.service.ProductService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Jittima
 */
public class ProfitLossController extends SMITravelController{
    private static final ModelAndView ProfitLoss = new ModelAndView("ProfitLoss");
    private static final ModelAndView ProfitLoss_REFRESH = new ModelAndView(new RedirectView("ProfitLoss.smi", true));
    private static final String INVOICESUPLIST = "invSupList";
    private static final String STAFFLIST = "staffList";
    private static final String PRODUCTLIST = "productList";
    private static final String CITYLIST = "cityList";
    
    private UtilityService utilityService;
    private MProductTypeService mProductTypeService;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        List<InvoiceSupplier> invoiceSupplierList = utilityService.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        
        List<SystemUser> listStaff = utilityService.getUserList();
        request.setAttribute(STAFFLIST, listStaff);
        
        request.setAttribute(PRODUCTLIST, mProductTypeService.getlistProductType());
        
        request.setAttribute(CITYLIST, utilityService.getListMCity());
        return ProfitLoss;
    }
    
    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public MProductTypeService getmProductTypeService() {
        return mProductTypeService;
    }

    public void setmProductTypeService(MProductTypeService mProductTypeService) {
        this.mProductTypeService = mProductTypeService;
    }
}
