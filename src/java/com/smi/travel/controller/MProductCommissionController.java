/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.ProductComission;
import com.smi.travel.datalayer.service.MProductCommissionService;
import com.smi.travel.master.controller.SMITravelController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kanokporn
 */
public class MProductCommissionController extends SMITravelController{
    private static final ModelAndView MProductCommission = new ModelAndView("MProductCommission");
    private MProductCommissionService  mProductCommissionService;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String action = request.getParameter("action");
        String actionAdd = request.getParameter("actionAdd");
        String productName = request.getParameter("ProductNameSearch");
        String productCode = request.getParameter("ProductCodeSearch");
   
        if("search".equalsIgnoreCase(action) || "back".equalsIgnoreCase(action)){
          List<ProductComission> listProductCommission = mProductCommissionService.SearchProductComission(productCode, productName, 2);
          if(listProductCommission != null){
              request.setAttribute("ListProductCommission", listProductCommission);
              request.setAttribute("ProductCode", productCode);
              request.setAttribute("ProductName", productName);
              System.out.println("ListProductCommission");
              
          }else{
              request.setAttribute("ListProductCommission", null);
              request.setAttribute("ProductCode", null);
              request.setAttribute("ProductName", null);
              System.out.println("Not ListProductCommission");
          }
        }else if("delete".equalsIgnoreCase(action)){
            ProductComission proCom  = new ProductComission();
            proCom.setId(request.getParameter("ProductCommissionID"));
            String result =  mProductCommissionService.DeleteProductComission(proCom);
            if(result.equalsIgnoreCase("success")){
                List<ProductComission> listProductCommission = mProductCommissionService.SearchProductComission(productCode, productName, 2);
                request.setAttribute("ListProductCommission", listProductCommission);
                request.setAttribute("ProductCode", productCode);
                request.setAttribute("ProductName", productName);
                System.out.println("Delete Success");
            }else{
                request.setAttribute("ListProductCommission", null);
                request.setAttribute("ProductCode", null);
                request.setAttribute("ProductName", null);
                System.out.println("Delete Not Success");
            }
        }else if("new".equalsIgnoreCase(action)){
            request.setAttribute("actionAdd", actionAdd);
        }else{
            request.setAttribute("ListProductCommission", null);
            request.setAttribute("ProductCode", null);
            request.setAttribute("ProductName", null);
             request.setAttribute("actionAdd", null);
        }
        return MProductCommission;
    }

    public MProductCommissionService getmProductCommissionService() {
        return mProductCommissionService;
    }

    public void setmProductCommissionService(MProductCommissionService mProductCommissionService) {
        this.mProductCommissionService = mProductCommissionService;
    }
    
    
    
}
