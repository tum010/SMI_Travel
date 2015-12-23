/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.entity.Agent;
import com.smi.travel.datalayer.entity.Product;
import com.smi.travel.datalayer.entity.ProductComission;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.MProductCommissionService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    private UtilityService utilityService;
    private static final String COMMISSIONDELETE= "COMMISSIONDELETE";
    private static final String AGENTLIST = "AgentList";
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<Product> listProduct = mProductCommissionService.getListMasterProduct();
        SystemUser user = (SystemUser) session.getAttribute("USER");
        String action = request.getParameter("action");
        String counter = request.getParameter("counterCommission");
        String inputProductId = request.getParameter("InputProductId");
        String inputProductCode = request.getParameter("InputProductCode");
        String inputProductName = request.getParameter("InputProductName");
        // Attribute Edit
        String productComId = request.getParameter("ProductCommissionId");
        String productId = request.getParameter("ProductId");
        String productNameSearch = request.getParameter("ProductNameSearch");
        String productCodeSearch = request.getParameter("ProductCodeSearch");
        // Attribute Delete
        String proComId = request.getParameter("productComId");
        
        List<Agent> agentList = utilityService.getListAgent();
        request.setAttribute(AGENTLIST, agentList);
        
        if("save".equalsIgnoreCase(action)){
            //ProductComission productCommission = new ProductComission();
            Product product = new Product();
            product.setId(inputProductId);
            product.setCode(inputProductCode);
            product.setName(inputProductName);
            
            //productCommission.setProductId(product);
           List productCommissionList =  setProductCommission(request, counter,product,user.getUsername());
           product.setProductComissions(productCommissionList);
           
           String isSave = mProductCommissionService.updateProductCommission(product);
           
           if(isSave.equalsIgnoreCase("success")){
               List<ProductComission> listProductCommission = mProductCommissionService.getListProductCommissionFromID(inputProductId);
               System.out.println("Save Success !!!");
               request.setAttribute("Status", "Save");
               request.setAttribute("listProductCommission", listProductCommission);
               request.setAttribute("InputProductId", inputProductId);
               request.setAttribute("InputProductCode", inputProductCode);
               request.setAttribute("InputProductName", inputProductName);
           }else{
               System.out.println("Save Not Success !!!");
               request.setAttribute("listProductCommission", null);
               request.setAttribute("InputProductId", null);
               request.setAttribute("InputProductCode", null);
               request.setAttribute("InputProductName", null);
               request.setAttribute("Status", null);
           }
        }else if("edit".equalsIgnoreCase(action)){
                List<ProductComission> listProductCommission = mProductCommissionService.getListProductCommissionFromID(productId);
                if(listProductCommission != null){
                    request.setAttribute("listProductCommission", listProductCommission);
                    request.setAttribute("InputProdductCommissionId", productComId);
                    request.setAttribute("InputProductId", productId);
                    request.setAttribute("InputProductCode", productCodeSearch);
                    request.setAttribute("InputProductName", productNameSearch);
                }else{
                    request.setAttribute("listProductCommission", null);
                    request.setAttribute("InputProdductCommissionId", null);
                    request.setAttribute("InputProductId", null);
                    request.setAttribute("InputProductCode", null);
                    request.setAttribute("InputProductName", null);
                }      
        }else if("delete".equalsIgnoreCase(action)){
            ProductComission proCom = new ProductComission();
            proCom.setId(proComId);
            String isDelete = mProductCommissionService.DeleteProductComission(proCom);
            if(isDelete.equalsIgnoreCase("success")){
                request.setAttribute(COMMISSIONDELETE,"delete "+isDelete);
            }else{
                System.out.println("Error Delete");
            }
        }
    
        request.setAttribute("ListProduct", listProduct);
        return MProductCommissionDetail;
    }
    
    private List setProductCommission(HttpServletRequest request, String productCommissionCounter,Product product,String username){
        List productCommissionList = new ArrayList<ProductComission>();
        
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
            String commissionPercent = util.StringUtilReplaceChar(request.getParameter("InputCommissionPercent-"+i));
            String agentId = request.getParameter("AgentName-"+i);
            String agentCommissionPercent = util.StringUtilReplaceChar(request.getParameter("InputAgentCommissionPercent-"+i));
            String agentCommission = util.StringUtilReplaceChar(request.getParameter("InputAgentCommission-"+i));
            
            String createBy = request.getParameter("createBy-"+i);
            String createDate = request.getParameter("createDate-"+i);
            String updateBy = request.getParameter("updateBy-"+i);
            String updateDate = request.getParameter("updateDate-"+i);
            
            System.out.println("from"+i+" : "+from);
            System.out.println("to"+i+" : "+to);
            
            System.out.println("agentId"+i+" : "+agentId);
            
            Double commissionDouble = 0.0;
            Double commissionPercentDouble = 0.0;
            if(StringUtils.isNotEmpty(commission)){
                commissionDouble = Double.parseDouble(commission);
                System.out.println("commissionDouble = "+commissionDouble);
            }
            
            if(StringUtils.isNotEmpty(commissionPercent)){
                commissionPercentDouble = Double.parseDouble(commissionPercent);
                System.out.println("commissionPercentDouble = "+commissionPercentDouble);
            }
                        
            Date dateTo = util.convertStringToDateS(to);
            Date dateFrom = util.convertStringToDateS(from);
            
            System.out.println("dateTo"+i+" : "+dateTo);
            System.out.println("dateTo"+i+" : "+dateTo);
            
            if(dateTo != null && dateFrom != null){
                ProductComission productComm = new ProductComission();
                if(!id.equalsIgnoreCase("")){
                    productComm.setId(id);
                    productComm.setCreateBy(createBy);
                    productComm.setCreateDate(util.convertStringToDate(createDate));
                    productComm.setUpdateDate(new Date());
                    productComm.setUpdateBy(username);
                }else{
                    productComm.setCreateDate(new Date());
                    productComm.setCreateBy(username);
                }
                productComm.setEffectiveTo(dateTo);
                productComm.setEffectiveFrom(dateFrom);
                productComm.setComission(commissionDouble);
                productComm.setComissionPercent(commissionPercentDouble);
                productComm.setProductId(product);

                if(StringUtils.isNotEmpty(agentId)){
                    Agent agent = new Agent();
                    agent.setId(agentId);
                    productComm.setAgent(agent);
                }
                
                if(StringUtils.isNotEmpty(agentCommission)){
                    productComm.setAgentCommission(new BigDecimal(agentCommission));
                }
                
                if(StringUtils.isNotEmpty(agentCommissionPercent)){
                    productComm.setAgentCommissionPercent(new BigDecimal(agentCommissionPercent));
                }
                
                productCommissionList.add(productComm);
                
            }else{
                
            }    
        }
        
        return productCommissionList;
    }

    public void setmProductCommissionService(MProductCommissionService mProductCommissionService) {
        this.mProductCommissionService = mProductCommissionService;
    }

    public MProductCommissionService getmProductCommissionService() {
        return mProductCommissionService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }
    
}
