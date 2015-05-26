package com.smi.travel.masterdata.controller;

import com.smi.travel.datalayer.entity.Daytour;
import com.smi.travel.datalayer.entity.DaytourExpense;
import com.smi.travel.datalayer.entity.DaytourPrice;
import com.smi.travel.datalayer.entity.MPricecategory;
import com.smi.travel.datalayer.service.MDaytourService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class MDaytourDetailController extends SMITravelController {

    private static final Logger log = Logger.getLogger(MDaytourDetailController.class.getName());
    private static final ModelAndView MDaytourDetail = new ModelAndView("MDaytourDetail");
    private static final ModelAndView MDaytour = new ModelAndView("MDaytour");
    private UtilityService utilservice ;
    private static final String DATALIST = "MDaytour_list";
    private static final String CATEGORYLIST = "category_list";
    private static final String CURRENCYLIST = "currency_list";
    private static final String DAYTOUR  =  "DAYTOUR";
    private static final String DAYTOURPRICES  =  "DAYTOURPRICES";
    private static final String DAYTOUREXPENSES = "DAYTOUREXPENSES";
    private static final String TransectionResult = "result";
     private static final String TransectionUpdate= "resultupdate";
    private static final String resultExpense = "resultExpense";
    private static final String resultPrice = "resultPrice";
    private static final String VALIDATE = "VALIDATE";
    private MDaytourService daytourService;
    private UtilityFunction util;

    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        util = new UtilityFunction();
        
        String action = request.getParameter("action");
        String actionExpense = request.getParameter("actionExpense");
        String actionPrice = request.getParameter("actionPrice");
        String currentDaytourId = StringUtils.isNoneEmpty(request.getParameter("daytourid")) ? request.getParameter("daytourid") : null;
        String daytourCode = request.getParameter("InputCode");
        String daytourName = request.getParameter("InputName");
        String daytourMin = request.getParameter("InputMin");
        String daytourMax = request.getParameter("InputMax");
        String daytourGuideCommission =  util.StringUtilReplaceChar(request.getParameter("InputGuideCommission"));
        String daytourStatus = request.getParameter("InputStatus");
        String daytourRemark = request.getParameter("TextareaRemark");
        String daytourCondition = request.getParameter("TextareaCondition");
        String validateValue = "" ;
        // price
        String priceCounter  = request.getParameter("counterPrice");
        String priceId   = request.getParameter("priceId");

        // expense
        String expenseCounter  = request.getParameter("counterExpense");
        String expenseId   = request.getParameter("expenseId");
        Integer min = 0 ;
        Integer max = 0 ;
        Double  guideCommission = .0;
        min = util.convertStringToInteger(daytourMin);
        max = util.convertStringToInteger(daytourMax);
        if (StringUtils.isNotEmpty(daytourGuideCommission)) {
            guideCommission = Double.parseDouble(daytourGuideCommission);
        }
       
        log.info("action[" + action + "] id[" + currentDaytourId + "]");
        
        Daytour daytour = null;
        if(StringUtils.isEmpty(currentDaytourId)) {
            daytour = new Daytour();
        } else {
            daytour = daytourService.getTourFromID(currentDaytourId);
        }
        
        if ("edit".equalsIgnoreCase(action)) {
            request.setAttribute("disableMDaytourCode", "readonly");
              
        } else if ("save".equalsIgnoreCase(action)) {     
            daytour.setId(currentDaytourId);
            daytour.setCode(daytourCode);
            daytour.setName(daytourName);
            daytour.setMin(min);
            daytour.setMax(max);
            daytour.setGuideComission(guideCommission);
            daytour.setStatus(daytourStatus);
            daytour.setRemark(daytourRemark);
            daytour.setCondition(daytourCondition);
            List<DaytourPrice> tourprice = setPriceRows(request, priceCounter, daytour);
            setExpenseRows(request, expenseCounter, daytour);

            // validate whether this new tour code is already in the DB
            validateValue = daytourService.validateDaytour(daytour, action);
            // if found that the tour code for this insert process is alreay there in the DB, show the error popup
            if (StringUtils.isEmpty(currentDaytourId) && StringUtils.isNotEmpty(validateValue)) {
                request.setAttribute(VALIDATE, validateValue);
            } else {
                String transactionResult = daytourService.SaveTour(daytour, null, null,tourprice);
                request.setAttribute("disableMDaytourCode", "readonly");
                request.setAttribute(TransectionResult, "save : " + transactionResult);
                if (StringUtils.isEmpty(currentDaytourId)) {
                    List<Daytour> list = daytourService.searchTourList(daytour, 1);
                    request.setAttribute(DATALIST, list);
                    System.out.println("2222");
                    return MDaytour; 
                     
                }else{
                    System.out.println("3333");
                    String transactionUpdate = daytourService.SaveTour(daytour, null, null,tourprice);
                    return new ModelAndView("redirect:MDaytourDetail.smi?daytourid="+currentDaytourId+"&action=edit&result="+transactionUpdate+"");
                }
            }
             
        }else if("new".equalsIgnoreCase(action)){
            request.setAttribute(DAYTOUR, daytour);
            request.setAttribute(DAYTOURPRICES, new ArrayList(daytour.getDaytourPrices()));
            request.setAttribute(DAYTOUREXPENSES, new ArrayList(daytour.getDaytourExpenses()));
            request.setAttribute(CATEGORYLIST, utilservice.getListMPricecategory());
            request.setAttribute(CURRENCYLIST, utilservice.getListMCurrency());
            
            return MDaytourDetail;
        }

        if("delete".equalsIgnoreCase(actionExpense)){
              System.out.println("expenseIdAfter=="+expenseId);
              String transactionExpenseResult = daytourService.DeleteTourExpense(expenseId);
              request.setAttribute(resultExpense, transactionExpenseResult);
        }

        if("delete".equalsIgnoreCase(actionPrice)){
            System.out.println("priceIdAfter=="+priceId);
            String transactionPriceResult = daytourService.DeleteTourPrice(priceId);
            request.setAttribute(resultPrice, transactionPriceResult);
        }
        
        request.setAttribute("daytourid", daytour.getId());
        request.setAttribute(DAYTOUR, daytour);
        request.setAttribute(DAYTOURPRICES, new ArrayList(daytour.getDaytourPrices()));
        request.setAttribute(DAYTOUREXPENSES, new ArrayList(daytour.getDaytourExpenses()));
        request.setAttribute(CATEGORYLIST, utilservice.getListMPricecategory());
        request.setAttribute(CURRENCYLIST, utilservice.getListMCurrency());
        
        return MDaytourDetail;
//          return new ModelAndView("redirect:MDaytourDetail.smi?daytourid="+currentDaytourId+"&action=edit");
    }

    private List<DaytourPrice> setPriceRows(HttpServletRequest request,String priceCounter, Daytour daytour){
        util = new UtilityFunction();
        List<DaytourPrice> priceList = new LinkedList<DaytourPrice>();
        int priceRows =  0;
        if(priceCounter != null){
             priceRows =  Integer.parseInt(priceCounter);
        }
        if(priceRows == 1){return priceList;}
        
        for(int i = 0 ; i < priceRows; i++){
            String id = request.getParameter("priceInputId-"+i);
            String category_id = request.getParameter("priceRowSelectCat-"+i);
            String detail = request.getParameter("priceInputDetail-"+i);
            String price = request.getParameter("priceInputPrice-"+i);
            String currencyCode = request.getParameter("priceRowSelectCur-"+i);
            Integer priceInt = null;
            
            if(StringUtils.isNotEmpty(price)){
                priceInt = util.convertStringToInteger(price);
            }
           
            log.info("detail:= "+ detail);
            DaytourPrice dayprice = getDayPrice(id, daytour);
            MPricecategory category = new MPricecategory();
            if(dayprice == null){
                dayprice = new DaytourPrice();
            }
            category.setId(category_id);
            
            dayprice.setMPricecategory(category);
            dayprice.setDetail(detail);
            dayprice.setPrice(priceInt);
            dayprice.setCurrency(currencyCode);
            
            if( dayprice.getPrice() != null){ //for check if add row
                if(dayprice.getId() == null){
                    dayprice.setDaytour(daytour);
                    priceList.add(dayprice);
                }else{
                    dayprice.setDaytour(daytour);
                    priceList.add(dayprice);
                }
            }else{
                System.out.println("Detail of Price  is null  ,Not update DB this object " + i);
            }
            
        }
        return priceList;
        
    }
    
    private void setExpenseRows(HttpServletRequest request,String expenseCounter, Daytour daytour){
        util = new UtilityFunction();
        int expenseRows =  0;
        if(expenseCounter != null){
             expenseRows =  Integer.parseInt(expenseCounter);
        }
        if(expenseRows == 1){return;}
        
        for(int i = 0 ; i < expenseRows; i++){
            String id = request.getParameter("expenseInputId-"+i);
            String description = request.getParameter("expenseInputDescription-"+i);
            String expense = request.getParameter("expenseInputAmount-"+i);
            String currencyCode = request.getParameter("ExpenseRowCur-"+i);
            String priceType  = request.getParameter("price_type-"+i);
            Integer expenseInt = null;
            
            if(StringUtils.isNotEmpty(expense)){
                expenseInt = util.convertStringToInteger(expense);
            }
            DaytourExpense dayexpense = getDayExpense(id, daytour);

            if(dayexpense == null){
                dayexpense = new DaytourExpense();
            }
            
            dayexpense.setDescription(description);
            dayexpense.setAmount(expenseInt);
            dayexpense.setCurrency(currencyCode);
            dayexpense.setPriceType(priceType);
            
            if(StringUtils.isNotEmpty(dayexpense.getDescription())){ //for check if add row
                if(dayexpense.getId() == null){
                    daytour.getDaytourExpenses().add(dayexpense);
                    dayexpense.setDaytour(daytour);
                }
            }else{
                System.out.println("Detail of Expense  is null  ,Not update DB this object " + i);
            }
            
        }
        
    }
    
    private DaytourPrice getDayPrice(String dayPriceId, Daytour daytour  ){
        System.out.println("getDayPrice Begin");
        if(dayPriceId == null){return null;}
        if(daytour.getDaytourPrices().isEmpty()){return null;}
        
        Iterator<DaytourPrice> iterator = daytour.getDaytourPrices().iterator();
        while(iterator.hasNext()){
            DaytourPrice dayprice =  iterator.next();
            
            if(dayPriceId.equalsIgnoreCase(dayprice.getId())){
                return dayprice;
            }
        }
        return null;
    }
    
    private DaytourExpense getDayExpense(String dayExpenseId, Daytour daytour  ){
        if(dayExpenseId == null){return null;}
        if(daytour.getDaytourExpenses().isEmpty()){return null;}
        
        Iterator<DaytourExpense> iterator = daytour.getDaytourExpenses().iterator();
        while(iterator.hasNext()){
            DaytourExpense dayExpense =  iterator.next();
            
            if(dayExpenseId.equalsIgnoreCase(dayExpense.getId())){
                return dayExpense;
            }
        }
        return null;
    }
    
    public MDaytourService getDaytourService() {
        return daytourService;
    }

    public void setDaytourService(MDaytourService daytourService) {
        this.daytourService = daytourService;
    }

    public UtilityService getUtilservice() {
        return utilservice;
    }

    public void setUtilservice(UtilityService utilservice) {
        this.utilservice = utilservice;
    }

    
}
