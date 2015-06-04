/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.AgentComission;
import com.smi.travel.datalayer.entity.AgentTourComission;
import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import com.smi.travel.datalayer.service.MProductCommissionService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Kanokporn
 */
public class MProductCommissionDetailController extends SMITravelController{
    private static final ModelAndView MProductCommissionDetail = new ModelAndView("MProductCommissionDetail");
    private MProductCommissionService  mProductCommissionService;
    private UtilityFunction util;
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> listProduct = mProductCommissionService.getListMasterProduct();
        String action = request.getParameter("action");
        String counter = request.getParameter("counterCommission");
        String inputProductCode = request.getParameter("InputProductCode");
        String inputProductName = request.getParameter("InputProductName");
        
        if("save".equalsIgnoreCase(action)){
            //ProductComission productCommission = new ProductComission();
            Product product = new Product();
            product.setCode(inputProductCode);
            product.setName(inputProductName);
            //productCommission.setProductId(product);
           List productCommissionList =  setProductCommission(request, counter);
           product.setProductComissions(productCommissionList);
           String isSave = mProductCommissionService.insertProductCommission(product);
           
           if(isSave.equalsIgnoreCase("success")){
               System.out.println("Save Success !!!");
           }else{
               System.out.println("Save Not Success !!!");
           }
          
        }
        
        request.setAttribute("ListProduct", listProduct);
        return MProductCommissionDetail;
    }
    
    private List setProductCommission(HttpServletRequest request, String productCommissionCounter){
        List productCommissionList = null;
        util = new UtilityFunction();
        int productCommissionRows = 0;
        if(productCommissionCounter != null){
             productCommissionRows =  Integer.parseInt(productCommissionCounter);
        }
      
        
        for(int i = 1 ; i < productCommissionRows; i++){
            String id = request.getParameter("InputId-"+i);
            String from = request.getParameter("InputFrom-"+i);
            String to = request.getParameter("InputTo-"+i);
            String commission = util.StringUtilReplaceChar(request.getParameter("InputCommission-"+i));
            
            System.out.println("from"+i+" : "+from);
            System.out.println("to"+i+" : "+to);
            
            
            Double commissionDouble = 0.0;
            if(StringUtils.isNotEmpty(commission)){
                commissionDouble = Double.parseDouble(commission);
                System.out.println("commissionDouble = "+commissionDouble);
            }
            
            Date dateTo = util.convertStringToDateS(to);
            Date dateFrom = util.convertStringToDateS(from);
            
            System.out.println("dateTo"+i+" : "+dateTo);
            System.out.println("dateTo"+i+" : "+dateTo);
            
            ProductComission productComm = new ProductComission();
            productComm.setEffectiveTo(dateTo);
            productComm.setEffectiveFrom(dateFrom);
            productComm.setComission(commissionDouble);
            productCommissionList.add(productComm);
        }
        
        return productCommissionList;
    }
    
    

    public void setmProductCommissionService(MProductCommissionService mProductCommissionService) {
        this.mProductCommissionService = mProductCommissionService;
    }

    public MProductCommissionService getmProductCommissionService() {
        return mProductCommissionService;
    }
    
}
