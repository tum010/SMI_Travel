package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.CheckDuplicateUserService;
import com.smi.travel.datalayer.service.InvoiceService;
import com.smi.travel.datalayer.service.UtilityService;
import com.smi.travel.datalayer.view.entity.CheckDuplicateUser;
import com.smi.travel.datalayer.view.entity.CustomerAgentInfo;
import com.smi.travel.master.controller.SMITravelController;
import com.smi.travel.util.UtilityFunction;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceController extends SMITravelController {
    private static final ModelAndView Invoice = new ModelAndView("Invoice");
    private static final String LINKNAME = "Invoice";
    private static final String CHECKDUPLICATEUSER = "checkDuplicateUser";
    private static final ModelAndView Invoice_REFRESH = new ModelAndView(new RedirectView("Invoice.smi", true));
    private UtilityService utilityService;
    private InvoiceService invoiceService;
    private CheckDuplicateUserService checkDuplicateUserService;
    Invoice invoice = null;
    List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
    SystemUser staff = new SystemUser();
    MFinanceItemstatus mStatus = new MFinanceItemstatus();
    MAccterm type = new MAccterm();
    UtilityFunction utilty = new UtilityFunction();
    Date opDate;
    String opUser;
//    private UtilityFunction utilty; test
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction util = new UtilityFunction();
        System.out.println("request.getRequestURI() :"+request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        String buttonVoid = request.getParameter("buttonVoid");
        String action = request.getParameter("action");
        //Attribute Invoice
        System.out.println("callPageFrom : "+callPageFrom);
       
        String invoiceType = request.getParameter("InputInvoiceType");
        String invoiceId = request.getParameter("InvoiceId");
        String InputInvDate = util.covertStringDateToFormatYMD(request.getParameter("InputInvDate"));
        String invoiceNo = request.getParameter("InvNo");
        String invoiceTo = request.getParameter("InvTo");
        String invoiceName = request.getParameter("InvToName");
        String invoiceAddress = request.getParameter("InvToAddress");
        String dueDate = util.covertStringDateToFormatYMD(request.getParameter("InputDueDate"));
        String termPay = request.getParameter("TermPay");
        String department = request.getParameter("Department");
        String staffId =  request.getParameter("SaleStaffId");
        String staffCode = request.getParameter("SaleStaffCode");
        String staffName = request.getParameter("SaleStaffName");
        String arCode = request.getParameter("ARCode");
        String remark = request.getParameter("Remark");
        String isGroup[] = request.getParameterValues("Grpup");
        String result = "";  
        String subDepartment = request.getParameter("Department");
        String wildCardSearch = request.getParameter("wildCardSearch");
        String keyCode = request.getParameter("keyCode");
        String invNoForCheckUser = request.getParameter("invNoForCheckUser");
        String operationTableId = request.getParameter("operationTableId");
        String operationUser = request.getParameter("operationUser");
        String invoiceCreateBy = request.getParameter("invoiceCreateBy");
        
        String checkDuplicateUser = "";
        String clearDuplicateUser = "";
        System.out.println("Action : "+action);
        
        if(callPageFrom != null){
           //String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", callPageFrom.substring(1));  
           department =  callPageFrom.substring(0,1);
           invoiceType   =  callPageFrom.substring(1);
        }
        request.setAttribute("invoiceType", invoiceType);
        System.out.println("invoiceType : "+invoiceType);
        System.out.println("department : "+department);
        request.setAttribute("duedate", dueDate);
        //Role User
        SystemUser  user = (SystemUser) session.getAttribute("USER");
        String roleName = user.getRole().getName();
        request.setAttribute("username", user.getUsername());
        if("Finance Manager".equals(roleName)){
            roleName = "YES";
            request.setAttribute("roleName", roleName);
        }else{
            roleName = "NO";
            request.setAttribute("roleName", roleName);
        }
              
        //List Currency
        List<MCurrency> listCurrency = new ArrayList<MCurrency>();
        listCurrency = utilityService.getListMCurrency();
        if(listCurrency != null){
            request.setAttribute("listCurrency", listCurrency);
        }else{
            request.setAttribute("listCurrency", null);
        }
        //List Default Data
        MDefaultData defaultData = new MDefaultData();
        defaultData = utilityService.getMDefaultDataFromType("vat");
        if(defaultData != null){
            request.setAttribute("defaultData", defaultData);
        }else{
            request.setAttribute("defaultData", null);
        }     
        // List Agent feild Invoice To
        List<CustomerAgentInfo> listCustomerAgentInfo = new ArrayList<CustomerAgentInfo>();
//        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
        if(listCustomerAgentInfo != null){
            request.setAttribute("listCustomerAgentInfo", listCustomerAgentInfo);
        }else{
            request.setAttribute("listCustomerAgentInfo", null);
        } 
        
        //List Staff
        List<SystemUser> listStaff = new ArrayList<SystemUser>();
        listStaff = utilityService.getUserList();
        if(listStaff != null){
            request.setAttribute("listStaff", listStaff);
        }else{
            request.setAttribute("listStaff", null);
        } 
        //List Term Pay
        List<MAccterm> listTermPay = new ArrayList<MAccterm>();
        listTermPay = utilityService.getListMAccterm();
        if(listTermPay != null){
            request.setAttribute("listTermPay", listTermPay);
        }else{
            request.setAttribute("listTermPay", null);
        } 
        // List Type
        List<MBilltype> listType = new ArrayList<MBilltype>();
        listType = utilityService.getListMBilltype();
        if(listType != null){
            request.setAttribute("listType", listType);
        }else{
            request.setAttribute("listType", null);
        } 
       
        //Get Vat
        MDefaultData mDefaultVat = utilityService.getMDefaultDataFromType("vat");
        String vat = mDefaultVat.getValue();
        if(vat != null){
            request.setAttribute("vat", vat);
        }else{
            request.setAttribute("vat", null);
        }
        
        //Duplicate User
        if("operationUpdate".equalsIgnoreCase(action)){
            System.out.println("operationTableId : "+operationTableId);
            checkDuplicateUser = checkDuplicateUser(request,response,session,operationTableId,3);
            action = "searchInvoice";
        }
               
        // Save Invoice And Update
        if("save".equals(action)){         
            //Duplicate User
            if(!"".equalsIgnoreCase(invoiceId) && invoiceId != null){
                checkDuplicateUser = checkDuplicateUser(request,response,session,invoiceId,2);
                if("fail".equalsIgnoreCase(checkDuplicateUser)){
                    request.setAttribute("page", callPageFrom);
                    return new ModelAndView(new RedirectView("Invoice"+callPageFrom+".smi?action=searchInvoice&InvNo="+invoiceNo, true));
    //                return new ModelAndView(LINKNAME+callPageFrom);
                }
            }
                
//            invoice = new Invoice();
            invoice.setId(invoiceId);
            if(invoiceId != null && !"".equals(invoiceId)){
                action = "update";
            }else{
                invoiceCreateBy = user.getUsername();
            }
            invoice = setValueInvoice(action, invoiceCreateBy , invoiceType, invoiceId, invoiceTo, invoiceName, invoiceAddress, isGroup, termPay, dueDate, department, staffCode, staffName, staffId, arCode, remark, invoiceNo, InputInvDate, request,subDepartment);
            
            String checkOverFlow = invoiceService.checkOverflowValueOfInvoice(invoice.getInvoiceDetails());
            if("okMoney".equals(checkOverFlow)){
                result = invoiceService.saveInvoice(invoice);
                System.out.println("ddddd result : "+result);
                saveAction(result, invoiceNo, invoice, "", request);
                if(invoice.getInvoiceDetails() != null){
                    // Check Flag Booking
                    String checkFlag = invoiceService.checkFlagBooking(invoice);
                    System.out.println("Check Flag :" + checkFlag);

                    //Set Booking Status
                    String setBookingStatus = invoiceService.setBookingStatus(invoice);
                    System.out.println("Set Booking Status : " + setBookingStatus);
                }
            }else{
                result = invoiceService.checkOverflowValueOfInvoice(invoice.getInvoiceDetails());
                request.setAttribute("listInvoiceDetail", invoice.getInvoiceDetails());
                request.setAttribute("invoice", invoice);
                request.setAttribute("result", result);
            }
            request.setAttribute("thisdate", InputInvDate);
//            System.out.println("invoiceService checkOverflowValueOfInvoice:"+invoiceService.checkOverflowValueOfInvoice(invoice.getInvoiceDetails()));
            
            if(!"fail".equalsIgnoreCase(result) && !"update fail".equalsIgnoreCase(result)){
                //Duplicate User
                    checkDuplicateUser = checkDuplicateUser(request,response,session,invoice.getId(),1);
//                request.setAttribute("page", callPageFrom);
//                return new ModelAndView(new RedirectView("Invoice"+callPageFrom+".smi?action=searchInvoice&InvNo="+invoice.getInvNo(), true));
            }    
            
        }else if("searchInvoice".equals(action)){ // search invoice when input invoice no
            if(invNoForCheckUser != null){
                if(!"".equalsIgnoreCase(invNoForCheckUser) && !invNoForCheckUser.equalsIgnoreCase(invoiceNo)){
                    CheckDuplicateUser cdu = new CheckDuplicateUser();
                    cdu.setOperationTable("Invoice");
                    cdu.setTableId(operationTableId);
                    cdu.setOperationUser(user.getUsername());
                    checkDuplicateUserService.updateOperationNull(cdu);
                }
            }
            String depart = (invoiceNo.length() > 2 ? invoiceNo.substring(0,1) : "");
            String type = (invoiceNo.length() > 2 ? invoiceNo.substring(1,2) : "");
            if("W".equals(depart)){
                if("V".equals(type)){
                    type = "V";
                }else if("T".equals(type)){
                    type = "A";
                }else if("N".equals(type)){
                    type = "N";
                }else{
                    type = "T";
                }
            }
            if("O".equals(depart)){
                if("V".equals(type)){
                    type = "V";
                }else if("T".equals(type)){
                    type = "A";
                }else if("N".equals(type)){
                    type = "N";
                }else{
                    type = "T";
                }
            }
            if((invoiceNo.indexOf("%") >= 0)){
                depart = department;
                type = invoiceType;
            }
            System.out.println("Search Invoice : Depart >>>> " + depart +  "  Typee >>> " + type);
            if(depart.equals(department) && type.equals(invoiceType)){
                invoice = invoiceService.getInvoiceFromInvoiceNumber(invoiceNo,depart,type);
                System.out.println(invoiceNo);
                System.out.println("1");
                listInvoiceDetail = invoice.getInvoiceDetails();
                if(invoice != null){
                    System.out.println("2");
                    System.out.println(invoice.getId());
                    System.out.println(invoice.getInvNo());
                    request.setAttribute("invoice", invoice);
                    //result = "success";
                    
                    //Duplicate User
                    checkDuplicateUser = checkDuplicateUser(request,response,session,invoice.getId(),1);
                    
                }else{
                    System.out.println("3");
                    request.setAttribute("invoice", null);
                }
                if(listInvoiceDetail != null){
                    request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                    //result = "success";
                }else{
                    request.setAttribute("listInvoiceDetail", null);
                }
                result="yesInvoice";
                System.out.println("result : "+result);
                request.setAttribute("result", result);
            }else{
                result = "notInvoice";
                System.out.println("result : "+result);
                request.setAttribute("result", result);
            }
            request.setAttribute("thisdate", invoice.getInvDate());                     
            
        }else if("disableVoid".equals(action)){
            //Duplicate User
            if(!"".equalsIgnoreCase(invoiceId) && invoiceId != null){
                checkDuplicateUser = checkDuplicateUser(request,response,session,invoiceId,2);
                if("fail".equalsIgnoreCase(checkDuplicateUser)){
                    request.setAttribute("page", callPageFrom);
                    return new ModelAndView(new RedirectView("Invoice"+callPageFrom+".smi?action=searchInvoice&InvNo="+invoiceNo, true));
                }
            }
            
            //checck Tax invoice
            int checkReciptAndTaxInvoice = 0;
            if("yesTaxinvoice".equals(invoiceService.checkTaxInvoice(invoiceNo))){
                checkReciptAndTaxInvoice++;
                request.setAttribute("checkTaxinvoice", "yesTaxinvoice");
            }else{
                checkReciptAndTaxInvoice += 0;
                request.setAttribute("checkTaxinvoice", "noTaxinvoice");
            }
            // check Recipt
            if("yesReceipt".equals(invoiceService.checkReceipt(invoiceNo))){
                checkReciptAndTaxInvoice++;
                request.setAttribute("checkRecipt", "yesReceipt");
            }else{
                checkReciptAndTaxInvoice += 0;
                request.setAttribute("checkRecipt", "noReceipt");
            }
            if(checkReciptAndTaxInvoice == 0){ 
                invoice = setValueInvoice(action, invoiceCreateBy , invoiceType, invoiceId, invoiceTo, invoiceName, invoiceAddress, isGroup, termPay, dueDate, department, staffCode, staffName, staffId, arCode, remark, invoiceNo, InputInvDate, request,subDepartment);
                result = invoiceService.saveInvoice(invoice);
                if(result.equals("update success")){
                   result = "void";
                   request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                   request.setAttribute("invoice", invoice);
                   request.setAttribute("result", result);
                   request.setAttribute("thisdate", InputInvDate);
                }
            }else{
                invoice = setValueInvoice("", invoiceCreateBy, invoiceType, invoiceId, invoiceTo, invoiceName, invoiceAddress, isGroup, termPay, dueDate, department, staffCode, staffName, staffId, arCode, remark, invoiceNo, InputInvDate, request,subDepartment);
                mStatus.setId("1");
                mStatus.setName("NORMAL");
                invoice.setMFinanceItemstatus(mStatus);
                request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                request.setAttribute("invoice", invoice);
                request.setAttribute("thisdate", InputInvDate);
            }
            
            //Duplicate User
            checkDuplicateUser = checkDuplicateUser(request,response,session,invoice.getId(),1);
                     
        }else if("enableVoid".equals(action)){
            //Duplicate User
            if(!"".equalsIgnoreCase(invoiceId) && invoiceId != null){
                checkDuplicateUser = checkDuplicateUser(request,response,session,invoiceId,2);
                if("fail".equalsIgnoreCase(checkDuplicateUser)){
                    request.setAttribute("page", callPageFrom);
                    return new ModelAndView(new RedirectView("Invoice"+callPageFrom+".smi?action=searchInvoice&InvNo="+invoiceNo, true));
                }
            }
            
            invoice = setValueInvoice(action, invoiceCreateBy, invoiceType, invoiceId, invoiceTo, invoiceName, invoiceAddress, isGroup, termPay, dueDate, department, staffCode, staffName, staffId, arCode, remark, invoiceNo, InputInvDate, request,subDepartment);
            result = invoiceService.saveInvoice(invoice);
            if(result.equals("update success")){
                   result = "cancelvoid";
                   request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                   request.setAttribute("invoice", invoice);
                   request.setAttribute("result", result);  
                   request.setAttribute("thisdate", InputInvDate);
            }
            
            //Duplicate User
            checkDuplicateUser = checkDuplicateUser(request,response,session,invoice.getId(),1);
            
        }else if("edit".equals(action)){// Edit Invoice From Search invoice page
            String invoiceIdSearch = request.getParameter("idInvoice"); // invoice id from search invoice page
            String departmentInvoice = request.getParameter("departmentInvoice");
            String typeInvoice = request.getParameter("typeInvoice");
            invoice = invoiceService.searchInvoiceNo(invoiceIdSearch, departmentInvoice, typeInvoice);
            listInvoiceDetail = invoice.getInvoiceDetails();
            if(invoice != null){
                request.setAttribute("invoice", invoice);
                                
                if(listInvoiceDetail != null){
                    request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                    // Check Flag Booking
//                    String checkFlag = invoiceService.checkFlagBooking(invoice);
//                    System.out.println("Check Flag :" + checkFlag);
//
//                    //Set Booking Status
//                    String setBookingStatus = invoiceService.setBookingStatus(invoice);
//                    System.out.println("Set Booking Status : " + setBookingStatus);
                                       
                }else{
                    request.setAttribute("listInvoiceDetail", null);
                }
            }else{
                request.setAttribute("invoice",null);
            }
            request.setAttribute("thisdate", (InputInvDate != null && !"".equalsIgnoreCase(InputInvDate) ? InputInvDate : utilty.convertDateToString(new Date())));
            if(!"".equalsIgnoreCase(invoiceIdSearch) && invoiceIdSearch != null){
                //Duplicate User
                checkDuplicateUser = checkDuplicateUser(request,response,session,invoice.getId(),1);
            }
            
        }else if("delete".equals(action)){// Delete Invoice From Search invoice page
            invoice = setValueInvoice(action,invoiceCreateBy, invoiceType, invoiceId, invoiceTo, invoiceName, invoiceAddress, isGroup, termPay, dueDate, department, staffCode, staffName, staffId, arCode, remark, invoiceNo, InputInvDate, request,subDepartment);
            String rowId  = request.getParameter("idDeleteDetailBillable");
            result = invoiceService.DeleteInvoiceDetail(rowId);
            if("success".equals(result)){
                invoice = invoiceService.getInvoiceFromInvoiceNumber(invoice.getInvNo(),"","");
                listInvoiceDetail = invoice.getInvoiceDetails();
                if(invoice != null){
                    request.setAttribute("invoice", invoice);
                    if(listInvoiceDetail != null){
                        request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                    }else{
                        request.setAttribute("listInvoiceDetail", null);
                    }
                }else{
                    request.setAttribute("invoice",null);
                }  
            }else{
                request.setAttribute("invoice",null);
                request.setAttribute("listInvoiceDetail", null);
            }
            
            //Duplicate User
            checkDuplicateUser = checkDuplicateUser(request,response,session,invoiceId,1);
            
        }else if("copyInvoice".equals(action)){
            invoice = setValueInvoice(action, invoiceCreateBy, invoiceType, invoiceId, invoiceTo, invoiceName, invoiceAddress, isGroup, termPay, dueDate, department, staffCode, staffName, staffId, arCode, remark, invoiceNo, InputInvDate, request,subDepartment);
            invoice.setId(null);
            result = invoiceService.saveInvoice(invoice);
            System.out.println("Copy Invoice result : "+result);
            saveAction(result, invoice.getInvNo(), invoice, "", request);
            request.setAttribute("thisdate", InputInvDate);
        }else if("new".equals(action)){
//            invoice = setValueInvoice(action, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, request,null);
            clearDuplicateUser = clearDuplicateUser(request,response,session,invoiceId);
            invoice.setInvoiceDetails(null);
            request.setAttribute("invoice",null);
            request.setAttribute("listInvoiceDetail", null);
            result = "NEW";
            request.setAttribute("result", result);
            request.setAttribute("thisdate", utilty.convertDateToString(new Date()));                     
            
        }else if("wildCardSearch".equalsIgnoreCase(action)){
            Invoice invoice = new Invoice();
            if(department != null){
                if(department.equals("W")){
                    department = "Wendy";
                } else if(department.equals("O")){
                    department = "Outbound";
                } else if(department.equals("I")){
                    department = "Inbound";
                } 
            } 
            
            invoice = invoiceService.getInvoiceByWildCardSearch(invoiceId,invoiceNo,wildCardSearch,keyCode,department,invoiceType); 
            if(invoice != null){
                saveAction(invoice.getInvNo(), invoiceNo, invoice, "wildCardSearch", request);
                request.setAttribute("thisdate", invoice.getInvDate());
                if(invNoForCheckUser != null){
                    if(!"".equalsIgnoreCase(invNoForCheckUser) && !"".equalsIgnoreCase(keyCode)){
                        System.out.println(" invNoForCheckUser " + invNoForCheckUser);
                        CheckDuplicateUser cdu = new CheckDuplicateUser();
                        cdu.setOperationTable("Invoice");
                        cdu.setTableId(operationTableId);
                        cdu.setOperationUser(user.getUsername());
                        checkDuplicateUserService.updateOperationNull(cdu);
                    }
                }
                //Duplicate User
                checkDuplicateUser = checkDuplicateUser(request,response,session,invoice.getId(),1);
            
            }else{
               request.setAttribute("thisdate", utilty.convertDateToString(new Date())); 
            }           
            
        }else{
            request.setAttribute("thisdate", utilty.convertDateToString(new Date()));
        }
        
        if((!"".equalsIgnoreCase(invoiceNo)) && (invoiceNo != null)){
            if("searchInvoice".equalsIgnoreCase(action)){
                if((invoiceNo.indexOf("%") >= 0)){
                    request.setAttribute("wildCardSearch", invoiceNo);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }else if("118".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else if("119".equalsIgnoreCase(keyCode)){
                request.setAttribute("wildCardSearch", ""); 
            }else{
                if((invoiceNo.indexOf("%") >= 0)){
                    request.setAttribute("wildCardSearch", invoiceNo);
                }else if((!"".equalsIgnoreCase(wildCardSearch)) && (wildCardSearch.indexOf("%") == 0)){
                    request.setAttribute("wildCardSearch", wildCardSearch);
                }else{
                    request.setAttribute("wildCardSearch", ""); 
                }
            }
        }
        
//        request.setAttribute("thisdate", utilty.convertDateToString(new Date()));
        request.setAttribute("page", callPageFrom);
        return new ModelAndView(LINKNAME+callPageFrom);
    }   
    
    public void saveAction(String result ,String invoiceNo, Invoice invoice ,String wildCardSearch ,HttpServletRequest request){
        if(result.equals("update success")){
            result = "success";
//            request.setAttribute("listInvoiceDetail", listInvoiceDetail);
            invoice = invoiceService.getInvoiceFromInvoiceNumber(invoiceNo,"","");
            request.setAttribute("invoice", invoice);
            request.setAttribute("listInvoiceDetail", invoice.getInvoiceDetails());
            request.setAttribute("result", result);
                   
        }else if(!result.equals("fail")){ // Save New Success
            // search invoice from invoice no
            System.out.println("save result : "+result); 
            Invoice newInvoice = invoiceService.getInvoiceFromInvoiceNumber(result,"","");
            result = "success";
            // set invoice detail in list
            List<InvoiceDetail> listInvoiceDetailNew = newInvoice.getInvoiceDetails();
            if(newInvoice != null){

                request.setAttribute("invoice", newInvoice);             
            }else{
                request.setAttribute("invoice", null);    
            }
            if(listInvoiceDetailNew != null){
                request.setAttribute("listInvoiceDetail", listInvoiceDetailNew);
            }else{
                request.setAttribute("listInvoiceDetail", null);
            }
            
            if("".equalsIgnoreCase(wildCardSearch)){
                request.setAttribute("result", result);
            }
                      
        }else{
            request.setAttribute("result", result);
            request.setAttribute("invoice", invoice);
            request.setAttribute("listInvoiceDetail", null);
        }  
    }
    
    public Invoice setValueInvoice(String action , String username,String invoiceType,String invoiceId, String invoiceTo,String invoiceName,String invoiceAddress,
            String isGroup[],String termPay ,String dueDate,String department,String staffCode,String staffName,String staffId,String arCode,
            String remark,String invoiceNo,String InputInvDate,HttpServletRequest request,String subDepartment){
        
            invoice.setCreateBy(username);
            invoice.setIsLock(0);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            String createDate = dateFormat.format(cal.getTime());
            Date date = new Date();
            date = utilty.convertStringToDate(createDate);
            // Create Date
            invoice.setCreateDate(date);
          
           DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   Date date2 = new Date();
	   System.out.println("Date 2 : "+dateFormat2.format(date2));
           String updateDate = dateFormat2.format(date2);
           System.out.println("String Date 2 : "+dateFormat2.format(date2));
           date2 = utilty.convertStringToDateS(updateDate);
            if("update".equals(action)){
//               invoice.setUpdateDate(date2);
                invoice.setUpdateDate(new Date());
            }
            
            // Status
            if("disableVoid".equals(action)){
                mStatus.setId("2");
                mStatus.setName("VOID");
                invoice.setMFinanceItemstatus(mStatus);
            }else{
                mStatus.setId("1");
                mStatus.setName("NORMAL");
                invoice.setMFinanceItemstatus(mStatus);
            }
            
            // Invoice Type 
            if(invoiceType != null && !"".equals(invoiceType) ){
                invoice.setInvType(invoiceType);
            }
            if(!"".equals(invoiceId) && invoiceId != null){
                invoice.setId(invoiceId);
            }else{
                invoice.setId(null);
            }
          
            if(invoiceTo != null){
                invoice.setInvTo(invoiceTo);
            }
            if(invoiceName != null){
                invoice.setInvName(invoiceName);
            }
            if(invoiceAddress != null){
                invoice.setInvAddress(invoiceAddress);
            }
            if(!"".equals(isGroup) && isGroup != null){
                if(isGroup[0].equals("on")){
                    invoice.setIsGroup(1);
                }else{
                    invoice.setIsGroup(0);
                }
            }else if(isGroup == null){
                invoice.setIsGroup(0);
            }
            if(termPay != null && !termPay.equals("")){
                type.setId(termPay);
                invoice.setMAccTerm(type);
            }else{
                invoice.setMAccTerm(null);
            }
            
            if(dueDate != null){
                Date due = utilty.convertStringToDate(dueDate);
                invoice.setDueDate(due);
            }
            
            if(InputInvDate != null){
                Date duee = utilty.convertStringToDate(InputInvDate);
                invoice.setInvDate(duee);
            }

            if(department != null){
                if(department.equals("W")){
                    invoice.setDepartment("Wendy");
                    department = "Wendy";
                } else if(department.equals("O")){
                    invoice.setDepartment("Outbound");
                    department = "Outbound";
                }  
            }

            if(staffId != null && !"".equals(staffId)){
                staff.setId(staffId);
                invoice.setStaff(staff);
                if(staffCode != null){
                    staff.setUsername(staffCode);
                }
                if(staffName != null){
                    staff.setName(staffName);
                }
            }else{
                staff.setId(null);
                invoice.setStaff(null);
            }
            
            if(arCode != null && !arCode.equals("")){
                invoice.setArcode(arCode);
            }
            if(remark != null){
                invoice.setRemark(remark);
            }
            // Invoice No
            if(invoiceNo != null && !invoiceNo.equals("")){
                invoice.setInvNo(invoiceNo);         
            }else{
                String day[] = new String[2];
//                if("copyInvoice".equals(action)){
                    day = InputInvDate.split("-");
                    date = utilty.convertStringToDate(InputInvDate);
//                    invoice.setCreateDate(date);
//                }else{
//                    day = createDate.split("-");
//                }
                SimpleDateFormat df = new SimpleDateFormat();
                df.applyPattern("MMyy");
                String month = day[1];
                String year = day[0];
                String da = day[2];
                year = year.substring(2);
                System.out.println("Date MMYY : " + df.format(date));
                String lastInvoiceType = invoiceService.searchInvoiceNum(department, invoiceType,df.format(date));
                String invoiceNoNew ="";
                if(lastInvoiceType != null && !lastInvoiceType.equals("")){
                   invoiceNoNew = generateInvoiceNo(department, invoiceType, month, year,lastInvoiceType,da);
                   if(invoiceNoNew != null && !invoiceNoNew.equals("")){
                        invoice.setInvNo(invoiceNoNew);
                   }else{
                        invoice.setInvNo("");
                   }
                }else{
                    invoiceNoNew = generateInvoiceNo(department, invoiceType, month, year,null,da);
                    if(invoiceNoNew != null && !invoiceNoNew.equals("")){
                         invoice.setInvNo(invoiceNoNew);
                    }else{
                         invoice.setInvNo("");
                    }
                }
            }
            
            if(subDepartment != null && !"".equals(subDepartment) ){
                invoice.setSubDepartment(subDepartment);
            }
            
            invoice.setOperationDate(opDate);
            invoice.setOperationUser(opUser);
            
            listInvoiceDetail = setInvoiceDetailList(request, invoice,action);
            if(listInvoiceDetail != null){
                invoice.setInvoiceDetails(listInvoiceDetail);
            }
        return invoice;
    }
    
    public String generateInvoiceNo(String department,String invoiceType,String month ,String year,String lastInvoiceType,String date){
        String invoiceNum = "";
        String invoiceLast = "";
        int count = 0;
        if(lastInvoiceType != null && !"".equals(lastInvoiceType) ){
            count = lastInvoiceType.length();
            count = count-4;
            invoiceLast = lastInvoiceType.substring(count);    
            count = Integer.parseInt(invoiceLast);
           
        }
        String numberAsString = "";
//        System.out.println("Day Invoice : " + date);
//        if("01".equals(date)){
//            numberAsString = String.format ("%04d", 1);
//        }else{
//            numberAsString = String.format ("%04d", (count+1));
//        }
        numberAsString = String.format ("%04d", (count+1));
        if(department.equals("Wendy")){
            if(invoiceType.equals("T")){
                invoiceNum = "W"+year+""+month+""+numberAsString;
            }else if(invoiceType.equals("V")){
                invoiceNum = "WV"+year+""+month+""+numberAsString;
            }else if(invoiceType.equals("N")){
                invoiceNum = "WN"+year+""+month+""+numberAsString;
            }else if(invoiceType.equals("A")){
                invoiceNum = "WT"+year+""+month+""+numberAsString;
            }
        }else if (department.equals("Outbound")){
            if(invoiceType.equals("T")){
                invoiceNum = "O"+year+""+month+""+numberAsString;
            }else if(invoiceType.equals("V")){
                invoiceNum = "OV"+year+""+month+""+numberAsString;
            }else if(invoiceType.equals("N")){
                invoiceNum = "ON"+year+""+month+""+numberAsString;
            }else if(invoiceType.equals("A")){
                invoiceNum = "OT"+year+""+month+""+numberAsString;
            }
        }

        return invoiceNum;
    }
    
    public List<InvoiceDetail> setInvoiceDetailList(HttpServletRequest request,Invoice invoice,String action){
        List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
        
        String invoiceDetailRows = request.getParameter("counterTable");
        if (invoiceDetailRows == null) {
            return null;
        }
        int driverRows = Integer.parseInt(invoiceDetailRows);
        if (driverRows == 1) {
            return null;
        }
         for (int i = 1; i <= driverRows; i++) {
             InvoiceDetail invoiceDetail = new InvoiceDetail();
             BillableDesc bill = new BillableDesc();
             MBilltype type = new MBilltype();
             String idDetail = request.getParameter("detailId"+i);
             String invoiceDetailId = request.getParameter("DetailBillId"+i);
             String description = request.getParameter("BillDescription"+i);
             String product = request.getParameter("SelectProductType"+i);
             String cost = request.getParameter("InputCost"+i);
             String costLocal = request.getParameter("InputCostLocal"+i);
             String costCurren = request.getParameter("SelectCurrencyCost"+i);
             String amount = request.getParameter("InputAmount"+i);
             String amountLocal = request.getParameter("InputAmountLocal"+i);
             String amountCurren = request.getParameter("SelectCurrencyAmount"+i);
             String vat = request.getParameter("InputVatTemp"+i);
             String isVat = request.getParameter("checkUse"+i);
             String gross = request.getParameter("InputGross"+i);
             String displayDescription = request.getParameter("DescriptionInvoiceDetail"+i);
             String exRate = request.getParameter("InputExRate"+i);
             request.getParameterMap();
             System.out.println("isvat : ["+i+"]"+isVat);
             if(idDetail != null && !idDetail.equals("")){
                  invoiceDetail.setId(idDetail);
             }
             if(cost != null && !cost.equals("")){
                if(cost.equals("0")){
                    BigDecimal costInt = BigDecimal.ZERO;
                    invoiceDetail.setCost(costInt);
                }
                BigDecimal costInt =  new BigDecimal(cost.replaceAll(",", ""));
                invoiceDetail.setCost(costInt);
             }
             
             if(costLocal != null && !costLocal.equals("")){
                 BigDecimal costLocalInt =  new BigDecimal(costLocal.replaceAll(",", ""));
                 invoiceDetail.setCostLocal(costLocalInt);
             }
             if(costCurren != null ){
                invoiceDetail.setCurCost(costCurren);
             }
            if(amount != null && !amount.equals("")){
                System.out.println(""+amount);
                BigDecimal amountInt = new BigDecimal(amount.replaceAll(",", ""));
                invoiceDetail.setAmount(amountInt);
            }
            if(gross != null && !gross.equals("")){
                    System.out.println("Gross"+gross);  
                    BigDecimal grossInt = new BigDecimal(gross.replaceAll(",", ""));
                    invoiceDetail.setGross(grossInt);
            }

             if(amountCurren != null){
                 invoiceDetail.setCurAmount(amountCurren);
             }
             
             if(amountLocal != null && !amountLocal.equals("")){
                 BigDecimal amountLocalInt =  new BigDecimal(amountLocal.replaceAll(",", ""));
                 invoiceDetail.setAmountLocal(amountLocalInt);
             }
             
             if(vat != null && !vat.equals("")){
                 BigDecimal vatInt =  new BigDecimal(vat);
                 invoiceDetail.setVat(vatInt);
             }
              if( isVat == null){
                  invoiceDetail.setIsVat(0);
                  invoiceDetail.setGross(null);
//                  invoiceDetail.setVat(null);
              }else{
                  invoiceDetail.setIsVat(1);
              }
              if(displayDescription != null){
                  displayDescription = displayDescription.trim();
                  invoiceDetail.setDisplayDescription(displayDescription);
              }
             
             if(description != null){
                 invoiceDetail.setDescription(description);
                 invoiceDetail.setInvoice(invoice);
                 listInvoiceDetail.add(invoiceDetail);
             }
             
             if(exRate != null && !exRate.equals("")){
                 BigDecimal exRateInt =  new BigDecimal(exRate.replaceAll(",", ""));
                 invoiceDetail.setExRate(exRateInt);
             }
             
             
             if(invoiceDetailId != null && !"".equals(invoiceDetailId)){
                 bill.setId(invoiceDetailId);
                 invoiceDetail.setBillableDesc(bill);
             }
               
             if(product != null && !product.equals("")){
                 type.setId(product);
                 invoiceDetail.setMbillType(type);
            }else{
                 listInvoiceDetail.remove(invoiceDetail);
             }
             if("update".equals(action)){
//               invoice.setUpdateDate(date2);
                invoiceDetail.setIsExport(1);
            }else{
                invoiceDetail.setIsExport(0);
             }
            
         }
         
        return listInvoiceDetail;
    }

    public void setUtilityService(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    public UtilityService getUtilityService() {
        return utilityService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public CheckDuplicateUserService getCheckDuplicateUserService() {
        return checkDuplicateUserService;
    }

    public void setCheckDuplicateUserService(CheckDuplicateUserService checkDuplicateUserService) {
        this.checkDuplicateUserService = checkDuplicateUserService;
    }

    private String checkDuplicateUser(HttpServletRequest request, HttpServletResponse response,HttpSession session, String invoiceId, int step) {
        UtilityFunction util = new UtilityFunction();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        String result = "fail";
        SystemUser user = (SystemUser) session.getAttribute("USER");
        CheckDuplicateUser chuSession = new CheckDuplicateUser();
        chuSession.setOperationTable("Invoice");
        chuSession.setTableId(invoiceId);
        if(step == 1){
            chuSession.setOperationDate(String.valueOf(df.format(new Date())));
            chuSession.setOperationUser(user.getUsername());
        }else if(step == 2){
            String operationDate = request.getParameter("operationDate");
            String operationUser = request.getParameter("operationUser");
            System.out.println("operationDate : "+operationDate);
            System.out.println("new Date : "+new Date());
            chuSession.setOperationDate((df.format(util.convertStringToDateTime(operationDate))));
            chuSession.setOperationUser(operationUser);
            System.out.println("chuSession.getOperationDate() : "+chuSession.getOperationDate());
        }else if(step == 3){
            chuSession.setOperationDate(String.valueOf(df.format(new Date())));
            chuSession.setOperationUser(user.getUsername());
        }    
        session.setAttribute("checkDuplicateUser", chuSession);
        CheckDuplicateUser cdu = checkDuplicateUserService.CheckAndUpdateOperationDetail(chuSession, step);
        request.setAttribute(CHECKDUPLICATEUSER, cdu);
        if(cdu != null && cdu.getIsDuplicateUser() == 0){
            result = "success";
//            if(!"null".equalsIgnoreCase(String.valueOf(cdu.getOperationDate())) && !"".equalsIgnoreCase(String.valueOf(cdu.getOperationDate()))){
//                invoice.setOperationDate(util.convertStringToDateTime(cdu.getOperationDate()));
//            }
//            invoice.setOperationUser(cdu.getOperationUser());
            
            opDate = util.convertStringToDateTime(cdu.getOperationDate());
            opUser = cdu.getOperationUser();
        }
        return result;
    }
    
    private String clearDuplicateUser(HttpServletRequest request, HttpServletResponse response,HttpSession session, String invoiceId){
        String result = "fail";
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        SystemUser  user = (SystemUser) session.getAttribute("USER");
        CheckDuplicateUser chuSession = new CheckDuplicateUser();
        session.setAttribute("checkDuplicateUser", chuSession);
        CheckDuplicateUser chu = new CheckDuplicateUser();
        chu.setOperationTable("Invoice");
        chu.setTableId(invoiceId);
        chu.setOperationDate(String.valueOf(df.format(new Date())));
        chu.setOperationUser(user.getUsername());     
        int update = checkDuplicateUserService.updateOperationNull(chu);
        if(update == 1){
            result = "success";
        }
        return result;
    }
    
}
