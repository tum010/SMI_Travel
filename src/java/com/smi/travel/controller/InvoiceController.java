package com.smi.travel.controller;
import com.smi.travel.datalayer.entity.BillableDesc;
import com.smi.travel.datalayer.entity.Invoice;
import com.smi.travel.datalayer.entity.InvoiceDetail;
import com.smi.travel.datalayer.entity.MAccpay;
import com.smi.travel.datalayer.entity.MAccterm;
import com.smi.travel.datalayer.entity.MBilltype;
import com.smi.travel.datalayer.entity.MCurrency;
import com.smi.travel.datalayer.entity.MDefaultData;
import com.smi.travel.datalayer.entity.MFinanceItemstatus;
import com.smi.travel.datalayer.entity.SystemUser;
import com.smi.travel.datalayer.service.InvoiceService;
import com.smi.travel.datalayer.service.UtilityService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
public class InvoiceController extends SMITravelController {
    private static final ModelAndView Invoice = new ModelAndView("Invoice");
    private static final String LINKNAME = "Invoice";
    private static final ModelAndView Invoice_REFRESH = new ModelAndView(new RedirectView("Invoice.smi", true));
    private UtilityService utilityService;
    private InvoiceService invoiceService;
//    private UtilityFunction utilty; test
    
    @Override
    protected ModelAndView process(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        UtilityFunction utilty = new UtilityFunction();
        System.out.println("request.getRequestURI() :"+request.getRequestURI());
        String callPageFrom = utilty.getAddressUrl(request.getRequestURI()).replaceAll(LINKNAME, "");//request.getParameter("type");
        String buttonVoid = request.getParameter("buttonVoid");
        String action = request.getParameter("action");
        //Attribute Invoice
        System.out.println("callPageFrom : "+callPageFrom);
       
        String invoiceType = request.getParameter("InputInvoiceType");
            String invoiceTypeSub = request.getParameter("InputInvoiceSubType");
            String invoiceId = request.getParameter("InvoiceId");
//            String invoiceNoId = request.getParameter("InvTo_Id");
            String invoiceNo = request.getParameter("InvNo");
            String invoiceTo = request.getParameter("InvTo");
            String invoiceName = request.getParameter("InvToName");
            String invoiceAddress = request.getParameter("InvToAddress");
            String dueDate = request.getParameter("InputDueDate");
            String termPay = request.getParameter("TermPay");
            String department = request.getParameter("Department");
            String staffId =  request.getParameter("SaleStaffId");
            String staffCode = request.getParameter("SaleStaffCode");
            String staffName = request.getParameter("SaleStaffName");
            String arCode = request.getParameter("ARCode");
            String remark = request.getParameter("Remark");
            String isGroup[] = request.getParameterValues("Grpup");
        
        if(callPageFrom != null){
           //String[] type = callPageFrom.split("\\?");
           request.setAttribute("typeInvoice", callPageFrom.substring(1));  
           department =  callPageFrom.substring(0,1);
           invoiceType   =  callPageFrom.substring(1);
        }
        System.out.println("invoiceType : "+invoiceType);
        System.out.println("department : "+department);
        //Role User
        SystemUser  user = (SystemUser) session.getAttribute("USER");
        String roleName = user.getRole().getName();
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
        listCustomerAgentInfo = utilityService.getListCustomerAgentInfo();
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
        
        // Save Invoice And Update
        if("save".equals(action)){
            Invoice invoice = new Invoice();
            SystemUser staff = new SystemUser();
            MFinanceItemstatus mStatus = new MFinanceItemstatus();
            MAccpay type = new MAccpay();
           
            System.out.println(""+user.getUsername());
            // Create By
            invoice.setCreateBy(user.getUsername());
            // Is Lock
            invoice.setIsLock(0);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            String createDate = dateFormat.format(cal.getTime());
            Date date = new Date();
            date = utilty.convertStringToDate(createDate);
            // Create Date
            invoice.setCreateDate(date);
           
            // Status
            mStatus.setId("1");
            mStatus.setName("NORMAL");
            invoice.setMFinanceItemstatus(mStatus);
            // Invoice Type 
            if(invoiceType != null && !"".equals(invoiceType) ){
                invoice.setInvType(invoiceType);
            }
            if(!invoiceId.equals("") && (invoiceId != null)){
                invoice.setId(invoiceId);
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
                invoice.setMAccpay(type);
            }
            
            if(dueDate != null){
                Date due = utilty.convertStringToDate(dueDate);
                invoice.setDueDate(due);
            }

            if(department != null){
                if(department.equals("W")){
                    invoice.setDeparement("Wendy");
                    //invoice.setSubDepartment("Air Ticket");
                } else if(department.equals("O")){
                    invoice.setDeparement("Outbound");
                    //invoice.setSubDepartment("Package");
                }
                
            }else if(department == null){
                invoice.setDeparement(invoiceTypeSub);
            }
            if(staffCode != null){
                staff.setUsername(staffCode);
            }
            if(staffName != null){
                staff.setName(staffName);
            }
            if(staffId != null){
                staff.setId(staffId);
                invoice.setStaff(staff);
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
                String day[] = createDate.split("-");
                String month = day[1];
                String year = day[0];
                year = year.substring(2);
                String lastInvoiceType = invoiceService.searchInvoiceNo(invoiceTypeSub, invoiceType);
                if(lastInvoiceType != null && !lastInvoiceType.equals("")){
                   invoiceNo = generateInvoiceNo(department, invoiceType, month, year,lastInvoiceType);
                   if(invoiceNo != null && !invoiceNo.equals("")){
                        invoice.setInvNo(invoiceNo);
                   }else{
                        invoice.setInvNo("");
                   }
                }else{
                    invoiceNo = generateInvoiceNo(department, invoiceType, month, year,null);
                    if(invoiceNo != null && !invoiceNo.equals("")){
                         invoice.setInvNo(invoiceNo);
                    }else{
                         invoice.setInvNo("");
                    }
                }
            }
            
            List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
            listInvoiceDetail = setInvoiceDetailList(request, invoice);
            if(listInvoiceDetail != null){
                invoice.setInvoiceDetails(listInvoiceDetail);
            }
            String result = invoiceService.saveInvoice(invoice);
            if(result.equals("success")){ // Save New Success
                // search invoice from invoice no
                Invoice newInvoice = invoiceService.getInvoiceFromInvoiceNumber(invoiceNo);
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
                request.setAttribute("result", result);
            }else if(result.equals("update success")){
                   result = "success";
                   request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                   request.setAttribute("invoice", invoice);
                   request.setAttribute("result", result);
                   
            }else{
                request.setAttribute("result", result);
                request.setAttribute("invoice", null);
                request.setAttribute("listInvoiceDetail", null);
            }  
        }else if("searchInvoice".equals(action)){ // search invoice when input invoice no 
            String result = "";
            String invoiceNum = request.getParameter("InvNo");
            Invoice invoice = new Invoice();
            List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
            invoice = invoiceService.getInvoiceFromInvoiceNumber(invoiceNum);
            listInvoiceDetail = invoice.getInvoiceDetails();
            if(invoice != null){
                request.setAttribute("invoice", invoice);
                result = "success";
            }else{
                request.setAttribute("invoice", null);
            }
            if(listInvoiceDetail != null){
                request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                result = "success";
            }else{
                request.setAttribute("listInvoiceDetail", null);
            }
            request.setAttribute("result", result);   
        }else if("disableVoid".equals(action)){
            String result = "";
            Invoice invoice = new Invoice();
            SystemUser staff = new SystemUser();
            MFinanceItemstatus mStatus = new MFinanceItemstatus();
            MAccpay type = new MAccpay();
            
            System.out.println(""+user.getUsername());
            // Create By
            invoice.setCreateBy(user.getUsername());
            // Is Lock
            invoice.setIsLock(0);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            String createDate = dateFormat.format(cal.getTime());
            Date date = new Date();
            date = utilty.convertStringToDate(createDate);
            // Create Date
            invoice.setCreateDate(date);
           
            // Status Void
            mStatus.setId("2");
            mStatus.setName("NORMAL");
            invoice.setMFinanceItemstatus(mStatus);
            // Invoice Type 
            if(invoiceType != null && !"".equals(invoiceType) ){
                invoice.setInvType(invoiceType);
            }
            if(!invoiceId.equals("") && (invoiceId != null)){
                invoice.setId(invoiceId);
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
                invoice.setMAccpay(type);
            }
            
            if(dueDate != null){
                Date due = utilty.convertStringToDate(dueDate);
                invoice.setDueDate(due);
            }

            if(department != null){
                if(department.equals("W")){
                    invoice.setDeparement("Wendy");
                    //invoice.setSubDepartment("Air Ticket");
                  
                    
                } else if(department.equals("O")){
                    invoice.setDeparement("Outbound");
                    //invoice.setSubDepartment("Package");
                  
                }
                invoiceTypeSub = department;
            }else if(department == null){
                invoice.setDeparement(invoiceTypeSub);
            }
            
            if(staffCode != null){
                staff.setUsername(staffCode);
            }
            if(staffName != null){
                staff.setName(staffName);
            }
            if(staffId != null){
                staff.setId(staffId);
                invoice.setStaff(staff);
            }
            
            if(arCode != null && !arCode.equals("")){
//                Integer arC = Integer.parseInt(arCode);
                invoice.setArcode(arCode);
            }
            if(remark != null){
                invoice.setRemark(remark);
            }
            // Invoice No
            if(invoiceNo != null && !invoiceNo.equals("")){
                invoice.setInvNo(invoiceNo);         
            }else{
                String day[] = createDate.split("-");
                String month = day[1];
                String year = day[0];
                year = year.substring(2);
                String lastInvoiceType = invoiceService.searchInvoiceNo(department, invoiceType);
                if(lastInvoiceType != null && !lastInvoiceType.equals("")){
                   invoiceNo = generateInvoiceNo(department, invoiceType, month, year,lastInvoiceType);
                   if(invoiceNo != null && !invoiceNo.equals("")){
                        invoice.setInvNo(invoiceNo);
                   }else{
                        invoice.setInvNo("");
                   }
                }else{
                    invoiceNo = generateInvoiceNo(department, invoiceType, month, year,null);
                    if(invoiceNo != null && !invoiceNo.equals("")){
                         invoice.setInvNo(invoiceNo);
                    }else{
                         invoice.setInvNo("");
                    }
                }
            }
            
            List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
            listInvoiceDetail = setInvoiceDetailList(request, invoice);
            if(listInvoiceDetail != null){
                invoice.setInvoiceDetails(listInvoiceDetail);
            }
            result = invoiceService.saveInvoice(invoice);
            if(result.equals("update success")){
                   result = "void";
                   request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                   request.setAttribute("invoice", invoice);
                   request.setAttribute("result", result);
                   
            }
        }else if("enableVoid".equals(action)){
            String result = "";
            Invoice invoice = new Invoice();
            SystemUser staff = new SystemUser();
            MFinanceItemstatus mStatus = new MFinanceItemstatus();
            MAccpay type = new MAccpay();
            
            System.out.println(""+user.getUsername());
            // Create By
            invoice.setCreateBy(user.getUsername());
            // Is Lock
            invoice.setIsLock(0);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            System.out.println(dateFormat.format(cal.getTime()));
            String createDate = dateFormat.format(cal.getTime());
            Date date = new Date();
            date = utilty.convertStringToDate(createDate);
            // Create Date
            invoice.setCreateDate(date);
           
            // Status Cancel Void
            mStatus.setId("1");
            mStatus.setName("NORMAL");
            invoice.setMFinanceItemstatus(mStatus);
            // Invoice Type 
            if(invoiceType != null && !"".equals(invoiceType) ){
                invoice.setInvType(invoiceType);
            }
            if(!invoiceId.equals("") && (invoiceId != null)){
                invoice.setId(invoiceId);
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
                invoice.setMAccpay(type);
            }
            
            if(dueDate != null){
                Date due = utilty.convertStringToDate(dueDate);
                invoice.setDueDate(due);
            }

            if(department != null){
                if(department.equals("W")){
                    invoice.setDeparement("Wendy");
                    //invoice.setSubDepartment("Air Ticket");        
                } else if(department.equals("O")){
                    invoice.setDeparement("Outbound");
                    //invoice.setSubDepartment("Package");
                }
                
            }else if(department == null){
                invoice.setDeparement(invoiceTypeSub);
            }
            
            if(staffCode != null){
                staff.setUsername(staffCode);
            }
            if(staffName != null){
                staff.setName(staffName);
            }
            if(staffId != null){
                staff.setId(staffId);
                invoice.setStaff(staff);
            }
            
            if(arCode != null && !arCode.equals("")){
//                Integer arC = Integer.parseInt(arCode);
                invoice.setArcode(arCode);
            }
            if(remark != null){
                invoice.setRemark(remark);
            }
            // Invoice No
            if(invoiceNo != null && !invoiceNo.equals("")){
                invoice.setInvNo(invoiceNo);         
            }else{
                String day[] = createDate.split("-");
                String month = day[1];
                String year = day[0];
                year = year.substring(2);
                String lastInvoiceType = invoiceService.searchInvoiceNo(department, invoiceType);
                if(lastInvoiceType != null && !lastInvoiceType.equals("")){
                   invoiceNo = generateInvoiceNo(department, invoiceType, month, year,lastInvoiceType);
                   if(invoiceNo != null && !invoiceNo.equals("")){
                        invoice.setInvNo(invoiceNo);
                   }else{
                        invoice.setInvNo("");
                   }
                }else{
                    invoiceNo = generateInvoiceNo(department, invoiceType, month, year,null);
                    if(invoiceNo != null && !invoiceNo.equals("")){
                         invoice.setInvNo(invoiceNo);
                    }else{
                         invoice.setInvNo("");
                    }
                }
            }
            
            List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
            listInvoiceDetail = setInvoiceDetailList(request, invoice);
            if(listInvoiceDetail != null){
                invoice.setInvoiceDetails(listInvoiceDetail);
            }
            result = invoiceService.saveInvoice(invoice);
            if(result.equals("update success")){
                   result = "cancelvoid";
                   request.setAttribute("listInvoiceDetail", listInvoiceDetail);
                   request.setAttribute("invoice", invoice);
                   request.setAttribute("result", result);
                   
            }
        }else if("edit".equals(action)){// Edit Invoice From Search invoice page
            Invoice invoice = new Invoice();
             List<InvoiceDetail> listInvoiceDetail = new LinkedList<InvoiceDetail>();
            String typeInvoice = request.getParameter("typeInvoice"); // type Invoice
            String departmentInvoice = request.getParameter("departmentInvoice"); // department invoice
            String invoiceIdSearch = request.getParameter("idInvoice"); // invoice id from search invoice page
            invoice = invoiceService.searchInvoiceNo(invoiceIdSearch, departmentInvoice, typeInvoice);
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
            
        }
        request.setAttribute("page", callPageFrom);
        return new ModelAndView(LINKNAME+callPageFrom);
    }
    
    public String generateInvoiceNo(String department,String invoiceType,String month ,String year,String lastInvoiceType){
        String invoiceNo = "";
        String invoiceLast = "";
        int count = 0;
        if(lastInvoiceType != null){
            count = lastInvoiceType.length();
            count = count-4;
            invoiceLast = lastInvoiceType.substring(count);    
            count = Integer.parseInt(invoiceLast);
        }
        
        String numberAsString = String.format ("%04d", (count+1));
        if(department.equals("W")){
            if(invoiceType.equals("T")){
                invoiceNo = "W"+month+""+year+""+numberAsString;
            }else if(invoiceType.equals("V")){
                invoiceNo = "WV"+month+""+year+""+numberAsString;
            }else if(invoiceType.equals("N")){
                invoiceNo = "WN"+month+""+year+""+numberAsString;
            }
        }else if (department.equals("O")){
            invoiceNo = "O"+month+""+year+""+numberAsString;
        }

        return invoiceNo;
    }
    
    public List<InvoiceDetail> setInvoiceDetailList(HttpServletRequest request,Invoice invoice){
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
             String costLocal = request.getParameter("InputCostLocalTemp"+i);
             String costCurren = request.getParameter("SelectCurrencyCost"+i);
             String amount = request.getParameter("InputAmount"+i);
             String amountLocal = request.getParameter("InputAmountLocalTemp"+i);
             String amountCurren = request.getParameter("SelectCurrencyAmount"+i);
             String vat = request.getParameter("InputVatTemp"+i);
             String isVat = request.getParameter("checkUse"+i);
             String gross = request.getParameter("InputGross"+i);
             String displayDescription = request.getParameter("DescriptionInvoiceDetail"+i);
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
             
             if(product != null && !product.equals("")){
                 type.setId(product);
                 invoiceDetail.setMbillType(type);
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
              }else{
                  invoiceDetail.setIsVat(1);
              }
              if(displayDescription != null){
                  displayDescription = displayDescription.trim();
                  invoiceDetail.setDisplayDescription(displayDescription);
              }
             
             if(description != null && !description.equals("")){
                 invoiceDetail.setDescription(description);
                 invoiceDetail.setInvoice(invoice);
                 listInvoiceDetail.add(invoiceDetail);
             }else{
                 listInvoiceDetail.remove(invoiceDetail);
             }
             if(invoiceDetailId != null && !"".equals(invoiceDetailId)){
                 bill.setId(invoiceDetailId);
                 invoiceDetail.setBillableDesc(bill);
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
    
}
