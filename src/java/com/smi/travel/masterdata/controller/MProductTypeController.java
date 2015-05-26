/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.MProductType;
import com.smi.travel.datalayer.service.MProductTypeService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Thitikul
 */
public class MProductTypeController extends SMITravelController {
    
    private static final ModelAndView MProductType = new ModelAndView("MProductType");
    private static final ModelAndView MProductType_REFRESH = new ModelAndView(new RedirectView("MProductType.smi", true));
    private static final String DataList = "ProductType_List";
    private static final String DataLap = "productTypeLap";
    private static final String TransectionResult = "result";
    private MProductTypeService productTypeService;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        String action = request.getParameter("action");
        String productTypeName = request.getParameter("ProductTypeName");
        String productTypeID = request.getParameter("ProductTypeID");
        System.out.println("action  :" + action);
        int result = 0;
        String resultValidate = "";
        MProductType productType = new MProductType();
        productType.setName(productTypeName);
        productType.setId(productTypeID);

        if ("search".equalsIgnoreCase(action)) {
            List<MProductType> list = productTypeService.getlistProductType(productType,2);
            request.setAttribute(DataList, list);
        } else if ("add".equalsIgnoreCase(action)) {
            resultValidate = productTypeService.validateProductType(productType, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = productTypeService.insertProductType(productType);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, productTypeService.getlistProductType(productType,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }

            }
        } else if ("update".equalsIgnoreCase(action)) {
            resultValidate = productTypeService.validateProductType(productType, action);
            if (!"".equalsIgnoreCase(resultValidate)) {
                request.setAttribute(DataLap, resultValidate);
            } else {
                result = productTypeService.updateProductType(productType);
                if (result == 1) {
                    request.setAttribute(TransectionResult, "save successful");
                    request.setAttribute(DataList, productTypeService.getlistProductType(productType,1));
                } else {
                    request.setAttribute(TransectionResult, "save unsuccessful");
                }
            }

        } else if ("delete".equalsIgnoreCase(action)) {
            result = productTypeService.deleteProductType(productType);
            if (result == 1) {
                request.setAttribute(TransectionResult, "delete successful");
            } else {
                request.setAttribute(TransectionResult, "delete unsuccessful");
            }
        }
        
        request.setAttribute("productTypeName", productTypeName);
        
        return MProductType;
    }
    
    public MProductTypeService getProductTypeService() {
        return productTypeService;
    }

    public void setProductTypeService(MProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }
    
}
