/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.service.ProductService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.datalayer.view.entity.InvoiceSupplier;
import com.smi.travel.master.controller.SMITravelController;
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
public class StockSummaryController extends SMITravelController{
    private static final ModelAndView StockSummary = new ModelAndView("StockSummary");
    private static final ModelAndView StockSummary_REFRESH = new ModelAndView(new RedirectView("StockSummary.smi", true));
    private static final String PRODUCTLIST = "productList";
    private static final String CUSTOMERAGENTLIST = "customerAgentList";
    private static final String INVSUPLIST = "invSupList";
    private ProductService productservice;
    private UtilityService utilityService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Product product = new Product();
        List<Product> productList = productservice.searchProduct(product, 2);
        request.setAttribute(PRODUCTLIST, productList);
        List<CustomerAgentInfo> customerAgentList = utilityService.SearchListCustomerAgentInfo("");
        request.setAttribute(CUSTOMERAGENTLIST, customerAgentList);
        List<InvoiceSupplier> invSupList = utilityService.getListInvoiceSuppiler();
        request.setAttribute(INVSUPLIST, invSupList);
        return StockSummary;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public ProductService getProductservice() {
        return productservice;
    }

    public void setProductservice(ProductService productservice) {
        this.productservice = productservice;
    }

    
}
