/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smi.travel.controller;

import com.smi.travel.datalayer.service.PostSaleVatService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.OutputTaxView;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.LinkedList;
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
public class PostSaleVatController extends SMITravelController {
    private static final ModelAndView PostSaleVat = new ModelAndView("PostSaleVat");
    private static final ModelAndView PostSaleVat_REFRESH = new ModelAndView(new RedirectView("PostSaleVat.smi", true));
//    private static final String MPAYTYPELIST = "mpaytype_list";
    private UtilityService utilservice;
    private PostSaleVatService postsalevatservice;
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        String action = request.getParameter("action");
        String department = request.getParameter("department");
        String from = util.covertStringDateToFormatYMD(request.getParameter("postFromDate"));
        String to = util.covertStringDateToFormatYMD(request.getParameter("postToDate"));
        String status = request.getParameter("postStatus");
        String type = request.getParameter("postType");
        //Search
        if("searchPostSaleVat".equals(action)){
           List<OutputTaxView> listPost = new LinkedList<>();
           listPost = postsalevatservice.SearchOutputTaxViewFromFilter(from, to, department, status,type);
            if(listPost != null){
                request.setAttribute("listPost", listPost);
            }else{
                request.setAttribute("listPost", null);
            }
            
        }else if("postSaleVat".equals(action)){
            String postCount = request.getParameter("postCount");
            List<OutputTaxView> listPost = new LinkedList<>();
            int count = Integer.parseInt(postCount);
            for(int i=1;i<=count;i++){
                String isSelect = request.getParameter("selectAll"+i);
//                System.out.println("isSelect : "+isSelect);
                if(isSelect != null){
                    OutputTaxView otv = new OutputTaxView();
                    String taxId = request.getParameter("taxId"+i);
                    String taxType = request.getParameter("taxType"+i);
                    otv.setTaxid(taxId);
                    otv.setType(taxType);
                    listPost.add(otv);
                    System.out.println("data : "+otv);
                }
            }
           if(listPost != null){
                System.out.println("Post : ");
                String isUpdate = postsalevatservice.UpdateOutputTaxStatus(listPost);
                System.out.println("Update ??? : " + isUpdate);
                request.setAttribute("update", isUpdate);
                
                listPost = postsalevatservice.SearchOutputTaxViewFromFilter(from, to, department, status,type);
                request.setAttribute("listPost", listPost);
            }else{
                request.setAttribute("listPost", null);
            }
        }
        request.setAttribute("Department", department);
        request.setAttribute("From", from);
        request.setAttribute("To", to);
        request.setAttribute("Status", status);
        request.setAttribute("Type", type);
        return PostSaleVat;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    public PostSaleVatService getPostsalevatservice() {
        return postsalevatservice;
    }

    public void setPostsalevatservice(PostSaleVatService postsalevatservice) {
        this.postsalevatservice = postsalevatservice;
    }
    
}
