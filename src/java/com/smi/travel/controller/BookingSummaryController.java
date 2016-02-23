/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.SystemUser;
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
 * @author Jittima
 */
public class BookingSummaryController extends SMITravelController{
    private static final ModelAndView BookingSummary = new ModelAndView("BookingSummary");
    private static final ModelAndView BookingSummary_REFRESH = new ModelAndView(new RedirectView("BookingSummary.smi", true));
    private static final String INVOICESUPLIST = "invSupList";
    private static final String STAFFLIST = "staffList";
    private static final String PRODUCTLIST = "productList";
    private UtilityService utilityService;
    private ProductService productService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        List<InvoiceSupplier> invoiceSupplierList = utilityService.getListInvoiceSuppiler();
        request.setAttribute(INVOICESUPLIST, invoiceSupplierList);
        
        List<SystemUser> listStaff = utilityService.getUserList();
        request.setAttribute(STAFFLIST, listStaff);
        
        List<Product> listProduct = productService.searchProduct(new Product(), 2);
        request.setAttribute(PRODUCTLIST, listProduct);
        
        return BookingSummary;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
}
